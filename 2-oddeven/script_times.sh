#!/bin/bash

#for th in 2 4 6 8 10 12 16 18 20 30 40
#do



#export OMP_NUM_THREADS=$th
for size in 500 1000 5000 10000 50000 100000 #500000 #1000000 5000000 10000000 5000000
do
: '
echo "2-  $th $size"
time ./OddEven_omp2 $size

echo "3-  $th $size"
time ./OddEven_omp3 $size
done
'

echo "1-  $size"
./OddEven "$size"

done



