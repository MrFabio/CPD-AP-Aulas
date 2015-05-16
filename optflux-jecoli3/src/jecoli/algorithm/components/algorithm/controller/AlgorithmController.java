package jecoli.algorithm.components.algorithm.controller;

import jecoli.algorithm.components.algorithm.AlgorithmState;
import jecoli.algorithm.components.configuration.IConfiguration;
import jecoli.algorithm.components.representation.IRepresentation;

/**
 * Created by ptiago on 16-10-2014.
 */
public class AlgorithmController<T extends IRepresentation, C extends IConfiguration<T>> {
    public C processInitialAlgorithmState(AlgorithmState<T> algorithmState,C configuration){
        return configuration;
    }

    public C processAlgorithmState(AlgorithmState<T> algorithmState,C configuration){
        return configuration;
    }
}
