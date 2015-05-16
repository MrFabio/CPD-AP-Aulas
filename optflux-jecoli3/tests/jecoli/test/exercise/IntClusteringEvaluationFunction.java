package jecoli.test.exercise;

import java.util.ArrayList;
import java.util.List;

import jecoli.core.Clusters;
import jecoli.core.DataMatrix;
import jecoli.algorithm.components.evaluationfunction.AbstractEvaluationFunction;
import jecoli.algorithm.components.evaluationfunction.IEvaluationFunction;
import jecoli.algorithm.components.evaluationfunction.InvalidEvaluationFunctionInputDataException;
import jecoli.algorithm.components.randomnumbergenerator.IRandomNumberGenerator;
import jecoli.algorithm.components.representation.linear.ILinearRepresentation;

public class IntClusteringEvaluationFunction extends AbstractEvaluationFunction<ILinearRepresentation<Integer>>{

	IRandomNumberGenerator randomNumberGenerator;
	Clusters cluster;
	

	public IntClusteringEvaluationFunction(int k, DataMatrix ma)
	{
		super(false);
		this.cluster = new Clusters(k,ma);
		
    }
	
	
	@Override
	public void verifyInputData()
			throws InvalidEvaluationFunctionInputDataException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IEvaluationFunction<ILinearRepresentation<Integer>> deepCopy()
			throws Exception {
		return null;
	}

	@Override
	public double evaluate(ILinearRepresentation<Integer> solution)
			throws Exception {
		int sizeN = this.cluster.getDataMatrix().numRows();
		int[] aux = new int[sizeN];
		for(int i=0; i < sizeN; i++){
			aux[i] = solution.getElementAt(i);
		}
		cluster.updateCentroids(aux);
		double fitness = cluster.distAllRowsCentroid(aux);
		return fitness;
	}
	
}
