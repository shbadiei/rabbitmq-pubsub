# rabbitmq-pubsub

This project as a code challenge simulates a distributed software that has seven deployments in provinces:
1) Project has two spring profiles: pub for publisher and sub for the subscriber. You must specify one or both of them
for example use:  -Dspring.profiles.active=pub,sub  to activate all parts.
2) To start configured rabbitMQ and PostgreSQL needed to run the project, run 'docker-compose up -d' at the project's root.
3) The sample curl to call Post request to publish message is as below: 

curl --location --request POST 'http://localhost:8080/api/putmessage' \
--header 'Content-Type: application/json' \
--data-raw '{
    "destination": "Kerman",
    "payload":"Raspberry Pi is a series of small single-board computers developed in the United Kingdom by the Raspberry Pi Foundation in association with Broadcom."
}'

4) list of destinations(defined routingkeys) are:
KhorasanRazavi, Esfahan, Ilam, Mazandaran, Fars, Kerman, Yazd

5) Proximately once in every three attempts, in consumer throws DeliberateErrorForDLQTestException to reject the message, and MQ automatically sends the message to corresponding the Dead-Letter Queue.
