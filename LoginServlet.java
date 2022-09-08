package login;

import jakarta.servlet.*;

import java.io.*;
import java.sql.*;

import jakarta.servlet.annotation.*;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;
    Connection con;
    /**
     * @see GenericServlet#GenericServlet()
     */
    public LoginServlet() {
        super();
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
			String s1=request.getParameter("uname");
			String s2=request.getParameter("pword");
			PreparedStatement ps=con.prepareStatement("select * from uinfo where uname=? and password=?");
			ps.setString(1, s1);
			ps.setString(2, s2);
			ResultSet rs=ps.executeQuery();
			PrintWriter pw=response.getWriter();
			if(rs.next()) {
				pw.println("Welcome "+s1);
			}
			else {
				pw.println("Invalid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}


