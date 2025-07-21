Feature: Retrieve access token from either Vault or credentials file

Scenario:
* def env = karate.env || 'dev'
* def vaultToken = java.lang.System.getenv('VAULT_TOKEN')
* def useVault = vaultToken != null

# =========================
# Vault-based token retrieval
# =========================
* if (useVault) karate.log('🔐 Using Vault to retrieve token for env: ' + env)
* if (useVault)
  """
  # Define vault path and headers
  * def vaultUrl = 'https://vault.mycompany.com/v1/secret/data/' + env + '/sysOne/apiOne/token-creds'
  * header X-Vault-Token = vaultToken
  * url vaultUrl
  * method GET
  * status 200
  * def data = response.data.data
  * def client_id = data.client_id
  * def client_secret = data.client_secret
  * def grant_type = data.grant_type
  * def token_url = data.token_url
  """

# =========================
# File-based fallback
# =========================
* if (!useVault)
  """
  * def featurePath = __feature.pathFromRoot
  * def apiRoot = featurePath.replace(/\/features\/.*/, '')
  * def credentialsPath = 'classpath:' + apiRoot + '/testData/.credentials'

  * def credsText = read(credentialsPath)
  * def lines = credsText.split('\n')
  * def section = null
  * def creds = {}

  * eval
    """
    for (var i = 0; i < lines.length; i++) {
      var line = lines[i].trim();
      if (line.startsWith('[') && line.endsWith(']')) {
        section = line.substring(1, line.length - 1);
        continue;
      }
      if (section === '%s' && line.includes('=')) {
        var parts = line.split('=');
        creds[parts[0].trim()] = parts[1].trim();
      }
    }
    """.replace('%s', env)

  * def client_id = creds.client_id
  * def client_secret = creds.client_secret
  * def grant_type = creds.grant_type
  * def token_url = creds.token_url
  """

# =========================
# Get Access Token
# =========================
* def payload =
  """
  {
    client_id: '#(client_id)',
    client_secret: '#(client_secret)',
    grant_type: '#(grant_type)'
  }
  """

* url token_url
* header Content-Type = 'application/x-www-form-urlencoded'
* request karate.toEncodedString(payload)
* method POST
* status 200
* def accessToken = response.access_token
* karate.set('accessToken', accessToken)
