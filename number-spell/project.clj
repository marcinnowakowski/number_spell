(defproject number-spell "0.2.0-SNAPSHOT"
  :description "Number Spell"
  :url "https://github.com/marcinnowakowski/number_spell/tree/master/number-spell"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :main number_spell.core
  :aot [number_spell.core number_spell.java]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
