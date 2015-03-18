#!/bin/bash

for th in 2 4 6 8 10 
do



export OMP_NUM_THREADS=$th
for size in 500 1000 5000 10000 50000 100000 
do

echo "1-  $size"
./OddEven "$size"


echo "2-  $th $size"
./OddEven_omp2 $size


echo "3-    $th $size"
./OddEven_omp3 $size

echo "3_1-  $th $size"
./OddEven_omp3_1 $size

done

done


