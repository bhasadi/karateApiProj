Feature: Get Token using Java util

Background:
* def env = karate.env
* def apiRoot = 'sysOne/experience/apiOne'
* def tokenInfo = Java.type('utils.TokenUtil').loadTokenInfo(apiRoot, env)
* def creds = tokenInfo.toMap()

Scenario: Get OAuth token
* url creds.tokenUrl
* form fields creds
* method post
* status 200
* def token = response.access_token
