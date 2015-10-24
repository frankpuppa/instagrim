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
import java.util.HashMap;
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
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.lib.DbData;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;

/**
 *
 * @author frank
 */
@WebServlet(name = "Home", urlPatterns = {"/Home","/Home/Edit", "/Home/EditAbout"})
public class Home extends HttpServlet {
    
    Cluster cluster=null;
    private HashMap CommandsMap; 
    
    
    public void init(ServletConfig config) throws ServletException {
        CommandsMap = new HashMap();
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
        CommandsMap.put("Edit", 1);
        CommandsMap.put("EditAbout", 2);
       
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
        
        if(session.getAttribute("user")==null){
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn"); 
        session.setAttribute("user", lg.getUsername());
        
        }
        setFollowedUs(request);
        
        RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/view/Home.jsp");
        
        String args[] = Convertors.SplitRequestPath(request);
        if(args.length <3 ){
                rd = request.getRequestDispatcher("/WEB-INF/view/Home.jsp");
                getProfilePic(request,response);
        }else{
        int command;
        try {
            command = (Integer) CommandsMap.get(args[2]);
        } catch (Exception et) {
            displayError("Bad Operator", response);
            return;
        }
        
            switch (command) {
                case 1:
                    rd = request.getRequestDispatcher("/WEB-INF/view/Edit.jsp");
                    break;
                case 2:
                    rd = request.getRequestDispatcher("/WEB-INF/view/EditAbout.jsp");
                    break;
                default:
                    displayError("Bad Operator", response);
            }
        }
       
          
           
           getUserData(request,response);
          // Set standard HTTP/1.1 no-cache headers.
           response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");

          // Set standard HTTP/1.0 no-cache header.
           response.setHeader("Pragma", "no-cache");
           
           rd.forward(request, response);
        //response.sendRedirect("/InstagrimFrank/Home"); 
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
        String value=request.getParameter("submit");
        switch (value) {
            case "about":
                updateAbout(request, response);
                break;
            case "edit":
                updateProfile(request,response);
                break;
            default:
                displayError("Values cannot be updated!!",response);
                break;
        }
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
        String path=request.getContextPath();
	
        
        String[] values =new String[6];
        values[0]=(String)session.getAttribute("user");
        values[1]=request.getParameter("password");
        values[2]=request.getParameter("first_name");
        values[3]=request.getParameter("last_name");
        values[4]=request.getParameter("email");
        values[5]=request.getParameter("address");
        values[6]=request.getParameter("about");
        for(int i=0; i<values.length; i++){
                if(values[i]==""){
                    values[i]=null;
                }
            }
        User us=new User();
        us.setCluster(cluster);
        //us.RegisterUser(username, password, first_name, last_name, email, address);
        if(us.updateProfile(values[0],values[1],values[2],values[3],values[4],values[5],values[6])){
	response.sendRedirect(path);
        }else{
            displayError("Profile Could not be update! Sorry...",response);
        }
    }
    protected void updateAbout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username=(String)session.getAttribute("user");
        
        String path=request.getContextPath();
        String about=request.getParameter("about");
	///To finish update About
        // Fix Register
        //Check for wildchar
        // Check for errors
        
        User us=new User();
        us.setCluster(cluster);
        //us.RegisterUser(username, password, first_name, last_name, email, address);
        if(us.setAbout(username, about)){
	response.sendRedirect(path + "/Home");
        }else{
            displayError("About could not be updated! Sorry...",response);
        }
    }
    private void displayError(String error,HttpServletResponse response) 
            throws ServletException, IOException {
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Error</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>"+ error+ "  </h3>");
            out.println("</body>");
            out.println("</html>");
        
        }

    }
    protected void getProfilePic(HttpServletRequest request, HttpServletResponse response){
  
            HttpSession session = request.getSession();
            String username=(String)session.getAttribute("user");
            DbData db=new DbData();
            String picid=db.getProfilePic(username);
            request.setAttribute("profilepic",picid);
            //System.out.println("Session in servlet "+session);
    }
    
    protected void setFollowedUs(HttpServletRequest request){
        HttpSession session = request.getSession();
        String username=(String)session.getAttribute("user");
        User us =new User();
        us.setCluster(cluster);
        Set<String> followedSet=us.getFollowedUsers(username);
        if(session.getAttribute("followedUserSet")!=null){
            session.removeAttribute("followedUserSet");
        }
        session.setAttribute("followedUserSet", followedSet);
    }
}
