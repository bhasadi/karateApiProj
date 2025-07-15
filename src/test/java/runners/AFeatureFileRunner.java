package runners;

import com.intuit.karate.junit5.Karate;

// Use this command to run a specific featur file
// mvn test "-Dtest=runners.AFeatureFileRunner" "-Dkarate.options=--tags @bashar"
public class AFeatureFileRunner {

    @Karate.Test
    Karate run() {

        String systemFolder = "sysOne/apiOne/features/testOne.feature";
        return Karate.run("classpath:" + systemFolder).tags("~@ignore");
    }
}
