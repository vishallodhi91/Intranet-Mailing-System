import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class editaddr extends HttpServlet
{
	Connection con=null;
	Statement st=null;
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
	String name=req.getParameter("nam");
	String nick=req.getParameter("nic");
	String addr=req.getParameter("add");
	out.println("<html><head><h2>Adding new address to"+name+"addbook</h2></head><br><br>");
	out.println("<html><body bgcolor=pink><form action=http://localhost:8080/servlet/changename method=post>Name:<input type=text name=nametext value="+name+"><br>Nickname:<input type=text name=nicktext value="+nick+"><br>OldMail_ID:<input type=text name=addrtext value="+addr+"><br>NewMail_ID<input type=text name=newaddr><br><input type=submit name=button value=change></body></form></html>");
	}catch(Exception e)
	{out.println(e.toString());}
}
}