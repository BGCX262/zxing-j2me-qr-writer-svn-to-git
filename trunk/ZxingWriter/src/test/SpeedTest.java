/*
 * SpeedTest.java
 * JMUnit based test
 *
 * Created on Dec 27, 2012, 6:37:14 PM
 */
package test;

import jmunit.framework.cldc10.*;

/**
 * @author jack
 */
public class SpeedTest extends TestCase {

    public SpeedTest() {
        //The first parameter of inherited constructor is the number of test cases
        super(3, "SpeedTest");
    }

    public void tearDown() {
        removeAllPerformanceMeasurements();
    }

    public void testNoSpeedRequirements() throws InterruptedException {
        Thread.sleep(100);
    }

    public void testSpeedRequirements() throws InterruptedException {
        addPerformanceMeasurement(new TimedMeasurement(100, 0));
        Thread.sleep(100);
    }

    public void test(int testNumber) throws Throwable {
        switch (testNumber) {
            case 0:
            case 2:
                testNoSpeedRequirements();
                break;
            case 1:
                testSpeedRequirements();
                break;

            default:
                fail("No such test.");
        }
    }
}
