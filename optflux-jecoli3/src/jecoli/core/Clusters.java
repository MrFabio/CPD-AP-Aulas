package jecoli.core;

// keeps a set of clusters based on a data matrix
public class Clusters {

	DataMatrix ma;
	int k;
	double[][] centroids;
	
	public Clusters(int k, DataMatrix ma)
	{
		this.k = k;
		this.ma = ma;
		this.centroids = new double[k][ma.numColumns()];
	}
	
	//changes centroids of clusters
	public void setCentroids(double[][] c)
	{
		this.centroids = c;
	}
	
	public double[][] getCentroids()
	{
		return this.centroids;
	}
	
	public DataMatrix getDataMatrix ()
	{
		return this.ma;
	}

	public int getK ()
	{
		return this.k;
	}



	// updates centroids from cluster assignment
	public void updateCentroids (int [] clusters)
	{
		int[] freqs = new int[k];
		
		for(int i=0; i < this.centroids.length; i++)
			for(int j=0; j < this.centroids[i].length; j++)
				this.centroids[i][j] = 0.0;
		
		for(int i=0; i < freqs.length; i++)	freqs[i] = 0;
		
		for(int i=0; i< ma.numRows(); i++)
		{
			int cl_i = clusters[i];
			freqs[cl_i]++;
			for(int j=0; j < this.centroids[cl_i].length; j++)
				this.centroids[cl_i][j] += this.ma.getValue(i, j);
		}
		
		for(int i=0; i< k; i++)
			for(int j=0; j < centroids[i].length; j++)
				if (freqs[i] > 0) this.centroids[i][j] /= freqs[i];
				else this.centroids[i][j] = 0.0;
	}
	
	// assigns a cluster to an entity (data matrix row) based on current centroids of clusters
	// assigns cluster as the one with nearest centroid
	public int assignCluster (int row)
	{
		double mindist = Double.MAX_VALUE;
		int res = -1;
		for(int j=0; j<this.k; j++) // centroid
		{
			double dist = euclideanDistance(centroids[j], ma.getRow(row));
			if (dist < mindist) {
				mindist = dist;
				res = j;
			}
		}
		return res;
	}
	

	// assigns clusters to all entities (rows of matrix)
	public int[] assignClusters ()
	{
		int[] cls = new int[ma.numRows()];
		for(int i=0; i< ma.numRows(); i++) 
			cls[i] = assignCluster(i);
		return cls;	
	}
	
	// distance between a row and a centroid
	public double distGeneCentroid (int row, int cent)
	{
		return euclideanDistance(this.centroids[cent], this.ma.getRow(row));
	}
	
	// given a solution - cluster assignment - calculates mean of distances between rows and centroids 
	// (cost of a solution to the kmeans problem)
	public double distAllRowsCentroid (int [] sol)
	{
		double sum = 0.0;
		for(int i=0; i< ma.numRows(); i++)
		{
			int cl_i = sol[i];
			sum += this.distGeneCentroid(i, cl_i);
		}
			
		return sum/ma.numRows();
	}	
	
	// euclidian distance between two vectors
	public static double euclideanDistance (double[] a, double[] b)
	{
		// assumes a and b are of the same size
		double sq = 0.0;
		for(int i=0; i < a.length; i++)
			sq += (Math.pow(a[i]-b[i],2.0));
		return Math.sqrt(sq);
	}
}
