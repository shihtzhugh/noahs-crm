<script lang="ts">
	import { onMount } from 'svelte';
	import ToolForm from './ToolForm.svelte';

	interface Tool {
		purchasePrice: number;
		manual: string;
		serialNumber: string;
		modelNumber: string;
		category: string;
		brand: string;
	}

	let tools: Tool[] = [];
	let loading = true;
	let error: string | null = null;
	let showModal = false;

	async function loadTools() {
		loading = true;
		error = null;

		try {
			const response = await fetch('http://localhost:8080/api/v1/tools');

			if (!response.ok) {
				throw new Error(`Failed to fetch tools: ${response.statusText}`);
			}

			tools = await response.json();
		} catch (err) {
			error = err instanceof Error ? err.message : 'Unknown error occurred';
			console.error('Error loading tools:', err);
		} finally {
			loading = false;
		}
	}

	onMount(() => {
		loadTools();
	});

	function formatCurrency(value: number): string {
		return new Intl.NumberFormat('en-US', {
			style: 'currency',
			currency: 'USD'
		}).format(value);
	}

	function openModal() {
		showModal = true;
	}

	function closeModal() {
		showModal = false;
	}

	function handleToolCreated() {
		closeModal();
		loadTools(); // Refresh the table
	}
</script>

<div class="space-y-4">
	<!-- Add Tool Button -->
	<div class="flex justify-between items-center">
		<h3 class="text-lg font-semibold text-gray-800">Tool Inventory</h3>
		<button
			onclick={openModal}
			class="inline-flex items-center px-4 py-2 bg-blue-600 text-white font-medium rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 transition-colors"
		>
			<svg class="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
				<path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4v16m8-8H4"></path>
			</svg>
			Add Tool
		</button>
	</div>

	<!-- Table Content -->
	{#if loading}
		<div class="flex items-center justify-center py-12">
			<div class="text-gray-500">Loading tools...</div>
		</div>
	{:else if error}
		<div class="bg-red-50 border border-red-200 rounded-lg p-4 my-4">
			<p class="text-red-800 text-sm">
				<strong>Error:</strong> {error}
			</p>
		</div>
	{:else if tools.length === 0}
		<div class="bg-gray-50 border border-gray-200 rounded-lg p-8 text-center my-4">
			<p class="text-gray-500">No tools found. Add your first tool to get started.</p>
		</div>
	{:else}
		<div class="overflow-x-auto">
			<table class="min-w-full divide-y divide-gray-200 border border-gray-200 rounded-lg">
				<thead class="bg-gray-50">
					<tr>
						<th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
							Brand
						</th>
						<th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
							Model Number
						</th>
						<th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
							Serial Number
						</th>
						<th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
							Category
						</th>
						<th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
							Purchase Price
						</th>
						<th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
							Manual
						</th>
					</tr>
				</thead>
				<tbody class="bg-white divide-y divide-gray-200">
					{#each tools as tool}
						<tr class="hover:bg-gray-50">
							<td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
								{tool.brand}
							</td>
							<td class="px-6 py-4 whitespace-nowrap text-sm text-gray-600">
								{tool.modelNumber}
							</td>
							<td class="px-6 py-4 whitespace-nowrap text-sm text-gray-600">
								{tool.serialNumber}
							</td>
							<td class="px-6 py-4 whitespace-nowrap text-sm text-gray-600">
								{tool.category}
							</td>
							<td class="px-6 py-4 whitespace-nowrap text-sm text-gray-600">
								{formatCurrency(tool.purchasePrice)}
							</td>
							<td class="px-6 py-4 text-sm text-gray-600">
								{#if tool.manual}
									<a href={tool.manual} target="_blank" rel="noopener noreferrer"
									   class="text-blue-600 hover:text-blue-800 underline">
										View Manual
									</a>
								{:else}
									<span class="text-gray-400">-</span>
								{/if}
							</td>
						</tr>
					{/each}
				</tbody>
			</table>
		</div>
	{/if}
</div>

<!-- Modal -->
{#if showModal}
	<div class="fixed inset-0 z-50 flex items-center justify-center p-4 bg-black bg-opacity-50" onclick={closeModal}>
		<!-- Modal Content -->
		<div
			class="bg-white rounded-lg shadow-xl max-w-2xl w-full max-h-[90vh] overflow-y-auto"
			onclick={(e) => e.stopPropagation()}
		>
			<div class="p-6">
				<div class="flex justify-between items-start mb-6">
					<h3 class="text-xl font-semibold text-gray-900">
						Add New Tool
					</h3>
					<button
						onclick={closeModal}
						class="text-gray-400 hover:text-gray-600 transition-colors"
					>
						<svg class="h-6 w-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
							<path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
						</svg>
					</button>
				</div>
				<ToolForm onSuccess={handleToolCreated} onCancel={closeModal} />
			</div>
		</div>
	</div>
{/if}