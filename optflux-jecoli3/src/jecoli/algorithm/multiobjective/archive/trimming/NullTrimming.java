package jecoli.algorithm.multiobjective.archive.trimming;

import jecoli.algorithm.components.representation.IRepresentation;
import jecoli.algorithm.components.solution.ISolutionSet;
import jecoli.algorithm.multiobjective.archive.components.AMFunctionType;

public class NullTrimming<T extends IRepresentation> implements ITrimmingFunction<T> {

	@Override
	public ISolutionSet<T> trimm(ISolutionSet<T> original) {
		return original;
	}

	@Override
	public AMFunctionType getFunctionType() {
		return AMFunctionType.TRIMMER;
	}

}
