<script lang="ts">
    import type { PageData } from "./$types"
    import AssetForm from "$lib/components/assets/AssetForm.svelte";

    let { data }: { data: PageData } = $props()
</script>

<h3>Assets Page</h3>
<table>
    <thead>
        <tr>
            <th scope="col">Image</th>
            <th scope="col">Brand</th>
            <th scope="col">Category</th>
            <th scope="col">Model Number</th>
            <th scope="col">Serial Number</th>
            <th scope="col">Purchase Price</th>
            <th scope="col">Manual</th>
        </tr>
    </thead>
    <tbody>
        {#each data.tools as tool}
            <tr>
                <td><img src="{tool.imageUri}" alt="asset" width="50" height="50" /></td>
                <td>{tool.brand}</td>
                <td>{tool.category}</td>
                <td>{tool.modelNumber}</td>
                <td>{tool.serialNumber}</td>
                <td>{tool.purchasePrice}</td>
                <td>{tool.manual}</td>
                <td>
                    <form method="POST" action="?/deleteTool">
                        <input name="id" type="hidden" value="{tool.id}" />
                        <button>Delete</button>
                    </form>
                </td>
            </tr>
        {/each}
    </tbody>
</table>
<AssetForm />