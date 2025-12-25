<form method="POST" action="?/createTool" enctype="multipart/form-data">
    <label for="image">Image</label>
    <input id="image" name="image" type="file">
    <label for="brand">Brand</label>
    <input id="brand" name="brand" type="text" required />
    <label for="category">Category</label>
    <input id="category" name="category" type="text" required />
    <label for="modelNumber">Model Number</label>
    <input id="modelNumber" name="modelNumber" type="text" required />
    <label for="serialNumber">Serial Number</label>
    <input id="serialNumber" name="serialNumber" type="text" required />
    <label for="purchasePrice">Purchase Price</label>
    <input id="purchasePrice" name="purchasePrice" type="number" required />
    <label for="manual">Manual</label>
    <input id="manual" type="file" />
    <button>Submit</button>
</form>

<style>
    form {
        display: flex;
        flex-direction: column;
    }
</style>