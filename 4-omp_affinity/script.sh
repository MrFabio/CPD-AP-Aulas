

echo "8M x 8 $i"
for i in 1 2 4
do
./omp_aff "$i" 8000000 8
done

echo "8k x 8k $i"
for i in 1 2 4
do
./omp_aff "$i" 8000 8000
done

echo "8 x 8M $i"
for i in 1 2 4
do
./omp_aff "$i" 8 8000000
done
