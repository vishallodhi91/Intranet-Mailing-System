import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class getaddbook extends HttpServlet
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
	String una=null;
	Cookie[] c = req.getCookies();
	if(c!=null)
		for(int i=0;i<c.length;i++)
		{
			if(c[i].getName().equals("signin"))
				una=c[i].getValue();
			break;
		}	
	ResultSet rs=st.executeQuery("select * from "+una+"addbook");
	out.println("<p align=right><a href='http://localhost:8080/addnew.html'>Add</a></p>");
	out.println("<table border=2><tr><th>Name<th>NickName<th>E_mail Address<th>Options</tr>");
	while(rs.next())
	{
		String  name=rs.getString(1);
		String  nick=rs.getString(2);
		String  addr=rs.getString(3);
		out.println("<tr><td>"+name+"<td>"+nick+"<td>"+addr+"<td><form action=http://localhost:8080/servlet/editaddr  method=post><input type=hidden name=nam value="+name+"><br><input type=hidden name=nic value="+nick+"><br><input type=hidden name=add value="+addr+"><input type=submit name=editadd value=Edit></form><form action=http://localhost:8080/servlet/deleteadd  method=post><input type=hidden name=del value="+addr+"><input type=submit name=delbtn  value=Delete></form></tr>");
	}
	}catch(Exception e)
	{out.println(e.toString());}
}
}