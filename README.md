# Subscription System
## _Subscription  Service_

This microservice is part of a Subscription System composed by three different microservices.
- The [public service]: Backend for Frontend microservice to be used by UI frontend
- The [subscription service]: microservice implementing subscription logic, including persistence of subscription data in a database and email notification to confirm process is completed
- The [email service]: microservice implementing email notifications

Subscriptions contain this information:
- Email
- First name (optional)
- Gender (optional)
- Date of birth
- Flag for consent
- Newsletter id corresponding to the campaign

This system provides these operations:
- Create new subscription: By sending a POST request to the public service endpoint 
```sh
/subscriptions
``` 
with a valid model. System will return the ID of the created subscription
- Get details of a subscription: By sending a GET request to the puclic service endpoint
```sh
/subscriptions/{subscriptionId}
``` 
with an existing subscription id in the Path Variable
- Cancel a subscription: By sending a PUT request to the public service endpoint 
```sh
/subscriptions/{subscriptionId}/cancel
```
or by sending a PUT request to the public service endpoint 
```sh
/subscriptions/cancel
``` 
with two request params, the email and the campaignId
- Get all subscriptions: By sending a GET request to the public service endpoint 
```sh
/subscriptions/all
```

## Installation

To run these three Spring Boot applications locally make sure that you meet the application.yml requirements, for example:
- MySQL must be running
- You need to create a database matching with the Spring Datasource defined in the subscription service application.yml
- You need to set up your email crendentials in the email service application.yml

Each microservice contains his own Dockerfile.

Clean, compile and package:
```sh
mvn -e clean compile package
```
Create the Docker image:
```sh
docker build .
```
Run the Docker containers (you can check the ports you should map in the application.yml):
```sh
docker run -d -p 1949:1949
```

## Dependencies

These are the dependencies included in the pom files.

| Dependency | Purpose |
| ------ | ------ |
| httpclient | Send and receive HTTP requests |
| spring-boot-starter-mail | Send emails programatically |
| spring-boot-configuration-processor | Get values from application.properties |
| spring-boot-starter-data-jpa | Create repositories |
| mysql-connector-java | Connect with database |

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

[public service]: <https://github.com/rubenlg94/subscription-system-public-service>
[subscription service]: <https://github.com/rubenlg94/subscription-system-subscription-service>
[email service]: <https://github.com/rubenlg94/subscription-system-email-service>
