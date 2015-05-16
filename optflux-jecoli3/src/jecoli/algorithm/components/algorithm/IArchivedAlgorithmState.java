package jecoli.algorithm.components.algorithm;

import jecoli.algorithm.components.representation.IRepresentation;
import jecoli.algorithm.components.solution.ISolutionSet;

public interface IArchivedAlgorithmState<T extends IRepresentation> {
	
	ISolutionSet<T> getArchive();
	
	void updateArchiveState(ISolutionSet<T> newArchive);

}
