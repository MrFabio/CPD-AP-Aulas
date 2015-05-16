package jecoli.algorithm.components.evaluationfunction;

import java.io.Serializable;

public interface IFitnessFunctionEvaluator<T> extends Serializable {
	double evaluate(T data) throws Exception;
}
