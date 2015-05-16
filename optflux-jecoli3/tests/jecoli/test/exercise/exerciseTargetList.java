/**
* Copyright 2009,
* CCTC - Computer Science and Technology Center
* IBB-CEB - Institute for Biotechnology and  Bioengineering - Centre of Biological Engineering
* University of Minho
*
* This is free software: you can redistribute it and/or modify
* it under the terms of the GNU Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This code is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU Public License for more details.
*
* You should have received a copy of the GNU Public License
* along with this code.  If not, see <http://www.gnu.org/licenses/>.
* 
* Created inside the SysBio Research Group <http://sysbio.di.uminho.pt/>
* University of Minho
*/
package jecoli.test.exercise;

import jecoli.algorithm.components.algorithm.IAlgorithm;
import jecoli.algorithm.components.algorithm.IAlgorithmResult;
import jecoli.algorithm.components.algorithm.IAlgorithmStatistics;
import jecoli.algorithm.components.algorithm.writer.IAlgorithmResultWriter;
import jecoli.algorithm.components.configuration.InvalidConfigurationException;
import jecoli.algorithm.components.evaluationfunction.IEvaluationFunction;
import jecoli.algorithm.components.operator.container.ReproductionOperatorContainer;
import jecoli.algorithm.components.operator.reproduction.linear.LinearGenomeRandomMutation;
import jecoli.algorithm.components.operator.reproduction.linear.OnePointCrossover;
import jecoli.algorithm.components.operator.reproduction.set.SetRandomMutation;
import jecoli.algorithm.components.operator.reproduction.set.SetUniformCrossover;
import jecoli.algorithm.components.operator.selection.TournamentSelection;
import jecoli.algorithm.components.randomnumbergenerator.DefaultRandomNumberGenerator;
import jecoli.algorithm.components.randomnumbergenerator.IRandomNumberGenerator;
import jecoli.algorithm.components.representation.integer.IntegerRepresentationFactory;
import jecoli.algorithm.components.representation.integer.IntegerSetRepresentationFactory;
import jecoli.algorithm.components.representation.linear.ILinearRepresentation;
import jecoli.algorithm.components.representation.set.ISetRepresentation;
import jecoli.algorithm.components.representation.set.ISetRepresentationFactory;
import jecoli.algorithm.components.statistics.StatisticsConfiguration;
import jecoli.algorithm.components.terminationcriteria.ITerminationCriteria;
import jecoli.algorithm.components.terminationcriteria.InvalidNumberOfIterationsException;
import jecoli.algorithm.components.terminationcriteria.IterationTerminationCriteria;
import jecoli.algorithm.singleobjective.evolutionary.EvolutionaryAlgorithm;
import jecoli.algorithm.singleobjective.evolutionary.EvolutionaryConfiguration;
import jecoli.algorithm.singleobjective.evolutionary.RecombinationParameters;
import jecoli.core.DataMatrix;
import jecoli.test.targetlist.TargetListSetEvaluation;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc

/**
 * The Class EATargetList.
 */
public class exerciseTargetList {

	/**
	 * The main method.
	 * 
	 * @param args the args
	 */
	public static void main(String[] args) {

		try{
			
				//testTargetList();
				//testSetRep();
		
		}catch (Exception e) {
			e.printStackTrace();
		}


	}

	
	/**
	 * Test target list.
	 */
	public static double testTargetList () {

		try {
			EvolutionaryConfiguration<ILinearRepresentation<Integer>,IntegerRepresentationFactory> configuration = new EvolutionaryConfiguration<ILinearRepresentation<Integer>,IntegerRepresentationFactory>();
			
			int minimum = 1;
			int maximum = 5;
			int solutionSize = 200;
			IRandomNumberGenerator randomNumberGenerator = new DefaultRandomNumberGenerator();
			//IEvaluationFunction<ILinearRepresentation<Integer>> evaluationFunction =
			//		new IntClusteringEvaluationFunction(solutionSize, minimum, maximum,randomNumberGenerator);
			//configuration.setEvaluationFunction(evaluationFunction);
			
			IntegerRepresentationFactory solutionFactory = new IntegerRepresentationFactory(solutionSize,maximum,minimum);
			configuration.setSolutionFactory(solutionFactory);
			
			int populationSize = 100;
			configuration.setPopulationSize(populationSize);

			int numberGenerations = 1000;
			ITerminationCriteria terminationCriteria = new IterationTerminationCriteria(numberGenerations);
			configuration.setTerminationCriteria(terminationCriteria);
			
			RecombinationParameters recombinationParameters = new RecombinationParameters(40,55,5,true);
			configuration.setRecombinationParameters(recombinationParameters);
			
			configuration.setSelectionOperator(new TournamentSelection<ILinearRepresentation<Integer>>(1,2));
			configuration.setSurvivorSelectionOperator(new TournamentSelection<ILinearRepresentation<Integer>>(1,2));
			
			configuration.setRandomNumberGenerator(randomNumberGenerator);
			configuration.setProblemBaseDirectory("nullDirectory");
			configuration.setAlgorithmStateFile("nullFile");
			configuration.setSaveAlgorithmStateDirectoryPath("nullDirectory");
			configuration.setAlgorithmResultWriterList(new ArrayList<IAlgorithmResultWriter<ILinearRepresentation<Integer>>>());
			configuration.setStatisticsConfiguration(new StatisticsConfiguration());
			configuration.getStatisticsConfiguration().setVerbose(true);
			
			ReproductionOperatorContainer operatorContainer = new ReproductionOperatorContainer();
			operatorContainer.addOperator(0.5,new OnePointCrossover<Integer>());
			operatorContainer.addOperator(0.5,new LinearGenomeRandomMutation<Integer>(0.001));	
			configuration.setReproductionOperatorContainer(operatorContainer);
			
			IAlgorithm<ILinearRepresentation<Integer>> algorithm = 
					new EvolutionaryAlgorithm<ILinearRepresentation<Integer>,IntegerRepresentationFactory>(configuration);
			IAlgorithmResult<ILinearRepresentation<Integer>> result =  algorithm.run();

			IAlgorithmStatistics<ILinearRepresentation<Integer>> stats = result.getAlgorithmStatistics();
//			for (int i=0; i < 1000; i++)
//				System.out.println(stats.getStatisticCell(i).getMaxValue());
			
			return result.getSolutionContainer().getBestSolutionCellContainer(true).getSolution().getScalarFitnessValue();
			
		} catch (InvalidNumberOfIterationsException e) {
			e.printStackTrace();
		}
		catch (InvalidConfigurationException e) {
			e.printStackTrace(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0.0;
	}

	
	
	/**
	 * Test set rep.
	 */
	public static void testSetRep (int k, DataMatrix da) {

		try {
			EvolutionaryConfiguration<ISetRepresentation<Integer>,ISetRepresentationFactory<Integer>> configuration = new EvolutionaryConfiguration<ISetRepresentation<Integer>,ISetRepresentationFactory<Integer>>();
			
			int minimum = 1;
			int maximum = 100;
			int solutionSize = 50;
			
			IRandomNumberGenerator randomNumberGenerator = new DefaultRandomNumberGenerator();
			IntClusteringEvaluationFunction icef = new IntClusteringEvaluationFunction(k,da);
			IEvaluationFunction<ISetRepresentation<Integer>> evaluationFunction = (IEvaluationFunction)icef;
			configuration.setEvaluationFunction(evaluationFunction);
			
			IntegerSetRepresentationFactory solutionFactory = new IntegerSetRepresentationFactory(maximum,solutionSize);
			((IntegerSetRepresentationFactory)solutionFactory).setInitialMinSize(solutionSize);
			configuration.setSolutionFactory(solutionFactory);
			
			int populationSize = 100;
			configuration.setPopulationSize(populationSize);

			int numberGenerations = 1000;
			ITerminationCriteria terminationCriteria = new IterationTerminationCriteria(numberGenerations);
			configuration.setTerminationCriteria(terminationCriteria);
			
			RecombinationParameters recombinationParameters = new RecombinationParameters(40,55,5,true);
			configuration.setRecombinationParameters(recombinationParameters);
			
			configuration.setSelectionOperator(new TournamentSelection<ISetRepresentation<Integer>>(1,2));
			configuration.setSurvivorSelectionOperator(new TournamentSelection<ISetRepresentation<Integer>>(1,2));
	
			configuration.setRandomNumberGenerator(randomNumberGenerator);
			configuration.setProblemBaseDirectory("nullDirectory");
			configuration.setAlgorithmStateFile("nullFile");
			configuration.setSaveAlgorithmStateDirectoryPath("nullDirectory");
			configuration.setAlgorithmResultWriterList(new ArrayList<IAlgorithmResultWriter<ISetRepresentation<Integer>>>());
			configuration.setStatisticsConfiguration(new StatisticsConfiguration());
			configuration.getStatisticsConfiguration().setVerbose(true);

			ReproductionOperatorContainer<ISetRepresentation<Integer>,ISetRepresentationFactory<Integer>> operatorContainer = new ReproductionOperatorContainer<ISetRepresentation<Integer>,ISetRepresentationFactory<Integer>>();
			operatorContainer.addOperator(0.5,new SetUniformCrossover<Integer>());
			operatorContainer.addOperator(0.5,new SetRandomMutation<Integer>());	
			configuration.setReproductionOperatorContainer(operatorContainer);
			
			IAlgorithm<ISetRepresentation<Integer>> algorithm = new EvolutionaryAlgorithm<ISetRepresentation<Integer>,ISetRepresentationFactory<Integer>>(configuration);
			IAlgorithmResult<ISetRepresentation<Integer>> result =  algorithm.run();

			IAlgorithmStatistics<ISetRepresentation<Integer>> stats = result.getAlgorithmStatistics();
//			for (int i=0; i < 1000; i++)
//				System.out.println(stats.getStatisticCell(i).getMaxValue());
			
			
		} catch (InvalidNumberOfIterationsException e) {
			e.printStackTrace();
		}
		catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		} 

	}
	
}
