package runners;

import utls.utlsReporting;
import utls.utlsKarateExecutor;
import com.intuit.karate.Results;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyRunner {

    // ✅ JUnit 5 execution
    @org.junit.jupiter.api.Test
    void runJUnitTest() {
        System.out.println("[JUnit] Starting Karate run...");
        Results results = utlsKarateExecutor.runKarate();
        utlsReporting.generateCucumberReport(results.getReportDir());
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }

    // ✅ CLI/main() execution
    public static void main(String[] args) {
        System.out.println("[Main] Starting Karate run...");
        Results results = utlsKarateExecutor.runKarate();
        utlsReporting.generateCucumberReport(results.getReportDir());

        if (results.getFailCount() > 0) {
            System.err.println("Some scenarios failed:");
            System.err.println(results.getErrorMessages());
            System.exit(1);
        } else {
            System.out.println("All scenarios passed!");
            System.exit(0);
        }
    }

}// class
