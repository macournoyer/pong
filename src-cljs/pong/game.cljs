(ns pong.game
  (:require [pong.systems.renderer :as r]
            [pong.systems.physics :as phys]
            [pong.systems.moveable :as move]
            [pong.lib.core :refer [all-e load]])
  (:require-macros [pong.lib.macros :refer [dofs letc ! ?]])
  (:use [pong.components :only [renderable colored position
                                keyboard actions bounds rebounds
                                rectangular moveable solid
                                paddle score scores ]]))


; settings
(def fps 60)
(def pad-width 20)
(def pad-height 60)
(def ball-diameter 20)

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
   :top-boundary [(rectangular width 0)
      (position 0 0)      
      (bounds)]
   :bottom-boundary [(rectangular width 0)
      (position 0 height)
      (bounds)]
   :pad-1 [(renderable r/draw-rectangular)
      (colored "#FFFFFF")
      (position 5 30)
      (rectangular pad-width pad-height)
      (moveable 0 0)
      (keyboard :w :s)
      (actions)
      (solid)
      (paddle)
      (score 0)]
   :pad-2 [ (renderable r/draw-rectangular)
      (colored "#FFFFFF")
      (position (- width 5 pad-width) 60)
      (rectangular pad-width pad-height)
      (moveable 0 0)
      (keyboard :up :down)
      (actions)
      (solid)
      (paddle)
      (score 0)]
    :ball [(renderable r/draw-rectangular)
      (colored "#FFFFFF")
      (position (- (/ width 2) (/ ball-diameter 2))
                (- (/ height 2) (/ ball-diameter 2)))
      (rectangular ball-diameter ball-diameter)
      (moveable 5 10)      
      (solid)
      (rebounds)
      (scores :pad-1 0 :pad-2 width)]
    ])

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
      (phys/rebound-paddle (all-e :rebounds))     
      (phys/check-scores (all-e :scores))     
       
      (phys/step (all-e :moveable))

      (r/render context (all-e :renderable))      
      ) (/ 1000 fps)))

