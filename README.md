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

Sample Request and Response
---
1, POST    http://localhost:8080/v1/apple/push-recalculate?value=4
response: 
{
  "average": 4,
  "standard_deviation": 0
}

2, POST    http://localhost:8080/v1/apple/push-recalculate?value=7
response:
{
  "average": 5.5,
  "standard_deviation": 1.5
}

3. POST    http://localhost:8080/v1/apple/push-recalculate-encrypt?value=6
response:
{
  "encrypted_average": "lqlWeJBXeyfnlmqDbkq3lQ==",
  "encrypted_standard_deviation": "NH7lfUsyok7iKDwRnfKDPw=="
}

4. GET  http://localhost:8080/v1/apple/decrypt?value=lqlWeJBXeyfnlmqDbkq3lQ==
response:
{
  "plaintext": "5.667"
}

5. GET  http://localhost:8080/v1/apple/decrypt?value=NH7lfUsyok7iKDwRnfKDPw==
response:
{
  "plaintext": "1.247"
}

Design
---
Mean
--
keep tracking the total sum and # of values that has been pushed, return (sum / #) to get average/mean.

Standard Deviation
--
By far, the Welford's method is most accurate and efficient way to compute standard deviation.
Reference: http://jonisalonen.com/2013/deriving-welfords-method-for-computing-variance/

Encryptor
--
AES/CBC/PKCS5PADDING symmetric cryptography algorithm is used with 128 bits random key and 16 bytes random IV.

TODO
---
1, Unit test coverage.
2, Integration test coverage.
3, Logging.
4, Error and exception handling.
5, Fix Dependency Injection thus we can stopping using Enum of singleton pattern.
6, Use 256 bits key for AES encrypt/decrypt, which is better practice.
7, More comments.
8, Api Spec/Doc.

Reference
---
https://www.dropwizard.io/1.3.5/docs/getting-started.html
http://jonisalonen.com/2013/deriving-welfords-method-for-computing-variance/
https://www.johndcook.com/blog/standard_deviation/