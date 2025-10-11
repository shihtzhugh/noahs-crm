<script lang="ts">
	import favicon from '$lib/assets/favicon.svg';
	import '../app.css';

	let { children } = $props();
</script>

<svelte:head>
	<link rel="icon" href={favicon} />
</svelte:head>

<div class="h-screen flex flex-col">
	<header class="bg-gray-800 text-white p-4">
		<h1 class="text-xl font-bold">Noah's CRM</h1>
	</header>
	<div class="flex flex-1 overflow-hidden">
		<nav class="w-64 bg-gray-100 border-r border-gray-200 overflow-y-auto">
			<div class="p-4 space-y-2">
				<a href="/" class="block px-4 py-2 rounded hover:bg-gray-200">📊 Dashboard</a>
				<a href="/jobs" class="block px-4 py-2 rounded hover:bg-gray-200">🔨 Jobs</a>
				<a href="/clients" class="block px-4 py-2 rounded hover:bg-gray-200">👥 Clients</a>
				<a href="/team" class="block px-4 py-2 rounded hover:bg-gray-200">👷 Team</a>
				<a href="/assets" class="block px-4 py-2 rounded hover:bg-gray-200">🚗 Assets</a>
				<a href="/finances" class="block px-4 py-2 rounded hover:bg-gray-200">💰 Finances</a>
				<a href="/documents" class="block px-4 py-2 rounded hover:bg-gray-200">📄 Documents</a>
			</div>
		</nav>
		<main class="flex-1 overflow-y-auto p-6 bg-white">
			{@render children?.()}
		</main>
	</div>
</div>
