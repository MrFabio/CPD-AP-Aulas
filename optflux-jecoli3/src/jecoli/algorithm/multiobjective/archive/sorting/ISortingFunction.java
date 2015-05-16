package jecoli.algorithm.multiobjective.archive.sorting;

import jecoli.algorithm.components.representation.IRepresentation;
import jecoli.algorithm.components.solution.ISolutionSet;
import jecoli.algorithm.multiobjective.archive.components.IArchiveManagementFunction;

public interface ISortingFunction<T extends IRepresentation> extends IArchiveManagementFunction {

	public void sort(ISolutionSet<T> original);
}
