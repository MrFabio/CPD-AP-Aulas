package jecoli.algorithm.components.algorithm.writer;

import java.io.IOException;
import java.io.Serializable;

import jecoli.algorithm.components.IDeepCopy;
import jecoli.algorithm.components.algorithm.IAlgorithmResult;
import jecoli.algorithm.components.representation.IRepresentation;

public interface IAlgorithmResultWriter<T extends IRepresentation> extends Serializable,IDeepCopy{
	void writeResult(IAlgorithmResult<T> algorithmResult) throws IOException;//Escreve para o caminho default
	void writeResult(IAlgorithmResult<T> algorithmResult,String directoryUID) throws IOException;//Escreve para o caminho default com dir com UID
	
	IAlgorithmResultWriter<T> deepCopy() throws Exception;
}
