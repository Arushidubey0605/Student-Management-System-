<%@ page import="java.util.*,dao.StudentDAO" %>

<form action="student" method="post">
Name:<input name="name">
Email:<input name="email">
Course:<input name="course">
<button>Add</button>
</form>

<table border="1">
<%
for(String[] s : StudentDAO.getAllStudents()) {
%>
<tr>
<td><%=s[0]%></td>
<td><%=s[1]%></td>
<td><%=s[2]%></td>
<td><%=s[3]%></td>
</tr>
<% } %>
</table>
