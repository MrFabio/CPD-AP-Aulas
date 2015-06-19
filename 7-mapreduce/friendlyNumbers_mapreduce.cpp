#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <mpi.h>
#include "mapreduce.h"
#include "keyvalue.h"

using namespace MAPREDUCE_NS;
unsigned int start,end;
    int pid,n_proc;
    
    
typedef struct sPair {
  int x, y; // start,end and fnum1,fnum2
}Pair;

    

void printArray(int* array, int size)
{
    int i;
    for(i=0;i<size;i++)
        printf(" %d ", array[i]);
    printf("\n");
}


int gcd(int u, int v)
{
    if (v == 0) 
        return u;
    return gcd(v, u % v);
}


void FriendlyNumbers(int itask, KeyValue *kv, void* ptr)
{
    
    int num,den;
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
		num = sum;
		den = i;
		n = gcd(num, den);
		num /= n;
		den /= n;
		
        Pair p;p.x=num;p.y=den;
        int val = i;
		kv->add((char*)&p,sizeof(Pair),(char*) &val,sizeof(int));

	} // end for
 }
  // end parallel region
}

void transform(int* numbers,char* mv, int nv,int* vb)
{
    int* values = (int*) mv;
    for (int i = 0; i < nv; ++i)
    {
        numbers[i] = values[i];
    }
}

void output(char *key, int keybytes, char *multivalue, int nvalues, int *valuebytes, KeyValue *kv, void *ptr) 
{
    int* numbers=(int*)malloc(sizeof(int)*nvalues);
    
    transform(numbers,multivalue,nvalues,valuebytes);
    if(nvalues>1)
    {
        for (int i = 0; i < nvalues; i++)
        {
           ;printf("%d ", multivalue[i]);
        }
        printf("\n");
    }
}


int main(int argc, char **argv)
{
    start = atoi(argv[1]);
    end = atoi(argv[2]);
	MPI_Init(&argc,&argv);
    double time=MPI_Wtime();
    MPI_Status status1,status2;
    MPI_Comm_size(MPI_COMM_WORLD,&n_proc);
    MPI_Comm_rank(MPI_COMM_WORLD,&pid);

    int size = end-start+1;
    int offset = size / (n_proc);
    int off = 0,proc;
	
	end=start+(offset*(pid+1)-1);
	start+=(offset*(pid));
	Pair pair; 
	pair.x=start;
	pair.y=end;
    
    // MapReduce
   MapReduce *mr = new MapReduce(MPI_COMM_WORLD);
   mr->verbosity=0;
   mr->timer = 1;
   MPI_Barrier(MPI_COMM_WORLD);
   int nNumber = mr->map(n_proc,&FriendlyNumbers,NULL);
   int nChunks = mr->mapfilecount;
   mr->collate(NULL); // Collate Keys
    
   if(pid==0)
        printf("The following numbers are friendly:\n");
   int nunique = mr->reduce(output,NULL); 
   MPI_Barrier(MPI_COMM_WORLD);

   if(pid==0){
   	    time=MPI_Wtime()-time;
	    printf("Time:%lf seconds\n",time);}
   
   delete mr;
   MPI_Finalize();
}
