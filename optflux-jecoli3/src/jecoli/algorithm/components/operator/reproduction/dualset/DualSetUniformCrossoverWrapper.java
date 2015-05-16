package jecoli.algorithm.components.operator.reproduction.dualset;


import jecoli.algorithm.components.operator.reproduction.set.SetUniformCrossover;
import jecoli.algorithm.components.randomnumbergenerator.IRandomNumberGenerator;
import jecoli.algorithm.components.representation.dualset.DualSetRepresentation;
import jecoli.algorithm.components.representation.dualset.DualSetRepresentationFactory;
import jecoli.algorithm.components.representation.integer.IntegerSetRepresentationFactory;
import jecoli.algorithm.components.representation.set.ISetRepresentation;
import jecoli.algorithm.components.representation.set.ISetRepresentationFactory;
import jecoli.algorithm.components.representation.set.SetRepresentation;
import jecoli.algorithm.components.solution.ISolution;
import jecoli.algorithm.components.solution.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;

/**
 * Created by ptiago on 14-01-2015.
 */
public class DualSetUniformCrossoverWrapper  extends AbstractDualSetCrossoverOperator {
    SetUniformCrossover<Integer> setUniformCrossover;
    List<Integer> genomeApplicationList;

    public DualSetUniformCrossoverWrapper(List<Integer> genomeApplicationList,List<IntegerSetRepresentationFactory> representationSolutionFactoryVector) {
        this.genomeApplicationList = genomeApplicationList;
        setUniformCrossover = new SetUniformCrossover<>();
        this.representationSolutionFactoryVector = representationSolutionFactoryVector;
    }

    @Override
    public void crossoverGenomes(DualSetRepresentation parentGenome1, DualSetRepresentation parentGenome2, DualSetRepresentation childGenome1, DualSetRepresentation childGenome2, DualSetRepresentationFactory solutionFactory, IRandomNumberGenerator randomNumberGenerator) {

        for(Integer individualIndex:genomeApplicationList){
            List<ISolution<ISetRepresentation<Integer>>> newSolutionList = new ArrayList<>();
            try {
                TreeSet<Integer> parent1SetRepresentation = parentGenome1.getIndividualRepresentation(individualIndex);
                TreeSet<Integer> parent2SetRepresentation = parentGenome2.getIndividualRepresentation(individualIndex);
                newSolutionList.add(new Solution<ISetRepresentation<Integer>>(new SetRepresentation<Integer>(parent1SetRepresentation)));
                newSolutionList.add(new Solution<ISetRepresentation<Integer>>(new SetRepresentation<Integer>(parent2SetRepresentation)));
                IntegerSetRepresentationFactory setSolutionFactory =  getSetSolutionFactory(individualIndex);
                List<ISolution<ISetRepresentation<Integer>>> newChildGenomeList = setUniformCrossover.apply(newSolutionList,setSolutionFactory,randomNumberGenerator);
                copyChildSolutions(individualIndex,childGenome1,newChildGenomeList.get(0).getRepresentation().getGenome());
                copyChildSolutions(individualIndex,childGenome2,newChildGenomeList.get(1).getRepresentation().getGenome());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }




    @Override
    public DualSetUniformCrossover deepCopy() throws Exception {
        return new DualSetUniformCrossover();
    }
}
