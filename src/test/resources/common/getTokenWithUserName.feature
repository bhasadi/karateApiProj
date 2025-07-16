Feature: Get auth token

Scenario:
  Given url baseUrl + '/auth/token'
  And request { username: '#(username)', password: '#(password)' }
  And header Content-Type = 'application/json'
  When method post
  Then status 200
  * def token = response.access_token

