package jecoli.algorithm.multiobjective.archive.plotting;

import jecoli.algorithm.components.representation.IRepresentation;
import jecoli.algorithm.components.solution.ISolutionSet;
import jecoli.algorithm.multiobjective.archive.components.IArchiveManagementFunction;

public interface IPlotter<T extends IRepresentation> extends IArchiveManagementFunction {
	
	void plot(double[][] data);
	
	void plot(ISolutionSet<T> solutionSet);

}
