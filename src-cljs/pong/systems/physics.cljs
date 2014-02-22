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
      (letc e [ rebounds :rebounds ]
        (letc e [ vel :moveable ]
          (let [reb-factor (if rebounds -1 0)]
            (cond 
              (and (= (blocked? e b) :top) (< (? vel :velocity-y) 0)) (! vel :velocity-y (* reb-factor (? vel :velocity-y)))
              (and (= (blocked? e b) :bottom) (> (? vel :velocity-y) 0)) (! vel :velocity-y (* reb-factor (? vel :velocity-y))))))))))

