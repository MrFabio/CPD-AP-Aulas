
#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

#define N 10

int A[N];

void OESort(int NN, int *A)
{
	int exch = 1, start = 0, i;
	#pragma omp parallel
	{int temp;
	 exch = omp_get_num_threads();
	while (1) {
		if(exch == 0 && start == 0) break;
		
		#pragma omp single
		exch--;
		#pragma omp for
		for (i = start; i < NN-1; i+=2) {
			if (A[i] > A[i+1]) {
				temp = A[i]; A[i] = A[i+1]; A[i+1] = temp;
				#pragma omp critical
				exch = omp_get_num_threads(); //KEY
			}
		}
		#pragma omp single
		if (start == 0) start = 1;
		else start = 0;
	 }
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

	init_data();

//	for ( i = 0; i < N; i++) printf("%3d ",A[i]);
//	printf("\n\n");

	OESort(N,A);

	for ( j = 0; j < N; j++) printf("%3d ",A[j]);
	return 0;
}
