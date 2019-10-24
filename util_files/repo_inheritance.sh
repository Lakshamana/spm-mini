#!/bin/bash
repo_base='src/main/java/br/ufpa/labes/spm/repository'
cd $repo_base
classes=`find . -regextype posix-extended -regex '(.*)Repository.java' | cut -d'/' -f2 | sed -r 's/(.*)Repository.java/\1/'`
daos=`find impl/ -regextype posix-extended -regex '(.*)DAO.java' | cut -d'/' -f3 | sed -r 's/(.*)DAO.java/\1/'`

for c in $classes; do
  class_path=`echo "$c"Repository.java`

  # if do not have repository -> dao mapping, ignore current class
  dao_seek=`echo "$daos" | grep -w $c`
  if [[ $dao_seek == '' ]]; then
    continue
  fi

  # Add DAO inheritance
  super=`echo I"$c"DAO`
  package=`find interfaces/ -iname "$super".java | cut -d'/' -f2`
  if [[ $package != '' ]]; then
    package=".$package"
  fi

  sed -ri "/package/ s/(package .*)/\1\n\nimport br.ufpa.labes.spm.repository.interfaces$package.$super;\n/" $class_path
  sed -ri "/public interface/ s/public interface "$c"Repository extends (.*)/public interface "$c"Repository extends $super, \1/" $class_path
done

echo 'Finish adding inheritance...'
