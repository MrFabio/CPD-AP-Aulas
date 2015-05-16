package jecoli.jUnitsTests;

import java.util.ArrayList;

import jecoli.algorithm.components.operator.selection.RouletteWheelSelection;
import jecoli.algorithm.components.representation.linear.ILinearRepresentation;
import jecoli.algorithm.components.representation.linear.ILinearRepresentationFactory;
import jecoli.algorithm.components.solution.ISolution;

public class RouletteWheelSelectionTest extends AbstractSelectionOperatorTest<ILinearRepresentation<Boolean>, ILinearRepresentationFactory<Boolean>>{

	
	public RouletteWheelSelectionTest()
	{
		this.testName = "RouletteWheelSelection";
	
		this.expectedSolutions = new ArrayList<ISolution<ILinearRepresentation<Boolean>>>();
		this.initialPopulation = new ArrayList<ISolution<ILinearRepresentation<Boolean>>>();
	}


	@Override
	protected void specificOperatorTests() {
		
		
	}
		@Override
	public void setTestValues() {
		this.operator = new RouletteWheelSelection<ILinearRepresentation<Boolean>>();		
	}

}
