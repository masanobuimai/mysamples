<%@page contentType="text/html" pageEncoding="Windows-31J" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <title>JSP Page</title>
</head>
<body>
<h1>Hello World!</h1>

<form action="index.jsp" method="post">
  <input type="text" name="q"/>
</form>

Res: <%= request.getParameter("q") %>
</body>
</html>
