import { sveltekit } from '@sveltejs/kit/vite';
import { defineConfig } from 'vite';

export default defineConfig({
    server: {
        proxy: {
            "/uploads": "http://localhost:8080"
        }
    },
    plugins: [sveltekit()]
});
