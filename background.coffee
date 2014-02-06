class Background extends Entity

  draw: (context) ->
    context.fillStyle = '#000'
    context.fillRect(0, 0, game.width, game.height)

    # Print scores
    context.fillStyle = '#fff'
    context.font = "40px monospace"
    context.fillText(game.player.score, game.width * 3 / 8, 50)
    context.fillText(game.bot.score,    game.width * 5 / 8, 50)
