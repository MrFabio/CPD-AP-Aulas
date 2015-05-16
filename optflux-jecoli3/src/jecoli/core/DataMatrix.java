package jecoli.core;

import java.io.BufferedReader;
import java.io.FileReader;

// Simple class to keep a matrix of values for clustering - jecoli.test.exercise for MSc students

public class DataMatrix {

	private double[][] madata;
	
	public DataMatrix (String filename) throws Exception
	{
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);	
		int numLines = 0;
		int numCols = 0;
		String str = br.readLine();
		if (br.readLine() != null)
		{
			numLines++;
			String[] tok = str.split("\t");
			numCols = tok.length;
			while (br.readLine()!=null) numLines++;
		}
		br.close();
		fr.close();
		
		madata = new double[numLines][numCols];
		fr = new FileReader(filename);
		br = new BufferedReader(fr);
		for(int i=0; i<numLines; i++)
		{
			str = br.readLine();
			String[] tok = str.split("\t");
			for(int j=0; j < numCols; j++)
				madata[i][j] = Double.parseDouble(tok[j]);
		}
	}
	
	public int numRows ()
	{
		return madata.length;
	}
	
	public int numColumns ()
	{
		return madata[0].length;
	}
	
	public double[] getRow (int i)
	{
		return madata[i];
	}
	
	public double getValue(int i, int j)
	{
		return madata[i][j];
	}
	
	public void print()
	{
		System.out.println("Number of rows; " + this.numRows());
		System.out.println("Number of columns; " + this.numColumns());
		for(int i=0; i < this.numRows(); i++)
		{
			for(int j=0; j < this.numColumns(); j++)
				System.out.print(madata[i][j] + " ");
			System.out.println("");
		}
	}
	
	public double minValue ()
	{
		double res = Double.MAX_VALUE;

		for(int i=0; i < this.numColumns(); i++)
		{
			for(int j=0; j < this.numRows(); j++)
				if (madata[j][i] < res) res = madata[j][i];
		}
		return res;
	}

	public double maxValue ()
	{
		double res = Double.MIN_VALUE;

		for(int i=0; i < this.numColumns(); i++)
		{
			for(int j=0; j < this.numRows(); j++)
				if (madata[j][i] > res) res = madata[j][i];
		}
		return res;
	}

	
	public static void main (String[] args)
	{
		try {
			DataMatrix ma = new DataMatrix(args[0]);
			ma.print();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
