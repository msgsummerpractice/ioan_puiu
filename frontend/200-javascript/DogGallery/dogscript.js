async function showDog() {
  try {
    const response = await fetch('https://dog.ceo/api/breeds/image/random'); // over network
    const data = await response.json(); // Parse response as JSON
    return data; // URL of the dog image
  } catch (error) {
    console.error('Failed to fetch dog image:', error);
  }
}




const button = document.getElementById("showDogBtwn");

const dogLoader = document.getElementById("dogLoader");

dogLoader.textContent = "Arata un caine<3"; 

function startTabLoading() {
    dogLoader.textContent = "Loading..."; 

  dogLoader.style.display = "block"; // Show the loader
  button.disabled = true; // Disable the button
}

function stopTabLoading() {
    dogLoader.textContent = "Arata un caine<3"; 

  dogLoader.style.display = "block";
  button.disabled = false; // Enable the button
}

button.addEventListener("click", async () => {

    startTabLoading(); // Start loading when button is clicked
  const dogData = await showDog();
  const dogImage = document.getElementById("dogImage");
  setTimeout(() => {
    dogImage.src = dogData.message;
    stopTabLoading(); // Stop loading after the image is set
  }, 3000); // Wait for 3 seconds before setting the image
});
        