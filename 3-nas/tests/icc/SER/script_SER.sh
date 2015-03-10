#!/bin/bash



# MPI

exec > "t_MPI.txt"

echo  $th "\t./ep.B.x"
./ep.B.x
echo  $th "\t./ft.B.x"
./ft.B.x
echo  $th "\t./is.B.x"
./is.B.x
echo  $th "\t./sp.B.x"
./sp.B.x


























