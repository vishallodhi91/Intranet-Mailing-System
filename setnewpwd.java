import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class setnewpwd extends HttpServlet
{
	Connection con;
	Statement st;
	PrintWriter out;
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
	res.setContentType("text/plain");
	out=res.getWriter();
	String oldpwd=req.getParameter("oldp");
	String newpwd=req.getParameter("newp");
	String conpwd=req.getParameter("conp");
	String una=null;
	Cookie[] c = req.getCookies();
		if(c!=null)
		for(int i=0;i<c.length;i++)
		{
			if(c[i].getName().equals("signin"))
				una=c[i].getValue();
			break;
		}
	ResultSet rs=st.executeQuery("select pwd from mailusers where uname='"+una+"'");
	while(rs.next())
	{
		if(rs.getString("pwd").equalsIgnoreCase(oldpwd))
		{
			st.executeUpdate("update mailusers set pwd='"+newpwd+"' where pwd='"+oldpwd+"'");
			out.println("<h3>Your New Pasword was set successfully wants to go for <a href=http://localhost:8080/servlet/inboxlocalhost target=rightf><i> Inbox </i></a>");
			break;
		}
		else
			out.println("Invalid password");
	}
	}catch(Exception e)
		{out.println(e.toString());}
}
}
