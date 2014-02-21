(ns pong.systems.moveable
  (:require-macros [pong.lib.macros :refer [letc dofs ? !]]))

(def key? js/input.key)

(defn keyboard 
  [ents]
  (dofs [e ents]
    (letc e [actions :actions]
      (! actions :move-up false)
      (! actions :move-down false)
      (cond 
        (key? :up) (! actions :move-up true)
        (key? :down) (! actions :move-down true)))))

(defn move
  [ents]
  (dofs [e ents]
    (letc e [ actions :actions
              vel :moveable]      
      (cond 
        ; check if not blocked
        (? actions :move-up) (! vel :velocity-y -8)
        (? actions :move-down) (! vel :velocity-y 8)
        :else (! vel :velocity-y 0)))))
