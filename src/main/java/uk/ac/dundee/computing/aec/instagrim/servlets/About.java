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
import java.util.Collections;
import java.util.Comparator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.lib.DbData;
import uk.ac.dundee.computing.aec.instagrim.models.Comment;

/**
 *
 * @author frank
 */
@WebServlet(name = "About", urlPatterns = {
    "/About",
    "/About/*"
})
public class About extends HttpServlet {
    
    private Cluster cluster;
    
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
        String username=(String)session.getAttribute("user");
        String args[] = Convertors.SplitRequestPath(request);
        request.setAttribute("uservisited",args[2]);
        
        DbData db=new DbData();
        String picid=db.getProfilePic(args[2]);
        String about=db.getAbout(args[2]);
        
        Comment cm=new Comment();
        cm.setCluster(cluster);
        
        ArrayList<ArrayList<String>> guestbook=cm.getGuestBook(args[2]);
        if(guestbook!=null){
        Collections.sort(guestbook, new Comparator<ArrayList<String>>() {    
        @Override
        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
            return o1.get(2).compareTo(o2.get(2));
        }               
});
        }     
        request.setAttribute("guestbook",guestbook);
        request.setAttribute("profilepic",picid);
        request.setAttribute("about", about);
        
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/WEB-INF/view/About.jsp");
        rd.forward(request, response);
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
            case "guestbook":
             //write comment
                writeGuestBook(request,response);
                break;
            case "Delete":
                deleteGuestBookEntry(request,response);
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
      
      private void writeGuestBook(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
         
        HttpSession session = request.getSession();
        String username=(String)session.getAttribute("user");
        if(username==null){
            username="Anonymous";
        }
        
        String path=request.getContextPath();
        String message=request.getParameter("guestbook");
        String args[] = Convertors.SplitRequestPath(request);
        
        Comment cm=new Comment();
        cm.setCluster(cluster);
        
        if(cm.addGuestBookEntry(args[2],username,message)){
            response.sendRedirect(path + "/About/" + args[2]);
        }else {
            displayError("About could not be updated! Sorry...",response);
        }
      }

    private void deleteGuestBookEntry(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        HttpSession session = request.getSession();
        String username=(String)session.getAttribute("user");
        String path=request.getContextPath();
        String guestbookid=request.getParameter("delete");
        
        Comment cm=new Comment();
        cm.setCluster(cluster); 
       
        if(cm.deleteguestBookEntry(guestbookid)){
            
            response.sendRedirect(path + "/About/" + username );
        }else {
            displayError("Guestbook entry cannot be delete!! Sorry...",response);
        }
        
    }
}

