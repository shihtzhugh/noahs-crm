<script lang="ts">
    interface CatFact {
        fact: string;
    }

    let catFact = $state("");

    async function fetchCatFact() {
        const response = await fetch("/api/cats")
        const data = await response.json() as CatFact;
        catFact = data.fact;
    }
</script>

<h3>Cats</h3>
<button onclick={async () => (await fetchCatFact())}>
    Cat Fact
</button>
<p>{catFact}</p>
