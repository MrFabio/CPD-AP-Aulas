package jecoli.algorithm.multiobjective.archive.sorting;

import java.util.Arrays;

import jecoli.algorithm.components.representation.IRepresentation;
import jecoli.algorithm.components.solution.ISolution;

public class SolutionRepresentationSorter<T extends IRepresentation> implements ISolutionSorter<T>{
	
	public SolutionRepresentationSorter(){
		
	}
	
	@Override
	public void sort(ISolution<T> solution) {	
		Arrays.sort(solution.getRepresentation().stringRepresentation().toCharArray());
	}

}
