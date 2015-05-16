package jecoli.algorithm.components.evaluationfunction;

import jecoli.algorithm.components.representation.IRepresentation;

public interface IEvaluationFunctionListener<T extends IRepresentation> {
	
	void processEvaluationFunctionEvent(EvaluationFunctionEvent<T> event);

}
