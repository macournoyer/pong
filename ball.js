var Ball = function() {
  Entity.call(this)

  this.width = 20
  this.height = 20

  this.x = game.width / 2 - this.width
  this.y = game.height / 2 - this.height
}

Ball.prototype = new Entity()
Ball.prototype.constructor = Ball
