function fn() {
  var env = karate.env; // gets from -Dkarate.env=dev
  karate.log("Running in environment:", env);

  var config = {
    env: env,
    baseUrl: "",
    clientId: "",
    clientSecret: "",
  };

  if (env === "dev") {
    config.baseUrl = "https://api.dev.example.com";
    config.clientId = "dev-client-id";
    config.clientSecret = "dev-secret";
  } else if (env === "qa") {
    config.baseUrl = "https://api.qa.example.com";
    config.clientId = "qa-client-id";
    config.clientSecret = "qa-secret";
  } else {
    karate.log("No matching environment config found");
  }

  return config;
}

function fn() {
  var system = karate.properties["system"] || "sysOne";

  // 👇 Load credentials from Java class
  var credentials = Java.type("utls.utlsGeneric").loadCredentials(
    "secrets/.credentials"
  );

  // 👇 Access shared keys
  var config = {
    client_id: credentials.client_id,
    client_secret: credentials.client_secret,
    username: credentials[system].username,
    password: credentials[system].password,
  };

  return config;
}
