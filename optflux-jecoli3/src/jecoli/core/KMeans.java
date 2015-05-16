package jecoli.core;



public class KMeans extends Clusters {
	
	public KMeans(int k, DataMatrix ma)
	{
		super(k, ma);	
	}
	
	// initialize centroids randomly
	public void initCentroids ()
	{
		double maxv = this.getDataMatrix().maxValue();
		double minv = this.getDataMatrix().minValue();
		double[][] c = this.getCentroids();
		
		for(int i=0; i< c.length; i++)
			for(int j=0; j < c[i].length; j++)
				c[i][j] = frandom(minv, maxv);
	}
	

	public int[] kmeans ()
	{
		return kmeans(null);
	}
	
	public int[] kmeans (double[][] initialCentroids)
	{
		boolean eq = false;
		if (initialCentroids == null) this.initCentroids();
		else this.setCentroids(initialCentroids);
		
		int [] clusters = this.assignClusters();
		while(!eq)
		{
			this.updateCentroids(clusters);
			int [] newcl = this.assignClusters();
			eq = equalClusters(clusters, newcl);
			if (!eq) clusters = newcl;
		}
		return clusters;
	}
	
	
	public int[] multiStartKmeans (int attempts)
	{
		int [] sol = null;
		int [] bestsol = null;
		double bestval = Double.MAX_VALUE;
		
		for(int i=0; i < attempts; i++)
		{
			sol = kmeans();
			this.updateCentroids(sol);
			double val = distAllRowsCentroid(sol);
			if (val < bestval)
			{
				bestval = val;
				bestsol = sol;
			}
		}
		return bestsol; 
	}
	
	private boolean equalClusters(int [] old, int [] newc)
	{
		boolean res = true;
		for(int i=0; i < old.length && res; i++)
			if(old[i] != newc[i]) res = false;
		return res;
	}
	
	public static double frandom(double a, double b) 
	// Random real within range [a,b[
        {return (b-a)*Math.random()+a;}
	
	public static void main (String[] args)
	{
		try {
			DataMatrix ma = new DataMatrix("exemploMA.txt");
			int k = 3;
			
			KMeans km = new KMeans(k, ma);
			int [] sol = km.multiStartKmeans(1000);
			km.updateCentroids(sol);
			for (int i=0; i < sol.length; i++)
				System.out.print(sol[i]);
			System.out.println("\nVal: " + km.distAllRowsCentroid(sol));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
