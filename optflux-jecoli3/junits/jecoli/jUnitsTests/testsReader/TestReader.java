package jecoli.jUnitsTests.testsReader;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import jecoli.algorithm.components.representation.IRepresentation;
import jecoli.algorithm.components.solution.ISolutionFactory;
import jecoli.jUnitsTests.ITest;
import jecoli.jUnitsTests.TestControlCenter;
import jecoli.jUnitsTests.parser.ParseException;
import jecoli.jUnitsTests.parser.Test;

public class TestReader {

	private TestControlCenter<IRepresentation,ISolutionFactory<IRepresentation>> center;
	private String dirPath;
	private Test parser;
	private FileReader in;
	private final String extention = ".test";
	
	public TestReader(String dirPath)
	{
		center = new TestControlCenter<IRepresentation,ISolutionFactory<IRepresentation>>();
		this.dirPath = dirPath;
	}
	
	private ITest<IRepresentation,ISolutionFactory<IRepresentation>> getTestFile(String filePath)
	{
		ITest<IRepresentation,ISolutionFactory<IRepresentation>> test=null;
		try {
			in = new FileReader(filePath);
			parser = new Test(in);
			parser.ReInit(in);
			test = parser.start();
		} 
		catch (FileNotFoundException e) {e.printStackTrace();} 
		catch (ParseException e) {e.printStackTrace();} 
		catch (Exception e) {e.printStackTrace();}
		
		return test;
	}
	
	
	private void checkDirFiles()
	{
		File dir = new File(this.dirPath);
		String[] children = dir.list();
		
		for(String fileName : children)
		{
			if(fileName!=null && fileName.contains(extention))
			{
				ITest<IRepresentation,ISolutionFactory<IRepresentation>> newTest = getTestFile(this.dirPath+fileName);
				newTest.setTestFileName(fileName);
				center.addTest(newTest);
			}
		}
	}
	
	private void loadPackageTest()
	{
		
	}
	
	
	public void startTests()
	{
		checkDirFiles();
		loadPackageTest();
		
		try{
			center.validadeAllTests();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		center.runTests();
	}
	
	
	
}

