package utls;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TokenUtil {

    public static class TokenInfo {
        public String tokenUrl;
        public String clientId;
        public String clientSecret;
        public String grantType;
        public String scope;

        public Map<String, String> toMap() {
            Map<String, String> map = new HashMap<>();
            map.put("tokenUrl", tokenUrl);
            map.put("client_id", clientId);
            map.put("client_secret", clientSecret);
            map.put("grant_type", grantType);
            if (scope != null)
                map.put("scope", scope);
            return map;
        }
    }

    public static TokenInfo loadTokenInfo(String apiRoot, String env) {
        String path = "src/test/resources/" + apiRoot + "/testData/.credentials";
        Properties props = new Properties();

        try (FileInputStream fis = new FileInputStream(path)) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read credentials file: " + path, e);
        }

        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.tokenUrl = props.getProperty(env + ".tokenUrl");
        tokenInfo.clientId = props.getProperty(env + ".clientId");
        tokenInfo.clientSecret = props.getProperty(env + ".clientSecret");
        tokenInfo.grantType = props.getProperty(env + ".grantType", "client_credentials");
        tokenInfo.scope = props.getProperty(env + ".scope"); // optional

        return tokenInfo;
    }

}

    // ==================

public static TokenData loadTokenData(String apiRoot, String env) {
    // 1. Try to load from .properties file
    Properties props = new Properties();
    try (InputStream input = Files.newInputStream(Paths.get(apiRoot, "testData", ".credentials.properties"))) {
        props.load(input);

        // Look for env-prefixed keys
        String prefix = env + ".";
        if (props.containsKey(prefix + "client_id")) {
            // Found credentials for env
            return new TokenData(
                props.getProperty(prefix + "client_id"),
                props.getProperty(prefix + "client_secret"),
                props.getProperty(prefix + "grant_type"),
                props.getProperty(prefix + "token_url")
            );
        }
    } catch (IOException e) {
        // Log error or continue to Vault fallback
    }

    // 2. Fallback to VAULT_TOKEN (if set)
    String vaultToken = System.getenv("VAULT_TOKEN");
    if (vaultToken != null && !vaultToken.isEmpty()) {
        // Use the vaultToken to fetch actual credentials (custom logic)
        return fetchFromVault(vaultToken, apiRoot, env); // assume you have this implemented
    }

    throw new RuntimeException("Credentials not found for env: " + env + " and no VAULT_TOKEN fallback available");
}
