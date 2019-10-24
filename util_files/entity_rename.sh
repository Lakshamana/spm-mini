#!/bin/bash

cd src/main/java/br/ufpa/labes/spm/repository
for file in `find . -regextype posix-extended -regex '(.*)DAO.java'`; do
  # echo $file
  replace=`echo $file | sed -r 's/(.*)DAO.java/\1RepositoryQuery.java/'`
  # echo $replace
  mv $file $replace
done

echo 'Finish renaming entity...'
