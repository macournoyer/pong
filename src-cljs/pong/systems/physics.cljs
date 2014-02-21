(ns pong.systems.physics
  (:require-macros [pong.lib.macros :refer [letc dofs ! ?]]))

(defn step
  [ents]
  (dofs [e ents]
    (letc e [ pos :position
              vel :moveable]
      (let [nx (+ (? pos :x) (? vel :velocity-x))            
            ny (+ (? pos :y) (? vel :velocity-y))]
        (! pos :x nx)
        (! pos :y ny)))))