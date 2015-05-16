package jecoli.jUnitsTests;

import java.util.List;

import jecoli.algorithm.components.randomnumbergenerator.IRandomNumberGenerator;
import jecoli.algorithm.components.representation.IRepresentation;
import jecoli.algorithm.components.solution.ISolution;
import jecoli.algorithm.components.solution.ISolutionFactory;
import jecoli.jUnitsTests.randomnumbergenerator.DummyRandomNumberGenerator;

public abstract class AbstractTestClass<T extends IRepresentation,F extends ISolutionFactory<T>> implements ITest<T,F> {
	
	protected List<ISolution<T>> expectedSolutions;
	protected List<ISolution<T>> initialPopulation;
	protected IRandomNumberGenerator randomNumberGenerator;
	
	protected String testName;
	protected String testFileName;
	
	public AbstractTestClass()
	{
		this.randomNumberGenerator = new DummyRandomNumberGenerator();
	}
	
	@Override
	public void setInitialPopulation(List<ISolution<T>> initialPopulation) {
		this.initialPopulation = initialPopulation;
	}
	
	@Override
	public void setExpectedSolutions(List<ISolution<T>> expectedSolution) {
		this.expectedSolutions = expectedSolution;
	}
	
	@Override
	public void setTestFileName(String testFileName) {
		this.testFileName = testFileName;
	}
	
	@Override
	public String getName() {
		return testName;
	}
	
	@Override
	public String getTestFileName()
	{
		return testFileName;
	}

	@Override
	public IRandomNumberGenerator getRandomNumberGenerator() {
		return randomNumberGenerator;
	}

	@Override
	public void setRandomNumberGenerator(IRandomNumberGenerator randomNumberGenerator) {
		this.randomNumberGenerator = randomNumberGenerator;
	}

	@Override
	public void setTestName(String testeName) {
		this.testName = testeName;
	}

	@Override
	public String getTestName() {
		return testName;
	}

	
	
	
}
