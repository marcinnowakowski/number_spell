(defproject number-spell "1.0.0-SNAPSHOT"
  :main number_spell.core
  :aot [number_spell.core number_spell.java]
  :description "Number Spell"
  :dependencies [[org.clojure/clojure "1.3.0"]]
  :dev-dependencies [
    [lein-eclipse "1.0.0"]
  ]
)
