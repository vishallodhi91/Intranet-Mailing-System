import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class changename extends HttpServlet
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
			{
				una=c[i].getValue();
				break;
			}
		}
	String name1=req.getParameter("nametext");
	String nick1=req.getParameter("nicktext");
	String addr1=req.getParameter("addrtext");
	String addr2=req.getParameter("newaddr");
	String updateadd="update "+una+"addbook set name='"+name1+"',nick='"+nick1+"',addr='"+addr2+"' where addr='"+addr1+"'";
	st.executeUpdate(updateadd);
	con.commit();
	out.println("<html><head><h2>Address updated successfully,do u want update onemore<a href='http://localhost:8080/servlet/getaddbook'>address </a></h2></head></html>");
	}catch(Exception e)
	{out.println(e.toString());}
}
}