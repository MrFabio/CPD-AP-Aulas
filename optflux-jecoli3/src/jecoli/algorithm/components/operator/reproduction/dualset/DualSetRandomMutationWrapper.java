package jecoli.algorithm.components.operator.reproduction.dualset;

import jecoli.algorithm.components.operator.IReproductionOperator;
import jecoli.algorithm.components.operator.reproduction.set.SetRandomMutation;
import jecoli.algorithm.components.operator.reproduction.set.SetUniformCrossover;
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

/**
 * Created by ptiago on 15-01-2015.
 */
public class DualSetRandomMutationWrapper extends AbstractDualSetMutationOperator {


    @Override
    protected void mutateGenome(DualSetRepresentation childGenome, DualSetRepresentationFactory solutionFactory, IRandomNumberGenerator randomNumberGenerator) {

    }

    @Override
    public IReproductionOperator<DualSetRepresentation, DualSetRepresentationFactory> deepCopy() throws Exception {
        return null;
    }
}
