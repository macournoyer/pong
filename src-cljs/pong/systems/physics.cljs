(ns pong.systems.physics
  (:require [pong.lib.collision :refer [blocked?]]
            [pong.lib.core :refer [all-e]])
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

(defn block-movement
  [ents]
  (dofs [e ents]
    (dofs [b (all-e :bounds)]
      (letc e [ vel :moveable]
        (cond 
          (and (= (blocked? e b) :top) (< (? vel :velocity-y) 0)) (! vel :velocity-y 0)
          (and (= (blocked? e b) :bottom) (> (? vel :velocity-y) 0)) (! vel :velocity-y 0))))))

