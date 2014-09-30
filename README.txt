========================================================
|                       Number spell                   |
--------------------------------------------------------
|       Installation instruction step by step          |
========================================================
git clone git://github.com/marcinnowakowski/number_spell.git

cd ../number_spell/number-spell
lein idefiles eclipse
# here project can be imported to eclipse as existing project
# counterclockwise plugin required
lein install

cd ../../number-spell-test
mvn test
# here project can be imported to eclipse as maven project

## License

Copyright (C) 2012-2014 Marcin Nowakowski

Distributed under the Eclipse Public License, the same as Clojure.


