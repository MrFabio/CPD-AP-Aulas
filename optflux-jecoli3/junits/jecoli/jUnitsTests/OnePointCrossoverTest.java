package jecoli.jUnitsTests;

import java.util.ArrayList;

import jecoli.algorithm.components.operator.reproduction.linear.OnePointCrossover;
import jecoli.algorithm.components.representation.binary.BinaryRepresentationFactory;
import jecoli.algorithm.components.representation.linear.ILinearRepresentation;
import jecoli.algorithm.components.representation.linear.ILinearRepresentationFactory;
import jecoli.algorithm.components.solution.ISolution;



public class OnePointCrossoverTest extends AbstractReproductionOperatorTest<ILinearRepresentation<Boolean>, ILinearRepresentationFactory<Boolean>>{
	
	public OnePointCrossoverTest()
	{
		this.testName = "OnePointCrossover";
		
		this.expectedSolutions = new ArrayList<ISolution<ILinearRepresentation<Boolean>>>();
		this.initialPopulation = new ArrayList<ISolution<ILinearRepresentation<Boolean>>>();
	}
	
	@Override
	public void setTestValues() {		
		this.solutionFactory = new BinaryRepresentationFactory(this.size);
		this.operator = new OnePointCrossover<Boolean>();
	}

	@Override
	protected void specificOperatorTests() {
		// TODO Auto-generated method stub
		
	}
	
}
