(ns pong.game
  (:require [pong.systems.renderer :as r]
            [pong.systems.physics :as phys]
            [pong.systems.moveable :as move]
            [pong.lib.core :refer [all-e load]])
  (:require-macros [pong.lib.macros :refer [dofs letc ! ?]])
  (:use [pong.components :only [renderable colored position
                                keyboard actions bounds
                                rectangular moveable solid]]))


; settings
(def fps 60)
(def pad-width 20)
(def pad-height 60)

(def context nil)
(def width nil)
(def height nil)
(def entities nil)


(defn configure-entities 
  "Setup the game-entities."
  [width height]
  [:background [(renderable r/draw-rectangular)
      (colored "#000000")
      (position 0 0)
      (rectangular width height)]
   :board [(rectangular width height)
      (position 0 0)
      (bounds)]
   :pad-1 [(renderable r/draw-rectangular)
      (colored "#FFFFFF")
      (position 5 30)
      (rectangular pad-width pad-height)
      (moveable)
      (keyboard :w :s)
      (actions)
      (solid)]
   :pad-2 [ (renderable r/draw-rectangular)
      (colored "#FFFFFF")
      (position (- width 5 pad-width) 60)
      (rectangular pad-width pad-height)
      (moveable)
      (keyboard :up :down)
      (actions)
      (solid)]])

(defn start [canvas]   
    (set! context (.getContext canvas "2d"))
    (set! width (.-width canvas))
    (set! height (.-height canvas))   
    (set! entities (configure-entities width height))

    (load entities)
    (js/setInterval (fn []
      (move/keyboard (all-e :keyboard))
      (move/move (all-e :keyboard))     

      (phys/block-movement (all-e :solid))      
      (phys/step (all-e :moveable))

      (r/render context (all-e :renderable))      
      ) (/ 1000 fps)))

