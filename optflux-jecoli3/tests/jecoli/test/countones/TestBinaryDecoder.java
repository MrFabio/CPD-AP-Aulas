package jecoli.test.countones;

import jecoli.algorithm.components.representation.linear.ILinearRepresentation;
import jecoli.algorithm.components.solution.decoder.ISolutionDecoder;

public class TestBinaryDecoder implements ISolutionDecoder<ILinearRepresentation<Boolean>, String> {
	
	private static final long	serialVersionUID	= 1L;

	@Override
	public Object deepCopy() throws Exception {
		return null;
	}
	
	@Override
	public String decode(ILinearRepresentation<Boolean> representation) throws Exception {
		String decoded = "";
		for (int i = 0; i < representation.getNumberOfElements(); i++){
			String concat = (representation.getElementAt(i) ? "T" : "F");
			decoded+=concat;
		}
		return decoded;
	}
	
}
