import type { PageServerLoad, Actions } from './$types'

interface Tool {
    id: number,
    imageUri: string,
    purchasePrice: number,
    manual: string,
    serialNumber: string,
    modelNumber: string,
    category: string,
    brand: string
}

export const actions = {
    createTool: async ({ request }) => {
        const data = await request.formData();

        const tool = {
            purchasePrice: data.get("purchasePrice"),
            manual: data.get("manual"),
            serialNumber: data.get("serialNumber"),
            modelNumber: data.get("modelNumber"),
            category: data.get("category"),
            brand: data.get("brand")
        };

        const toolResponse = await fetch("http://localhost:8080/api/v1/tools", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(tool)
        });

        if (!toolResponse.ok) {
            throw new Error("Failed to save tool data.")
        }

        if (!toolResponse.body) {
            throw new Error("Tool data returned null.")
        }

        const id = await toolResponse.json().then(
            json => {
                return json.id
            }
        )

        const image = data.get("image")
        if (!image) {
            throw new Error("No image uploaded.")
        }

        const myForm = new FormData()
        myForm.append("file", image);

        const imageResponse = await fetch(`http://localhost:8080/api/v1/tools/${id}/image`, {
            method: "POST",
            body: myForm
        });

        return imageResponse.status
    },
    deleteTool: async ({ request }) => {
        const data = await request.formData();
        const id = data.get("id");

        if (typeof id !== "string") {
            throw new TypeError("Invalid ID");
        }

        const response = await fetch(`http://localhost:8080/api/v1/tools/${id}`, {
            method: "DELETE",
        });

        return response.status
    }
} satisfies Actions

export const load: PageServerLoad = async () => {
    const response = await fetch("http://localhost:8080/api/v1/tools");
    return {
        tools: await response.json() as Tool[]
    };
}