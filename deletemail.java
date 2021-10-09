import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;
public class deletemail extends HttpServlet
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
			{
			una=c[i].getValue();
			break;
			}
		}
	//Deleting the unwanted mails
		Enumeration names = req.getParameterNames();
		StringTokenizer str; 	
         	while(names.hasMoreElements())
         	{
           	String name = (String)names.nextElement();
           	String value = req.getParameter(name);
	if(value.equals("on"))
		{
		str=new StringTokenizer(name,"|");
		while(str.hasMoreTokens())
			{
			String mfrom=str.nextToken();
			String mdat=str.nextToken();
			String del="delete from "+una+" where msgfrom='"+mfrom+"' and msgdate='"+mdat+"'";
			st.executeUpdate(del);
			con.commit();

			}
		}
	}
	
	//Remaining mails
				
		String mailsel="select msgfrom,subject,msgdate from "+una;
		rs=st.executeQuery(mailsel);
		out.println("<html><BODY BGCOLOR=AQUA><form action='http://localhost:8080/servlet/deletemail' method=post><h2>Welcome <FONT COLOR=PINK>"+una+"@QuickMail.com </FONT></h2>");
		out.println("<input type=submit name=t1 value=Delete><h3>Messages in inbox</h3><TABLE BORDER=1><TR><TH>Check</TH><TH>From</TH><TH>Subject</TH><TH>Date</TH></TR>");
		while(rs.next())
		 {	
			String from=rs.getString(1);
			String sub=rs.getString(2);
			String dat=rs.getString(3);
			out.println("<TR><TD><INPUT TYPE=CHECKBOX NAME="+from+"|"+dat+">");
			out.println("<TD width=200 align=center><A href=http://localhost:8080/servlet/getmsg?msgf="+from+"&msgd="+dat+" target=rightf>"+from+"</A>");
			out.println("<TD width=200 align=center>"+sub+"<TD width=200 align=center>"+dat+"</TR>");
		}
		out.println("</Table><p><input type=submit name=b1 value=Delete></form></BODY>");
		}catch(Exception e)
		{out.println(e.toString());}
	}
}