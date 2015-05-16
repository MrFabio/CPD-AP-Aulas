package jecoli.jUnitsTests;

import java.util.ArrayList;

import jecoli.algorithm.components.operator.reproduction.linear.GaussianPerturbationMutation;
import jecoli.algorithm.components.representation.linear.ILinearRepresentation;
import jecoli.algorithm.components.representation.real.RealValueRepresentationFactory;
import jecoli.algorithm.components.solution.ISolution;



public class GaussianPerturbationMutationTest extends AbstractReproductionOperatorTest<ILinearRepresentation<Double>, RealValueRepresentationFactory>{

	protected Double upperBound;
	protected Double lowerBound;
	
	public GaussianPerturbationMutationTest()
	{
		this.testName = "GaussianPerturbationMutation";
		
		this.expectedSolutions = new ArrayList<ISolution<ILinearRepresentation<Double>>>();
		this.initialPopulation = new ArrayList<ISolution<ILinearRepresentation<Double>>>();	
	}
	
		
	@Override
	protected void specificOperatorTests() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setTestValues() {
		this.solutionFactory = new RealValueRepresentationFactory(this.size, this.lowerBound, this.upperBound);
		this.operator = new GaussianPerturbationMutation(size);
	}

	public Double getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(double upperBound) {
		this.upperBound = upperBound;
	}

	public Double getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(double lowerBound) {
		this.lowerBound = lowerBound;
	}


}
