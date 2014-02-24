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

(defn- rebound-y!
  [c v pos]
  (! c :velocity-y v))

(defn- rebound-x!
  [c v pos]
  (! c :velocity-x v))

(defn block-movement
  [ents]
  (dofs [e ents]
        (dofs [b (all-e :bounds)]
              (letc e [rebounds :rebounds
                       vel :moveable
                       pos :position]
                    (let [reb-factor (if rebounds -1 0)
                          vy (? vel :velocity-y)
                          vx (? vel :velocity-x)]
                      (cond 
                        (and (top? e b) (< vy 0)) (rebound-y! vel (* reb-factor vy) pos)
                        (and (bottom? e b) (> vy 0)) (rebound-y! vel (* reb-factor vy) pos)
                        ))))))

(defn rebound-paddle
  [ents]
  (dofs [e ents]
        (dofs [b (all-e :paddle)]
        (letc e [rebounds :rebounds
                 vel :moveable
                 pos :position]
              (let [reb-factor (if rebounds -1 0)
                    vy (? vel :velocity-y)
                    vx (? vel :velocity-x)]
                (cond 
                  (and (left? e b) (< vx 0)) (rebound-x! vel (* reb-factor vx) pos)
                  (and (right? e b) (> vx 0)) (rebound-x! vel (* reb-factor vx) pos)
                  ))))))


