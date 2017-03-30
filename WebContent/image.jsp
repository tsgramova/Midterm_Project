<%@ page import="java.io.*" %>
<%@ page import="java.net.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@page contentType="image/gif" %><%
    OutputStream o = response.getOutputStream();
    String image = request.getParameter("image");
    byte[] buf = new byte[32 * 1024]; // 32k buffer
    int index = 0;
    while( index <= image.length() ) {
        o.write(buf, 0, image.charAt(index));
        index++;
    }
    o.flush();
    o.close();
    return; 
%>
