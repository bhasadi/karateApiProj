package runners;

import com.intuit.karate.junit5.Karate;
import com.intuit.karate.Results;
import static org.junit.jupiter.api.Assertions.*;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.*;

public class MyRunner {

    @org.junit.jupiter.api.Test
    void testParallel() {
        Results results = com.intuit.karate.Runner.path("classpath:sysOne")
                .outputCucumberJson(true)
                .parallel(5);

        generateReport(results.getReportDir());

        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }

    public static void generateReport(String karateOutputPath) {
        File reportOutputDirectory = new File("target/cucumber-html-reports");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add(karateOutputPath + "/cucumber-report.json");

        Configuration config = new Configuration(reportOutputDirectory, "Karate Test Report");
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, config);
        reportBuilder.generateReports();
    }
}
