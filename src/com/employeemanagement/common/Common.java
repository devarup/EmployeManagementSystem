/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employeemanagement.common;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author arup
 */
public class Common {

    public static String companyName = "";
    public static String empInitial = "Emp";
    public static String clientInitial = "Client";
    public static SimpleDateFormat formatter = new SimpleDateFormat(
	    "dd MMM, yyyy");
    public static SimpleDateFormat dbbackuoformatter = new SimpleDateFormat(
	    "dd_MMM_yyyy");
    private static String PROPERTIES_FILENAME = "/home/arup/emp.properties"; // see
    // private static String PROPERTIES_FILENAME = "C:\\emp.properties"; // see
    private static java.util.Properties props;

    static {
	FileInputStream fis = null;
	String fileNameWithPath;

	try {
	    fis = new FileInputStream(PROPERTIES_FILENAME);
	    props = new java.util.Properties();
	    props.load(fis);

	    String folderName = Common.getUploadFolder();

	    File f = new File(folderName);

	    if (!f.exists()) {
		f.mkdir();
		System.out.print("not exist");
	    }
	    f = new File(getPhotoDir());
	    if (!f.exists()) {
		f.mkdir();
	    }

	    f = new File(getPanDir());
	    if (!f.exists()) {
		f.mkdir();
	    }
	    f = new File(getVoterDir());
	    if (!f.exists()) {
		f.mkdir();
	    }

	    f = new File(getVoterBackDir());
	    if (!f.exists()) {
		f.mkdir();
	    }

	    f = new File(getPanchayetDir());
	    if (!f.exists()) {
		f.mkdir();
	    }

	    f = new File(getTempPath());
	    if (!f.exists()) {
		f.mkdir();
	    }

	    f = new File(getDbBackupPath());
	    if (!f.exists()) {
		f.mkdir();
	    }

	    f = new File(getOtherDir());
	    if (!f.exists()) {
		f.mkdir();
	    }

	    f = new File(getMessageDir());
	    if (!f.exists()) {
		f.mkdir();
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(0);
	}

    }

    /**
     * Get full company name.
     * 
     * @return
     */
    public static String getCompanyName() {

	return companyName;
    }

    /**
     * Get first three upper case character of company name
     * 
     * @return
     */
    public static String getCompInitial() {

	return companyName.substring(0, 3).toUpperCase();
    }

    /**
     * Get the upload folder.Upload folder path is mentioned
     * 
     * @return
     */
    public static String getUploadFolder() {

	// return uploadFolder;
	return props.getProperty("uploadfolder");

    }

    public static String getEmpString() {
	return empInitial;
    }

    public static String getClientString() {
	return clientInitial + "-";
    }

    public static String getPhotoDir() {

	String dir = getUploadFolder() + getSeparator() + "PHOTO"
		+ getSeparator();
	return dir;

    }

    public static String getPanDir() {

	String dir = getUploadFolder() + getSeparator() + "PAN"
		+ getSeparator();
	return dir;

    }

    public static String getVoterDir() {

	String dir = getUploadFolder() + getSeparator() + "VOTER"
		+ getSeparator();
	return dir;

    }

    public static String getVoterBackDir() {

	String dir = getUploadFolder() + getSeparator() + "VOTERBACK"
		+ getSeparator();
	return dir;

    }

    public static String getPanchayetDir() {

	String dir = getUploadFolder() + getSeparator() + "PANCHAYET"
		+ getSeparator();
	return dir;

    }

    public static String getOtherDir() {

	String dir = getUploadFolder() + getSeparator() + "OTHER"
		+ getSeparator();
	return dir;

    }

    public static String getMessageDir() {

	String dir = getUploadFolder() + getSeparator() + "MESSAGE"
		+ getSeparator();
	return dir;

    }

    public static Boolean copyFile(String source, String target) {

	InputStream inStream = null;
	OutputStream outStream = null;

	try {

	    File afile = new File(source);
	    File bfile = new File(target);

	    inStream = new FileInputStream(afile);
	    outStream = new FileOutputStream(bfile);

	    byte[] buffer = new byte[1024];

	    int length;
	    // copy the file content in bytes
	    while ((length = inStream.read(buffer)) > 0) {

		outStream.write(buffer, 0, length);

	    }

	    inStream.close();
	    outStream.close();

	    System.out.println("File is copied successfully from " + source
		    + " to " + target);

	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}

	return true;
    }

    public static String getTempPath() {

	return getUploadFolder() + getSeparator() + "TEMP";

    }

    public static String getDbBackupPath() {

	return getUploadFolder() + getSeparator() + "DBBACKUP";

    }

    public static String getSeparator() {

	String osName = System.getProperty("os.name");
	System.err.println(osName);
	if (osName.equalsIgnoreCase("Linux")) {
	    return "/";
	} else if (osName.trim().startsWith("Windows")) {
	    return "/";
	} else {
	    return "";
	}
    }

    public static SimpleDateFormat getDateFormatter() {
	return formatter;
    }

    public static void createFolderStructure() {
    }

    public static String getDbConnectionURL() {

	String dbconn = props.getProperty("dbUrl") + getDbHost() + ":"
		+ getDbport() + "/" + getDatabase();
	System.out.println("dbconn " + dbconn);
	return dbconn;
    }

    public static String getDbUserName() {
	System.out.println("dbUserName " + getProperty("dbUserName"));
	return props.getProperty("dbUserName");
    }

    public static String getDbPassword() {

	return props.getProperty("dbPassword");
    }

    public static String getDbHost() {

	return props.getProperty("dbServerName");

    }

    public static String getDatabase() {
	return props.getProperty("dbName");

    }

    public static String getProperty(String key) {
	System.out.println("key : " + key + " value : "
		+ props.getProperty(key));
	return props.getProperty(key);
    }

    public static String getDbport() {

	return props.getProperty("dbPort");
    }

    public static BufferedImage resizeImage(BufferedImage image, int width,
	    int height) {
	BufferedImage bi = new BufferedImage(width, height,
		BufferedImage.TRANSLUCENT);
	Graphics2D g2d = (Graphics2D) bi.createGraphics();
	g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
		RenderingHints.VALUE_RENDER_QUALITY));
	g2d.drawImage(image, 0, 0, width, height, null);
	g2d.dispose();
	return bi;
    }

    public static void copyFolder(File src, File dest) throws IOException {

	if (src.isDirectory()) {

	    // if directory not exists, create it
	    if (!dest.exists()) {
		dest.mkdir();
		System.out.println("Directory copied from " + src + "  to "
			+ dest);
	    }

	    // list all the directory contents
	    String files[] = src.list();

	    for (String file : files) {
		// construct the src and dest file structure
		File srcFile = new File(src, file);
		File destFile = new File(dest, file);
		// recursive copy
		copyFolder(srcFile, destFile);
	    }

	} else {
	    // if file, then copy it
	    // Use bytes stream to support all file types
	    InputStream in = new FileInputStream(src);
	    OutputStream out = new FileOutputStream(dest);

	    byte[] buffer = new byte[1024];

	    int length;
	    // copy the file content in bytes
	    while ((length = in.read(buffer)) > 0) {
		out.write(buffer, 0, length);
	    }

	    in.close();
	    out.close();
	    System.out.println("File copied from " + src + " to " + dest);
	}
    }

    public static Boolean getDbBackup(String sqlPath) throws IOException {

	String dbname = getDatabase();
	File f = new File(sqlPath);
	if (!f.exists()) {
	    f.createNewFile();
	}
	String executeCmd = "";

	if (Common.getDbPassword().equalsIgnoreCase(null)
		|| Common.getDbPassword().length() == 0) {
	    executeCmd = getMysqlDumpPath() + " -u " + Common.getDbUserName()
		    + " --add-drop-database -B " + dbname + " -r " + sqlPath;
	} // executeCmd =
	  // "mysqldump -u "+Common.getDbUserName()+" --add-drop-database -B "
	  // + dbname + " -r " + sqlPath;
	else {
	    executeCmd = getMysqlDumpPath() + " -u " + Common.getDbUserName()
		    + " -p" + Common.getDbPassword()
		    + " --add-drop-database -B " + dbname + " -r " + sqlPath;
	}

	Process runtimeProcess;
	try {

	    runtimeProcess = Runtime.getRuntime().exec(executeCmd);
	    int processComplete = runtimeProcess.waitFor();

	    if (processComplete == 0) {
		System.out.println("Backup created successfully");
		return true;
	    } else {
		System.out.println("Could not create the backup");
		return false;
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	    return false;
	}
    }

    public static boolean getBackup(String backupFileName) throws Exception {

	Date dt = new Date();
	String date = Common.dbbackuoformatter.format(dt);

	String path = Common.getDbBackupPath() + Common.getSeparator() + date
		+ Common.getSeparator() + backupFileName;
	File f = new File(path);
	if (!f.exists()) {
	    f.mkdirs();
	}

	String sqlfilePath = path + Common.getSeparator() + backupFileName
		+ ".sql";

	Boolean flag = getDbBackup(sqlfilePath);

	if (flag) {
	    boolean success = getDBFolderBackup(path);

	    if (success) {
		return true;
	    } else {
		return false;
	    }
	} else {

	    return false;
	}

    }

    public static Boolean getDBFolderBackup(String folderpath) {

	try {
	    File src = null;
	    File dest = null;

	    src = new File(getPhotoDir());
	    dest = new File(folderpath + getSeparator() + "PHOTO");
	    copyFolder(src, dest);

	    src = new File(getPanDir());
	    dest = new File(folderpath + getSeparator() + "PAN");
	    copyFolder(src, dest);

	    src = new File(getPanchayetDir());
	    dest = new File(folderpath + getSeparator() + "PANCHAYET");
	    copyFolder(src, dest);

	    src = new File(getVoterDir());
	    dest = new File(folderpath + getSeparator() + "VOTER");
	    copyFolder(src, dest);

	    src = new File(getVoterBackDir());
	    dest = new File(folderpath + getSeparator() + "VOTERBACK");
	    copyFolder(src, dest);

	    src = new File(getOtherDir());
	    dest = new File(folderpath + getSeparator() + "OTHER");
	    copyFolder(src, dest);

	    src = new File(getMessageDir());
	    dest = new File(folderpath + getSeparator() + "MESSAGE");
	    copyFolder(src, dest);

	} catch (Exception e) {
	    System.err.println("Exception creating backup folder");
	    e.printStackTrace();
	    return false;

	}

	return true;
    }

    public static boolean restoreDB(String dbName, String dbUserName,
	    String dbPassword, String source) {

	String[] executeCmd;

	if (dbPassword.equalsIgnoreCase(null) || dbPassword.length() == 0) {
	    executeCmd = new String[] { getMysqlPath(), "--user=" + dbUserName,
		    dbName, "-e", "source " + source };
	} else {
	    executeCmd = new String[] { getMysqlPath(), "--user=" + dbUserName,
		    "--password=" + dbPassword, dbName, "-e",
		    "source " + source };
	}

	// String cmd = "mysql -u arup -parup database_name"
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

    public static boolean restore(String sqlFilePath) {

	String dbName = getDatabase();
	String userName = getDbUserName();
	String pwd = getDbPassword();

	boolean flag = restoreDB(dbName, userName, pwd, sqlFilePath);

	if (flag) {
	    System.out.println("Successfully restore database");
	    String path = (new File(sqlFilePath)).getParent();
	    System.out.println("Path : " + path);
	    boolean f1 = restoreDBFolder(path);

	    return true;
	}

	return false;

    }

    public static boolean restoreDBFolder(String path) {

	try {
	    File src = null;
	    File dest = null;

	    src = new File(path + getSeparator() + "PHOTO");
	    dest = new File(getPhotoDir());
	    System.out.println("Photo Dir " + src.toString());
	    copyFolder(src, dest);

	    dest = new File(getPanDir());
	    src = new File(path + getSeparator() + "PAN");
	    System.out.println("Pab " + src.toString());
	    copyFolder(src, dest);

	    dest = new File(getPanchayetDir());
	    src = new File(path + getSeparator() + "PANCHAYET");
	    System.out.println("Panchaayet " + src.toString());
	    copyFolder(src, dest);

	    dest = new File(getVoterDir());
	    src = new File(path + getSeparator() + "VOTER");
	    System.out.println("Voter " + src.toString());
	    copyFolder(src, dest);

	    dest = new File(getVoterBackDir());
	    src = new File(path + getSeparator() + "VOTERBACK");
	    System.out.println("VOTERBACK " + src.toString());
	    copyFolder(src, dest);

	    dest = new File(getOtherDir());
	    src = new File(path + getSeparator() + "OTHER");
	    System.out.println("OTHER " + src.toString());
	    copyFolder(src, dest);

	    dest = new File(getMessageDir());
	    src = new File(path + getSeparator() + "MESSAGE");
	    System.out.println("OTHER " + src.toString());
	    copyFolder(src, dest);

	} catch (Exception e) {
	    System.err.println("Exception creating backup folder");
	    e.printStackTrace();
	    return false;

	}

	return true;
    }

    public static String getMysqlDumpPath() {

	return props.getProperty("mysqldumppath") + "mysqldump";

    }

    public static String getMysqlPath() {

	return props.getProperty("mysqldumppath") + "mysql";

    }

    public static void main(String args[]) throws Exception {
	// File f1 = new File("/home/arup/Desktop/newcon");
	// restoreDBFolder("/home/arup/Desktop/Test");
	// getBackupFinal(f1);
    }

    public static BufferedImage resize(BufferedImage image, int width,
	    int height) {
	BufferedImage bi = new BufferedImage(width, height,
		BufferedImage.TRANSLUCENT);
	Graphics2D g2d = (Graphics2D) bi.createGraphics();
	g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
		RenderingHints.VALUE_RENDER_QUALITY));
	g2d.drawImage(image, 0, 0, width, height, null);
	g2d.dispose();
	return bi;
    }

    public static boolean getBackupFinal(File file) throws Exception {

	String sqlfilePath = file.getPath() + ".sql";

	Boolean flag = getDbBackup(sqlfilePath);

	if (flag) {
	    boolean success = getDBFolderBackup(file.getParent());

	    if (success) {
		return true;
	    } else {
		return false;
	    }
	} else {

	    return false;
	}

    }

    public static String getEmployeeIDInitial(String post) {

	String initial = "";

	if (post.equalsIgnoreCase("Nurse")) {
	    initial = "Nur-";
	} else if (post.equalsIgnoreCase("12 hours")) {
	    initial = "12H-";
	} else {
	    initial = "24H-";
	}

	initial += getEmpString() + "-";
	return initial;
    }
}
