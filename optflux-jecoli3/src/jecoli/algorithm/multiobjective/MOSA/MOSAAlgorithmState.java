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
package jecoli.algorithm.multiobjective.MOSA;

import java.util.GregorianCalendar;

import jecoli.algorithm.components.algorithm.AlgorithmResult;
import jecoli.algorithm.components.algorithm.AlgorithmState;
import jecoli.algorithm.components.algorithm.IAlgorithm;
import jecoli.algorithm.components.algorithm.IArchivedAlgorithmState;
import jecoli.algorithm.components.configuration.IConfiguration;
import jecoli.algorithm.components.representation.IRepresentation;
import jecoli.algorithm.components.solution.ISolutionSet;
import jecoli.algorithm.components.statistics.StatisticsConfiguration;

// TODO: Auto-generated Javadoc
/**
 * The Class SPEA2AlgorithmState.
 */
public class MOSAAlgorithmState<T extends IRepresentation> extends AlgorithmState<T> implements IArchivedAlgorithmState<T>{
	
	/** For serialization purposes */
	private static final long serialVersionUID = 6842667362898173523L;
	
	/** The archive. */
	protected ISolutionSet<T> archive;

	/**
	 * Instantiates a new sPE a2 algorithm state.
	 * 
	 * @param algorithm the algorithm
	 */
	public MOSAAlgorithmState(IAlgorithm<T> algorithm) {
		super(algorithm);
	}
	
	/* (non-Javadoc)
	 * @see core.AlgorithmState#initializeState()
	 */
	@Override
	public void initializeState() {
        currentIteration = 0;
        overallTime = 0;
        currentTime = GregorianCalendar.getInstance().getTimeInMillis();
        lastIterationTime = 0;
        solutionSet = null;
        archive = null;
        IConfiguration<T> configuration = algorithm.getConfiguration();
        StatisticsConfiguration statisticsConfiguration = configuration.getStatisticConfiguration();
        algorithmResult = new AlgorithmResult<T>(statisticsConfiguration.getNumberOfBestSolutionsToKeepPerRun(), statisticsConfiguration.isVerbose());
    }

	/**
	 * Update archive state.
	 * 
	 * @param newArchive the new archive
	 */
	public void updateArchiveState(ISolutionSet<T> newArchive){
		archive = newArchive; 
	}
	
	/**
	 * Gets the archive.
	 * 
	 * @return the archive
	 */
	public ISolutionSet<T> getArchive() {
		return archive;
	}

	/**
	 * Sets the archive.
	 * 
	 * @param archive the new archive
	 */
	public void setArchive(ISolutionSet<T> archive) {
		this.archive = archive;
	}

}
