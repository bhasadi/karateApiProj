package utls;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class utlsGeneric {

    public static Map<String, Object> loadCredentials(String resourcePath) {
        Map<String, Object> result = new HashMap<>();

        try (
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                Objects.requireNonNull(
                                        utlsGeneric.class.getClassLoader().getResourceAsStream(resourcePath))))) {
            // working line by line
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Skip empty lines and comments
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                // split the line on e.g. = char
                String[] pair;
                if (line.contains(":")) {
                    pair = line.split(":", 2);
                } else if (line.contains("=")) {
                    pair = line.split("=", 2);
                } else {
                    continue; // Skip invalid line
                }

                String key = pair[0].trim();
                String value = pair[1].trim();

                if (key.contains(".")) {
                    String[] parts = key.split("\\.", 2);
                    String parent = parts[0];
                    String child = parts[1];

                    // Nested structure
                    Map<String, String> nested = (Map<String, String>) result.getOrDefault(parent, new HashMap<>());
                    nested.put(child, value);
                    result.put(parent, nested);
                } else {
                    result.put(key, value);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to load credentials file: " + resourcePath, e);
        }

        return result;
    }// methods

}// class
