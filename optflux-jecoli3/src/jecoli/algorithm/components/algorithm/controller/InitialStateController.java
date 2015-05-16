package jecoli.algorithm.components.algorithm.controller;

import jecoli.algorithm.components.algorithm.AlgorithmState;
import jecoli.algorithm.components.configuration.IConfiguration;
import jecoli.algorithm.components.operator.IOperator;
import jecoli.algorithm.components.operator.IReproductionOperator;
import jecoli.algorithm.components.operator.container.IOperatorContainer;
import jecoli.algorithm.components.representation.IRepresentation;
import jecoli.algorithm.components.solution.ISolution;
import jecoli.algorithm.components.solution.ISolutionFactory;
import jecoli.algorithm.components.solution.ISolutionSet;
import jecoli.algorithm.singleobjective.evolutionary.EvolutionaryConfiguration;

/**
 * Created by ptiago on 17-10-2014.
 */
public class InitialStateController<T extends IRepresentation,F extends ISolutionFactory<T>,S extends EvolutionaryConfiguration<T,F>> extends AlgorithmController<T,S> {
    protected IOperatorContainer<IReproductionOperator<T,F>> initialOperatorContainer;
    protected IOperatorContainer<IReproductionOperator<T,F>> algorithmOperatorContainer;
    protected int shiftIterationNumber = 0;
    public InitialStateController(IOperatorContainer<IReproductionOperator<T,F>> initialOperatorContainer,IOperatorContainer<IReproductionOperator<T,F>> algorithmOperatorContainer){
        this.initialOperatorContainer = initialOperatorContainer;
        this.algorithmOperatorContainer = algorithmOperatorContainer;
    }

    protected boolean verifyAllSolutionsAreZero(ISolutionSet<T> currentSolutionSet){
        for(int i = 0;i < currentSolutionSet.getNumberOfSolutions();i++){
            ISolution<T> solution = currentSolutionSet.getSolution(i);
            double fitnessValue = solution.getScalarFitnessValue();
            if(fitnessValue != 0)
                return false;
        }
        return true;
    }

    public S processAlgorithmState(AlgorithmState<T> algorithmState,S configuration){
        ISolutionSet<T> currentSolutionSet = algorithmState.getSolutionSet();

        if(verifyAllSolutionsAreZero(currentSolutionSet))
           configuration.setReproductionOperatorContainer(initialOperatorContainer);
        else{ configuration.setReproductionOperatorContainer(algorithmOperatorContainer);
            if(shiftIterationNumber == 0)
                shiftIterationNumber = algorithmState.getCurrentIteration()-1;}

        return configuration;
    }

    public int getShiftIterationNumber() {
        int newShiftIterationNumber = shiftIterationNumber;
        shiftIterationNumber = 0;
        return newShiftIterationNumber;
    }
}
