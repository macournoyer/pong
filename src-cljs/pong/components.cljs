(ns pong.components
  (:require-macros [pong.lib.macros :refer [component]]))

(component position [x y]
  :x x
  :y y)

(component moveable []
  :velocity-x 0
  :velocity-y 0)

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


