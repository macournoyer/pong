(ns pong.systems.renderer)

(defn render-all
  [context es]
  (doseq [[id e] es]
    ((:renderable e) context e)))

(defn draw-rectangular
  [context e]
  (let [color (:colored e)    
        {x :x y :y} (:position e)
        {w :width h :height} (:rectangular e)]
    (set! (.-fillStyle context) color)
    (.fillRect context x y w h)))