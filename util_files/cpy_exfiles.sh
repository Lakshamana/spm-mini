#!/bin/bash
# copy file if they exist in this repo

indir="$HOME/git/spmtmp/src/main/java/br/ufpa/labes/spm/domain"
outdir="$HOME/git/spm-mini/src/main/java/br/ufpa/labes/spm/domain"

indomain=`find $indir -iname '*.java' | cut -d/ -f14`
outdomain=`find $outdir -iname '*.java' | cut -d/ -f14`
s=0

for i in $indomain; do
  if [[ $i == 'package-info.java' ]]; then
    continue
  fi

  # echo $i
  seek=`echo "$outdomain" | grep -w $i`
  if [[ $seek != '' ]]; then
    cp "$indir/$i" $outdir
    # echo 'cp' "$indir/$i" $outdir
    ((s++))
  fi
done

echo Copied $s classes...
