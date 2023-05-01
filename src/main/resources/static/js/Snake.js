// Define canvas and context
		var canvas = document.getElementById("canvas");
		var ctx = canvas.getContext("2d");

		// Define initial snake position
		var snakeX = 10;
		var snakeY = 10;

		// Define snake movement
		var dx = 10;
		var dy = 0;

		// Define snake size and food position
		var snakeSize = 10;
		var foodX = 100;
		var foodY = 100;

		// Define game loop
		function gameLoop() {
			// Move the snake
			snakeX += dx;
			snakeY += dy;

			// Clear the canvas
			ctx.clearRect(0, 0, canvas.width, canvas.height);

			// Draw the food
			ctx.fillStyle = "red";
			ctx.fillRect(foodX, foodY, snakeSize, snakeSize);

			// Draw the snake
			ctx.fillStyle = "green";
			ctx.fillRect(snakeX, snakeY, snakeSize, snakeSize);

			// Check for collision with food
			if (snakeX == foodX && snakeY == foodY) {
				foodX = Math.floor(Math.random() * (canvas.width / snakeSize)) * snakeSize;
				foodY = Math.floor(Math.random() * (canvas.height / snakeSize)) * snakeSize;
			}

			// Check for collision with walls
			if (snakeX < 0 || snakeX >= canvas.width || snakeY < 0 || snakeY >= canvas.height) {
				clearInterval(interval);
				alert("Game Over");
			}
		}

		// Start the game loop
		var interval = setInterval(gameLoop, 100);

		// Define key event listener
		document.addEventListener("keydown", function(event) {
			if (event.keyCode == 37) { // Left arrow
				dx = -snakeSize;
				dy = 0;
			} else if (event.keyCode == 38) { // Up arrow
				dx = 0;
				dy = -snakeSize;
			} else if (event.keyCode == 39) { // Right arrow
				dx = snakeSize;
				dy = 0;
			} else if (event.keyCode == 40) { // Down arrow
				dx = 0;
				dy = snakeSize;
			}
		});