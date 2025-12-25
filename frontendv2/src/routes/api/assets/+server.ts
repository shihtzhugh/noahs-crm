import { type RequestHandler } from "@sveltejs/kit";

export const POST: RequestHandler = async ({ request }) => {
    return await fetch("http://localhost:8080/api/v1/tools", {
        method: "POST"
    })
}