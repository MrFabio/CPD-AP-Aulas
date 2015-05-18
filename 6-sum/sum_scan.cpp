#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

#define NUM_THREADS 4
#define NUM 16

int N=NUM;  // number of elements in array X
int X[NUM]={3,5,2,5,7,9,-4,6,7,-3,1,7,6,8,-1,2};
int gSum[NUM_THREADS];  // global storage for partial results
int gSum2[NUM_THREADS];  // global storage for partial results


void printArray(int * Y, int size){
int i;
  for(i=0;i<size;i++)
	printf("%d ",Y[i]);
  printf("\n");
}

void prefixScan(int * A, int * Ord,int size){
  Ord[0] = A[0];
  int i;
  for(i=1;i<size;i++)
	Ord[i] = Ord[i-1]+A[i];

} 

void prefixExclusiveScan(int * A, int * Ord, int size){
  Ord[0] = 0;
  int i;
  for(i=1;i<size;i++)
	Ord[i] = Ord[i-1]+A[i-1];

} 

void *Summation (void *pArg)
{
  int tNum = *((int *) pArg);
  int lSum = 0;
  int start, end;
  int i,z=0;
  int size = N/NUM_THREADS;
  int Y[size];
  int Y2[size];
  start = (size) * tNum;
  end = (size) * (tNum+1);
  printf("t:%d start:%d end:%d \n",tNum,start,end);
  if (tNum == (NUM_THREADS-1)) end = N;
  for (i = start; i < end; i++)
    {Y[z]=X[i];z++;}
  prefixScan(Y,Y2,size);
  printArray(Y2,size);
  gSum[tNum] = Y2[size-1];
  delete (int *)pArg;
}

void InitializeArray(int *S, int *N)
{
  int i;
  for (i = 0; i < NUM; i++){
    S[i] = i+1;
  }
}


int main(int argc, char* argv[])
{
  int j, sum = 0;
  pthread_t tHandles[NUM_THREADS];

  //InitializeArray(X,&N);  // get values into A array; not shown
  for (j = 0; j < NUM_THREADS; j++) {
    int *threadNum = (int *)malloc(sizeof (int));
    *threadNum = j;
    pthread_create(&tHandles[j], NULL, Summation, (void *)threadNum);
  }
  for (j = 0; j < NUM_THREADS; j++) {
    pthread_join(tHandles[j], NULL);
    sum += gSum[j];
  }
  printf("The sum of array elements is %d\n", sum);
  printArray(gSum,N/NUM_THREADS);
  prefixExclusiveScan(gSum,gSum2,N/NUM_THREADS);
  printArray(gSum2,N/NUM_THREADS);


  

  return 0;
}
