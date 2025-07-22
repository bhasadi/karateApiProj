* def tokenResp = call read('classpath:commonfeature/getToken.feature')
* def accessToken = tokenResp.token

Background:
* def apiRoot = 'classpath:sysOne/experience/apiOne'
* def credentialsPath = apiRoot + '/testData/.credentials'

  * def tokenResp = call read('classpath:commonfeature/getToken.feature') { apiRoot: apiRoot }
    * def accessToken = tokenResp.token


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
