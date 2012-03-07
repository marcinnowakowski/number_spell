(ns number_spell.java
  (:use [number_spell.core])
)

(gen-interface
  :name number_spell.IWordifiedNumber
  :methods [[toWords [int] String]])

(gen-class
  :name number_spell.WordifiedNumberForEnglishCardinals
  :implements [number_spell.IWordifiedNumber]
)

(defn -toWords [this number]
  (if (or (< number 0) (> number 999999999))
    (throw (IllegalArgumentException. 
      (str "Number out of range (0,999999999): " number)))
  )
  (get-words-for-a-number (str number))
)
  