var Paddle = function() {
  Entity.call(this)

  this.height = 100
  this.width = 20

  this.x = 20
  this.y = game.height / 2 - this.height / 2
}

Paddle.prototype = new Entity()
Paddle.prototype.constructor = Paddle
