/* File:    bubble.c
 *
 * Purpose: Use bubble sort to sort a list of ints.
 *
 * Compile: gcc -g -Wall -o bubble bubble.c
 * Usage:   bubble <n> <g|i>
 *             n:   number of elements in list
 *            'g':  generate list using a random number generator
 *            'i':  user input list
 *
 * Input:   list (optional)
 * Output:  sorted list
 *
 * IPP:     Section 3.7.1 (pp. 127 and ff.) and Section 5.6.1 
 *          (pp. 232 and ff.)
 */
#include <stdio.h>
#include <stdlib.h>
#include <omp.h>
/* For random list, 0 <= keys < RMAX */
const int RMAX = 100;

void Usage(char* prog_name);
void Get_args(int argc, char* argv[], int* n_p, char* g_i_p);
void Generate_list(int a[], int n);
void Print_list(int a[], int n, char* title);
void Read_list(int a[], int n);
void Bubble_sort(int a[], int n);

omp_lock_t *lock;
int block_size=5000;
int nthreads=1;

/*-----------------------------------------------------------------*/
int main(int argc, char* argv[]) {
   int  n;
   char g_i;
   int* a;

   Get_args(argc, argv, &n, &g_i);
   a = (int*) malloc(n*sizeof(int));
   lock = (omp_lock_t*) malloc((nthreads)*sizeof(omp_lock_t));
   if (g_i == 'g') {
      Generate_list(a, n);
   //   Print_list(a, n, "Before sort");
   } else {
      Read_list(a, n);
   }


int i;
   for(i =0;i<n/block_size;i++)
	omp_init_lock(&(lock[i]));

   Bubble_sort(a, n);

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
 */
void Bubble_sort(
      int  a[]  /* in/out */, 
      int  n    /* in     */) {
   int list_length=n, i,x,ind, temp,ltemp,max;
   double start = omp_get_wtime();
 //  printf("nt=%d NT=%d ll=%d\n",nthreads,omp_get_num_threads(),list_length);
   //block_size = (int)omp_get_num_threads()/list_length;
   block_size = (int)(list_length/nthreads);

   for(i=0;i<nthreads;i++)
	omp_init_lock(&(lock[i]));

int ordered = 0,changed = 0;

  #pragma omp parallel for shared(a,lock,n,list_length,ordered) num_threads(nthreads) private(temp,i,ltemp,ind) firstprivate(block_size,changed) 
   for (x=0; x<n-1; x++){
	
#pragma omp cancel[ordered==1]
	changed = 0;
   //for (list_length=n; list_length >= 2; list_length--){
	//printf("=%d len %d\n",omp_get_thread_num(),list_length);
	#pragma omp critical
	ltemp = --list_length;
     for(ind=0;ind<nthreads;ind++){
	 omp_set_lock(&(lock[ind]));
     max = ltemp>(ind+1)*block_size ? (ind+1)*block_size : ltemp;
      for (i = ind*block_size; i < max; i++){
//	 omp_set_lock(&(lock[i+1]));
	 
         if (a[i] > a[i+1]) {
            temp = a[i];
            a[i] = a[i+1];
            a[i+1] = temp;
	    changed = 1;
         }
//	 omp_unset_lock(&(lock[i+1]));
	}
	 omp_unset_lock(&(lock[ind]));
     }
	if(changed==0)
		ordered = 1;

}
for(i=0;i<nthreads;i++)
	omp_destroy_lock(&(lock[i]));

double end = omp_get_wtime();

printf("%3.2g\n",end-start);

}  /* Bubble_sort */

