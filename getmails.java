import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class getmails extends HttpServlet
{
	Connection con=null;
	Statement st=null;
	ResultSet rs;
	ResultSetMetaData meta=null;
	PrintWriter out=null;
public void init(ServletConfig sc)throws ServletException
{
	try
	{
	super.init(sc);
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	con=DriverManager.getConnection("jdbc:odbc:logindata","","");
	st=con.createStatement();
	}catch(Exception e)
		{System.out.println(e.toString());}
}
public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	try
	{
	res.setContentType("text/html");
	out=res.getWriter();
	String foldername=req.getParameter("folder");
	out.println("<html><h2><font color=red>Mails in "+foldername+"</font></h2>");
	out.println("<table border><BODY BGCOLOR=AQUA><form action=http://localhost:8080/servlet/deletefolder  method=post ><input  type=hidden name=folder value="+foldername+"><input type=submit  value=Delete >");
	out.println("<TABLE BORDER=1><TR><TH>Check</TH><TH>From</TH><TH>Subject</TH><TH>Date</TH></TR>");
	String fol="select * from "+foldername;
	rs=st.executeQuery(fol);
	while(rs.next())
	{
		String from2=rs.getString(1);
		String sub2=rs.getString(2);
		String msg2=rs.getString(3);
		String dat2=rs.getString(4);	               	
		out.println("<TR><TD><INPUT TYPE=CHECKBOX  NAME="+from2+"|"+dat2+"></tr>");
		out.println("<TD width=200 align=center><A href=http://localhost:8080/servlet/getmessage?from1="+from2+"&sub1="+sub2+"&msg1="+msg2+"&dat1="+dat2+"&foldname="+foldername+" target=rightf>"+from2+"</A>");
		out.println("<TD width=200 align=center>"+sub2+"<TD width=200 align=center>"+dat2+"</TR>");
	}
		out.println("</form>");
	}catch(Exception e)
	{out.println(e.toString());}
}
}	