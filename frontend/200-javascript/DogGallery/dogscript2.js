// var originalTitle = document.title;

// var originalFavicon = document.querySelector('link[rel*="icon"]')?.href;

// {/* <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16">
//   <circle cx="8" cy="8" r="7" fill="none" stroke="currentColor" stroke-width="2">
//     <!-- Animate stroke-dasharray to create a "spinning" effect -->
//     <animate attributeName="stroke-dasharray" values="44 0; 0 44" dur="1.5s" repeatCount="indefinite"/>
//     <!-- Rotate the circle -->
//     <animateTransform attributeName="transform" type="rotate" from="0 8 8" to="360 8 8" dur="1.5s" repeatCount="indefinite"/>
//   </circle>
// </svg> */}


// var spinnerFavicon = "data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIxNiIgaGVpZ2h0PSIxNiIgdmlld0JveD0iMCAwIDE2IDE2Ij48Y2lyY2xlIGN4PSI4IiBjeT0iOCIgcj0iNyIgZmlsbD0ibm9uZSIgc3Ryb2tlPSJjdXJyZW50Q29sb3IiIHN0cm9rZS13aWR0aD0iMiI+PCEtLSBBbmltYXRlIHN0cm9rZS1kYXNoYXJyYXkgdG8gY3JlYXRlIGEgc3BpbmluZyBlZmZlY3QgLTEtPjxyYWN0IHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgYXR0cmlidXRlTmFtZT0ic3Ryb2tlLWRhc2hhcnJheSIgdmFsdWVzPSI0NCAwOyAwIDQ0IiBkdXI9IjEuNXMifS8+PCEtLSBSb3RhdGUgdGhlIGNpcmNsZSAvLT48cmFjdCB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIGF0dHJpYnV0ZU5hbWU9InRyYW5zZm9ybSIgdHlwZT0icm90YXRlIiBmcm9tPSIwIDggOCIgdG89IjM2MCA4IDgiIGR1cj0iMS41cyIvPjwvY2lyY2xlPjwvc3ZnPg==";

// // Combined start function
// function startTabLoading() {
//   // Update title
//   document.title = '⏳ ' + (originalTitle || document.title);
//   // Update favicon - remove old one and create new
//   let faviconElement = document.querySelector('link[rel*="icon"]');
//   if (faviconElement) {
//     faviconElement.remove();
//   }
//   const newFavicon = document.createElement('link');
//   newFavicon.rel = 'icon';
//   newFavicon.href = spinnerFavicon + '?t=' + Date.now();
//   document.head.appendChild(newFavicon);
// }
 
// // Combined stop function
// function stopTabLoading() {
//   // Revert title
//   document.title = originalTitle;
//   // Revert favicon with cache-busting timestamp
//   const faviconElement = document.querySelector('link[rel*="icon"]');
//   if (faviconElement && originalFavicon) {
//     faviconElement.href = originalFavicon + '?t=' + Date.now();
//   } else if (faviconElement && !originalFavicon) {
//     document.head.removeChild(faviconElement);
//   }
// }

// function showDog() {
//             const startTime = Date.now();
//             const minLoadTime = 3000; // Minimum 3 seconds to show spinner
            
//             startTabLoading();
//             try {
//                 const dogImage = document.createElement('img');
//                 fetch('https://dog.ceo/api/breeds/image/random')
//                     .then(response => response.json())
//                     .then(data => {
//                         dogImage.src = data.message;
//                         document.body.appendChild(dogImage);
//                         // Calculate remaining time to meet minimum display time
//                         const elapsedTime = Date.now() - startTime;
//                         const remainingTime = Math.max(0, minLoadTime - elapsedTime);
//                         setTimeout(stopTabLoading, remainingTime);
//                     })
//                     .catch(error => {
//                         console.error('Error loading dog image:', error);
//                         const elapsedTime = Date.now() - startTime;
//                         const remainingTime = Math.max(0, minLoadTime - elapsedTime);
//                         setTimeout(stopTabLoading, remainingTime);
//                     });
//             } catch (error) {
//                 console.error('Error loading dog image:', error);
//                 const elapsedTime = Date.now() - startTime;
//                 const remainingTime = Math.max(0, minLoadTime - elapsedTime);
//                 setTimeout(stopTabLoading, remainingTime);
//             }
//         }
// document.getElementById('load-data-btn').addEventListener('click', showDog);

// function showDog2() {
//             alert('Here is a random dog!')
//             try {
//                 const dogImage = document.createElement('img');
//                 fetch('https://dog.ceo/api/breeds/image/random')
//                     .then(response => response.json())
//                     .then(data => {
//                         dogImage.src = data.message;
//                         document.body.appendChild(dogImage);
//                     });
//             } catch (error) {
//                 console.error('Error loading dog image:', error);
//             }
//             finally {
//             // Always runs, regardless of error
//             console.log("Execution finished.");
//             }
//         }

        