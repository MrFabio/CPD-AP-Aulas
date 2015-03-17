
#include <stdio.h>
#include <stdlib.h>
#include <omp.h>


int N;
int *A;

void OESort(int NN, int *A)
{
	int exch0, exch1=1, i,first=0;
	
	while (exch1) {
	    exch0=0;exch1=0;

	    #pragma omp parallel
	       {int temp;
		
		#pragma omp for
		for (i = 0; i < NN-1; i+=2) {
			if (A[i] > A[i+1]) {
				temp = A[i]; A[i] = A[i+1]; A[i+1] = temp;
				exch0=1;
			}
		}
		
		if(exch0 || !first){
		
		#pragma omp for
		for (i = 1; i < NN-1; i+=2) {
			if (A[i] > A[i+1]) {
				temp = A[i]; A[i] = A[i+1]; A[i+1] = temp;
				exch1=1;
			}
		}

	       }
	      }
	      first=1;
	}
}

void init_data()
{
	int i;
	for (i = 0; i < N/2; i++)
		A[i] = i + N/2;
	//	A[i] = rand() % N;
	for (i = N/2; i < N; i++)
		A[i] = i - N/2;

}

int main(int argc, char* argv[])
{	
	int i, j;
	N = (int)atoi(argv[1]);
	A = (int*) malloc(sizeof(int)*N);
	init_data();

//	for ( i = 0; i < N; i++) printf("%3d ",A[i]);
//	printf("\n\n");
	double start,end;

	start = omp_get_wtime();
	OESort(N,A);
	end = omp_get_wtime();

	printf("%3.2g\n",end-start);
	//for ( j = 0; j < N; j++) printf("%3d ",A[j]);
	return 0;
}
