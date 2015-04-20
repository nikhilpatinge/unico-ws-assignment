<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    	               "http://www.w3.org/TR/html4/loose.dtd">

<html>
<%
	String URL = request.getServerName() + ":"
			+ request.getServerPort();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nikhil Patinge - Unico Web Assignment</title>
</head>
<body>
	<h3>
		<u>Unico Web Assignment</u>
	</h3>
	<p>Below are the webservice URLs exposing the required
		functionalities:</p>
	<p>
		<u>1. Rest Webservices:</u>
	</p>
	<ul>
		<li>Push Operation - Adds integers in the Queue<br> <a
			href="http://<%=URL%>/UnicoWebServices/jaxrs/rest/push/1/2">http://<%=URL%>/UnicoWebServices/jaxrs/rest/push/1/2
		</a><br> <i>Here 1 and 2 are example integer values that would be
				added to the queue. Modify the URL for varying those</i>
		</li>
		<br>
		<li>Listing all the integers ever added to the queue in order<br>
			<a href="http://<%=URL%>/UnicoWebServices/jaxrs/rest/list">http://<%=URL%>/UnicoWebServices/jaxrs/rest/list
		</a>
		</li>
	</ul>
	<p>
		<u>2. SOAP Webservices:</u><br> All the 3 required
		functionalities -
		<code style="font-size: 13">gcd(),gcdList() and gcdSum() </code>
		are exposed in the below WSDL and can also be tested from the below
		test URL:
	</p>
	<ul>
		<li>WSDL<br> <a
			href="http://<%=URL%>/UnicoWebServices/SoapServiceService?wsdl">http://<%=URL%>/UnicoWebServices/SoapServiceService?wsdl
		</a><br>
		</li>
		<br>
		<li>Testing URL<br> <a
			href="http://<%=URL%>/UnicoWebServices/SoapServiceService?Tester">http://<%=URL%>/UnicoWebServices/SoapServiceService?Tester
		</a><br>
		</li>
	</ul>
	<br>
	<p>
		<u>SOURCE CODE:</u><br> The source code for this is available on
		GitHub <a href="https://github.com/nikhilpatinge/unico-ws-assignment">here</a>
	</p>
	<p>
		<u><i>NOTE:</i></u>
	</p>
	<ol>
		<li>To browse the queue for checking its messages at any point
			during the testing, I have created an additional service as:<br>
			<a href="http://<%=URL%>/UnicoWebServices/jaxrs/rest/browseQueue">http://<%=URL%>/UnicoWebServices/jaxrs/rest/browseQueue
		</a><br>
		</li>

		<li>To flush the queue use the below command:<br> <code
				style="font-size: 13">asadmin flush-jmsdest --desttype queue
				opsQueue</code>
		</li>
	</ol>
	<p>
		<b>Sincerely,<br> Nikhil Patinge
		</b>
	</p>
</body>
</html>
