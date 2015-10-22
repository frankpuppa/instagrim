package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.models.PicModel;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.models.Comment;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;

/**
 * Servlet implementation class Image
 */
@WebServlet(urlPatterns = {
    "/Image",
    "/Image/*",
    "/Thumb/*",
    "/Images",
    "/Images/*",
    "/DeletePhoto",
    "/SetProfile",
    "/AddComment"
})
@MultipartConfig

public class Image extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Cluster cluster;
    private HashMap CommandsMap = new HashMap();
    
    

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Image() {
        super();
        // TODO Auto-generated constructor stub
        CommandsMap.put("Image", 1); //upload
        CommandsMap.put("Images", 2);
        CommandsMap.put("Thumb", 3);
        CommandsMap.put("DeletePhoto",4);
        CommandsMap.put("SetProfile", 5);
        CommandsMap.put("AddComment",6);

    }

    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String args[] = Convertors.SplitRequestPath(request);
        int command;
        try {
            command = (Integer) CommandsMap.get(args[1]);
        } catch (Exception et) {
            error("Bad Operator", response);
            return;
        }
        //argv[0]=Instagrim argv[1]=Images argv[3]=username
        switch (command) {
            case 1:
                DisplayImage(Convertors.DISPLAY_PROCESSED,args[2], response);
                break;
            case 2: 
                DisplayImageList(args[2], request, response);
                break;
            case 3:
                DisplayImage(Convertors.DISPLAY_THUMB,args[2],  response);
                break;
            case 4:
                    deletePhoto(request,response);
                break;
            case 5:
                    updateProfilePic(request,response);
                break;
            case 6:
                    addComment(request,response);
                break;
            default:
                error("Bad Operator", response);
        }
    }

    private void DisplayImageList(String User, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        User us=new User();
        us.setCluster(cluster);
        
        Set<String>followedUsers=us.getFollowedUsers(User);
        
        PicModel tm = new PicModel();
        tm.setCluster(cluster);
        
        LinkedList<Pic> lsPics=null;
        lsPics= tm.getPicsForUser(User, lsPics);
        
        if(followedUsers!=null) {
//            if(lsPics==null)
//                lsPics=new LinkedList<>();
        for (String user : followedUsers){
           lsPics= tm.getPicsForUser(user, lsPics); 
            }
        }
        Comment cm=new Comment();
        cm.setCluster(cluster);
        
        if(lsPics !=null){
        Iterator<Pic> iterator=lsPics.iterator();
        ArrayList<ArrayList<String>> comments=new ArrayList<>();
       
        while (iterator.hasNext()) {
        Pic p = (Pic) iterator.next();
                comments=cm.getComments(comments, p.getSUUID());
        }
        
        //Sort linked list
    
        
    //Found part of this online. Then modified to suit my needs
    Collections.sort(lsPics, new Comparator<Pic>() {
        @Override
        public int compare(Pic m1, Pic m2) {
            if(m1.getDate().compareTo(m2.getDate()) <1){
                return 1;
            }else if(m1.getDate().compareTo(m2.getDate()) ==0){
                return 0;
            }else{
                return -1;
            }
           
     }
    });
        
        
        request.setAttribute("Pics", lsPics);
        request.setAttribute("Comments",comments );
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/UsersPics.jsp");
        rd.forward(request, response);

    }

    private void DisplayImage(int type,String Image, HttpServletResponse response) throws ServletException, IOException {
        PicModel tm = new PicModel();
        tm.setCluster(cluster);
  
        
        Pic p = tm.getPic(type,java.util.UUID.fromString(Image));
        
        OutputStream out = response.getOutputStream();

        response.setContentType(p.getType());
        response.setContentLength(p.getLength());
        //out.write(Image);
        InputStream is = new ByteArrayInputStream(p.getBytes());
        BufferedInputStream input = new BufferedInputStream(is);
        byte[] buffer = new byte[8192];
        for (int length = 0; (length = input.read(buffer)) > 0;) {
            out.write(buffer, 0, length);
        }
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        for (Part part : request.getParts()) {
            System.out.println("Part Name " + part.getName());

            String type = part.getContentType();
            String filename = part.getSubmittedFileName();
            String path=request.getContextPath();
            
            InputStream is = request.getPart(part.getName()).getInputStream();
            int i = is.available();
            HttpSession session=request.getSession();
            LoggedIn lg= (LoggedIn)session.getAttribute("LoggedIn");
            String username= lg.getUsername(); //="majed";
            if (lg.getlogedin()){
                username=lg.getUsername();
            }
            if (i > 0) {
                byte[] b = new byte[i + 1];
                is.read(b);
                System.out.println("Length : " + b.length);
                PicModel tm = new PicModel();
                tm.setCluster(cluster);
                tm.insertPic(b, type, filename, username);

                is.close();
            }
            response.sendRedirect(path +"/Images/" + username);
           // RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/upload.jsp");
           //  rd.forward(request, response);
        }

    }

    private void error(String mess, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = null;
        out = new PrintWriter(response.getOutputStream());
        out.println("<h1>You have a na error in your input</h1>");
        out.println("<h2>" + mess + "</h2>");
        out.close();
        return;
    }
    protected void deletePhoto(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        HttpSession session = request.getSession();
        String path=request.getContextPath();
        String username=(String)session.getAttribute("user");
        String picid = (String)request.getParameter("delete");
        PicModel pm=new PicModel();
            pm.setCluster(cluster);
          if (pm.deletePhoto(picid, username)) {
            response.sendRedirect(path + "/Images/" + username);
        } else {
           error("Photo Could not be deleted", response);
        }
         
            
    }
    protected void updateProfilePic(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        HttpSession session = request.getSession();
        String path=request.getContextPath();
        String username=(String)session.getAttribute("user");
        String picid = (String)request.getParameter("setProfile");
        
        User us=new User();
        us.setCluster(cluster);
        if(us.setProfilePhoto(picid, username)){
	response.sendRedirect(path + "/Home");
        }else{
            error("Profile Picture could not be set", response);
        } 
    }
    protected void addComment(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        HttpSession session = request.getSession();
        String path=request.getContextPath();
        String username=(String)session.getAttribute("user");
        String picid = (String)request.getParameter("picid");
        String comment =(String)request.getParameter("comment");
        
        Comment cm=new Comment();
        cm.setCluster(cluster);
        if(cm.addComment(picid,comment,username)){
	response.sendRedirect(path + "/Images/" + username);
        }else{
            error("Profile Comment could not be added", response);
        }
      
    }
}
