#!/bin/bash


# THREADS

exec > "t_OMP.txt"

for th in 2 10 20 30
do 
export OMP_NUM_THREADS=$th
echo  $th "\t./ep.B.x"
./ep.B.x
echo  $th "\t./ft.B.x"
./ft.B.x
echo  $th "\t./is.B.x"
./is.B.x
echo  $th "\t./sp.B.x"
./sp.B.x
done


























