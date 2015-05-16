package jecoli.test.multiobjective.wrapper;

import jecoli.algorithm.components.algorithm.IAlgorithm;
import jecoli.algorithm.components.algorithm.IAlgorithmResult;
import jecoli.algorithm.components.algorithm.IAlgorithmStatistics;
import jecoli.algorithm.components.configuration.InvalidConfigurationException;
import jecoli.algorithm.components.evaluationfunction.AbstractMultiobjectiveEvaluationFunction;
import jecoli.algorithm.components.operator.container.ReproductionOperatorContainer;
import jecoli.algorithm.components.operator.reproduction.linear.GaussianPerturbationMutation;
import jecoli.algorithm.components.operator.reproduction.linear.LinearGenomeRandomMutation;
import jecoli.algorithm.components.operator.reproduction.linear.RealValueRandomMutation;
import jecoli.algorithm.components.randomnumbergenerator.DefaultRandomNumberGenerator;
import jecoli.algorithm.components.representation.linear.ILinearRepresentation;
import jecoli.algorithm.components.representation.real.RealValueRepresentationFactory;
import jecoli.algorithm.components.statistics.StatisticsConfiguration;
import jecoli.algorithm.components.terminationcriteria.ITerminationCriteria;
import jecoli.algorithm.components.terminationcriteria.InvalidNumberOfIterationsException;
import jecoli.algorithm.components.terminationcriteria.IterationTerminationCriteria;
import jecoli.algorithm.multiobjective.archive.aggregation.IAggregationFunction;
import jecoli.algorithm.multiobjective.archive.aggregation.SimpleAdditiveAggregation;
import jecoli.algorithm.multiobjective.archive.components.ArchiveManager;
import jecoli.algorithm.multiobjective.archive.components.InsertionStrategy;
import jecoli.algorithm.multiobjective.archive.components.ProcessingStrategy;
import jecoli.algorithm.multiobjective.archive.plotting.IPlotter;
import jecoli.algorithm.multiobjective.archive.plotting.Plot2DGUI;
import jecoli.algorithm.multiobjective.archive.trimming.ITrimmingFunction;
import jecoli.algorithm.multiobjective.archive.trimming.ZitzlerTruncation;
import jecoli.algorithm.singleobjective.simulatedannealing.AnnealingSchedule;
import jecoli.algorithm.singleobjective.simulatedannealing.SimulatedAnnealing;
import jecoli.algorithm.singleobjective.simulatedannealing.SimulatedAnnealingConfiguration;
import jecoli.test.multiobjective.kursawe.KursaweEvaluationFunction;

public class SAMOGenericTest {


	public static void main(String[] args) {
		try {
			
			SimulatedAnnealingConfiguration<ILinearRepresentation<Double>,RealValueRepresentationFactory> configuration = new SimulatedAnnealingConfiguration<ILinearRepresentation<Double>,RealValueRepresentationFactory>();
			configuration.setStatisticsConfiguration(new StatisticsConfiguration());
			configuration.setRandomNumberGenerator(new DefaultRandomNumberGenerator());
			configuration.setProblemBaseDirectory("nullDirectory");
			configuration.setAlgorithmStateFile("nullFile");
			configuration.setSaveAlgorithmStateDirectoryPath("nullDirectory");
			
			AbstractMultiobjectiveEvaluationFunction<ILinearRepresentation<Double>> evaluationFunction = new KursaweEvaluationFunction<ILinearRepresentation<Double>>(false);
			IAggregationFunction aggregation = new SimpleAdditiveAggregation();
			evaluationFunction.setFitnessAggregation(aggregation);
			configuration.setEvaluationFunction(evaluationFunction);
			configuration.setNumberOfObjectives(2);

			int solutionSize = 3;
			RealValueRepresentationFactory solutionFactory = new RealValueRepresentationFactory(solutionSize,-5.0,5.0,2);
			configuration.setSolutionFactory(solutionFactory);
			
			int maximumArchiveSize = 200;		

			AnnealingSchedule as = new AnnealingSchedule(1, 0.000001, 100, 200000);
			configuration.setAnnealingSchedule(as);
			
			int numberGenerations = 500;
			ITerminationCriteria terminationCriteria;
			terminationCriteria = new IterationTerminationCriteria(numberGenerations);
			configuration.setTerminationCriteria(terminationCriteria);	
			
			ReproductionOperatorContainer reproductionOperatorContainer = new ReproductionOperatorContainer();
			reproductionOperatorContainer.addOperator(0.5,new GaussianPerturbationMutation(0.2));
			reproductionOperatorContainer.addOperator(0.25,new LinearGenomeRandomMutation<Double>(0.1));
			reproductionOperatorContainer.addOperator(0.25,new RealValueRandomMutation(1));
			configuration.setMutationOperatorContainer(reproductionOperatorContainer);			
			
			IAlgorithm<ILinearRepresentation<Double>> algorithm = new SimulatedAnnealing<ILinearRepresentation<Double>,RealValueRepresentationFactory>(configuration);			
			
			ArchiveManager<Double, ILinearRepresentation<Double>> archive = new ArchiveManager<Double, ILinearRepresentation<Double>>(
					algorithm,
					InsertionStrategy.ADD_ON_SINGLE_EVALUATION_FUNCTION_EVENT,
					InsertionStrategy.ADD_ALL,
					ProcessingStrategy.PROCESS_ARCHIVE_ON_ITERATION_INCREMENT
					);
			
			ITrimmingFunction<ILinearRepresentation<Double>> trimmer = new ZitzlerTruncation<ILinearRepresentation<Double>>(maximumArchiveSize, evaluationFunction);
			archive.addTrimmingFunction(trimmer);
			
			IPlotter<ILinearRepresentation<Double>> plotter = new Plot2DGUI<ILinearRepresentation<Double>>();
			archive.setPlotter(plotter);			
			
			IAlgorithmResult<ILinearRepresentation<Double>> result =  algorithm.run();
			IAlgorithmStatistics<ILinearRepresentation<Double>> stats = result.getAlgorithmStatistics();

						
		} catch (InvalidNumberOfIterationsException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
