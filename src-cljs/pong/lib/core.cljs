(ns pong.lib.core)

(def ! aset)
(def ? aget)
(def >< js/Game.merge)

(def add-c js/Game.addC)
(def rem-c js/Game.remC)
(def all-e js/Game.allE)
(def has? js/Game.has)
(def as js/Game.as)
(def clear! js/Game.clear)

(defn load [entities]
  (clear!)
  (doseq [[name comps] (partition-all 2 entities)]
    (let [ent (js/Game.create name)]
      (doseq [c comps]
        (js/Game.addC ent c)))))