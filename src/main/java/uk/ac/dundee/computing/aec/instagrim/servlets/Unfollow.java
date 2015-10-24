/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.User;

/**
 *
 * @author frank
 */
@WebServlet(name = "Unfollow", urlPatterns = {"/Unfollow"})
public class Unfollow extends HttpServlet {

    Cluster cluster=null;
    
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = (String)request.getParameter("follow");
        unFollow(request,response,action);
        //System.out.println("The action is " + action);
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    protected void unFollow(HttpServletRequest request, HttpServletResponse response,String userfollowed)
            throws ServletException, IOException {
        String path=request.getContextPath();
             User us=new User();
            us.setCluster(cluster);
            HttpSession session = request.getSession();
            String username=(String)session.getAttribute("user");
            boolean test=us.unfollowUser(userfollowed,username);
            if(test){
                //Ask Andy
//                Set<String> followedUsersSet=(Set<String>)session.getAttribute("followedUserSet");
//                 followedUsersSet.remove(userfollowed);
//                 session.removeAttribute("followedUserSet");
//                 session.setAttribute("followedUserSet", followedUsersSet);
                 //ystem.out.println("OK USER ADDED");
                 response.sendRedirect(path + "/Home");
             }else{
                 displayError(response,userfollowed);
             }
    }
    private void displayError(HttpServletResponse response, String user) 
            throws ServletException, IOException {
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Error</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h3> Request could not be satisfied. The user " + user + " has not been removed from the follow list </h3>");
            out.println("</body>");
            out.println("</html>");
        
        }

    }
}
