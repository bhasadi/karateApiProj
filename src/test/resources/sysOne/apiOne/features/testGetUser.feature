Background:
* def credentialsFile = 'classpath:sysOne/experience/apiOne/testData/.credentials'
* call read('../utils/getTokenFromCredentials.feature') { credentialsPath: credentialsFile }

* header Authorization = 'Bearer ' + accessToken
* url 'https://api.sysone.com'

Scenario: Get user
* path '/user'
* method GET
* status 200


# Background:
# * def featurePath = __feature.pathFromRoot
# * def apiRoot = featurePath.replace(/\/features\/.*/, '')
# * def credentialsPath = 'classpath:' + apiRoot + '/testData/.credentials'
# * call read(apiRoot + '/utils/getTokenFromCredentials.feature') { credentialsPath: credentialsPath }
# * header Authorization = 'Bearer ' + accessToken