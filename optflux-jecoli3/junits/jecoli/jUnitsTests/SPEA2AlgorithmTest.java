package jecoli.jUnitsTests;

import jecoli.algorithm.components.configuration.InvalidConfigurationException;
import jecoli.algorithm.components.evaluationfunction.IEvaluationFunction;
import jecoli.algorithm.components.operator.container.ReproductionOperatorContainer;
import jecoli.algorithm.components.operator.reproduction.linear.BitFlipMutation;
import jecoli.algorithm.components.operator.reproduction.linear.OnePointCrossover;
import jecoli.algorithm.components.operator.selection.EnvironmentalSelection;
import jecoli.algorithm.components.operator.selection.TournamentSelection;
import jecoli.algorithm.components.representation.binary.BinaryRepresentationFactory;
import jecoli.algorithm.components.representation.linear.ILinearRepresentation;
import jecoli.algorithm.components.statistics.StatisticsConfiguration;
import jecoli.algorithm.components.terminationcriteria.ITerminationCriteria;
import jecoli.algorithm.components.terminationcriteria.InvalidNumberOfIterationsException;
import jecoli.algorithm.components.terminationcriteria.IterationTerminationCriteria;
import jecoli.algorithm.multiobjective.spea2.SPEA2;
import jecoli.algorithm.multiobjective.spea2.SPEA2Configuration;
import jecoli.algorithm.singleobjective.evolutionary.RecombinationParameters;
import jecoli.test.multiobjective.countones.CountOnesMOEvaluationFunction;


public class SPEA2AlgorithmTest extends AbstractAlgorithmTest<ILinearRepresentation<Boolean>,BinaryRepresentationFactory>{

	public SPEA2AlgorithmTest()
	{
		this.testName = "SPEA2Algorithm";
	}
	
	@Override
	protected void specificAlgorithmTests() {
		// TODO Auto-generated method stub
		
	}
	
	protected void setCountingOnesConfigure(boolean isMaximization)
	{
		try {
			
			SPEA2Configuration<ILinearRepresentation<Boolean>,BinaryRepresentationFactory> algorithmConfiguration = new SPEA2Configuration<ILinearRepresentation<Boolean>,BinaryRepresentationFactory>();
			
			algorithmConfiguration.setStatisticsConfiguration(new StatisticsConfiguration());
			algorithmConfiguration.setRandomNumberGenerator(randomNumberGenerator);
			
			IEvaluationFunction<ILinearRepresentation<Boolean>> evaluationFunction = new CountOnesMOEvaluationFunction<ILinearRepresentation<Boolean>>(true);
			algorithmConfiguration.setEvaluationFunction(evaluationFunction);
			
			this.populationSize=3;
			BinaryRepresentationFactory solFactory = new BinaryRepresentationFactory(this.populationSize,2);
			algorithmConfiguration.setSolutionFactory(solFactory);
			
			this.populationSize = 3;
			int maximumArchiveSize = 3;
			algorithmConfiguration.setPopulationSize(populationSize);
			algorithmConfiguration.setMaximumArchiveSize(maximumArchiveSize);
			
			this.numberOfGenerations = 20;
			ITerminationCriteria terminationCriteria = new IterationTerminationCriteria(this.numberOfGenerations);
			algorithmConfiguration.setTerminationCriteria(terminationCriteria);

			RecombinationParameters recombinationParameters = new RecombinationParameters(0,populationSize,0,true);
			algorithmConfiguration.setRecombinationParameters(recombinationParameters);

			algorithmConfiguration.setSelectionOperator(new TournamentSelection<ILinearRepresentation<Boolean>>(1,2));
			algorithmConfiguration.setEnvironmentalSelectionOperator(new EnvironmentalSelection<ILinearRepresentation<Boolean>>());
			
			ReproductionOperatorContainer reproductionOperatorContainer = new ReproductionOperatorContainer();
			reproductionOperatorContainer.addOperator(0.5,new OnePointCrossover<Boolean>());
			reproductionOperatorContainer.addOperator(0.5,new BitFlipMutation(1));
			algorithmConfiguration.setReproductionOperatorContainer(reproductionOperatorContainer);
			
			this.algorithm = new SPEA2<ILinearRepresentation<Boolean>,BinaryRepresentationFactory>(algorithmConfiguration);
		}
		catch (InvalidConfigurationException e) {e.printStackTrace();}
		catch (InvalidNumberOfIterationsException e) {e.printStackTrace();} 
		catch (Exception e) {e.printStackTrace();} 
	}

	@Override
	public void setTestValues() {
		setCountingOnesConfigure(this.isMaximization);
		
	}

}
