package jecoli.algorithm.multiobjective.archive.trimming;

import jecoli.algorithm.components.representation.IRepresentation;
import jecoli.algorithm.components.solution.ISolutionSet;
import jecoli.algorithm.multiobjective.archive.components.IArchiveManagementFunction;

public interface ITrimmingFunction<T extends IRepresentation> extends IArchiveManagementFunction {
	
	ISolutionSet<T> trimm(ISolutionSet<T> original);
	

}
