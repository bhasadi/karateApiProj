Feature: Read env-specific credentials and get token

Scenario:
* def env = karate.env || 'dev'
* def credsText = read(karate.get('credentialsPath'))
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
""".replace('%s', env)

* def payload =
  """
  {
    client_id: '#(creds.client_id)',
    client_secret: '#(creds.client_secret)',
    grant_type: '#(creds.grant_type)'
  }
  """
* url creds.token_url
* header Content-Type = 'application/x-www-form-urlencoded'
* request karate.toEncodedString(payload)
* method POST
* status 200
* def accessToken = response.access_token
* karate.set('accessToken', accessToken)
