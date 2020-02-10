<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/FilesHandler?action=filesUpload" method="post" enctype="multipart/form-data">
		Select Image: <input type="file" name="files" multiple/>
		<input type="submit" value="Upload"/>
		
	
	
	
	
	</form>
	
	<a href="${pageContext.request.contextPath}/FilesHandler?action=listImages"> View Images</a>
</body>
</html>