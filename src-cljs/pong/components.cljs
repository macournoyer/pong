(ns pong.components
  (:require-macros [pong.lib.macros :refer [component]]))

(component position [x y]
  :x x
  :y y
  :initial-x x
  :initial-y y)

(component moveable [velocity-x velocity-y]
  :velocity-x (or velocity-x 0)
  :velocity-y (or velocity-y 0))

(component renderable [func]
  :fn func)

(component actions 
  []
  :move-down false
  :move-up false)

(component rectangular [w h]
  :width w
  :height h)

(component colored [c]
  :color c)

(component keyboard [up-key down-key]
  :up-key up-key
  :down-key down-key)

(component bounds [])
(component solid [])
(component rebounds [])
(component paddle [])

(component score [score]
  :score score)
(component scores [paddle-left left paddle-right right]
  :paddle-left paddle-left
  :left left
  :paddle-right paddle-right
  :right right)
