class Ball extends Entity
  constructor: ->
    super
    @width = 20
    @height = 20
    @reset()

  update: ->
    super
    # Detects if and which paddle we hit
    if @intersect(game.player)
      hitter = game.player
    else if @intersect(game.bot)
      hitter = game.bot

    # Hits a paddle.
    if hitter
      @xVelocity *= -1.1 # Rebound and increase speed
      @yVelocity *= 1.1

      # Transfer some of the paddle vertical velocity to the ball
      @yVelocity += hitter.yVelocity / 4

    # Rebound if it hits top or bottom
    if @y < 0 || @y + @height > game.height
      @yVelocity *= -1 # rebound, switch direction

    # Off screen on left. Bot wins.
    if @x < -@width
      game.bot.score += 1
      @reset()

    # Off screen on right. Player wins.
    if @x > game.width
      game.player.score += 1
      @reset()

  reset: ->
    @x = game.width / 2 - @width / 2
    @y = game.height / 2 - @height / 2

    # A simple way to start in a random direction
    # var max = 5, min = -5
    # @yVelocity = Math.floor(Math.random() * (max - min + 1) + min)
    # @xVelocity = 5

    # A better way to launch the ball at a random angle
    minAngle = -30
    maxAngle = 30
    angle = Math.floor(Math.random() * (maxAngle - minAngle + 1)) + minAngle

    # Convert angle to x,y coordinates
    radian = Math.PI / 180
    speed = 7
    @xVelocity = speed * Math.cos(angle * radian)
    @yVelocity = speed * Math.sin(angle * radian)

    # Alternate between right and left
    @xVelocity *= -1 if Math.random() > 0.5
