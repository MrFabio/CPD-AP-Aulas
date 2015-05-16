/**
 * 
 */
package jecoli.algorithm.components.representation.set;

import java.util.TreeSet;

import jecoli.algorithm.components.randomnumbergenerator.IRandomNumberGenerator;
import jecoli.algorithm.components.representation.IElementsRepresentation;

/**
 * @author pmaia
 *
 * Jan 9, 2013
 */
public interface ISetRepresentation<E> extends IElementsRepresentation<E> {
	
	void removeElementAt(int index);
		
	E getRandomElement(IRandomNumberGenerator randomNumberGenerator);
	
	TreeSet<E> getGenome();
}
