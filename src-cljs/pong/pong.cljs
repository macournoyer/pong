(ns pong
  (:use [pong.ball :only [Ball]]))

(deftype Background []
  Object
  (update [_])
  (draw [_ context]
    (set! (.-fillStyle context) "#000")
    (.fillRect context 0 0 (.-width game) (.-height game))
    ))

(defn start [canvas]
  (.set-entities game [
    (Background.)
    (Ball.)
    ])

  (.start game canvas))