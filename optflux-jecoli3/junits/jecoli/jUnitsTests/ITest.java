package jecoli.jUnitsTests;

import java.util.List;

import jecoli.algorithm.components.randomnumbergenerator.IRandomNumberGenerator;
import jecoli.algorithm.components.representation.IRepresentation;
import jecoli.algorithm.components.solution.ISolution;
import jecoli.algorithm.components.solution.ISolutionFactory;

import org.junit.Test;



public interface ITest<T extends IRepresentation,F extends ISolutionFactory<T>> { 
	
	@Test
	void test();
	void validate() throws Exception;
	void deepcopyTest() throws Exception;
	
	void setInitialPopulation(List<ISolution<T>> initialPopulation);
	void setExpectedSolutions(List<ISolution<T>> expectedSolution);
	void setTestValues();
	void setTestFileName(String testFileName);
	void setRandomNumberGenerator(IRandomNumberGenerator randomNumberGenerator);
	void setTestName(String testeName);
	
	IRandomNumberGenerator getRandomNumberGenerator();
	String getName();
	String getTestFileName();
	String getTestName();
}
