package jecoli.jUnitsTests;

import java.util.ArrayList;

import jecoli.algorithm.components.algorithm.writer.IAlgorithmResultWriter;
import jecoli.algorithm.components.configuration.InvalidConfigurationException;
import jecoli.algorithm.components.evaluationfunction.IEvaluationFunction;
import jecoli.algorithm.components.representation.linear.ILinearRepresentation;
import jecoli.algorithm.components.representation.real.RealValueRepresentationFactory;
import jecoli.algorithm.components.solution.SolutionSet;
import jecoli.algorithm.components.statistics.StatisticsConfiguration;
import jecoli.algorithm.components.terminationcriteria.ITerminationCriteria;
import jecoli.algorithm.components.terminationcriteria.InvalidNumberOfIterationsException;
import jecoli.algorithm.components.terminationcriteria.IterationTerminationCriteria;
import jecoli.algorithm.singleobjective.differentialevolution.BaseVectorSelectionType;
import jecoli.algorithm.singleobjective.differentialevolution.DifferentialEvolution;
import jecoli.algorithm.singleobjective.differentialevolution.DifferentialEvolutionConfiguration;
import jecoli.jUnitsTests.randomnumbergenerator.DummyRandomNumberGenerator;
import jecoli.test.numericalopt.NumericalOptimizationEvaluation;


public class DifferentialEvolutionAlgorithmTest extends AbstractAlgorithmTest<ILinearRepresentation<Double>, RealValueRepresentationFactory>{

	public DifferentialEvolutionAlgorithmTest()
	{
		this.testName = "DifferentialEvolutionAlgorithm";
		this.randomNumberGenerator = new DummyRandomNumberGenerator();
		this.isMaximization = false;
	}
	
	@Override
	protected void specificAlgorithmTests() {
	}
	
	
	protected void setCountingOnesConfigure()
	{
		try {
			
			DifferentialEvolutionConfiguration algorithmConfiguration = new DifferentialEvolutionConfiguration();
		
			IEvaluationFunction<ILinearRepresentation<Double>> evaluationFunction = new NumericalOptimizationEvaluation(18,1);
			algorithmConfiguration.setEvaluationFunction(evaluationFunction);
			
			//algorithmConfiguration.setInitialPopulation(new SolutionSet<ILinearRepresentation<Double>>(initialPopulation));
			
			algorithmConfiguration.setRandomNumberGenerator(this.randomNumberGenerator);
			algorithmConfiguration.setProblemBaseDirectory("nullDirectory");
			algorithmConfiguration.setAlgorithmStateFile("nullFile");
			algorithmConfiguration.setSaveAlgorithmStateDirectoryPath("nullDirectory");
			algorithmConfiguration.setPopulationSize(populationSize);
			algorithmConfiguration.setAlgorithmResultWriterList(new ArrayList<IAlgorithmResultWriter<ILinearRepresentation<Double>>>());
			algorithmConfiguration.setStatisticsConfiguration(new StatisticsConfiguration());
			
			RealValueRepresentationFactory solFactory = new RealValueRepresentationFactory(4, 0.0, 10.0);
			algorithmConfiguration.setSolutionFactory(solFactory);			
			
			BaseVectorSelectionType baseVecSelType = BaseVectorSelectionType.RANDOM;
			algorithmConfiguration.setBaseVectorSelectionType(baseVecSelType);
			
			algorithmConfiguration.setInitialPopulation(new SolutionSet<ILinearRepresentation<Double>>(initialPopulation));
			
			ITerminationCriteria terminationCriteria = new IterationTerminationCriteria(this.numberOfGenerations);
			algorithmConfiguration.setTerminationCriteria(terminationCriteria);
			
			BaseVectorSelectionType baseVectorSelectionType = BaseVectorSelectionType.RANDOM;
			algorithmConfiguration.setBaseVectorSelectionType(baseVectorSelectionType);
			
			algorithmConfiguration.setScaleFactorF(0.6);
			algorithmConfiguration.setCrossoverProbability(0.5);
			
			this.algorithm = new DifferentialEvolution(algorithmConfiguration);
		}
		catch (InvalidNumberOfIterationsException e) {e.printStackTrace();}
		catch (InvalidConfigurationException e) {e.printStackTrace();}
		catch (Exception e) {e.printStackTrace();} 
		
	}
	

	@Override
	public void setTestValues() {
		setCountingOnesConfigure();
		
	}

	
}
