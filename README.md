# SQS Consumer ![image](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white) ![image](https://img.shields.io/badge/Springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white) ![image](https://img.shields.io/badge/Amazon_AWS-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white)

Project for receive messages from [SQS](https://aws.amazon.com/sqs) Queue using SpringBoot.

This project makes use of the [custom-logging-starter](https://github.com/natanaelmartinezassis/custom-logging-starter)
library developed for sending logs to [Logstash](https://www.elastic.co/pt/logstash/)
and/or [AWS Kinesis](https://aws.amazon.com/pt/kinesis/).

We are assuming that messages received from SQS were encapsulated with the automatically generated traceId and
spandId. [See: [SNS Producer](https://github.com/natanaelmartinezassis/sns-producer)]  
In this case we inject this information into the MDC to replicate the trace.

---

# How To

### First Step: build

Build the application to generate the *.jar artifact.

```
$ ./mvnw clean package
```

### Second Step: docker image

Generate the application docker image.

```
$ cd docker-compose/
$ docker-compose build sqs-consumer
```

### Third Step: create SQS Queue

Create SQS Queue. [See: [SNS Producer](https://github.com/natanaelmartinezassis/sns-producer)]

```
$ aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name add-foobar-queue
```

### Fourth Step: subscribe to SNS Topic 

Subscribe to SNS Topic using SQS Queue. [See: [SNS Producer](https://github.com/natanaelmartinezassis/sns-producer)]

```
$ aws --endpoint-url=http://localhost:4566 sns subscribe --topic-arn arn:aws:sns:us-east-1:000000000000:add-foobar-topic --protocol sqs --notification-endpoint http://localhost:4566/000000000000/add-foobar-queue
```

### Fifth Step: application containers

Run application containers using docker-compose.

```
$ docker-compose up -d
```