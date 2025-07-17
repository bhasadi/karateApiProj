package runners;

import com.intuit.karate.Runner;
import com.intuit.karate.Results;

// mvn compile exec:java -Dexec.mainClass="runners.MyRunner"
public class UsingMainAsrunner {
    public static void main(String[] args) {
        System.setProperty("polyglot.js.allowAllAccess", "true");

        Results results = Runner.path("classpath:sysOne/apiOne/features")
                .parallel(1);

        if (results.getFailCount() > 0) {
            System.err.println(results.getErrorMessages());
            System.exit(1);
        }
    }
}
