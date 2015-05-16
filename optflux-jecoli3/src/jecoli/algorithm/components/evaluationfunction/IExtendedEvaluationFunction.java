package jecoli.algorithm.components.evaluationfunction;

import jecoli.algorithm.components.representation.IRepresentation;

public interface IExtendedEvaluationFunction<S extends IRepresentation, E> extends IEvaluationFunction<S>{

	public abstract IRepresentationDecoder<S, E> getDecoder();

	public abstract IFitnessFunctionEvaluator<E> getFitnessFunctionEvaluator();

}