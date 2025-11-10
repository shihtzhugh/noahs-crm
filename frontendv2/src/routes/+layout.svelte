<script lang="ts">
    import favicon from '$lib/assets/favicon.svg';
    import NavigationSidebar from "$lib/components/NavigationSidebar.svelte";

    let {children} = $props();
</script>

<svelte:head>
    <link rel="icon" href={favicon}/>
</svelte:head>

<header>
    <h1>Noah's CRM</h1>
</header>

<NavigationSidebar/>

{@render children()}
