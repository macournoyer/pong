(ns pong.systems.renderer
  (:require [pong.lib.core :refer [all-e as]])
  (:require-macros [pong.lib.macros :refer [dofs letc ! ?]]))

(defn render
  [context ents]
  (dofs [e ents]    
    (letc e [rend :renderable
             pos :position
             color :colored
             rect :rectangular]
    ((? rend :fn) context e))))

(defn draw-rectangular
  [ctx e]
  (letc e [rend :renderable
             pos :position
             color :colored
             rect :rectangular]
    ;(.log js/console y)
    (set! (.-fillStyle ctx) (? color :color))
    (.fillRect ctx 
      (? pos :x) 
      (? pos :y) 
      (? rect :width) 
      (? rect :height))))

