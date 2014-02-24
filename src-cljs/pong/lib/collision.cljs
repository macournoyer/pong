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
              (when (<= (? pos-a :y) (? pos-b :y))
                true))))

(defn bottom?
  "Does a collide on the bottom?" 
  [e b]
  (letc e [pos-a :position
           rect-a :rectangular]
        (letc b [pos-b :position
                 rect-b :rectangular]             
              (when (>= (+ (? pos-a :y) (? rect-a :height))
                        (? rect-b :height))
                true))))

(defn left?
  "Does a collide on the left?" 
  [e b]
  (letc e [pos-a :position
           rect-a :rectangular]
        (letc b [pos-b :position
                 rect-b :rectangular]     
              (when (= (? pos-a :x) 
                       (? pos-b :x))
                true))))

(defn right?
  "Does a collide on the right?" 
  [e b]
  (letc e [pos-a :position
           rect-a :rectangular]
        (letc b [pos-b :position
                 rect-b :rectangular]
              (when (= 
                      (+ (? pos-a :x) (? rect-a :width))
                      (+ (? pos-b :x) (? rect-b :width)))
                true))))
