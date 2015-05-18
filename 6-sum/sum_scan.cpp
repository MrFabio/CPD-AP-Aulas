#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <omp.h>


//#define NUM 16000000
//#define NUM_THREADS 8


int NUM_THREADS;
int NUM;  // number of elements in array X
//int X[NUM]={3,5,2,5,7,9,-4,6,7,-3,1,7,6,8,-1,2}; // Test values,16/4 sum=60
int *X;
int *inTotals;  // global storage for partial results
int *outTotals;  // global storage for partial results
pthread_mutex_t *mutexs1;
pthread_mutex_t *mutexs2;

void printArray(int * Y, int size){
  int i;
  for(i=0;i<size;i++)
	printf("%d ",Y[i]);
  printf("\n");
}

void printArray2(int * Y, int start, int end){
  int i;
  for(i=start;i<end;i++)
	printf("%d ",Y[i]);
  printf("\n");
}


void prefixScan(int * A, int start, int end){
  int i = start+1;
  for(;i<end;i++)
        A[i]+= A[i-1];
}

void prefixScan2(int * A, int * Ord,int size){
  Ord[0] = A[0];
  int i;
  for(i=1;i<size;i++)
        Ord[i] = Ord[i-1]+A[i];
}


void prefixExclusiveScan(int * A, int * Ord, int start, int end){
  int i = start;
  if(i==0)
  	{ Ord[0] = 0;i++;}

  for(;i<end;i++)
	A[i] += A[i-1];
} 


void prefixExclusiveScanPos(int * A, int * Ord, int pos){
	if(pos==0)
	  Ord[0] = 0;
	else
	  Ord[pos] = Ord[pos-1]+A[pos-1];
}

void *Summation (void *pArg)
{
  int tNum = *((int *) pArg);
  int lSum = 0;
  int start, end;
  int i,z=0;
  int size = NUM/NUM_THREADS;

  start = (size) * tNum;
  end = (size) * (tNum+1);

  if (tNum == (NUM_THREADS-1)) end = NUM;

  prefixScan(X,start,end);

  inTotals[tNum] = X[end-1];

  pthread_mutex_unlock (&mutexs1[tNum]);
  pthread_mutex_lock (&mutexs2[tNum]);
  for (i = start; i < end; i++)
    {X[i]+=outTotals[tNum];}
  pthread_mutex_destroy (&mutexs2[tNum]);
  


  delete (int *)pArg;
}

void InitializeArray(int *S, int *N)
{
  int i;
  for (i = 0; i < NUM; i++){
    S[i] = (i+1) ;
  }
}


int main(int argc, char* argv[])
{
  int j, sum = 0;

  NUM = atoi(argv[1]);
  NUM_THREADS = atoi(argv[2]);


  X = (int*) malloc(sizeof(int)*NUM);
  inTotals = (int*) malloc(sizeof(int)*NUM_THREADS);
  outTotals = (int*) malloc(sizeof(int)*NUM_THREADS);
  mutexs1 = (pthread_mutex_t*) malloc(sizeof(pthread_mutex_t)*NUM_THREADS);
  mutexs2 = (pthread_mutex_t*) malloc(sizeof(pthread_mutex_t)*NUM_THREADS);

  pthread_t * tHandles = (pthread_t*) malloc(sizeof(pthread_t)*NUM_THREADS);

  InitializeArray(X,&NUM);  // get values into A array; not shown

//  printArray(X,N);
  double start,end;
  start = omp_get_wtime();

  for (j = 0; j < NUM_THREADS; j++) {
    int *threadNum = (int *)malloc(sizeof (int));
    *threadNum = j;
    pthread_mutex_init(&mutexs1[j], NULL);
    pthread_mutex_init(&mutexs2[j], NULL);
    pthread_mutex_lock (&mutexs1[j]);
    pthread_mutex_lock (&mutexs2[j]);
    pthread_create(&tHandles[j], NULL, Summation, (void *)threadNum);
  }

  for (j = 0; j < NUM_THREADS; j++) {
    pthread_mutex_lock (&mutexs1[j]);
    pthread_mutex_destroy (&mutexs1[j]);
//    sum += inTotals[j];
//    printArray(inTotals,N/NUM_THREADS);
    prefixExclusiveScanPos(inTotals,outTotals,j);
  //  printArray(outTotals,N/NUM_THREADS);
//  printArray(X,N);
    pthread_mutex_unlock (&mutexs2[j]);
  }



  for (j = 0; j < NUM_THREADS; j++) {
    pthread_join(tHandles[j], NULL);
    sum += inTotals[j];
  }

  if(argc==3)
    printf("The sum of array elements is %d\n", sum);
//  printArray(inTotals,N/NUM_THREADS);

//  printArray(outTotals,N/NUM_THREADS);

//  printArray(X,N);


	end = omp_get_wtime();
	printf("%3.2g\n",end-start);
  

  return 0;
}
