import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class userlogin extends HttpServlet
{
	Connection con;
	Statement st;
	PrintWriter out;
	PreparedStatement prest;
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
	String username=req.getParameter("t1");
	String password=req.getParameter("t2");
	int age=Integer.parseInt(req.getParameter("t4"));
	String gender=req.getParameter("r1");
	String sex=null;
	if(gender.equals("Male"))
		sex="Male";
	else if(gender.equals("Female"))
		sex="Female";
	String city=req.getParameter("t6");
	String state=req.getParameter("t7");
	int pincode=Integer.parseInt(req.getParameter("t8"));
	String nation=req.getParameter("t9");
	Cookie cook1=new Cookie("signin1",username);
				res.addCookie(cook1);

	String Query="insert into mailusers values('"+username+"','"+password+"',"+age+",'"+sex+"','"+city+"','"+state+"',"+pincode+",'"+nation+"')";
	st.execute(Query);
	String UserTabCre ="create table "+username+"(msgfrom varchar,subject varchar,msg varchar,msgdate varchar)";
	String UserAddCre ="create table "+username+"addbook(name varchar,nick varchar,addr varchar)";
	String UserFolder="create table "+username+"folder(foldername varchar primary key,totalmails number)";
	System.out.println(UserTabCre);
	prest=con.prepareStatement(UserTabCre);
	prest.execute();
	prest=con.prepareStatement(UserAddCre);
	prest.execute();
	prest=con.prepareStatement(UserFolder);
	prest.execute();
	//res.sendRedirect("http://localhost:8080/inbox1.html");
	res.sendRedirect("http://localhost:8080/inbox.html");
	}catch(Exception e)
		{
		out.println(e.toString());
		e.printStackTrace();
		}
	//res.sendRedirect("http://localhost:8080/usersignup.html");}
}
}
