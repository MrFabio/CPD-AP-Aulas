#!/bin/bash

mpic++ -g -O -L /share/apps/mpiP-3.4.1/  -I mrmpi-7Apr14/src -DMPICH_IGNORE_CXX_SEEK -c $1.cpp -g -O3 -lm -fopenmp -lmpiP -lm -lbfd -liberty -lunwind
g++ -g -O $1.o mrmpi-7Apr14/src/libmrmpi_linux.so -lpthread  -o $1 -g -O3 -lm -fopenmp
