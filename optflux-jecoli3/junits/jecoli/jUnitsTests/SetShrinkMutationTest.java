package jecoli.jUnitsTests;

import java.util.ArrayList;

import jecoli.algorithm.components.operator.reproduction.set.SetShrinkMutation;
import jecoli.algorithm.components.representation.integer.IntegerSetRepresentationFactory;
import jecoli.algorithm.components.representation.set.ISetRepresentation;
import jecoli.algorithm.components.representation.set.ISetRepresentationFactory;
import jecoli.algorithm.components.solution.ISolution;


public class SetShrinkMutationTest extends AbstractReproductionOperatorTest<ISetRepresentation<Integer>, ISetRepresentationFactory<Integer>>{
	
	public SetShrinkMutationTest()
	{
		this.testName="SetShrinkMutation";
		
		this.expectedSolutions = new ArrayList<ISolution<ISetRepresentation<Integer>>>();
		this.initialPopulation = new ArrayList<ISolution<ISetRepresentation<Integer>>>();
	}

	@Override
	public void setTestValues() {
		this.solutionFactory = new IntegerSetRepresentationFactory(this.size);
		this.operator = new SetShrinkMutation<Integer>();
	}

	@Override
	protected void specificOperatorTests() {
		// TODO Auto-generated method stub
		
	}

}
