Feature: Get auth token using client ID and client secret

Scenario:
  Given url baseUrl + '/auth/token'  # replace with your actual token URL
  And header Content-Type = 'application/x-www-form-urlencoded'
  And request
    """
    client_id=#(clientId)&client_secret=#(clientSecret)&grant_type=client_credentials
    """
  When method post
  Then status 200
  * def token = response.access_token
