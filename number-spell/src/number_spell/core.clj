(ns number_spell.core)

(def digits {\1 "one" \2 "two" \3 "three" \4 "four" \5 "five" \6 "six" \7 "seven" \8 "eight" \9 "nine"})

(def teens {\0 "ten" \1 "eleven" \2 "twelve" \3 "thirteen" \4 "fourteen" \5 "fifteen" \6 "sixteen" \7 "seventeen" \8 "eighteen" \9 "nineteen" })

(def tens {\1 "ten" \2 "twenty" \3 "thirty" \4 "forty" \5 "fifty" \6 "sixty" \7 "seventy" \8 "eighty" \9 "ninety"})

(def powers_of_ten {1 "ten" 2 "hundred" 3 "thousand" 6 "million"})

(def zero {0 "zero"})

(defn is-digit? [character]
  (or (not character) (not (contains? "0123456789" character)))
)

(defn is-number [the_number]
  (= (count (filter is-digit? the_number)) count the_number)
)

(defn pom-cut-in-triples [result x]
  (let [head (first result) tail (rest result)]
    (if (empty? tail)
      (if (empty? head)
        [[x]]
        (if (< (count head) 3)
          [(cons x head)]
          [[x] head]
        )
      )
      (concat (pom-cut-in-triples [head] x) tail)
    )
  )
)

(defn cut-in-triples [the_number]
  (reduce pom-cut-in-triples [] the_number)   
)

(defn not-empty-not-zero? [digit]
  (and digit (not (= digit \0)))
)

(defn empty-or-zero? [digit]
  (or (not digit) (= digit \0))
)

(defn get-words-for-triple [first_pos second_pos third_pos]
  
  ;check if all are digits
  ;(reduce throw-if-not-a-digit [first_pos second_pos third_pos])
  
  ; we have for sure only digits here
  (str 
    ; hundreds
    (if (not-empty-not-zero? first_pos)
      (str (get digits first_pos) " " (get powers_of_ten 2))
    )
    ; separator between hundreds and tens / ones
    (if (and (not-empty-not-zero? first_pos) 
           (or (not-empty-not-zero? second_pos) (not-empty-not-zero? third_pos))
        )
    " "
    )
    ; ten and ones
    (if (empty-or-zero? second_pos)
      ; only ones
      (get digits third_pos)
      ; tens and ones or teens
      (if (= second_pos \1)
        ; teens
        (get teens third_pos)
        ; tens
        (str (get tens second_pos) 
          ; ones
          (if (not-empty-not-zero? third_pos) 
            (str "-" (get digits third_pos))
          )
        ) 
      )
    )
  )
)

(defn get-words-for-a-number [the_number]
  (let [result (first (let [reversed_triple_list (reverse (cut-in-triples (reverse the_number)))]
    (reduce 
      (fn [[result position] raw_triple] 
        (let [triple (if (= (count raw_triple) 3) raw_triple
               (if (= (count raw_triple) 2) (cons nil raw_triple)
                 (if (= (count raw_triple) 1) (cons nil (cons nil raw_triple))
                   [nil nil nil]
                 )
               )
             )
             words-for-triple (apply get-words-for-triple triple)]
          (if (empty? words-for-triple)
            [result (+ position 3)]
            [(str words-for-triple (if (> position 0) (str " " (get powers_of_ten position)))
               (if (not (empty? result)) (str " " result)))
             (+ position 3)]
          )
        )
      )
      ["" 0]
      reversed_triple_list
    )
  ))]
  (if (empty? result)
    (get zero 0)
    result
  )
  )
)