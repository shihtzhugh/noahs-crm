import { type RequestHandler } from "@sveltejs/kit";

export const GET: RequestHandler = async () => {
    return await fetch("http://localhost:8080/api/cats/facts");
}