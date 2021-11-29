package Test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MainTest {
    // This JUnit Test runs all the separate JUnit tests together at once.
    public static void main(String[] args) {
        Result runAllTests = JUnitCore.runClasses(TicketTest.class, AuthenticationTest.class, JsonTest.class);
        for (Failure notPassed : runAllTests.getFailures())
        {
            System.out.println("\n" + notPassed.toString());
        }

        if (runAllTests.wasSuccessful())
            System.out.println("\nSuccess");
        else
            System.out.println("\nSome tests failed");
    }
}
