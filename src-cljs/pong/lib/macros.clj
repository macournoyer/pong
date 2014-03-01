(ns pong.lib.macros)

(defmacro component [name params & r]
  `(defn ~name ~params
     (cljs.core/js-obj "name" ~(keyword (clojure.core/name name)) ~@r)))

(defmacro ? [obj k]
  `(aget ~obj ~k))

(defmacro ! [obj k v]
  `(aset ~obj ~k ~v))

(defmacro letc [ent pairs & body]
  (let [pairs (vec (apply concat
                          (for [[k v] (partition-all 2 pairs)]
                            [k `(js/Game.as ~ent ~v)])))]
    `(let ~pairs ~@body)))

(defmacro dofs [pair & body]
  (let [[n vs] pair]
    `(let [c# (count ~vs)]
       (loop [i# 0]
         (when (< i# c#)
           (let [~n (aget ~vs i#)]
             ~@body
             (recur (inc i#))))))))

(defmacro dofs2 [seq-exprs & body]
  (let [emit (fn emit [[sym coll & rest-exprs]]
               `(let [c# (count ~coll)]
                  (loop [i# 0] ;loop from zero
                     (when (< i# c#) ; as long a we have more items
                        (let [~sym (aget ~coll i#)]
                         ~(if rest-exprs
                            (emit rest-exprs)
                            `(do ~@body))
                         (recur (inc i#)))))))]
    (emit seq-exprs)))
