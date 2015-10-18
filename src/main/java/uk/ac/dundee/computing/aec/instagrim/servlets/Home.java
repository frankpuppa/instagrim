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
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;

/**
 *
 * @author frank
 */
@WebServlet(name = "Home", urlPatterns = {"/Home","/Home/*"})
public class Home extends HttpServlet {
    
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
        
        
       HttpSession session = request.getSession();
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        RequestDispatcher rd;
//        String action = request.getParameter("act");
        
            session.setAttribute("user", lg.getUsername());
            String action = (String)request.getParameter("param");
            
            if(action != null){
            rd = request.getRequestDispatcher("/WEB-INF/view/Edit.jsp");
            }else{
            rd = request.getRequestDispatcher("/WEB-INF/view/Home.jsp");
            }
//           response.setContentType("text/html");
          
           getProfilePic(request,response);
           getUserData(request,response);
          // Set standard HTTP/1.1 no-cache headers.
           response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");

          // Set standard HTTP/1.0 no-cache header.
           response.setHeader("Pragma", "no-cache");
           
           rd.forward(request, response);
        //response.sendRedirect("/Instagrim/Home"); 
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
        updateProfile(request,response);
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
    protected void getUserData(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            User us=new User();
            us.setCluster(cluster);
            HttpSession session = request.getSession();
            String username=(String)session.getAttribute("user");
            ArrayList<String> values=us.getUserDetails(username);
            Set<String>followedUsers=us.getFollowedUsers(username);
            //System.out.println("Value array " +values[0]);
            request.setAttribute("userData", values);
            request.setAttribute("followedUsers",followedUsers);
            //System.out.println("Session in servlet "+session);
            
       // }
    }
    protected void updateProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        String[] values =new String[6];
        values[0]=(String)session.getAttribute("user");
        values[1]=request.getParameter("password");
        values[2]=request.getParameter("first_name");
        values[3]=request.getParameter("last_name");
        values[4]=request.getParameter("email");
        values[5]=request.getParameter("address");
        for(int i=0; i<values.length; i++){
                if(values[i]==""){
                    values[i]=null;
                }
            }
        User us=new User();
        us.setCluster(cluster);
        //us.RegisterUser(username, password, first_name, last_name, email, address);
        if(us.updateProfile(values[0],values[1],values[2],values[3],values[4],values[5])){
	response.sendRedirect("/Instagrim");
        }else{
            displayError(response);
        }
    }
    private void displayError(HttpServletResponse response) 
            throws ServletException, IOException {
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Error</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h3> Request could not be satisfied. The Profile has not been update </h3>");
            out.println("</body>");
            out.println("</html>");
        
        }

    }
    protected void getProfilePic(HttpServletRequest request, HttpServletResponse response){
         User us=new User();
            us.setCluster(cluster);
            HttpSession session = request.getSession();
            String username=(String)session.getAttribute("user");
            String picid=us.getProfilePhoto(username);
            request.setAttribute("profilepic",picid);
            //System.out.println("Session in servlet "+session);
    }
}
