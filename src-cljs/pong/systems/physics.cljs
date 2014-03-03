(ns pong.systems.physics
  (:require [pong.lib.collision :refer [left? right? top? bottom?]]
            [pong.lib.core :refer [all-e get-e]])
  (:require-macros [pong.lib.macros :refer [letc dofs dofs2 ! ?]]))

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
  (dofs2 [e ents
         b (all-e :bounds)]
        (letc e [rebounds :rebounds
                       vel :moveable
                       pos :position]
                    (let [reb-factor (if rebounds -1 0)
                          vy (? vel :velocity-y)
                          vx (? vel :velocity-x)]
                      (cond 
                        (and (top? e b) (< vy 0)) (rebound-y! vel (* reb-factor vy) pos)
                        (and (bottom? e b) (> vy 0)) (rebound-y! vel (* reb-factor vy) pos)
                        )))))

(defn rebound-paddle
  [ents]
  (dofs2 [e ents
          b (all-e :paddle)]
        (letc e [rebounds :rebounds
                 vel :moveable
                 pos :position]
              (let [reb-factor (if rebounds -1 0)
                    vy (? vel :velocity-y)
                    vx (? vel :velocity-x)]
                (cond 
                  (and (left? e b) (< vx 0)) (rebound-x! vel (* reb-factor vx) pos)
                  (and (right? e b) (> vx 0)) (rebound-x! vel (* reb-factor vx) pos)
                  )))))

(defn score!
  [ball winner]
  ; Increment winner's score
  (letc winner [score :score]
    (! score :score (+ (? score :score) 1)))
  ; Reset ball position
  ; TODO reset velocity?
  (letc ball [pos :position]
    (! pos :x (? pos :initial-x))
    (! pos :y (? pos :initial-y)))
  )

(defn check-scores
  [ents]
  (dofs2 [e ents]
        (letc e [s :scores
                 p :position]
          (let [paddle-left (get-e (? s :paddle-left))
                paddle-right (get-e (? s :paddle-right))]
            (cond 
              (< (? p :x) (? s :left)) (score! e paddle-right)
              (> (? p :x) (? s :right)) (score! e paddle-left)
            )))))
