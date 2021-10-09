import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class user1 extends HttpServlet
{
	Connection con;
	Statement st;
	PrintWriter out;
	ResultSet rs;
	boolean b=false;
	public void init(ServletConfig sc)throws ServletException
	{
		try
		{
			super.init(sc);
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:logindata","","");
			st=con.createStatement();
		}catch(Exception e){System.out.println(e.toString());}
	}
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{ 
		try
		{
 		res.setContentType("text/html");
		out=res.getWriter();
		String u=req.getParameter("una");
		String p=req.getParameter("pwd");
		System.out.println("in the service" +u);
		rs=st.executeQuery("select pwd from mailusers where uname='"+u+"'"); 
		if(rs.next())
		{
			String pwd=rs.getString(1);
			if(pwd.equals(p))
			{
					System.out.println("in the service in password");
				Cookie cook=new Cookie("signin",u);
				res.addCookie(cook);
				//res.sendRedirect("http://localhost:8080/inbox.html");
				out.println("correct username <a href='http://localhost:8080/inbox.html'> inbox");
			}
			else
				out.println("Type correct password");
		}
		else
			out.println("<h1>Invalid user Name,press back button and try again....</h1>"); 
		}catch(Exception e)
		{out.println("Error"+e.toString());}
	}
}