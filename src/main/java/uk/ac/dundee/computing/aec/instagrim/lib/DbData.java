/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.lib;

import com.datastax.driver.core.Cluster;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.models.User;

/**
 *
 * @author frank
 */
public class DbData {
     Cluster cluster;
    public DbData(){
    cluster = CassandraHosts.getCluster();
    }
    
public String getProfilePic(String username){
         User us=new User();
            us.setCluster(cluster);
            String picid=us.getProfilePhoto(username);
            //System.out.println("Session in servlet "+session);
            return picid;
    }   
public String getAbout(String username){
    User us=new User();
            us.setCluster(cluster);
            String picid=us.getAbout(username);
            //System.out.println("Session in servlet "+session);
            return picid;
}
}
