var Paddle = function() {
  Entity.call(this)

  this.height = 100
  this.width = 20

  this.x = 20
  this.y = game.height / 2 - this.height / 2
}

Paddle.prototype = new Entity()
Paddle.prototype.constructor = Paddle

Paddle.prototype.update = function(){
  Entity.prototype.update.apply(this, arguments)
  var speed = 15

  if (game.keyPressed.up) {
    this.yVelocity = -speed
  } else if (game.keyPressed.down) {
    this.yVelocity = speed
  } else {
    this.yVelocity = 0
  }

  if(this.y > game.height || this.y < 0) {
    this.yVelocity = 0
  }

  this.y = Math.min(Math.max(this.y, 0), game.height - this.height)
}
