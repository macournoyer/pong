(ns pong.ball
  (:use [pong.entity :only [IEntity]]))

(deftype Ball []
  IEntity

  (update [_]

    )

  (draw [_ context]
    (set! (.-fillStyle context) "#fff")
    (.fillRect context 0 0 20 20)
    ))
