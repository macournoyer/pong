(ns pong.systems.physics
  (:require [pong.lib.collision :refer [left? right? top? bottom?]]
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

(defn- vel-y!
  [c v]
  (! c :velocity-y v))

(defn- vel-x!
  [c v]
  (! c :velocity-x v))

(defn block-movement
  [ents]
  (dofs [e ents]
        (dofs [b (all-e :bounds)]
              (letc e [rebounds :rebounds
                       vel :moveable]
                    (let [reb-factor (if rebounds -1 0)
                          vy (? vel :velocity-y)
                          vx (? vel :velocity-x)]
                      (cond 
                        (and (top? e b) (< vy 0)) (vel-y! vel (* reb-factor vy))
                        (and (bottom? e b) (> vy 0)) (vel-y! vel (* reb-factor vy))
                        (and (left? e b) (< vx 0)) (vel-x! vel (* reb-factor vx))
                        (and (right? e b) (> vx 0)) (vel-x! vel (* reb-factor vx))
                        ))))))


