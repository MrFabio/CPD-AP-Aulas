package jecoli.algorithm.components.operator.reproduction.dualset;

import jecoli.algorithm.components.operator.IReproductionOperator;
import jecoli.algorithm.components.operator.reproduction.set.AbstractSetMutationOperator;
import jecoli.algorithm.components.operator.reproduction.set.SetRandomMutation;
import jecoli.algorithm.components.randomnumbergenerator.IRandomNumberGenerator;
import jecoli.algorithm.components.representation.dualset.DualSetRepresentation;
import jecoli.algorithm.components.representation.dualset.DualSetRepresentationFactory;
import jecoli.algorithm.components.representation.integer.IntegerSetRepresentationFactory;
import jecoli.algorithm.components.representation.set.ISetRepresentation;
import jecoli.algorithm.components.representation.set.SetRepresentation;
import jecoli.algorithm.components.solution.ISolution;
import jecoli.algorithm.components.solution.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;

/**
 * Created by ptiago on 15-01-2015.
 */
public class DualSetMutationWrapper extends AbstractDualSetMutationOperator {
    protected AbstractSetMutationOperator setMutation;
    protected List<Integer> genomeApplicationList;


    public DualSetMutationWrapper(AbstractSetMutationOperator setMutation, List<Integer> genomeApplicationList,List<IntegerSetRepresentationFactory> representationSolutionFactoryVector) {
        this.setMutation = setMutation;
        this.genomeApplicationList = genomeApplicationList;
        this.representationSolutionFactoryVector = representationSolutionFactoryVector;
    }

    @Override
    protected void mutateGenome(DualSetRepresentation childGenome, DualSetRepresentationFactory solutionFactory, IRandomNumberGenerator randomNumberGenerator) {
        for(Integer individualIndex:genomeApplicationList){
            List<ISolution<ISetRepresentation<Integer>>> newSolutionList = new ArrayList<>();
            try {
                TreeSet<Integer> parent1SetRepresentation = childGenome.getIndividualRepresentation(individualIndex);
                newSolutionList.add(new Solution<ISetRepresentation<Integer>>(new SetRepresentation<Integer>(parent1SetRepresentation)));
                IntegerSetRepresentationFactory setSolutionFactory =  getSetSolutionFactory(individualIndex);
                List<ISolution<ISetRepresentation<Integer>>> newChildGenomeList = setMutation.apply(newSolutionList,setSolutionFactory,randomNumberGenerator);
                copyChildSolutions(individualIndex,childGenome,newChildGenomeList);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public IReproductionOperator<DualSetRepresentation, DualSetRepresentationFactory> deepCopy() throws Exception {
        return new DualSetMutationWrapper((AbstractSetMutationOperator) setMutation.deepCopy(),genomeApplicationList,representationSolutionFactoryVector);
    }
}
