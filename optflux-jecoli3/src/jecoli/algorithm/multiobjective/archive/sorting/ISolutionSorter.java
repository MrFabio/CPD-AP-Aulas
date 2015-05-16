package jecoli.algorithm.multiobjective.archive.sorting;

import jecoli.algorithm.components.representation.IRepresentation;
import jecoli.algorithm.components.solution.ISolution;

public interface ISolutionSorter<T extends IRepresentation> {
	
	public void sort(ISolution<T> solution);

}
