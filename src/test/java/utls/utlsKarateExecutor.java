package utls;

import java.util.Optional;
import com.intuit.karate.Runner;
import com.intuit.karate.Results;

public class utlsKarateExecutor {

    public static Results runKarate() {
        String karateEnv = Optional.ofNullable(System.getProperty("karate.env")).orElse("dev");
        String tags = Optional.ofNullable(System.getProperty("karate.tags")).orElse("~@ignore");
        String path = Optional.ofNullable(System.getProperty("karate.path")).orElse("classpath:sysOne");

        System.out.printf("Environment: %s, Tags: %s, Path: %s%n", karateEnv, tags, path);

        return Runner.path(path)
                .tags(tags)
                .outputCucumberJson(true)
                .parallel(5);
    }

}// class
