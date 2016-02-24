/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empmanagement.test;

import com.employeemanagement.common.Common;
import static com.employeemanagement.common.Common.getVoterDir;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 *
 * @author arup
 */
public class DB {
    
    
    public static void main(String args[]) throws IOException{
       // File src = new File(Common.getPhotoDir());
       // File dest = new File(Common.getDbBackupPath()+"/A");
        //copyFolder(src, dest);
        
        //restoreDB("emp_management", "arup", "arup", "/home/arup/LATEST/DBBACKUP/30_Aug_2013/ggg/ggg.sql");
        String p = "/home/arup/LATEST/DBBACKUP/30_Aug_2013/ggg/ggg.sql";
        
        File f1 = new File(p);
        
        System.out.println(f1.getParent());
    }
    
    

public static void getDbBackup() throws IOException{

    String dbname = "emp_management";
    
    Date dt = new Date();
    
    String date = Common.dbbackuoformatter.format(dt);
    String fileName="db4";
    System.err.println("date "+date);
    String path = Common.getDbBackupPath() + Common.getSeparator() + date + Common.getSeparator()+fileName;
        
        File f = new File(path);
               if(!f.exists())
                   f.mkdirs();
               
    
   String sqlfilePath = path +Common.getSeparator()+fileName+".sql";
   
        System.err.println("sql path "+sqlfilePath);
   
   f= new File(sqlfilePath);
   if(!f.exists())
       f.createNewFile(); 
   
    String executeCmd = "mysqldump -u "+Common.getDbUserName()+" -p"+Common.getDbPassword()+" --add-drop-database -B " + dbname + " -r " + sqlfilePath;
        Process runtimeProcess;
        try {
 
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
 
            if (processComplete == 0) {
                System.out.println("Backup created successfully");
                
                
                
                
                
         
            } else {
                System.out.println("Could not create the backup");
            }
        } catch (Exception ex) {
            ex.printStackTrace();       

}
}
    
    public static void copyFolder(File src, File dest)
    	throws IOException{
 
    	if(src.isDirectory()){
 
    		//if directory not exists, create it
    		if(!dest.exists()){
    		   dest.mkdir();
    		   System.out.println("Directory copied from " 
                              + src + "  to " + dest);
    		}
 
    		//list all the directory contents
    		String files[] = src.list();
 
    		for (String file : files) {
    		   //construct the src and dest file structure
    		   File srcFile = new File(src, file);
    		   File destFile = new File(dest, file);
    		   //recursive copy
    		   copyFolder(srcFile,destFile);
    		}
 
    	}else{
    		//if file, then copy it
    		//Use bytes stream to support all file types
    		InputStream in = new FileInputStream(src);
    	        OutputStream out = new FileOutputStream(dest); 
 
    	        byte[] buffer = new byte[1024];
 
    	        int length;
    	        //copy the file content in bytes 
    	        while ((length = in.read(buffer)) > 0){
    	    	   out.write(buffer, 0, length);
    	        }
 
    	        in.close();
    	        out.close();
    	        System.out.println("File copied from " + src + " to " + dest);
    	}
    }
    
    
    public static boolean restoreDB(String dbName, String dbUserName, String dbPassword, String source) {
 
        String[] executeCmd = new String[]{"mysql", "--user=" + dbUserName, "--password=" + dbPassword, dbName,"-e", "source "+source};
 
        Process runtimeProcess;
        try {
 
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
 
            if (processComplete == 0) {
                System.out.println("Backup restored successfully");
                return true;
            } else {
                System.out.println("Could not restore the backup");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
 
        return false;
    }
   
}