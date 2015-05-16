package jecoli.algorithm.components;

import java.io.Serializable;

public interface IDeepCopy extends Serializable {
	Object deepCopy() throws Exception;
}
