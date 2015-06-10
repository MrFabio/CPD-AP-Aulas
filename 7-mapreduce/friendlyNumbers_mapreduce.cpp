#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <mpi.h>
#include "mapreduce.h"
#include "keyvalue.h"
using namespace MAPREDUCE_NS

	int *num;
	int *den;
	int size;
int gcd(int u, int v)
{
if (v == 0) return u;
return gcd(v, u % v);
}

void printArray(int *array,int size){
	int i=0;
	for(i;i<size;i++)
		printf("%d ",array[i]);
	printf("\n");
}

void FriendlyNumbers (int start, int end)
{
//	size = end-start+1;
//	printf("Tstart:%d Tend:%d Last:%d\n",start,end,size);
//	num = new int[offset];
//	den = new int[offset];
#pragma omp parallel
 {	int i, j, factor, ii, sum, done, n;
	// -- MAP --
	#pragma omp for schedule (dynamic, 16)
	for (i = start; i <= end; i++) {
		ii = i - start;
		sum = 1 + i;
		done = i;
		factor = 2;
		while (factor < done) {
			if ((i % factor) == 0) {
				sum += (factor + (i/factor));
				if ((done = i/factor) == factor) sum -= factor;
			}
			factor++;
		}
		num[ii] = sum; den[ii] = i;
		n = gcd(num[ii], den[ii]);
		num[ii] /= n;
		den[ii] /= n;
	} // end for
}
  // end parallel region
}


int main(int argc, char **argv)
{

    unsigned int start = atoi(argv[1]);
    unsigned int end = atoi(argv[2]);
	MPI_Init(&argc,&argv);
    double time=MPI_Wtime();
    int pid,n_proc;
    MPI_Status status1,status2;
    MPI_Comm_size(MPI_COMM_WORLD,&n_proc);
    MPI_Comm_rank(MPI_COMM_WORLD,&pid);

   // MapReduce
   MapReduce *mr = new Mapreduce(MPI_COMM_WORLD); // instantiate an MR Object
   mr->map(start+offset*pid,start+offset*(pid+1(-1,&FriendlyNumbers);
   mr->collate(); // Collate Keys
   mr->reduce(&myreduce);
   delete mr;


	    size = end-start+1;
    int offset = size / (n_proc);
    int off = 0,proc;

	printf("pid:%d  start:%d end:%d offset:%d size:%d\n",pid,start+(offset*pid),start+(offset*(pid+1)),offset,size);
//    int *the_num = new int[size];
	if(pid==0){
	  num = new int[size];
	  den = new int[size];}
	else
	{
	  num = new int[offset];
	  den = new int[offset];}

    	FriendlyNumbers(start+(offset*(pid)), start+(offset*(pid+1)-1));
    if(pid==0){
	
	    int i=1,j;
  	    for(i;i<n_proc;i++){
		MPI_Recv(&num[(i)*offset],offset, MPI_INT, i,1,MPI_COMM_WORLD,&status1);
		MPI_Recv(&den[(i)*offset],offset, MPI_INT, i,2,MPI_COMM_WORLD,&status2);
	    }

// -- REDUCE --
#pragma parallel omp for schedule (static, 8)
	for (i = 0; i < size; i++) {
		for (j = i+1; j< size; j++) {
			if ((num[i] == num[j]) && (den[i] == den[j]))
				printf ("%d and %d are FRIENDLY \n", start+i, start+j);
		}
	}	

	    time=MPI_Wtime()-time;
	    printf("Time:%f seconds\n");
/*	printf("num %d\n",pid);
	printArray(num,size);
	printf("den %d\n",pid);
	printArray(den,size);*/
	    
    }
    else{	
	MPI_Send(&num[0],offset, MPI_INT, 0,1,MPI_COMM_WORLD);
	MPI_Send(&den[0],offset, MPI_INT, 0,2,MPI_COMM_WORLD);
/*	printf("num %d\n",pid);
	printArray(num,offset);
	printf("den %d\n",pid);
	printArray(den,offset);*/
	
	//delete[] num;
//	delete[] den;
}
    MPI_Finalize();
/*	printf("num %d\n",pid);
	printArray(num,size);
	printf("den %d\n",pid);
	printArray(den,offset);*/
}
