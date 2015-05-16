package jecoli.test.multiobjective.kursawe;

import jecoli.algorithm.components.algorithm.IAlgorithm;
import jecoli.algorithm.components.algorithm.IAlgorithmResult;
import jecoli.algorithm.components.configuration.InvalidConfigurationException;
import jecoli.algorithm.components.evaluationfunction.IEvaluationFunction;
import jecoli.algorithm.components.operator.container.ReproductionOperatorContainer;
import jecoli.algorithm.components.operator.reproduction.linear.RealValueRandomMutation;
import jecoli.algorithm.components.operator.selection.EnvironmentalSelection;
import jecoli.algorithm.components.operator.selection.TournamentSelection;
import jecoli.algorithm.components.randomnumbergenerator.DefaultRandomNumberGenerator;
import jecoli.algorithm.components.representation.linear.ILinearRepresentation;
import jecoli.algorithm.components.representation.real.RealValueRepresentationFactory;
import jecoli.algorithm.components.statistics.StatisticsConfiguration;
import jecoli.algorithm.components.terminationcriteria.ITerminationCriteria;
import jecoli.algorithm.components.terminationcriteria.InvalidNumberOfIterationsException;
import jecoli.algorithm.components.terminationcriteria.IterationTerminationCriteria;
import jecoli.algorithm.multiobjective.MOSA.MOSA;
import jecoli.algorithm.multiobjective.MOSA.MOSAConfiguration;
import jecoli.algorithm.multiobjective.archive.plotting.Plot2DGUI;
import jecoli.algorithm.singleobjective.evolutionary.RecombinationParameters;
import jecoli.algorithm.singleobjective.simulatedannealing.AnnealingSchedule;

public class KursaweMOSATest {


	public static void main(String[] args) {
		try {
			
			MOSAConfiguration<ILinearRepresentation<Double>,RealValueRepresentationFactory> configuration = new MOSAConfiguration<ILinearRepresentation<Double>,RealValueRepresentationFactory>();
			configuration.setStatisticsConfiguration(new StatisticsConfiguration());
			
			configuration.setRandomNumberGenerator(new DefaultRandomNumberGenerator());
			
			IEvaluationFunction<ILinearRepresentation<Double>> evaluationFunction = new KursaweEvaluationFunction<ILinearRepresentation<Double>>(true);
			configuration.setEvaluationFunction(evaluationFunction);
			
			int solutionSize = 3;
			int numobjectives = 2;
			RealValueRepresentationFactory solutionFactory = new RealValueRepresentationFactory(solutionSize,-5.0,5.0,numobjectives);
			configuration.setSolutionFactory(solutionFactory);
			
			AnnealingSchedule as = new AnnealingSchedule(0.1, 0.00001, 50, 10000);
			configuration.setAnnealingSchedule(as);
			
			int populationSize = 100;
			int maximumArchiveSize = 100;
			configuration.setInitialPopulationSize(populationSize);
			configuration.setMaximumArchiveSize(maximumArchiveSize);

			int numberGenerations = 300;
			ITerminationCriteria terminationCriteria;
			terminationCriteria = new IterationTerminationCriteria(numberGenerations);
			configuration.setTerminationCriteria(terminationCriteria);
			
			RecombinationParameters recombinationParameters = new RecombinationParameters(0,populationSize,0,true);
			configuration.setRecombinationParameters(recombinationParameters);
			
			configuration.setSelectionOperator(new TournamentSelection<ILinearRepresentation<Double>>(1,2));
			configuration.setEnvironmentalSelectionOperator(new EnvironmentalSelection<ILinearRepresentation<Double>>());
			
			ReproductionOperatorContainer mutationOperatorContainer = new ReproductionOperatorContainer();
//			mutationOperatorContainer.addOperator(0.5,new LinearGenomeRandomMutation<Double>(1));
			mutationOperatorContainer.addOperator(1.0,new RealValueRandomMutation(1));
			configuration.setMutationOperatorsContainer(mutationOperatorContainer);
			
			IAlgorithm<ILinearRepresentation<Double>> algorithm = new MOSA<ILinearRepresentation<Double>,RealValueRepresentationFactory>(configuration);
			

			Plot2DGUI<ILinearRepresentation<Double>> plotter = new Plot2DGUI<ILinearRepresentation<Double>>(algorithm);
			plotter.setObserveArchive(true);
			plotter.run();

			IAlgorithmResult<ILinearRepresentation<Double>> result =  algorithm.run();
			
			
			
		} catch (InvalidNumberOfIterationsException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
