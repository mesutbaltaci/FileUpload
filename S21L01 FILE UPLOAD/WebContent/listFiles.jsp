<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="org.ms.entities.Files" %>
    <%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listing Images</title>
</head>
<body>
	<%! String form; int fileId; %>
	
Listing Images
<table border="1"> 
<th> Preview</th>
<th> Available info</th>
<th> Update Info </th>
<th> Action </th>

<%
String path =(String) request.getAttribute("path");
	List<Files> files = (List<Files>) request.getAttribute("files");
	for (Files f: files) {
		out.print ("<tr><td><img src="+path+f.getFileName() + " height='200' width='200'></td>");
		out.print("<td><lu>"+
				"<li> File ID: "+f.getId()+" </li>"+
				"<li> File Name: "+f.getFileName()+"</li>"+
				"<li> File Label: "+f.getLabel()+" </li>"+
				"<li> File Caption: "+f.getCaption()+" </li>"+
				
				"</lu></td>" );
		fileId=f.getId();
		
		String form = "<form action='FilesHandler' method='post'>"+
				"Label: <input type='text' name='label' /> <br/><br/>"+
				"Caption: <input type='text' name='caption' /> <br/><br/>"+
				"<input type='hidden' name='fileId' value='"+fileId+"'/>"+
				"<input type='hidden' name='fileName' value='"+f.getFileName()+"'/>"+
				"<input type='hidden' name='action' value='updateInformation'/>"+
				"<input type='submit' value='update' /> "+
				"</form>";
		out.print ("<td>"+form+" </td></tr>");
	}


%>



</li>
</table>
</body>
</html>