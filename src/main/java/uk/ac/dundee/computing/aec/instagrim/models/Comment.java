/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
/**
 *
 * @author frank
 */
public class Comment {
    Cluster cluster;
    private Object convertor;
    
    public Comment(){}
    
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
    
    public ArrayList<ArrayList<String>> getComments(ArrayList<ArrayList<String>> comments,String picid){
        //ArrayList<ArrayList<String>> comments=new ArrayList<>();
        UUID id= UUID.fromString(picid);
        /*** Took this part from web StackOverflow**/
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        
        
        Session session = cluster.connect("instafrank");
        PreparedStatement ps = session.prepare("select comment, user,date,commentid FROM comment WHERE picid=? ALLOW FILTERING");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        id));
        if (rs.isExhausted()) {
            System.out.println("No Comment returned");
            return comments;
        } else {
            for (Row row : rs) {
                ArrayList<String> x = new ArrayList<>();
                x.add(0, picid);
                x.add(1,row.getString("comment")); 
                x.add(2,row.getString("user"));
                x.add(3,df.format(row.getDate("date"))); //convert date to string
              //  UUID commentid=row.getUUID("commentid");
                x.add(4,row.getUUID("commentid").toString());
                comments.add(x);
            }
            return comments;
            }
        
    }
    
    public boolean addComment(String picid,String comment, String user){
        
        Convertors convertor = new Convertors();
        
        UUID id= UUID.fromString(picid);
        java.util.UUID commentid = convertor.getTimeUUID();
        
        Date CommentAdded = new Date();
        
        Session session = cluster.connect("instafrank");
        PreparedStatement ps = session.prepare("insert into comment (commentid,picid,comment,user,date) VALUES (?,?,?,?,?)");
        
        BoundStatement boundStatement = new BoundStatement(ps);
       session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        commentid,id,comment,user,CommentAdded));
        return true;
    
    }
    
    public boolean deleteComment(String commentid){
        
        Convertors convertor = new Convertors();
        
        UUID id= UUID.fromString(commentid);
        
        Session session = cluster.connect("instafrank");
        PreparedStatement ps = session.prepare("delete from comment where commentid=?");
        
        BoundStatement boundStatement = new BoundStatement(ps);
       session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        id));
        return true;
    
    }
    
    public boolean addGuestBookEntry(String user_owner, String user, String message){
        Convertors convertor = new Convertors();
        
        
        java.util.UUID bookid = convertor.getTimeUUID();
        
        Date date = new Date();
        
        Session session = cluster.connect("instafrank");
        PreparedStatement ps = session.prepare("insert into guestbook (bookid,message,user_owner,user,date) VALUES (?,?,?,?,?)");
        
        BoundStatement boundStatement = new BoundStatement(ps);
       session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        bookid,message,user_owner,user,date));
        return true;
        
    }
    
    public ArrayList<ArrayList<String>> getGuestBook(String username){
        
        ArrayList<ArrayList<String>> guestbook=new ArrayList<>();
        /*** Took this part from web StackOverflow**/
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        
        
        Session session = cluster.connect("instafrank");
        PreparedStatement ps = session.prepare("select user,message,date FROM guestbook WHERE user_owner=?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No guest messages returned");
            return null;
        } else {
            for (Row row : rs) {
                ArrayList<String> x = new ArrayList<>();
                x.add(0, row.getString("user"));
                x.add(1,row.getString("message")); 
             //   if(row.getDate("date")!=null){
                x.add(2,df.format(row.getDate("date"))); //convert date to string
              //  }
                guestbook.add(x);
            }
            return guestbook;
            }
        
    }
    
   
}
