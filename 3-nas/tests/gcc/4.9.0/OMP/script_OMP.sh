#!/bin/bash


# THREADS

#exec > "t_OMP.txt"


echo "OMP A"

for th in 2 10 20 30
do 
export OMP_NUM_THREADS=$th
echo  $th "\tOMP\t./ep.A.x"
./A/ep.A.x
echo  $th "\tOMP\t./ft.A.x"
./A/ft.A.x
echo  $th "\tOMP\t./is.A.x"
./A/is.A.x
echo  $th "\tOMP\t./sp.A.x"
./A/sp.A.x
done


echo "OMP B"

for th in 2 10 20 30
do 
export OMP_NUM_THREADS=$th
echo  $th "\tOMP\t./ep.B.x"
./B/ep.B.x
echo  $th "\tOMP\t./ft.B.x"
./B/ft.B.x
echo  $th "\tOMP\t./is.B.x"
./B/is.B.x
echo  $th "\tOMP\t./sp.B.x"
./B/sp.B.x
done


























