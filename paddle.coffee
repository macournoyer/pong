class Paddle extends Entity

  constructor: ->
    super
    @width = 20
    @height = 100

    @y = game.height / 2 - @height / 2
    @score = 0


  update: ->
    super
    # Keep the paddle within the screen
    @y = Math.min(Math.max(@y, 0), game.height - @height)
