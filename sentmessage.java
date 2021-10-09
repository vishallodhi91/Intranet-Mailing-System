import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class sentmessage extends HttpServlet
{
	Connection con;
	ResultSet rs=null;
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
		String mailsel="Select msgfrom,subject from "+una;
		rs=st.executeQuery(mailsel);
		ResultSetMetaData meta=rs.getMetaData();
		int cc = meta.getColumnCount();		
		out.println("<html><h2>Welcome "+una+"@QuickMail.com </h2>");
		out.println("<input type=button name=t1 value=Delete><input type=button name=t2 value=Deselect><h3>Messages in inbox</h3><TABLE BORDER=1><TR><TH>Check</TH><TH>From</TH><TH>Subject</TH><TH>Date</TH></TR>");
		while(rs.next())
		{	
			out.println("<TR><TD align=center><INPUT TYPE=CHECKBOX NAME=CB></TD><TD width=300 align=center>"+rs.getString("msgfrom")+"</TD><TD width=300 align=center><A href='http://localhost3:8080/compose.htm' target=rightf>"+rs.getString("subject")+"</A></TD></TR>");
		}
		out.println("</Table><input type=button name=b1 value=Delete><input type=button name=b2 value=Deselect>");
	}catch(Exception e)
		{out.println(e.toString());}
	}
}