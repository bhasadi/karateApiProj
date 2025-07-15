package runners;

import com.intuit.karate.junit5.Karate;

/**
Why You Can’t Just Do mvn test Without Any Runner
Karate doesn’t generate test classes automatically. The Maven Surefire plugin requires Java classes to run — 
it can’t execute .feature files alone like Postman or Cucumber CLI.
*/

/**
 * mvn test "-Dtest=runners.GenericRunner"
 * "-Dkarate.options=classpath:sysOne/apiOne/features/testJava.feature"
 */
public class GenericRunner {
    @Karate.Test
    Karate runAll() {
        return Karate.run();
    }
}
