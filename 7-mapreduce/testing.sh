for n in 1000 10000 100000 1000000
do
echo -e "\n"
for t in 2 4 8 10 12 16 20
do
echo -e "MPI $n\t$t"
mpirun -np $t FriendlyNumbers_MPI 1 $n
echo -e "MRMPI $n\t$t"
mpirun -np $t fn2 1 $n
done

done
