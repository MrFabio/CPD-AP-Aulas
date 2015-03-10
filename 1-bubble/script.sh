##!/bin/bash
#PBS -l walltime=50:30
#PBS-j oe
#PBS -N eco
#PBS -lnodes=2:ppn=8
#cat $PBS_NODEFILE
#cd ~/AP/bubble/
module add gnu/4.8.2
module add gnu/openmpi_eth/1.8.4
module add pgi/pgi/14.7
module add cuda/6.5
module add intel
module add intel/2013.1.117

export PATH=/usr/bin/:/share/apps/papi/5.3.2/bin/:/share/apps/ompP/bin/:$PATH
export OMPP_OUTFORMAT=CSV
export OMP_NUM_THREADS=4
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/share/apps/papi/5.3.2/lib/

exec > "$1"

for th in 2 4 #6 8 10 14 16 20 30 40
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


























