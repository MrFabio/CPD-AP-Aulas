package jecoli.algorithm.components.operator;

import jecoli.algorithm.components.IDeepCopy;


public interface IOperator extends IDeepCopy{
	
	IOperator deepCopy() throws Exception;
}
