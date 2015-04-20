---------------README.txt-----------
Application - UnicoWebServices

Environment:
-------------
JDK / JRE 1.7.0_75
Java EE 6 SDK 
GlassFish Server 3.1.2
Derby DB 10.8.1.2
 
 
Deployment steps:
--------------
1. Execute the DB scripts present at the below location on the Derby database:
2. Deploy the ear on the Glassfish container.


Resource JNDI names used in this application:
----------------------------------------
1. JMS Queue: 
    JNDI Name - jms/opsQueue
	Physical Destination name - opsQueue
	
2. Queue connectionFactory:
	JNDI name - jms/opsQueueConnectionFactory
	The pool setting have been kept as default.
	
Both 1 and 2 will get created automatically on the server once it is deployed as the same is included in glassfish-resources.xml
	
3. Database JDBC Resource: 
	JNDI name - jdbc/__default


ASSUMPTIONS:
------------
Since the "push" method of the REST service is the only one that accepts input in this applications, the only assumption for the steady working of the program is that the input URL format is strictly followed as below, with the arguments being integers only.

	
URL's For Accessing the Application:
-------------------------------------
Launch the application URL: http://<your_IP>:<your_port>/UnicoWebServices 
All the below documentation is present on that page.

REST Services:

1. Push Operation:
http://<your_IP>:<your_port>/UnicoWebServices/jaxrs/rest/push/1/2 

Here 1 and 2 are example integer values that would be added to the queue

2. Listing all the integers ever added to the queue in order:
http://<your_IP>:<your_port>/UnicoWebServices/jaxrs/rest/list 


SOAP Services:

The WSDL location is:
http://<your_IP>:<your_port>/UnicoWebServices/SoapServiceService?wsdl 

These services can also be tested by using the URL:
http://<your_IP>:<your_port>/UnicoWebServices/SoapServiceService?Tester 


NOTE:
---------

1. To browse the queue for checking its messages at any point during the testing, I have created an additional service as:
http://<your_IP>:<your_port>/UnicoWebServices/jaxrs/rest/browseQueue 

2. To flush the queue use the below command:
asadmin flush-jmsdest --desttype queue opsQueue