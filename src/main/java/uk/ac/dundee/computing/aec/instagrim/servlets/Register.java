/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.User;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {
    Cluster cluster=null;
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
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

        
        
        String[] values =new String[7];

        values[0]=request.getParameter("username");
        values[1]=request.getParameter("password");
        values[2]=request.getParameter("first_name");
        values[3]=request.getParameter("last_name");
        values[4]=request.getParameter("email");
        values[5]=request.getParameter("address");
        values[6]="Please modify your about page!";
        for(int i=2; i<7; i++){
                if(values[i].equals("")){
                    values[i]="";
                }
            }
        if(values[0].equals("") || values[1].equals("")){
            displayError("Username and password are mandatory",response);
        }
        User us=new User();
        us.setCluster(cluster);
        if(us.checkUserExist(values[0])){
            displayError("Username already taken. Please choose another one",response);
        }
        us.RegisterUser(values[0],values[1],values[2],values[3],values[4],values[5],values[6]);
        String path=request.getContextPath();
	response.sendRedirect(path + "/Login");
        
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
         RequestDispatcher rd;
        rd = request.getRequestDispatcher("/WEB-INF/view/Register.jsp");
         rd.forward(request,response);
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
}
