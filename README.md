# CodePractice

How to start the CodePractice application
---
1. Run `git clone https://github.com/wenjiezhou/code-practice.git`
1. Run `cd code-practice`
1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/codepractice-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

End Points Supported By this App
---
    GET     /v1/apple/decrypt
    POST    /v1/apple/push-recalculate
    POST    /v1/apple/push-recalculate-encrypt
    
How to test this APIs
---
- Any API testing tools can be used to test these 3 endpoint.
- In case if you are using PostMan, share the collection here: https://www.getpostman.com/collections/1137cb06803b3dfdf618

Sample Request and Response
---
1. POST    http://localhost:8080/v1/apple/push-recalculate?value=4
response: 
{
  "average": 4,
  "standard_deviation": 0
}

1. POST    http://localhost:8080/v1/apple/push-recalculate?value=7
response:
{
  "average": 5.5,
  "standard_deviation": 1.5
}

1. POST    http://localhost:8080/v1/apple/push-recalculate-encrypt?value=6
response:
{
  "encrypted_average": "lqlWeJBXeyfnlmqDbkq3lQ==",
  "encrypted_standard_deviation": "NH7lfUsyok7iKDwRnfKDPw=="
}

1. GET  http://localhost:8080/v1/apple/decrypt?value=lqlWeJBXeyfnlmqDbkq3lQ==
response:
{
  "plaintext": "5.667"
}

1. GET  http://localhost:8080/v1/apple/decrypt?value=NH7lfUsyok7iKDwRnfKDPw==
response:
{
  "plaintext": "1.247"
}

Design
---
Mean
--
Keep tracking the total sum and # of values that has been pushed, return (sum / #) to get average/mean.

Standard Deviation
--
By far, the Welford's method is most accurate and efficient way to compute standard deviation.
Derived the formula from Welford's method, by keep updating m and n when new value is pushed, we can find out 
the sd for current value without knowing the data history.
Reference: http://jonisalonen.com/2013/deriving-welfords-method-for-computing-variance/

Encryptor
--
AES/CBC/PKCS5PADDING symmetric cryptography algorithm is used with 128 bits random key and 16 bytes random IV.

TODO
---
1. Unit test coverage.
1. Integration test coverage.
1. Logging.
1. Error and exception handling.
1. Fix Dependency Injection thus we can stopping using Enum of singleton pattern.
1. Use 256 bits key for AES encrypt/decrypt, which is better practice.
1. More comments.
1. Api Spec/Doc.

Reference
---
1. https://www.dropwizard.io/1.3.5/docs/getting-started.html
1. http://jonisalonen.com/2013/deriving-welfords-method-for-computing-variance/
1. https://www.johndcook.com/blog/standard_deviation/
