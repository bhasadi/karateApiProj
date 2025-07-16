package utls;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// mvn test -Dtest=utls.TestUtlsMethods
public class TestUtlsMethods {
    @Test
    public void testFormatDate() {
        int result = utlsDataTime.getNumber(1, 2);
        assertEquals(3, result); // update based on your expected format
    }
}// class

// =========================================================

// replace dynamic values
// * def request = read('classpath:data/sysOne/apiOne/request.json')
// * request.user.name = 'John Doe'

// Modular Payloads with Templates - Use a base template and merge:
// * def basePayload = read('classpath:templates/user-template.json')
// * def customUser = { name: 'Alice', age: 35 }
// * def payload = karate.merge(basePayload, customUser)