package jecoli.algorithm.components.operator.reproduction;

import jecoli.algorithm.components.operator.*;
import jecoli.algorithm.components.randomnumbergenerator.IRandomNumberGenerator;
import jecoli.algorithm.components.representation.IRepresentation;
import jecoli.algorithm.components.solution.ISolution;
import jecoli.algorithm.components.solution.ISolutionFactory;

import java.util.List;

/**
 * Created by ptiago on 20-11-2014.
 */
public class IncreasedDiversityCrossover<T extends IRepresentation,S extends ISolutionFactory<T>> implements IReproductionOperator<T,S> {
    protected IReproductionOperator<T,S> defaultOperator;
    protected IReproductionOperator<T,S> secondaryOperator;

    public IncreasedDiversityCrossover(IReproductionOperator<T,S> defaultOperator,IReproductionOperator<T,S> secondaryOperator){
        this.defaultOperator = defaultOperator;
        this.secondaryOperator = secondaryOperator;
    }
    @Override
    public List<ISolution<T>> apply(List<ISolution<T>> selectedSolutions, S solutionFactory, IRandomNumberGenerator randomNumberGenerator) throws InvalidNumberOfInputSolutionsException, InvalidNumberOfOutputSolutionsException {
        boolean areSolutionsEqual = true;
        double currentFitness = selectedSolutions.get(0).getScalarFitnessValue();
        for(int i = 0;i < selectedSolutions.size();i++){
            ISolution<T> currentSoltuion = selectedSolutions.get(i);
            if(currentFitness != currentSoltuion.getScalarFitnessValue()){
                areSolutionsEqual = false;
                break;
            }
        }
        if(areSolutionsEqual)
            return secondaryOperator.apply(selectedSolutions,solutionFactory,randomNumberGenerator);
        return defaultOperator.apply(selectedSolutions,solutionFactory,randomNumberGenerator);
    }

    @Override
    public int getNumberOfInputSolutions() {
        return defaultOperator.getNumberOfInputSolutions();
    }

    @Override
    public int getNumberOfOutputSolutions() {
        return defaultOperator.getNumberOfOutputSolutions();
    }

    @Override
    public ReproductionOperatorType getReproductionType() {
        return defaultOperator.getReproductionType();
    }

    @Override
    public IReproductionOperator<T, S> deepCopy() throws Exception {
        return new IncreasedDiversityCrossover(defaultOperator,secondaryOperator);
    }


}
