import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class getmsg extends HttpServlet
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
		String mfrom=req.getParameter("msgf");
		String mdate=req.getParameter("msgd");
		
		String una=null;
		Cookie[] c = req.getCookies();
		if(c!=null)
		for(int i=0;i<c.length;i++)
		{
			if(c[i].getName().equals("signin"))
				una=c[i].getValue();
			break;
		}

		String selst="select * from "+una+" where msgfrom='"+mfrom+"' and msgdate='"+mdate+"'";
		rs=st.executeQuery(selst);
		while(rs.next())
		{
			out.println("<a href=http://localhost:8080/servlet/inboxlocalhost target=rightf>Inbox</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href= target=rightf>Next</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href= target=rightf>Previous</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href= target=rightf>Reply</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href= target=rightf>Reply all</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href= target=rightf>Delete</a><p>");
			out.println("<body><table><tr><td align=right width=100> Message From  :<td width=200>"+rs.getString(1));
			out.println("<tr><td align=right width=100> Subject : <td width=200>"+rs.getString(2)+"</table>");
			out.println("<h4><i>"+mfrom+"</i> Wrote </h4><br>"+rs.getString(3));
			out.println("<p><a href=http://localhost:8080/servlet/inboxserver target=rightf>Inbox</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href= target=rightf>Next</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href= target=rightf>Previous</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href= target=rightf>Reply</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href= target=rightf>Reply all</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href= target=rightf>Delete</a>");
		}
		}catch(Exception e)
		{out.println(e.toString());}
	}
}
