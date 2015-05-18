
for x in 1 2 3 4 5
do 

for n in 10000000 40000000 100000000 200000000 400000000
do
echo -e "\n"
for t in 2 4 8 10 12 16 20 32
do
echo -e "$n\t$t"
./SUM_SCAN $n $t x
done

done

done
