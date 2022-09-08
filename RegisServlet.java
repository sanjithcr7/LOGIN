package login;
import jakarta.servlet.*;
import java.io.IOException;
import java.sql.*;
import jakarta.servlet.annotation.*;
import java.io.*;

/**
 * Servlet implementation class RegisServlet
 */
@WebServlet("/regis")
public class RegisServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;
	Connection con;

    /**
     * Default constructor. 
     */
    public RegisServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			String s1=config.getInitParameter("driver");
			String s2=config.getInitParameter("url");
			String s3=config.getInitParameter("name");
			String s4=config.getInitParameter("password");
			Class.forName(s1);
			con=DriverManager.getConnection(s2, s3, s4);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String s1=request.getParameter("fname");
			String s2=request.getParameter("lname");
			String s3=request.getParameter("uname");
			String s4=request.getParameter("pword");
			PreparedStatement ps=con.prepareStatement("insert into uinfo values(?,?,?,?)");
			ps.setString(1,s1);
			ps.setString(2,s2);
			ps.setString(3,s3);
			ps.setString(4,s4);
			ps.executeUpdate();
			PrintWriter pw=response.getWriter();
			pw.println("You have registered successfully");
		} catch(Exception e)
		{
			e.printStackTrace();
		}

		
		
	}

}
