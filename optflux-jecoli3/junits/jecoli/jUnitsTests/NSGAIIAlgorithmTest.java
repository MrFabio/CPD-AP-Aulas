package jecoli.jUnitsTests;

import jecoli.algorithm.components.configuration.InvalidConfigurationException;
import jecoli.algorithm.components.evaluationfunction.IEvaluationFunction;
import jecoli.algorithm.components.operator.container.ReproductionOperatorContainer;
import jecoli.algorithm.components.operator.reproduction.linear.BitFlipMutation;
import jecoli.algorithm.components.operator.reproduction.linear.OnePointCrossover;
import jecoli.algorithm.components.operator.selection.TournamentSelection2;
import jecoli.algorithm.components.representation.binary.BinaryRepresentationFactory;
import jecoli.algorithm.components.representation.linear.ILinearRepresentation;
import jecoli.algorithm.components.terminationcriteria.ITerminationCriteria;
import jecoli.algorithm.components.terminationcriteria.InvalidNumberOfIterationsException;
import jecoli.algorithm.components.terminationcriteria.IterationTerminationCriteria;
import jecoli.algorithm.multiobjective.nsgaII.NSGAII;
import jecoli.algorithm.multiobjective.nsgaII.NSGAIIConfiguration;
import jecoli.algorithm.singleobjective.evolutionary.RecombinationParameters;
import jecoli.test.multiobjective.countones.CountOnesMOEvaluationFunction;


public class NSGAIIAlgorithmTest extends AbstractAlgorithmTest<ILinearRepresentation<Boolean>,BinaryRepresentationFactory>{

	public NSGAIIAlgorithmTest(boolean isMaximization)
	{
		this.testName = "NSGAIIAlgorithm";
		this.isMaximization = isMaximization;
	}
	
	protected void setCountingOnesConfigure(boolean isMaximization)
	{
		try {
			
			NSGAIIConfiguration<ILinearRepresentation<Boolean>,BinaryRepresentationFactory> algorithmConfiguration = new NSGAIIConfiguration<ILinearRepresentation<Boolean>,BinaryRepresentationFactory>();
			
			algorithmConfiguration.setRandomNumberGenerator(randomNumberGenerator);
			
			IEvaluationFunction<ILinearRepresentation<Boolean>> evaluationFunction = new CountOnesMOEvaluationFunction<ILinearRepresentation<Boolean>>(true);
			algorithmConfiguration.setEvaluationFunction(evaluationFunction);
			
			int solutionSize = 3;
			BinaryRepresentationFactory solFactory = new BinaryRepresentationFactory(solutionSize,2);
			algorithmConfiguration.setSolutionFactory(solFactory);
			
			algorithmConfiguration.setPopulationSize(this.populationSize);
			
			ITerminationCriteria terminationCriteria = new IterationTerminationCriteria(this.numberOfGenerations);
			algorithmConfiguration.setTerminationCriteria(terminationCriteria);

			RecombinationParameters recombinationParameters = new RecombinationParameters(0,populationSize,0,true);
			algorithmConfiguration.setRecombinationParameters(recombinationParameters);

			algorithmConfiguration.setSelectionOperator(new TournamentSelection2<ILinearRepresentation<Boolean>>(1,2,randomNumberGenerator));
			
			ReproductionOperatorContainer reproductionOperatorContainer = new ReproductionOperatorContainer();
			reproductionOperatorContainer.addOperator(0.5,new OnePointCrossover<Boolean>());
			reproductionOperatorContainer.addOperator(0.5,new BitFlipMutation(1));
			algorithmConfiguration.setReproductionOperatorContainer(reproductionOperatorContainer);
			
			algorithm = new NSGAII<ILinearRepresentation<Boolean>,BinaryRepresentationFactory>(algorithmConfiguration);
			
		}
		catch (InvalidConfigurationException e) {e.printStackTrace();}
		catch (InvalidNumberOfIterationsException e) {e.printStackTrace();} 
		catch (Exception e) {e.printStackTrace();} 
		
	}
	
	@Override
	protected void specificAlgorithmTests() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTestValues() {
		setCountingOnesConfigure(this.isMaximization);
		
	}

}
