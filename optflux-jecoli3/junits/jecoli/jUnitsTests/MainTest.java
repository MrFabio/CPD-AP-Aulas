package jecoli.jUnitsTests;

import jecoli.jUnitsTests.testsReader.TestReader;

import org.junit.Test;

public class MainTest {

	
	@Test
	public void maintest()
	{

		TestReader tr = new TestReader("./src/jecoli/jUnitsTests/testsFiles/");
		tr.startTests();
		
	}
}
