

for i in 1 2 4
do
echo "8M x 8 $i"
./omp_aff "$i" 8000000 8
done

for i in 1 2 4
do
echo "8k x 8k $i"
./omp_aff "$i" 8000 8000
done

for i in 1 2 4
do
echo "8 x 8M $i"
./omp_aff "$i" 8 8000000
done
