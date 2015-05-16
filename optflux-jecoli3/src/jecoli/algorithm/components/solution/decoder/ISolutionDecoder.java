package jecoli.algorithm.components.solution.decoder;

import jecoli.algorithm.components.IDeepCopy;
import jecoli.algorithm.components.representation.IRepresentation;

public interface ISolutionDecoder<T extends IRepresentation,E> extends IDeepCopy {
	E decode(T representation) throws Exception;
}
