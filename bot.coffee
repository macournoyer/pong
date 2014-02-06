class Bot extends Paddle
  constructor: ->
    super

    @x = game.width - @width - 20

    @speed = 5

  update: ->
    # Follow the ball
    if @y < game.ball.y
      @yVelocity = @speed
    else
      @yVelocity = -@speed

    super

