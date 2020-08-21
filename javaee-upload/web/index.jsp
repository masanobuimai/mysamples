<%@ page contentType="text/html; charset=Windows-31J" %>
<html>
<head>
  <meta http-equiv="content-type" content="text/html;charset=Shift_JIS">
  <title>Simple jsp page</title>
</head>
<body>
<title>ファイルアップロード</title>
<form method="POST" enctype="multipart/form-data" action="upload">
  <input type="file" name="file"/><br/>
  <input type="submit" value="アップロード"/>
</form>
</body>
</html>
