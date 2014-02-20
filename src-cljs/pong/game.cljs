(ns pong.game
  (:require [pong.systems.renderer :as r]))

; settings
(def fps 60)
(def pad-width 20)
(def pad-height 60)


(def context nil)
(def width nil)
(def height nil)

(defn entities 
  [width height]
  {:background {:renderable r/draw-rectangular
      :colored "#000000"
      :position {:x 0 :y 0}
      :rectangular {:width width :height height}
      :user-controlled :keys-1}

   :pad-1 {:renderable r/draw-rectangular
      :colored "#FFFFFF"
      :position {:x 5 :y 30}
      :rectangular {:width pad-width :height pad-height}
      :computer-controlled 1}

   :pad-2 {:renderable r/draw-rectangular
      :colored "#FFFFFF"
      :position {:x (- width pad-width 5) :y 50}
      :rectangular {:width pad-width :height pad-height}}
      :user-controlled :keys-2})

(defn all-e
  [es keyword]
  (apply dissoc es (for [[k v] es :when (not (keyword v))] k)))

(defn start [canvas]
  (set! context (.getContext canvas "2d"))
  (set! width (.-width canvas))
  (set! height (.-height canvas))
  (let [entities (entities width height)]
    (js/setInterval (fn []
      (r/render-all context (all-e entities :renderable))
      ) (/ 1000 fps))))

(defn set-entities [entities*]
  (set! entities entities*))