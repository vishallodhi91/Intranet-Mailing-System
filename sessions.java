import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class sessions extends HttpServlet
{
 public void init(ServletConfig sc) throws ServletException
	{
 		super.init(sc);
	}
 public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	res.setContentType("html/text");
	PrintWriter out=res.getWriter();
	HttpSession ses=req.getSession(true);		
	if(ses.isNew())
	{
		ses.putValue("userid","1");
	}
else
		{
String val=(String)ses.getValue("userid");
out.println(val);
int i=new Integer(val).intValue()+1;
ses.putValue("userid",new Integer(i));
}
}
}
	