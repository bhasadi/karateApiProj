Feature: Retrieve secrets from Vault

Scenario:
  # read from ENV/props
  * def vaultToken = 'Bearer ' + java.lang.System.getenv('VAULT_TOKEN')  
  * url 'https://vault.mycompany.com/v1/secret/data/sysOne'              
  * header Authorization = vaultToken
  When method get
  Then status 200
  * def secrets = response.data.data
#  This assumes the Vault API returns a structure like:

# Valult data
#   {
#   "data": {
#     "data": {
#       "client_id": "abc",
#       "client_secret": "xyz"
#     }
#   }
# }
