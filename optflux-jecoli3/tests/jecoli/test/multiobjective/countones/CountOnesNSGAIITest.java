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
package jecoli.test.multiobjective.countones;

import jecoli.algorithm.components.algorithm.IAlgorithm;
import jecoli.algorithm.components.algorithm.IAlgorithmResult;
import jecoli.algorithm.components.configuration.InvalidConfigurationException;
import jecoli.algorithm.components.evaluationfunction.IEvaluationFunction;
import jecoli.algorithm.components.operator.container.ReproductionOperatorContainer;
import jecoli.algorithm.components.operator.reproduction.linear.BitFlipMutation;
import jecoli.algorithm.components.operator.reproduction.linear.OnePointCrossover;
import jecoli.algorithm.components.operator.selection.TournamentSelection2;
import jecoli.algorithm.components.randomnumbergenerator.DefaultRandomNumberGenerator;
import jecoli.algorithm.components.randomnumbergenerator.IRandomNumberGenerator;
import jecoli.algorithm.components.representation.binary.BinaryRepresentationFactory;
import jecoli.algorithm.components.representation.linear.ILinearRepresentation;
import jecoli.algorithm.components.statistics.StatisticTypeMask;
import jecoli.algorithm.components.statistics.StatisticsConfiguration;
import jecoli.algorithm.components.terminationcriteria.ITerminationCriteria;
import jecoli.algorithm.components.terminationcriteria.InvalidNumberOfIterationsException;
import jecoli.algorithm.components.terminationcriteria.IterationTerminationCriteria;
import jecoli.algorithm.multiobjective.nsgaII.NSGAII;
import jecoli.algorithm.multiobjective.nsgaII.NSGAIIConfiguration;
import jecoli.algorithm.singleobjective.evolutionary.RecombinationParameters;

// TODO: Auto-generated Javadoc
/**
 * The Class CountOnesNSGAIITest.
 */
public class CountOnesNSGAIITest {

	/**
	 * The main method.
	 * 
	 * @param args the args
	 */
	public static void main(String[] args) {
		try {
			NSGAIIConfiguration<ILinearRepresentation<Boolean>,BinaryRepresentationFactory> configuration = new NSGAIIConfiguration<ILinearRepresentation<Boolean>,BinaryRepresentationFactory>();
			
			IRandomNumberGenerator randomNumberGenerator = new DefaultRandomNumberGenerator();
			configuration.setRandomNumberGenerator(randomNumberGenerator);
			
			StatisticsConfiguration stats = new StatisticsConfiguration();
			StatisticTypeMask mask = new StatisticTypeMask();
			mask.setMaxFitnessValue(false);
			mask.setMeanFitnessValue(false);
			mask.setMinFitnessValue(false);
			mask.setStdDevValue(false);
			stats.setScreenStatisticsMask(mask);
			configuration.setStatisticsConfiguration(stats);
			
			IEvaluationFunction<ILinearRepresentation<Boolean>> evaluationFunction = new CountOnesMOEvaluationFunction<ILinearRepresentation<Boolean>>(true);
			configuration.setEvaluationFunction(evaluationFunction);
			
			int solutionSize = 100;
			BinaryRepresentationFactory solutionFactory = new BinaryRepresentationFactory(solutionSize,2);
			configuration.setSolutionFactory(solutionFactory);
			
			int populationSize = 100;
			configuration.setPopulationSize(populationSize);
			
			configuration.setNumberOfObjectives(2);

			int numberGenerations = 500;
			ITerminationCriteria terminationCriteria;
			terminationCriteria = new IterationTerminationCriteria(numberGenerations);
			configuration.setTerminationCriteria(terminationCriteria);
			
			RecombinationParameters recombinationParameters = new RecombinationParameters(0,populationSize,0,true);
			configuration.setRecombinationParameters(recombinationParameters);
			
			configuration.setSelectionOperator(new TournamentSelection2<ILinearRepresentation<Boolean>>(1,2,randomNumberGenerator));
			
			ReproductionOperatorContainer reproductionOperatorContainer = new ReproductionOperatorContainer();
			reproductionOperatorContainer.addOperator(0.5,new OnePointCrossover<Boolean>());
			reproductionOperatorContainer.addOperator(0.5,new BitFlipMutation(1));
			configuration.setReproductionOperatorContainer(reproductionOperatorContainer);
			
			IAlgorithm<ILinearRepresentation<Boolean>> algorithm = new NSGAII<ILinearRepresentation<Boolean>,BinaryRepresentationFactory>(configuration);

			IAlgorithmResult<ILinearRepresentation<Boolean>> result =  algorithm.run();
//			IAlgorithmStatistics stats = result.getAlgorithmStatistics();

			
			
		} catch (InvalidNumberOfIterationsException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
