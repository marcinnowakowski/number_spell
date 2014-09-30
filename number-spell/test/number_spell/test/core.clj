(ns number_spell.test.core
  (:use [number_spell.core])
  (:use [clojure.test]))

(defn test-one-number [number correct_spelling]
  (let [result (get-words-for-a-number number)] 
  (is (= result correct_spelling) (str "Number " number 
    " spelled incorrect: " result "!=" correct_spelling) )
  )
)

(deftest chosen-numbers-test
  (test-one-number "1" "one")  
  (test-one-number "10" "ten")  
  (test-one-number "21" "twenty-one")  
  (test-one-number "101" "one hundred")
  (test-one-number "1001" "one thousand one") 
)

