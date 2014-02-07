(ns pong.game)

(def fps 60)
(def context nil)
(def width nil)
(def height nil)
(def entities [])

(defn start [canvas]
  (set! context (.getContext canvas "2d"))
  (set! width (.-width canvas))
  (set! height (.-height canvas))

  (js/setInterval (fn []
    (update)
    (draw)
    ) (/ 1000 fps)))

(defn update [_]
  (doseq [entity entities]
    (.update entity)))

(defn draw [_]
  (doseq [entity entities]
    (.draw entity context)))

(defn set-entities [entities*]
  (set! entities entities*))