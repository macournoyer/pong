# Initialize and start the game

game = new Game($('canvas')[0])

# Load the game entities
game.entities = [
  new Background()
  game.ball = new Ball()
  game.player = new Player()
  game.bot = new Bot()
]

game.start()
$('canvas')[0].focus()
