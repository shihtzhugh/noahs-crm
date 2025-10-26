<script lang="ts">
	interface VehicleFormProps {
		onSuccess?: () => void;
		onCancel?: () => void;
	}

	let { onSuccess, onCancel }: VehicleFormProps = $props();

	let make = $state('');
	let model = $state('');
	let vin = $state('');
	let licensePlate = $state('');
	let engine = $state('');
	let purchasePrice = $state('');
	let manual = $state('');

	let submitting = $state(false);
	let error = $state<string | null>(null);

	async function handleSubmit(event: SubmitEvent) {
		event.preventDefault();
		submitting = true;
		error = null;

		try {
			const vehicleData = {
				make,
				model,
				vin,
				licensePlate,
				engine,
				purchasePrice: parseFloat(purchasePrice) || 0,
				manual
			};

			const response = await fetch('http://localhost:8080/api/v1/vehicles', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify(vehicleData)
			});

			if (!response.ok) {
				throw new Error(`Failed to create vehicle: ${response.statusText}`);
			}

			// Reset form
			make = '';
			model = '';
			vin = '';
			licensePlate = '';
			engine = '';
			purchasePrice = '';
			manual = '';

			// Call success callback
			onSuccess?.();
		} catch (err) {
			error = err instanceof Error ? err.message : 'Unknown error occurred';
			console.error('Error creating vehicle:', err);
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
			<label for="vehicle-make" class="block text-sm font-medium text-gray-700 mb-1">
				Make <span class="text-red-500">*</span>
			</label>
			<input
				id="vehicle-make"
				bind:value={make}
				type="text"
				required
				class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
				placeholder="e.g., Ford"
			/>
		</div>

		<div>
			<label for="vehicle-model" class="block text-sm font-medium text-gray-700 mb-1">
				Model <span class="text-red-500">*</span>
			</label>
			<input
				id="vehicle-model"
				bind:value={model}
				type="text"
				required
				class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
				placeholder="e.g., F-150"
			/>
		</div>
	</div>

	<div class="grid grid-cols-1 md:grid-cols-2 gap-4">
		<div>
			<label for="vehicle-vin" class="block text-sm font-medium text-gray-700 mb-1">
				VIN <span class="text-red-500">*</span>
			</label>
			<input
				id="vehicle-vin"
				bind:value={vin}
				type="text"
				required
				maxlength="17"
				class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
				placeholder="e.g., 1FTFW1E84MFA12345"
			/>
		</div>

		<div>
			<label for="vehicle-license" class="block text-sm font-medium text-gray-700 mb-1">
				License Plate <span class="text-red-500">*</span>
			</label>
			<input
				id="vehicle-license"
				bind:value={licensePlate}
				type="text"
				required
				class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
				placeholder="e.g., ABC1234"
			/>
		</div>
	</div>

	<div>
		<label for="vehicle-engine" class="block text-sm font-medium text-gray-700 mb-1">
			Engine <span class="text-red-500">*</span>
		</label>
		<input
			id="vehicle-engine"
			bind:value={engine}
			type="text"
			required
			class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
			placeholder="e.g., 3.5L V6 EcoBoost"
		/>
	</div>

	<div>
		<label for="vehicle-price" class="block text-sm font-medium text-gray-700 mb-1">
			Purchase Price <span class="text-red-500">*</span>
		</label>
		<div class="relative">
			<span class="absolute left-3 top-2 text-gray-500">$</span>
			<input
				id="vehicle-price"
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
		<label for="vehicle-manual" class="block text-sm font-medium text-gray-700 mb-1">
			Manual URL (Optional)
		</label>
		<input
			id="vehicle-manual"
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
			{submitting ? 'Adding...' : 'Add Vehicle'}
		</button>
	</div>
</form>