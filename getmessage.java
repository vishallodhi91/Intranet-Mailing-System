import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class getmessage extends HttpServlet
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
		String folder=req.getParameter("foldname");
//out.println("foldername="+folder);
		String mfrom1=req.getParameter("from1");
		String msub1=req.getParameter("sub1");
		String mmsg1=req.getParameter("msg1");
		String mdat1=req.getParameter("dat1");
		
		/*String selst="select * from "+folder+" where message='"+mfrom1+"' and to_char(dat,'dd-mon-yyyy:hh:mm:ss')='"+mdate1+"'";
		rs=st.executeQuery(selst);
		while(rs.next())
		{
			String from3=rs.getString(1);
			String sub3=rs.getString(2);
			String msg3=rs.getString(3);
			String dat3=rs.getString(4);
			out.println("<a href=http://localhost:8080/servlet/inboxlocalhost target=rightf>Inbox</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href= target=rightf>Next</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href= target=rightf>Previous</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href= target=rightf>Reply</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href= target=rightf>Reply all</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href= target=rightf>Delete</a><p>");*/
			out.println("<body><table><tr><td align=right width=100> Message From  :<td width=200>"+mfrom1);
			out.println("<tr><td align=right width=100> Subject : <td width=200>"+msub1+"</table>");
			out.println("<h4><i>"+mfrom1+"</i> Wrote </h4><br>"+mmsg1);
			out.println("<p><a href=http://localhost3:8080/servlet/inboxlocalhost target=rightf>Inbox</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href= target=rightf>Next</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href= target=rightf>Previous</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href= target=rightf>Reply</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href= target=rightf>Reply all</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href= target=rightf>Delete</a>");
		
		}catch(Exception e)
		{out.println(e.toString());}
	}
}
