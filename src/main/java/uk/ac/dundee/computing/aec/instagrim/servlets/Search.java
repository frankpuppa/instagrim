/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;


/**
 *
 * @author frank
 */
@WebServlet(name = "Search", urlPatterns = {"/Search"})
public class Search extends HttpServlet {
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
    
    HttpSession session = request.getSession();
     //   LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        RequestDispatcher rd;
//        String action = request.getParameter("act");
        
         //   session.setAttribute("user", lg.getUsername());
            rd = request.getRequestDispatcher("/WEB-INF/view/Search.jsp");
//           response.setContentType("text/html");
           String user = request.getParameter("fname");
           if(user!=null && !user.equals("")){
               
               searchUser(request, response ,user);
           }
         
          // Set standard HTTP/1.1 no-cache headers.
           response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
          // Set standard HTTP/1.0 no-cache header.
           response.setHeader("Pragma", "no-cache");
          //forward request 
           rd.forward(request, response);
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

     /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void searchUser(HttpServletRequest request, HttpServletResponse response,String username)
            throws ServletException, IOException {
        
        User us=new User();
            us.setCluster(cluster);
            //HttpSession session = request.getSession();
            //String username=(String)session.getAttribute("user");
            ArrayList<ArrayList<String>> users=us.searchUser(username);
            Set<String>followedUsers=us.getFollowedUsers(username);
            //System.out.println("Value array " +values[0]);
             request.setAttribute("usersV", users);
             request.setAttribute("followedUsers", followedUsers);
            //System.out.println("Session in servlet "+session);
        
    }
    
    
}
