<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="net.zeroat.openv.handler.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Example of Ajax</title>
<script type="text/javascript"
            src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<script type="text/javascript" src="ajax.js"></script>
<script type="text/javascript">
	(function poll() {
		$.ajax({
			url : "temp.jsp",
			success : function(data) {
				eval("document.forms.namedItem('tForm').tElement").value = data;
			},
			dataType : "text",
			complete : poll,
			timeout : 30000
		});
	})();
</script>
</head>
<body>
	<form name="tForm" action="">
		<span>The temp is:</span> <input type="text" name="tElement"
			readonly="readonly" size="30" value="<%=Fake.getTemp()%>" /> <input
			type="button" value="+" onclick="ajaxFun('tForm', 'tElement');" />
	</form>
</body>
</html>
