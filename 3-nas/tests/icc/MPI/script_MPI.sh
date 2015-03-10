#!/bin/bash




# THREADS

exec > "t_MPI.txt"

for $th in 2 10 20 30
do
echo  $th "\t./ep.B.x"
mpirun -np $th ./ep.B.x
echo  $th "\t./ft.B.x"
mpirun -np $th ./ft.B.x
echo  $th "\t./is.B.x"
mpirun -np $th ./is.B.x
echo  $th "\t./sp.B.x"
mpirun -np $th ./sp.B.x
done

























