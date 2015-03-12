#!/bin/bash

#gcc
#v="4.9.0"
#module load gnu/4.9.0

v="4.4.6"
module load gnu/4.4.6

#NAS
cd ~/NPB3.3.1/

#SER
cd NPB3.3-SER

#: '
make is CLASS=A
make ep CLASS=A
make ft CLASS=A
make sp CLASS=A

make is CLASS=B
make ep CLASS=B
make ft CLASS=B
make sp CLASS=B
#'
cp bin/*.A.x "../tests/gcc/$v/SER/A/"
cp bin/*.B.x "../tests/gcc/$v/SER/B/"

cd ..

#MPI
cd NPB3.3-MPI


for th in 2 4 8 16
do
#: '
make is CLASS=A "NPROCS=$th"
make ep CLASS=A "NPROCS=$th"
make ft CLASS=A "NPROCS=$th"
make sp CLASS=A "NPROCS=$th"

make is CLASS=B "NPROCS=$th"
make ep CLASS=B "NPROCS=$th"
make ft CLASS=B "NPROCS=$th"
make sp CLASS=B "NPROCS=$th"
#'
done

cp bin/*.A.* "../tests/gcc/$v/MPI/A/"
cp bin/*.B.* "../tests/gcc/$v/MPI/B/"

cd ..


#OMP
cd NPB3.3-OMP

#: '
make is CLASS=A
make ep CLASS=A
make ft CLASS=A
make sp CLASS=A

make is CLASS=B
make ep CLASS=B
make ft CLASS=B
make sp CLASS=B
#'
cp bin/*.A.x "../tests/gcc/$v/OMP/A/"
cp bin/*.B.x "../tests/gcc/$v/OMP/B/"

cd ..

