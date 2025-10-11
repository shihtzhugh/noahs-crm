<script lang="ts">
	let brand = $state('');
	let category = $state('');

	function handleSubmit(event: SubmitEvent) {
		event.preventDefault();
		// TODO: Submit to backend API
		console.log('Submitting tool:', { brand, category });
	}
</script>

<form onsubmit={handleSubmit} class="space-y-4 max-w-md">
	<div>
		<label for="tool-brand" class="block text-sm font-medium text-gray-700 mb-1">Brand</label>
		<input
			id="tool-brand"
			bind:value={brand}
			type="text"
			class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
			placeholder="Enter tool brand"
		/>
	</div>
	<div>
		<label for="tool-category" class="block text-sm font-medium text-gray-700 mb-1"
			>Category</label
		>
		<input
			id="tool-category"
			bind:value={category}
			type="text"
			class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
			placeholder="Enter tool category"
		/>
	</div>
	<button
		type="submit"
		class="px-4 py-2 bg-blue-600 text-white font-medium rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
	>
		Add Tool
	</button>
</form>
