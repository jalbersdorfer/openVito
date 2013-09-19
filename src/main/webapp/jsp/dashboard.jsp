<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="net.zeroat.openv.handler.*" %>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html>
<link rel="stylesheet" type="text/css" href="../css/openv.css" media="screen" />

<script type="text/javascript">

function tempInc_click()
{
	Fake.setTemp(99);
}

</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>openVito Dashboard</title>
</head>
<body>

<%= Fake.getTemp() %> °C
<f:view>
  <h:form>
<%--     <h:commandButton action="#{Fake.inc}" value="+"></h:commandButton> --%>
  </h:form>
</f:view>

</body>
</html>