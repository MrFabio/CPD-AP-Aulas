package jecoli.jUnitsTests;

import java.util.ArrayList;

import jecoli.algorithm.components.operator.reproduction.set.SetGrowthMutation;
import jecoli.algorithm.components.representation.integer.IntegerSetRepresentationFactory;
import jecoli.algorithm.components.representation.set.ISetRepresentation;
import jecoli.algorithm.components.representation.set.ISetRepresentationFactory;
import jecoli.algorithm.components.solution.ISolution;


public class SetGrowthMutationTest extends AbstractReproductionOperatorTest<ISetRepresentation<Integer>, ISetRepresentationFactory<Integer>> {
	
	public SetGrowthMutationTest()
	{
		this.testName="SetGrowthMutation";
		
		this.expectedSolutions = new ArrayList<ISolution<ISetRepresentation<Integer>>>();
		this.initialPopulation = new ArrayList<ISolution<ISetRepresentation<Integer>>>();
	}

	@Override
	public void setTestValues() {
		this.solutionFactory = new IntegerSetRepresentationFactory(this.size);
		this.operator = new SetGrowthMutation<Integer>(1);
	}

	@Override
	protected void specificOperatorTests() {
		// TODO Auto-generated method stub

	}

}
