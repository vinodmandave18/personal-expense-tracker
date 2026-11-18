<%@ page language="java" import="javax.servlet.http.*, javax.servlet.*" %>
<%
    // Invalidate the session if exists
    if (session != null) {
        session.invalidate();
    }
    
    // Redirect to index.jsp
    response.sendRedirect("index.jsp");
%>
