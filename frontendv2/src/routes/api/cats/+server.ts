import { json, type RequestHandler } from "@sveltejs/kit";

export const GET: RequestHandler = async () => {
    const response = await fetch("http://localhost:8080/api/cats/facts");
    const data = await response.json();
    return json(data);
}