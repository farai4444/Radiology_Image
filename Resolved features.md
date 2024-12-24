# Spring Security Authentication
## Configurations done
* Created a custom log-in page
* created a custom UserDetailsSevice class
* customized security filter chain to accept any request and only specific request would require authentication depending on role.
* Used a custom AuthenticationProvider instance to the AuthenticationManager
* Used a token creator to authenticate details to the AuthenticationManager instance
* Passed the Authentication instance into the Security Context for retreival of details when session is created