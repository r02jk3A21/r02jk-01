

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DatabaseComminInterface;

/**
 * Servlet implementation class Gamen9Servlet
 */
@WebServlet("/Gamen9")
public class Gamen9Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Gamen9Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			/*String PRO_id = (String)request.getAttribute("PRO_id");*/
			request.setCharacterEncoding("UTF-8");
			Connection con = DatabaseComminInterface.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT�@PRO.pro_name as pname, REPO.repo_date as repodate�@,  USERX.user_name as uname ,TASK.task_name as tname , USERX.user_id as uid FROM PRO�@"
					+ "JOIN�@REPO on PRO.pro_id  = REPO.pro_id "
					+ "JOIN PROMEN on PRO.pro_id�@= PROMEN.pro_id "
					+ "JOIN TASK on PRO.pro_id�@= TASK.pro_id "
					+ "JOIN USERX on PROMEN.user_id�@= USERX.user_id "
					+ "where REPO.pro_id = ? order by USERX.user_id asc");
			
			/*�Q���҂̕񍐂�\������̂ɕK�v�ȏ��𒊏o����sql*/
			
			pstmt.setString(1, "1");
			ResultSet rs = pstmt.executeQuery();
			ArrayList<String[]> resultList = new ArrayList<>();
			
			while(rs.next() == true) {
				String[] ss = new String[5];
				ss[0]=rs.getString("pname");
				ss[1]=rs.getString("repodate");
				ss[2]=rs.getString("uname");
				ss[3]=rs.getString("tname");
				ss[4]=rs.getString("uid");
				resultList.add(ss);
			}
			request.setAttribute("resultList", resultList);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/gamen9.jsp");
			rd.forward(request, response);
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace(out);
			e.printStackTrace(System.out);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(out);
			e.printStackTrace(System.out);
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String[]> list = new ArrayList<String[]>();
		int a=0;
		while(a<6) {
			String[] s=new String[2];
			s[0]="TaskName"+a;
			s[1]="WorkName"+a;
			list.add(s);
			a++;
		}
		String Wname="hoge";
		request.setAttribute("name", Wname);
		request.setAttribute("result", list);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/gamen9.jsp");
		rd.forward(request, response);
	}

}
