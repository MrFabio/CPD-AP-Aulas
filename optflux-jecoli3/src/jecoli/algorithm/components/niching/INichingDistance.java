package jecoli.algorithm.components.niching;

import jecoli.algorithm.components.representation.IRepresentation;

/**
 * Created by ptiago on 05-01-2015.
 */
public interface INichingDistance<R extends IRepresentation> {
    double computeDistance(R individualRepresentationI, R individualRepresentationJ);
}
