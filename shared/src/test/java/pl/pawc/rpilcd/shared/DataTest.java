package pl.pawc.rpilcd.shared;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class DataTest extends TestCase{

    public DataTest(String testName){
        super(testName);
    }

    public static Test suite(){
        return new TestSuite(DataTest.class);
    }

    public void testMessage(){
        //no tests
    }
}
