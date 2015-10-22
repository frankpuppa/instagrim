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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import uk.ac.dundee.computing.aec.instagrim.lib.AeSimpleSHA1;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;

/**
 *
 * @author Administrator
 */
public class User {
    Cluster cluster;
    public User(){
        
    }
    protected String getEncodedPassword(String password){
           AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword;
        try {
            EncodedPassword= sha1handler.SHA1(password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return null;
        }
        return EncodedPassword;
       }
    
    public boolean RegisterUser(String username, String password, String first_name, String last_name, String email, String address, String about){
        String EncodedPassword=getEncodedPassword(password);
        if(EncodedPassword==null)
            return false;
        Session session = cluster.connect("instafrank");
        PreparedStatement ps = session.prepare("insert into userprofiles (login,password,first_name,last_name,email,addresses,about) Values(?,?,?,?,?,?,?)");
       
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username,EncodedPassword,first_name,last_name,email,address,about));
        //We are assuming this always works.  Also a transaction would be good here !
        
        return true;
    }
    
    public boolean checkUserExist(String username){
       
        Session session = cluster.connect("instafrank");
        PreparedStatement ps = session.prepare("select login from userprofiles where login=?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs=session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("User does not exist");
            return false;
        } 
        //If another user has the same name it should be not allowed to register.
        return true;  
    }
    
    
    public boolean IsValidUser(String username, String Password){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("instafrank");
        PreparedStatement ps = session.prepare("select password from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return false;
        } else {
            for (Row row : rs) {
               
                String StoredPass = row.getString("password");
                if (StoredPass.compareTo(EncodedPassword) == 0)
                    return true;
            }
        }
   
    return false;  
    }
       public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
       public ArrayList<String> getUserDetails(String username){
        ArrayList<String> values=new ArrayList<>();
        Session session = cluster.connect("instafrank");
        PreparedStatement ps = session.prepare("select login, addresses, email, first_name, last_name,about FROM userprofiles WHERE login=?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return null;
        } else {
            for (Row row : rs) {
               values.add(0, row.getString("login"));
               values.add(1, row.getString("addresses"));
               values.add(2, row.getString("email"));
               values.add(3, row.getString("first_name"));
               values.add(4, row.getString("last_name"));
               values.add(5, row.getString("about"));
//               Set<String> m=row.getSet("follow",  String.class);
//               int j=5;
//                for (String g : m){
//                values.add(j,g);
//                j++;
               
            }
            return values;
        }
       }
       
       public Set<String> getFollowedUsers(String username){
       //ArrayList<String> users=new ArrayList<>();
        Set<String> users = null;
           Session session = cluster.connect("instafrank");
        PreparedStatement ps = session.prepare("select follow FROM userprofiles WHERE login=?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return null;
        } else {
            for (Row row : rs) {
               users=row.getSet("follow",  String.class);
              
//                for (String g : m){
//                values.add(j,g);
//                j++;
//               }
            }
            return users;
        }
       }
       public ArrayList<ArrayList<String>> searchUser(String username){
        
           ArrayList<ArrayList<String>> users= new ArrayList<>();
        
           ResultSet rs = null;
           PreparedStatement ps;
        Session session = cluster.connect("instafrank");
        if(username.equals("*")){
             ps= session.prepare("select * FROM userprofiles");
        
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        ));
        }else{
        ps = session.prepare("select login, first_name, last_name, email FROM userprofiles WHERE login=?");
        
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        }
        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return null;
        } else {
            for (Row row : rs) {
                ArrayList<String> x = new ArrayList<>();
               x.add(0,row.getString("login")); 
               x.add(1,row.getString("first_name"));
               x.add(2,row.getString("last_name"));       
               x.add(3,row.getString("email"));
               users.add(x);
            }
            return users;
        }
       
       }
       public boolean followUser(String userN,String username){
           Set<String> follow=new HashSet<>();
           follow.add(userN);
           Session session = cluster.connect("instafrank");
        PreparedStatement ps = session.prepare("UPDATE userprofiles SET follow = follow + ? WHERE login=?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        follow,username));
        return true;
       }
       
       public boolean unfollowUser(String followeduser, String username){ 
            Set<String> follow=new HashSet<>();
           follow.add(followeduser);
           Session session = cluster.connect("instafrank");
        PreparedStatement ps = session.prepare("UPDATE userprofiles SET follow = follow - ? where login=?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        follow,username));
        return true;
       }
       public boolean updateProfile(String username, String password, String first_name, String last_name, String email, String address,String about){
        String EncodedPassword;
        Session session = cluster.connect("instafrank");
        
        if(password!=null){
            EncodedPassword=getEncodedPassword(password);
                if(EncodedPassword==null){
                    return false;
                }
            PreparedStatement ps = session.prepare("update userprofiles SET password=?, first_name=?, last_name=?,email=?,addresses=?,about=? where login=?");
            BoundStatement boundStatement = new BoundStatement(ps);
            session.execute( // this is where the query is executed
            boundStatement.bind( // here you are binding the 'boundStatement'
                        EncodedPassword,first_name,last_name,email,address,about,username));
        //We are assuming this always works.  Also a transaction would be good here !
        
        
        }else{
            PreparedStatement ps = session.prepare("update userprofiles SET first_name=?, last_name=?, email=?, addresses=? where login=?");
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                       first_name,last_name,email,address,username));
        //We are assuming this always works.  Also a transaction would be good here !
        }
        return true;
    }
       
       public boolean setProfilePhoto(String picid, String username){
        
        Session session = cluster.connect("instafrank");
        PreparedStatement ps = session.prepare("UPDATE userprofiles SET profilepic = ? where login=?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        picid,username));
        
        return true;
       }
       public String getProfilePhoto(String username){
           String picid=null;
           Session session = cluster.connect("instafrank");
        PreparedStatement ps = session.prepare("select profilepic FROM userprofiles WHERE login=?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return null;
        } else {
            for (Row row : rs) {
                picid=row.getString("profilepic");
            }
            return picid;
        }
       
       }
       public String getAbout(String username){
           String about=null;
           Session session = cluster.connect("instafrank");
        PreparedStatement ps = session.prepare("select about FROM userprofiles WHERE login=?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return null;
        } else {
            for (Row row : rs) {
                about=row.getString("about");
            }
            return about;
        }
       
       }
        public boolean setAbout(String username, String about){
           
           Session session = cluster.connect("instafrank");
        PreparedStatement ps = session.prepare("update userprofiles SET about=? where login=?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        about,username));
        return true;  
       }
}   
