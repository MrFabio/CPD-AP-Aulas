

#include <stdio.h>
#include <stdlib.h>
#include <omp.h>
#include <pthread.h>
/* For random list, 0 <= keys < RMAX */
const int RMAX = 100;

void Usage(char* prog_name);
void Get_args(int argc, char* argv[], int* n_p, char* g_i_p);
void Generate_list(int a[], int n);
void Print_list(int a[], int n, char* title);
void Read_list(int a[], int n);
void Bubble_sort(void * voi);


int block_size=5000,n;
int nthreads=1;
int* a;
pthread_mutex_t *mutex;
pthread_t * threads;

/*-----------------------------------------------------------------*/
int main(int argc, char* argv[]) {
   char g_i;

   Get_args(argc, argv, &n, &g_i);
   a = (int*) malloc(n*sizeof(int));
   if (g_i == 'g') {
      Generate_list(a, n);
      //Print_list(a, n, "Before sort");
   } else {
      Read_list(a, n);
   }


    int i;

    threads = malloc(nthreads * sizeof(pthread_t));
    mutex = malloc((nthreads) * sizeof(pthread_mutex_t));

    double start = omp_get_wtime();
    int thread;
    for(thread=0; thread <= nthreads; thread++){
        pthread_mutex_init(&mutex[thread], NULL);
    }

	for(thread=0; thread < nthreads; thread++){
        pthread_create(&threads[thread], NULL, Bubble_sort, (void *)a);
    }
   
    
   
    for(thread=0; thread < nthreads; thread++){
        pthread_join( threads[thread], NULL);
    }


    double end = omp_get_wtime();
    printf("%3.2g\n",end-start);


   //Print_list(a, n, "After sort");
    
//   free(a);
   return 0;
}  /* main */


/*-----------------------------------------------------------------
 * Function:  Usage
 * Purpose:   Summary of how to run program
 */
void Usage(char* prog_name) {
   fprintf(stderr, "usage:   %s <n> <g|i>\n", prog_name);
   fprintf(stderr, "   n:   number of elements in list\n");
   fprintf(stderr, "  'g':  generate list using a random number generator\n");
   fprintf(stderr, "  'i':  user input list\n");
}  /* Usage */


/*-----------------------------------------------------------------
 * Function:  Get_args
 * Purpose:   Get and check command line arguments
 * In args:   argc, argv
 * Out args:  n_p, g_i_p
 */
void Get_args(int argc, char* argv[], int* n_p, char* g_i_p) {
   if (argc != 4 ) {
      Usage(argv[0]);
      exit(0);
   }
   *n_p = atoi(argv[1]);
   *g_i_p = argv[2][0];
   nthreads = atoi(argv[3]);
   if (*n_p <= 0 || (*g_i_p != 'g' && *g_i_p != 'i') ) {
      Usage(argv[0]);
      exit(0);
   }
}  /* Get_args */


/*-----------------------------------------------------------------
 * Function:  Generate_list
 * Purpose:   Use random number generator to generate list elements
 * In args:   n
 * Out args:  a
 */
void Generate_list(int a[], int n) {
   int i;

   srandom(0);
   for (i = 0; i < n; i++)
      a[i] = random() % RMAX;
}  /* Generate_list */


/*-----------------------------------------------------------------
 * Function:  Print_list
 * Purpose:   Print the elements in the list
 * In args:   a, n
 */
void Print_list(int a[], int n, char* title) {
   int i;

   printf("%s:\n", title);
   for (i = 0; i < n; i++)
      printf("%d ", a[i]);
   printf("\n\n");
}  /* Print_list */


/*-----------------------------------------------------------------
 * Function:  Read_list
 * Purpose:   Read elements of list from stdin
 * In args:   n
 * Out args:  a
 */
void Read_list(int a[], int n) {
   int i;

   printf("Please enter the elements of the list\n");
   for (i = 0; i < n; i++)
      scanf("%d", &a[i]);
}  /* Read_list */


/*-----------------------------------------------------------------
 * Function:     Bubble_sort
 * Purpose:      Sort list using bubble sort
 * In args:      n
 * In/out args:  a
 */void Bubble_sort(
void * voi){
    
    int list_length=n, i,x,ind=0, temp,ltemp,max;
    block_size = (int)(list_length/nthreads);
    
    int ordered = 0,changed = 0;
    
//	pthread_mutex_lock(&mutex[0]);

    while(!ordered){
        changed = 0;
        for(ind=0;ind<nthreads;ind++){
            pthread_mutex_lock(&mutex[ind]);
            max = list_length>(ind+1)*block_size ? (ind+1)*block_size : list_length;
            for (i = ind*block_size; i < max; i++){
                if (a[i] > a[i+1]) {
                    temp = a[i];
                    a[i] = a[i+1];
                    a[i+1] = temp;
                    changed = 1;
                }
            }
            pthread_mutex_unlock(&mutex[ind]);
            
        }
        if(changed==0)
        ordered = 1;
        list_length--;
    }
    

}  /* Bubble_sort */

