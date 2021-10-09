import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;
public class deletefolder extends HttpServlet
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
		String fold=req.getParameter("folder");
out.println("folder="+fold);
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
out.println("name="+name);
           	String value = req.getParameter(name);
out.println("value="+value);
	if(value.equals("on"))
		{
		str=new StringTokenizer(name,"|");
		while(str.hasMoreTokens())
			{
			String mfrom=str.nextToken();
out.println("mfrom="+mfrom);
			String mdat=str.nextToken();
out.println("mdat="+mdat);
			String del="delete from "+fold+" where frommail='"+mfrom+"' and dat='"+mdat+"'";
out.println(del);
			st.executeUpdate(del);
			
out.println("row deleted");con.commit();
			}
		}
	}
	
	//Remaining mails
				
		String mailsel="select frommail,subject,dat from "+fold;
		rs=st.executeQuery(mailsel);
		out.println("<html><BODY BGCOLOR=AQUA><form action='http://localhost:8080/servlet/deletefolder' method=post><h2>Mails in <FONT COLOR=PINK>"+fold+"</FONT></h2>");
		out.println("<input type=submit name=t1 value=Delete><TABLE BORDER=1><TR><TH>Check</TH><TH>From</TH><TH>Subject</TH><TH>Date</TH></TR>");
		while(rs.next())
		 {	
			String from=rs.getString(1);
out.println("from="+from);
			String sub=rs.getString(2);
out.println("sub="+sub);
			String dat=rs.getString(3);
out.println("dat="+dat);
			out.println("<TR><TD><INPUT TYPE=CHECKBOX NAME="+from+"|"+dat+">");
			out.println("<TD width=200 align=center><A href=http://localhost:8080/servlet/getmessage?from1="+from+"&dat1="+dat+" target=rightf>"+from+"</A>");
			out.println("<TD width=200 align=center>"+sub+"<TD width=200 align=center>"+dat+"</TR>");
		}
		out.println("</Table></form></BODY>");
		}catch(Exception e)
		{out.println(e.toString());}
	}
}