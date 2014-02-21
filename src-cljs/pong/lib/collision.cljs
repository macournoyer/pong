(ns pong.lib.collision
  (:require [pong.lib.core :refer [all-e]])
  (:require-macros [pong.lib.macros :refer [letc dofs ! ?]]))

(defn top?
  "Does a collide on top?" 
  [e b]
  (letc e [pos-a :position
           rect-a :rectangular]
    (letc b [pos-b :position
             rect-b :rectangular]             
             (<= (? pos-a :y) (? pos-b :y)))))

(defn bottom?
  "Does a collide on the bottom?" 
  [e b]
  (letc e [pos-a :position
           rect-a :rectangular]
    (letc b [pos-b :position
             rect-b :rectangular]             
             (>= (+ (? pos-a :y) (? rect-a :height)) (? rect-b :height)))))

(defn blocked? 
  "Check if an entity e is blocked by the board bounds."
  [e b] 
  (cond
    (top? e b) :top
    (bottom? e b) :bottom))