package jecoli.algorithm.components.operator.container;

import jecoli.algorithm.components.operator.IOperator;

public interface IOperatorContainerCell<T extends IOperator> {
	T getOperator();
	void setOperator(T operator);
	double getProbability();
	void setProbability(double probability);
}
