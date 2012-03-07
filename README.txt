========================================================
|                       Number spell                   |
--------------------------------------------------------
|       Installation instruction step by step          |
========================================================
git clone git://github.com/marcinnowakowski/number_spell.git
cd ../number_spell/number-spell
lein deps
lein eclipse
# here first project can be imported to eclipse
lein install
rm pom.xml
# pom.xml have to be removed to force maven-eclipse-plugin to use jar not project reference
cd ../../number-spell-test
mvn compile
mvn eclipse:eclipse
# here second project can be imported to eclipse
mvn test


