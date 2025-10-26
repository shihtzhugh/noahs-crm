<script lang="ts">
	interface ToolFormProps {
		onSuccess?: () => void;
		onCancel?: () => void;
	}

	let { onSuccess, onCancel }: ToolFormProps = $props();

	let brand = $state('');
	let category = $state('');
	let modelNumber = $state('');
	let serialNumber = $state('');
	let purchasePrice = $state('');
	let manual = $state('');

	let submitting = $state(false);
	let error = $state<string | null>(null);

	async function handleSubmit(event: SubmitEvent) {
		event.preventDefault();
		submitting = true;
		error = null;

		try {
			const toolData = {
				brand,
				category,
				modelNumber,
				serialNumber,
				purchasePrice: parseFloat(purchasePrice) || 0,
				manual
			};

			const response = await fetch('http://localhost:8080/api/v1/tools', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify(toolData)
			});

			if (!response.ok) {
				throw new Error(`Failed to create tool: ${response.statusText}`);
			}

			// Reset form
			brand = '';
			category = '';
			modelNumber = '';
			serialNumber = '';
			purchasePrice = '';
			manual = '';

			// Call success callback
			onSuccess?.();
		} catch (err) {
			error = err instanceof Error ? err.message : 'Unknown error occurred';
			console.error('Error creating tool:', err);
		} finally {
			submitting = false;
		}
	}
</script>

<form onsubmit={handleSubmit} class="space-y-4">
	{#if error}
		<div class="bg-red-50 border border-red-200 rounded-lg p-3">
			<p class="text-red-800 text-sm">
				<strong>Error:</strong> {error}
			</p>
		</div>
	{/if}

	<div class="grid grid-cols-1 md:grid-cols-2 gap-4">
		<div>
			<label for="tool-brand" class="block text-sm font-medium text-gray-700 mb-1">
				Brand <span class="text-red-500">*</span>
			</label>
			<input
				id="tool-brand"
				bind:value={brand}
				type="text"
				required
				class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
				placeholder="e.g., DeWalt"
			/>
		</div>

		<div>
			<label for="tool-category" class="block text-sm font-medium text-gray-700 mb-1">
				Category <span class="text-red-500">*</span>
			</label>
			<input
				id="tool-category"
				bind:value={category}
				type="text"
				required
				class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
				placeholder="e.g., Power Drill"
			/>
		</div>
	</div>

	<div class="grid grid-cols-1 md:grid-cols-2 gap-4">
		<div>
			<label for="tool-model" class="block text-sm font-medium text-gray-700 mb-1">
				Model Number <span class="text-red-500">*</span>
			</label>
			<input
				id="tool-model"
				bind:value={modelNumber}
				type="text"
				required
				class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
				placeholder="e.g., DCD771C2"
			/>
		</div>

		<div>
			<label for="tool-serial" class="block text-sm font-medium text-gray-700 mb-1">
				Serial Number <span class="text-red-500">*</span>
			</label>
			<input
				id="tool-serial"
				bind:value={serialNumber}
				type="text"
				required
				class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
				placeholder="e.g., SN123456789"
			/>
		</div>
	</div>

	<div>
		<label for="tool-price" class="block text-sm font-medium text-gray-700 mb-1">
			Purchase Price <span class="text-red-500">*</span>
		</label>
		<div class="relative">
			<span class="absolute left-3 top-2 text-gray-500">$</span>
			<input
				id="tool-price"
				bind:value={purchasePrice}
				type="number"
				step="0.01"
				min="0"
				required
				class="w-full pl-7 pr-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
				placeholder="0.00"
			/>
		</div>
	</div>

	<div>
		<label for="tool-manual" class="block text-sm font-medium text-gray-700 mb-1">
			Manual URL (Optional)
		</label>
		<input
			id="tool-manual"
			bind:value={manual}
			type="url"
			class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
			placeholder="https://example.com/manual.pdf"
		/>
	</div>

	<div class="flex justify-end gap-3 pt-4">
		{#if onCancel}
			<button
				type="button"
				onclick={onCancel}
				disabled={submitting}
				class="px-4 py-2 border border-gray-300 text-gray-700 font-medium rounded-md hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 disabled:opacity-50 disabled:cursor-not-allowed"
			>
				Cancel
			</button>
		{/if}
		<button
			type="submit"
			disabled={submitting}
			class="px-4 py-2 bg-blue-600 text-white font-medium rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 disabled:opacity-50 disabled:cursor-not-allowed"
		>
			{submitting ? 'Adding...' : 'Add Tool'}
		</button>
	</div>
</form>
