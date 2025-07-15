package runners;

import com.intuit.karate.junit5.Karate;

// "-Dkarate.env=trn"
// mvn test "-Dtest=runners.DynamicRunner" "-Dsystem=sysOne" "-Dkarate.options=--tags @bashar"
// mvn test "-Dtest=runners.AllFeatureFilesRunner" "-Dkarate.options=--tags @bashar"
public class AllFeatureFilesRunner {

    @Karate.Test
    Karate run() {

        String systemFolder = System.getProperty("system", ""); // default if not set

        // this will run all the featur files under src\test\resources
        // String systemFolder = "";

        // this will run all the featur files under src\test\resources\sysOne
        // String systemFolder = "sysOne";

        return Karate.run("classpath:" + systemFolder);
    }
}
