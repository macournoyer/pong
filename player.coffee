class Player extends Paddle
  constructor: ->
    super
    
    @x = 20

    @speed = 15

  update: ->
    if game.keyPressed.up
      @yVelocity = -@speed
    else if game.keyPressed.down
      @yVelocity = @speed
    else
      @yVelocity = 0

    super
  
