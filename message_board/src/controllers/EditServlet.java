package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Message;
import utils.DBUtil;


/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    var em = DBUtil.createEntityManager();
	    
	    var m = em.find(Message.class, Integer.parseInt(request.getParameter("id")));
	    
	    em.close();
	    
	    
	    request.setAttribute("message", m);
	    request.setAttribute("_token", request.getSession().getId());
	    
	    if(em != null) {
	        request.getSession().setAttribute("message_id", m.getId());
	    }
	    
	    
	    var rd = request.getRequestDispatcher("/WEB-INF/views/messages/edit.jsp");
	    rd.forward(request,  response);
	}

}
