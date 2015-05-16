package jecoli.algorithm.components.operator;

import jecoli.algorithm.components.evaluationfunction.IEvaluationFunction;
import jecoli.algorithm.components.representation.IRepresentation;

public interface ILocalOptimizationOperator<T extends IRepresentation> extends IOperator {

	IEvaluationFunction<T> getEvaluationFunction ();
	
	int getNumberFunctionEvaluationPerfomed ();
	
	boolean hasImproved();
}
