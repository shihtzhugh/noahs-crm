<script lang="ts">
	import ToolsTable from '$lib/components/assets/ToolsTable.svelte';
	import VehiclesTable from '$lib/components/assets/VehiclesTable.svelte';

	type TabType = 'tools' | 'vehicles';

	let activeTab: TabType = 'tools';

	function switchTab(tab: TabType) {
		activeTab = tab;
	}
</script>

<h2 class="text-2xl font-semibold mb-4">Assets</h2>
<p class="text-gray-600 mb-6">Equipment and inventory tracking</p>

<!-- Tab Navigation -->
<div class="border-b border-gray-200 mb-6">
	<nav class="-mb-px flex space-x-8">
		<button
			on:click={() => switchTab('tools')}
			class="
				{activeTab === 'tools'
					? 'border-blue-500 text-blue-600'
					: 'border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300'}
				whitespace-nowrap py-4 px-1 border-b-2 font-medium text-sm transition-colors
			"
		>
			Tools
		</button>
		<button
			on:click={() => switchTab('vehicles')}
			class="
				{activeTab === 'vehicles'
					? 'border-blue-500 text-blue-600'
					: 'border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300'}
				whitespace-nowrap py-4 px-1 border-b-2 font-medium text-sm transition-colors
			"
		>
			Vehicles
		</button>
	</nav>
</div>

<!-- Tab Content -->
<div class="mt-6">
	{#if activeTab === 'tools'}
		<ToolsTable />
	{:else if activeTab === 'vehicles'}
		<VehiclesTable />
	{/if}
</div>
