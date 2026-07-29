document.addEventListener("DOMContentLoaded", () => {

const button = document.querySelector<HTMLButtonElement>("#showDogBtwn");
const image = document.querySelector<HTMLImageElement>("#dogImage");
const dogLoader = document.querySelector<HTMLDivElement>("#dogLoader");



if (!button || !image || !dogLoader) {
  throw new Error("Required elements not found in the DOM");
}

dogLoader.innerText = "Show me a dog!"; // Set initial text for the loader

type DogApiResponse = {
  message: string
}

const url : string = "https://dog.ceo/api/breeds/image/random";

button.addEventListener("click", async () => {
  button.disabled = true; // Disable the button to prevent multiple clicks
  dogLoader.style.display = "block"; // Show the loader
  dogLoader.innerText = "Loading..."; // Optional: Add loading text
await fetchDogImage(url).finally(() => {
  button.disabled = false; // Re-enable the button after fetching
  dogLoader.innerText = "Show me a dog!"; // Reset the loader text
  });
});


const fetchDogImage = async (url: string): Promise<void> => {
  try {


  const response = await fetch(url);

  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`);
  }

  const data: DogApiResponse = await response.json();
  image.src = data.message;
  } catch (error) {
    console.error("Error fetching dog image:", error);
  }
};


});