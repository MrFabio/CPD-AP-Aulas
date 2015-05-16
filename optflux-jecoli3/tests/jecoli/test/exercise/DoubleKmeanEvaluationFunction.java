package jecoli.test.exercise;

import java.util.ArrayList;
import java.util.List;

import jecoli.core.KMeans;
import jecoli.core.Clusters;
import jecoli.core.DataMatrix;
import jecoli.algorithm.components.evaluationfunction.AbstractEvaluationFunction;
import jecoli.algorithm.components.evaluationfunction.IEvaluationFunction;
import jecoli.algorithm.components.evaluationfunction.InvalidEvaluationFunctionInputDataException;
import jecoli.algorithm.components.randomnumbergenerator.IRandomNumberGenerator;
import jecoli.algorithm.components.representation.linear.ILinearRepresentation;


public class DoubleKmeanEvaluationFunction extends AbstractEvaluationFunction<ILinearRepresentation<Double>>{

	KMeans cluster;
	
	

	public DoubleKmeanEvaluationFunction (int k, DataMatrix ma)
	{
		super(false);
		this.cluster = new KMeans(k,ma);
		
    }
	
	
	@Override
	public void verifyInputData()
			throws InvalidEvaluationFunctionInputDataException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IEvaluationFunction<ILinearRepresentation<Double>> deepCopy()
			throws Exception {
		return null;
	}

	@Override
	public double evaluate(ILinearRepresentation<Double> solution)
			throws Exception {
		int k = this.cluster.getK();
		int N = this.cluster.getDataMatrix().numColumns();
		double[][] aux = new double[k][N];
		for(int i=0; i < k; i++){
			for(int j=0; j < N; j++){
				aux[i][j] = solution.getElementAt(i*k + j);
			}
		}
		int[] clust = cluster.kmeans(aux);
		double fitness = cluster.distAllRowsCentroid(clust);
		return fitness;
	}
	
}
