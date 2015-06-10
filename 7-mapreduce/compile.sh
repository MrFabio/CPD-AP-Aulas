#!/bin/bash

mpic++ -g -O -I mrmpi-7Apr14/src -DMPICH_IGNORE_CXX_SEEK -c $1.cpp
g++ -g -O wordfreq.o mrmpi-7Apr14/src/libmrmpi_linux.so -lpthread  -o $1

