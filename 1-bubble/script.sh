#!/bin/bash

exec > "$1"

for th in 2 4 6 8 10 14 16 20 30 40
do 
export OMP_NUM_THREADS=$th
echo -n "100/" $th "\t"
./bubble_omp 100 g $th
echo -n "500/" $th "\t"
./bubble_omp 500 g $th
echo -n "1000/" $th "\t"
./bubble_omp 1000 g $th
echo -n "5000/" $th "\t"
./bubble_omp 5000 g $th
echo -n "10000/" $th "\t"
./bubble_omp 10000 g $th
echo -n "50000/" $th "\t"
./bubble_omp 50000 g $th
echo -n "100000/" $th "\t"
./bubble_omp 100000 g $th
echo -n "200000/" $th "\t"
./bubble_omp 200000 g $th
done


























