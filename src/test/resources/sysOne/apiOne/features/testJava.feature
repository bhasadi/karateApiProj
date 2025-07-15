Feature: To Test Java code
for help, see: https://github.com/karatelabs/karate/wiki/IDE-Support

Background:
  * url 'https://jsonplaceholder.typicode.com'

@bashar
Scenario: SysOne apiOne Test Java Method with tag bashar

* def dateTime =  Java.type("utls.DataTime") 
* def result = dateTime.getNumber(1, 2)
* print 'The result returned is: ', result

  Given path 'users'
  When method get
  Then status 200
