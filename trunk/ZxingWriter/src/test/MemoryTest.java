/*
 * MemoryTest.java
 * JMUnit based test
 *
 * Created on Dec 28, 2012, 9:43:47 AM
 */
package test;

import jmunit.framework.cldc10.*;

/**
 * @author jack
 */
public class MemoryTest extends TestCase {

    public MemoryTest() {
        //The first parameter of inherited constructor is the number of test cases
        super(3, "MemoryTest");
        addPerformanceMeasurement(new MemoryMeasurement(80, false));
    }

    public void test40Bytes() {
        byte[] b = new byte[40];
    }

    public void test60Bytes() {
        byte[] b = new byte[60];
    }

    public void test100Bytes() {
        byte[] b = new byte[100];
    }

    public void test(int testNumber) throws Throwable {
        switch (testNumber) {
            case 0:
                test40Bytes();
                break;
            case 1:
                test60Bytes();
                break;
            case 2:
                test100Bytes();
                break;

            default:
                fail("No such test.");
        }
    }
}
