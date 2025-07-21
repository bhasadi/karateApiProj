package utls;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class utlsReporting {

    public static void generateCucumberReport(String karateOutputPath) {
        File reportOutputDirectory = new File("target/cucumber-html-reports");

        List<String> jsonFiles = Collections.singletonList(
                karateOutputPath + "/cucumber-report.json");

        Configuration config = new Configuration(reportOutputDirectory, "Karate Test Report");
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, config);
        reportBuilder.generateReports();
    }
}
