/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empmanagement.persistence;

import com.employeemanagement.common.Common;
import static com.employeemanagement.common.Common.getCompInitial;
import com.employeemanagement.models.Client;
import com.employeemanagement.models.EmpJob;
import com.employeemanagement.models.Employee;
import com.empmanagement.view.bean.JobDetail;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author user
 */
public class Persistence implements IPersistence {

    public static IPersistence persistence = null;
    public static Connection conn = null;

    public static IPersistence getInstance() {
        System.out.print("getInstance \n");
        if (persistence == null) {
            persistence = new Persistence();
        }

        return persistence;

    }

    private Persistence() {
        System.out.print("constructuor \n");
        getConnection();
    }

    public static void getConnection() {
        System.out.print("getConnection \n");
        try {
            String connurl = Common.getDbConnectionURL();
            String mysql = "";
            Class.forName("com.mysql.jdbc.Driver");
            String user_id = Common.getDbUserName();
            String pwd = Common.getDbPassword();

            conn = DriverManager.getConnection(connurl, user_id, pwd);
        } catch (Exception e) {
            System.out.println("Error in connection");

        }


    }

    @Override
    public Boolean saveEmployee(Employee emp) {

        if (conn == null) {
            getConnection();
        }

        String sql = "Insert into emp (emp_id,f_name,m_name,l_name,address,landmark,"
                + "way, gurdian_name,age,post,photo_id,"
                + "voter_id,pan_id,mob_no1,mob_no2,adminssion_dt,"
                + "isActive,Remark,panchayet_id,voter_id_back,other,permanent_add) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, emp.getEmp_id());
            ps.setString(2, emp.getF_name());
            ps.setString(3, emp.getM_name());
            ps.setString(4, emp.getL_name());
            ps.setString(5, emp.getAddress());
            ps.setString(6, emp.getLandmark());
            ps.setString(7, emp.getWayToGo());
            ps.setString(8, emp.getGuardian_name());
            ps.setInt(9, emp.getAge());
            ps.setString(10, emp.getPost());
            ps.setString(11, emp.getPhoto_ID());
            ps.setString(12, emp.getVoter_ID());
            ps.setString(13, emp.getPan_ID());
            ps.setString(14, emp.getMob1());
            ps.setString(15, emp.getMob2());
            ps.setDate(16, new java.sql.Date(emp.getAdm_dt().getTime()));
            ps.setBoolean(17, true);
            ps.setString(18, "NO remark");
            ps.setString(19, emp.getPanchayat());
            ps.setString(20, emp.getVoterBack_ID());
            ps.setString(21, emp.getOtherDoc());
            ps.setString(22, emp.getPermanentAddress());

            int val = ps.executeUpdate();

            if (val != 0) {
                return true;
            } else {
                return false;
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

            closeConn();
        }


    }

    @Override
    public Boolean saveClient(Client c) {
        if (conn == null) {
            getConnection();
        }

        String sql = "Insert into client (client_id,client_name,client_mob1,client_mob2,client_add,isactive) values(?,?,?,?,?,?)";

        try {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getClient_id());
            ps.setString(2, c.getClient_name());
            ps.setString(3, c.getClient_mob1());
            ps.setString(4, c.getClient_mob2());
            ps.setString(5, c.getClient_add());
            ps.setBoolean(6, true);


            int val = ps.executeUpdate();

            if (val != 0) {
                return true;
            } else {
                return false;
            }



        } catch (Exception e) {
            e.printStackTrace();
            return false;

        } finally {

            closeConn();
        }


    }

    @Override
    public Employee getEmpDetail(String emp_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Employee getClientDetail(String emp_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateEmployee(Employee emp) {


        if (conn == null) {
            getConnection();
        }



        String updateSQL = "update emp set f_name = ?, m_name = ?, l_name = ?, address = ?, "
                + " landmark = ?, way = ?, gurdian_name = ?, age = ?, post = ?, photo_id = ?,"
                + " voter_id = ?, pan_id = ? ,mob_no1 = ?, mob_no2 = ?, adminssion_dt = ?, "
                + " isActive = ?, Remark = ?, panchayet_id = ?, voter_id_back = ?, other = ?, permanent_add =? where emp_id = ?";



        try {
            PreparedStatement ps = conn.prepareStatement(updateSQL);

            ps.setString(1, emp.getF_name());
            ps.setString(2, emp.getM_name());
            ps.setString(3, emp.getL_name());
            ps.setString(4, emp.getAddress());
            ps.setString(5, emp.getLandmark());
            ps.setString(6, emp.getWayToGo());
            ps.setString(7, emp.getGuardian_name());
            ps.setInt(8, emp.getAge());
            ps.setString(9, emp.getPost());
            ps.setString(10, emp.getPhoto_ID());
            ps.setString(11, emp.getVoter_ID());
            ps.setString(12, emp.getPan_ID());
            ps.setString(13, emp.getMob1());
            ps.setString(14, emp.getMob2());
            ps.setDate(15, new java.sql.Date(emp.getAdm_dt().getTime()));
            ps.setBoolean(16, true);
            ps.setString(17, "NO remark");
            ps.setString(18, emp.getPanchayat());
            ps.setString(19, emp.getVoterBack_ID());
            ps.setString(20, emp.getOtherDoc());
            ps.setString(21, emp.getPermanentAddress());
            ps.setString(22, emp.getEmp_id());

            int val = ps.executeUpdate();

            if (val != 0) {
                return true;
            } else {
                return false;
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConn();
        }




    }

    @Override
    public List<Employee> getEmployies(String jobCategory) {

        List<Employee> employees = new ArrayList<Employee>();

        String post;
        if (jobCategory.equalsIgnoreCase("All")) {
            post = "";
        } else {
            post = " And post='" + jobCategory + "'";
        }





        String sql = "SELECT * FROM emp where isActive=true" + post;

        System.err.println("Sql : " + sql);
        PreparedStatement pstmt;

        Statement st = null;
        ResultSet rs = null;
        try {
            if (conn == null) {
                getConnection();
            }


            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Employee emp3 = new Employee();
                emp3.setEmp_id(rs.getString("emp_id"));
                emp3.setF_name(rs.getString("f_name"));
                emp3.setM_name(rs.getString("m_name"));
                emp3.setL_name(rs.getString("l_name"));
                emp3.setAddress(rs.getString("address"));
                emp3.setWayToGo(rs.getString("way"));
                emp3.setGuardian_name(rs.getString("gurdian_name"));
                emp3.setAge(rs.getInt("age"));
                emp3.setAdm_dt(rs.getDate("adminssion_dt"));
                emp3.setMob1(rs.getString("mob_no1"));
                emp3.setMob2(rs.getString("mob_no2"));
                emp3.setPost(rs.getString("post"));
                emp3.setPhoto_ID(rs.getString("photo_id"));
                emp3.setVoter_ID(rs.getString("voter_id"));
                emp3.setPan_ID(rs.getString("pan_id"));
                emp3.setPanchayat(rs.getString("panchayet_id"));
                emp3.setVoterBack_ID(rs.getString("voter_id_back"));
                emp3.setOtherDoc(rs.getString("other"));
                emp3.setPermanentAddress(rs.getString("permanent_add"));
                employees.add(emp3);

            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            closeConn();

        }

        return employees;

    }

    public Boolean closeConn() {
        try {
            if (conn != null) {
                conn.close();
            }
            conn = null;
        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }
        return true;
    }

    public void generateSequence() {
        //TODO
    }

    public Integer getEmpMaxId() {

        Integer maxID = 0;
        String sql = "Select max(id) from emp_management.emp";

        System.out.print(sql);
        PreparedStatement pstmt;


        try {

            if (conn == null) {
                getConnection();
            }

            Statement statemen = conn.createStatement();
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = statemen.executeQuery(sql);


            while (rs.next()) {

                maxID = rs.getInt(1);
                System.out.println("max ID : " + maxID);
            }
//           ;  

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConn();
        }
        return maxID;


    }

    @Override
    public Integer getClientMaxId() {

        Integer maxID = 0;
        String sql = "Select max(id) from client";
        PreparedStatement pstmt;
        try {
            if (conn == null) {
                getConnection();
            }

            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                maxID = rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.printf("Exception in getting client max id");
            ex.printStackTrace();;
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConn();
        }
        return maxID;

    }

    @Override
    public String getEmployeeID() {

        int id = getEmpMaxId() + 1;

        return Common.getCompInitial() + Common.getEmpString() + String.valueOf(id);


    }

    @Override
    public String getClientID() {

        int id = getClientMaxId() + 1;
        return Common.getClientString() + String.valueOf(id);

    }

    @Override
    public List<Employee> getEmployees() {

        List<Employee> employees = new ArrayList<Employee>();

        Integer maxID = 0;
        String sql = "SELECT * FROM emp where isActive=true";
        PreparedStatement pstmt;

        Statement st = null;
        ResultSet rs = null;
        try {
            if (conn == null) {
                getConnection();
            }


            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Employee emp3 = new Employee();
                emp3.setEmp_id(rs.getString("emp_id"));
                emp3.setF_name(rs.getString("f_name"));
                emp3.setM_name(rs.getString("m_name"));
                emp3.setL_name(rs.getString("l_name"));
                emp3.setAddress(rs.getString("address"));
                emp3.setWayToGo(rs.getString("way"));
                emp3.setGuardian_name(rs.getString("gurdian_name"));
                emp3.setAge(rs.getInt("age"));
                emp3.setAdm_dt(rs.getDate("adminssion_dt"));
                emp3.setMob1(rs.getString("mob_no1"));
                emp3.setMob2(rs.getString("mob_no2"));
                emp3.setPost(rs.getString("post"));
                emp3.setPhoto_ID(rs.getString("photo_id"));
                emp3.setVoter_ID(rs.getString("voter_id"));
                emp3.setPan_ID(rs.getString("pan_id"));
                emp3.setPanchayat(rs.getString("panchayet_id"));
                emp3.setVoterBack_ID(rs.getString("voter_id_back"));
                emp3.setOtherDoc(rs.getString("other"));
                emp3.setPermanentAddress(rs.getString("permanent_add"));
                employees.add(emp3);

            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            closeConn();

        }

        return employees;






    }

    @Override
    public Employee getEmployee(String empId) {

        String sql = "SELECT * FROM emp where emp_id='" + empId + "' AND isActive=true";
        PreparedStatement pstmt;

        Statement st = null;
        ResultSet rs = null;
        try {
            if (conn == null) {
                getConnection();
            }

            st = conn.createStatement();
            rs = st.executeQuery(sql);

            Employee emp = null;
            while (rs.next()) {
                emp = new Employee();
                emp.setEmp_id(rs.getString("emp_id"));
                emp.setF_name(rs.getString("f_name"));
                emp.setM_name(rs.getString("m_name"));
                emp.setL_name(rs.getString("l_name"));
                emp.setAddress(rs.getString("address"));
                emp.setLandmark(rs.getString("landmark"));
                emp.setWayToGo(rs.getString("way"));
                emp.setGuardian_name(rs.getString("gurdian_name"));
                emp.setAge(rs.getInt("age"));
                emp.setMob1(rs.getString("mob_no1"));
                emp.setMob2(rs.getString("mob_no2"));
                emp.setPost(rs.getString("post"));
                emp.setPhoto_ID(rs.getString("photo_id"));
                emp.setVoter_ID(rs.getString("voter_id"));
                emp.setPan_ID(rs.getString("pan_id"));
                emp.setPanchayat(rs.getString("panchayet_id"));
                emp.setAdm_dt(rs.getDate("adminssion_dt"));
                emp.setVoterBack_ID(rs.getString("voter_id_back"));
                emp.setOtherDoc(rs.getString("other"));
                emp.setPermanentAddress(rs.getString("permanent_add"));

                int active = rs.getInt("isActive");
                boolean isAct = false;
                if (active == 1) {
                    isAct = true;
                } else if (active == 1) {
                    isAct = false;
                }
                emp.setIsActive(isAct);
                emp.setRemark(rs.getString("Remark"));

            }


            return emp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        } finally {

            closeConn();
        }


    }

    @Override
    public List<Employee> getSearchedEmployeeList(List<String> listParam) {

        List<Employee> employees = new ArrayList<Employee>();

        Statement stmt = null;
        ResultSet rs = null;

        String post = listParam.get(0);
        String name = listParam.get(1);
        String phone = listParam.get(2);


        String sql = "SELECT * FROM emp ";

        String WEHERCLAUSE = "WHERE ";
        String ANDCLAUSE = "AND ";
        // String NAMECLAUSE = " ("
        //      + "f_name like '%"+name+"%' or m_name like '%"+name+"%'or l_name like '%"+name+"%'"
        //       + ") ";  

        String NAMECLAUSE = " ("
                + "f_name like '" + name + "%') ";



        //String PHONECLAUSE  = "( mob_no1 like '%"+phone+"%' or mob_no2 like '%"+phone+"%'"
        //              + ") ";

        String PHONECLAUSE = "( mob_no1 like '%" + phone + "%') ";


        String POSTCLAUSE = " (post = '" + post + "')";


        if (!name.trim().equalsIgnoreCase("")
                && phone.trim().equalsIgnoreCase("")
                && post.trim().equalsIgnoreCase("All")) {

            sql += WEHERCLAUSE + NAMECLAUSE + ANDCLAUSE + "isActive=true";

        } else if (name.trim().equalsIgnoreCase("")
                && !phone.trim().equalsIgnoreCase("")
                && post.trim().equalsIgnoreCase("All")) {

            sql += WEHERCLAUSE + PHONECLAUSE + ANDCLAUSE + "isActive=true";

        } else if (name.trim().equalsIgnoreCase("")
                && phone.trim().equalsIgnoreCase("")
                && !post.trim().equalsIgnoreCase("All")) {

            sql += WEHERCLAUSE + POSTCLAUSE + ANDCLAUSE + "isActive=true";

        } else if (!name.trim().equalsIgnoreCase("")
                && !phone.trim().equalsIgnoreCase("")
                && post.trim().equalsIgnoreCase("All")) {

            sql += WEHERCLAUSE + NAMECLAUSE + ANDCLAUSE + PHONECLAUSE + ANDCLAUSE + "isActive=true";

        } else if (!name.trim().equalsIgnoreCase("")
                && phone.trim().equalsIgnoreCase("")
                && !post.trim().equalsIgnoreCase("All")) {

            sql += WEHERCLAUSE + NAMECLAUSE + ANDCLAUSE + POSTCLAUSE + ANDCLAUSE + "isActive=true";

        } else if (name.trim().equalsIgnoreCase("")
                && !phone.trim().equalsIgnoreCase("")
                && !post.trim().equalsIgnoreCase("All")) {

            sql += WEHERCLAUSE + PHONECLAUSE + ANDCLAUSE + POSTCLAUSE + ANDCLAUSE + "isActive=true";

        } else if (!name.trim().equalsIgnoreCase("")
                && !phone.trim().equalsIgnoreCase("")
                && !post.trim().equalsIgnoreCase("All")) {

            sql += WEHERCLAUSE + NAMECLAUSE + ANDCLAUSE + PHONECLAUSE + ANDCLAUSE + POSTCLAUSE
                    + ANDCLAUSE + "isActive=true";
        } else if (name.trim().equalsIgnoreCase("")
                && phone.trim().equalsIgnoreCase("")
                && post.trim().equalsIgnoreCase("All")) {
            sql += WEHERCLAUSE + "isActive=true";
        }



        try {
            if (conn == null) {
                getConnection();
            }

            stmt = conn.createStatement();

            System.err.println("____________________________________________________________");
            System.out.println(sql);
            System.err.println("____________________________________________________________");

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Employee emp3 = new Employee();
                emp3.setEmp_id(rs.getString("emp_id"));
                emp3.setF_name(rs.getString("f_name"));
                emp3.setM_name(rs.getString("m_name"));
                emp3.setL_name(rs.getString("l_name"));
                emp3.setAddress(rs.getString("address"));
                emp3.setWayToGo(rs.getString("way"));
                emp3.setGuardian_name(rs.getString("gurdian_name"));
                emp3.setAge(rs.getInt("age"));
                emp3.setMob1(rs.getString("mob_no1"));
                emp3.setMob2(rs.getString("mob_no2"));
                emp3.setPost(rs.getString("post"));
                emp3.setPhoto_ID(rs.getString("photo_id"));
                emp3.setVoter_ID(rs.getString("voter_id"));
                emp3.setPan_ID(rs.getString("pan_id"));
                emp3.setPanchayat(rs.getString("panchayet_id"));
                emp3.setAdm_dt(rs.getDate("adminssion_dt"));
                emp3.setVoterBack_ID(rs.getString("voter_id_back"));
                emp3.setOtherDoc(rs.getString("other"));
                emp3.setPermanentAddress(rs.getString("permanent_add"));
                employees.add(emp3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            closeConn();

        }

        return employees;

    }

    @Override
    public JobDetail getJobStatusDetail(String empID, String clientID, java.util.Date startDate, String status) {

        if (conn == null) {
            getConnection();
        }

        String sql = "Select e.emp_id,e.f_name,e.m_name,e.l_name,e.mob_no1,e.mob_no2,"
                + "c.client_id,c.client_name,c.client_mob1,client_mob2,"
                + "ce.start_dt,ce.end_dt,ce.job_desc,ce.remark,ce.status,e.post "
                + " from emp e, client c,client_emp ce "
                + "where ce.emp_id=? AND ce.client_id = ? AND start_dt=? AND status=? AND "
                + "e.emp_id=? AND c.client_id=? AND e.isActive=true AND c.isactive=true";

        JobDetail jobdetail = null;


        try {

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = null;
            ps.setString(1, empID);
            ps.setString(2, clientID);
            ps.setDate(3, new java.sql.Date(startDate.getTime()));
            ps.setString(4, status);
            ps.setString(5, empID);
            ps.setString(6, clientID);

            System.out.println("sql ----- \n" + sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                jobdetail = new JobDetail();

                jobdetail.setEmpID(rs.getString("emp_id"));
                jobdetail.setEmpName(rs.getString("f_name") + " " + rs.getString("m_name") + " " + rs.getString("l_name"));
                String empMob1 = rs.getString("mob_no1");
                String empMob2 = rs.getString("mob_no2");
                String empContact = "";
                if (!empMob1.trim().equalsIgnoreCase("") && empMob1 != null && empMob1.trim().length() > 0) {
                    empContact = empMob1.trim();
                } else if (!empMob2.trim().equalsIgnoreCase("") && empMob2 != null && empMob2.trim().length() > 0) {
                    empContact = empMob2.trim();
                }

                //TODO append "," in b/w phone nos if two mob exist;


                jobdetail.setEmpContact(empMob1);
                jobdetail.setClientID(rs.getString("client_id"));
                jobdetail.setClientName(rs.getString("client_name"));

                String clientMob1 = rs.getString("client_mob1");
                String clientMob2 = rs.getString("client_mob2");
                String clientContact = "";

                if (!clientMob1.trim().equalsIgnoreCase("") && clientMob1 != null && clientMob1.trim().length() > 0) {
                    clientContact = empMob1.trim();
                }
                // else if(!clientMob2.trim().equalsIgnoreCase("") && clientMob2 != null && clientMob2.trim().length()>0 )
                //   clientContact = clientMob2.trim();  

                //TODO append "," in b/w phone nos if two mob exist;

                jobdetail.setClientContact(clientContact);

                jobdetail.setStartDate(rs.getDate("start_dt"));
                jobdetail.setEndDate(rs.getDate("end_dt"));
                jobdetail.setJobDesc(rs.getString("job_desc"));
                jobdetail.setRemarks(rs.getString("remark"));
                jobdetail.setStatus(rs.getString("status"));
                jobdetail.setPost(rs.getString("post"));

            }

        } catch (Exception e) {
            System.err.println("Error in fetching job status detail by ID");
            e.printStackTrace();

        } finally {
            closeConn();

        }
        return jobdetail;

    }

    @Override
    public List<JobDetail> getJobStatusDetail(String status) {

        if (conn == null) {
            getConnection();
        }

        Statement st = null;
        ResultSet rs = null;
        List<JobDetail> jobdetails = new ArrayList<JobDetail>();
        //String jobStatus = "Work in progress";
        String jobStatus = status;


        String whereClause = "";


        if (status.equalsIgnoreCase("Work in progress")) {
            whereClause = " where ce.status='" + status + "'";
        } else if (status.equalsIgnoreCase("Completed")) {
            whereClause = " where ce.status='" + status + "' AND ce.end_dt >= DATE_SUB(CURDATE(),INTERVAL 30 DAY) ";
        }



        String sql = "Select e.emp_id,e.f_name,e.m_name,e.l_name,e.mob_no1,e.mob_no2,"
                + "c.client_id,c.client_name,c.client_mob1,client_mob2,"
                + "ce.start_dt,ce.end_dt,ce.job_desc,ce.remark,e.post"
                + " from emp e, client c,client_emp ce " + whereClause + " AND ce.client_id = c.client_id AND ce.emp_id=e.emp_id AND e.isActive=true AND c.isactive=true";
        System.out.printf("--- Work in progress-------- \n" + sql + "\n");
        try {

            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                JobDetail jobdetail = new JobDetail();

                jobdetail.setEmpID(rs.getString("emp_id"));
                jobdetail.setEmpName(rs.getString("f_name") + " " + rs.getString("m_name") + " " + rs.getString("l_name"));
                String empMob1 = rs.getString("mob_no1");
                String empMob2 = rs.getString("mob_no2");
                String empContact = "";
                if (!empMob1.trim().equalsIgnoreCase("") && empMob1 != null && empMob1.trim().length() > 0) {
                    empContact = empMob1.trim();
                } else if (!empMob2.trim().equalsIgnoreCase("") && empMob2 != null && empMob2.trim().length() > 0) {
                    empContact = empMob2.trim();
                }

                //TODO append "," in b/w phone nos if two mob exist;


                jobdetail.setEmpContact(empMob1);
                jobdetail.setClientID(rs.getString("client_id"));
                jobdetail.setClientName(rs.getString("client_name"));

                // jobdetail.setClientName(rs.getString("client_name"));


                String clientMob1 = rs.getString("client_mob1");
                String clientMob2 = rs.getString("client_mob2");
                String clientContact = "";

                if (!clientMob1.trim().equalsIgnoreCase("") && clientMob1 != null && clientMob1.trim().length() > 0) {
                    clientContact = clientMob1.trim();
                }
                // else if(!clientMob2.trim().equalsIgnoreCase("") && clientMob2 != null && clientMob2.trim().length()>0 )
                //  clientContact = clientMob2.trim();  

                //TODO append "," in b/w phone nos if two mob exist;

                jobdetail.setClientContact(clientContact);

                jobdetail.setStartDate(rs.getDate("start_dt"));
                jobdetail.setEndDate(rs.getDate("end_dt"));
                jobdetail.setJobDesc(rs.getString("job_desc"));
                jobdetail.setRemarks(rs.getString("remark"));
                jobdetail.setPost(rs.getString("post"));
                jobdetails.add(jobdetail);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return jobdetails;
    }

    @Override
    public Boolean jobAssign(EmpJob empjob) {

        if (conn == null) {
            getConnection();
        }

        String sql = "Insert into client_emp(client_id,emp_id,start_dt,end_dt,remark,status,job_desc) values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, empjob.getClient_id());
            ps.setString(2, empjob.getEmp_id());
            java.sql.Date startDate = null;
            java.sql.Date endDate = null;

            if (empjob.getStart_date() != null) {
                startDate = new java.sql.Date(empjob.getStart_date().getTime());
            }

            if (empjob.getEnd_date() != null) {
                endDate = new java.sql.Date(empjob.getEnd_date().getTime());
            }



            ps.setDate(3, startDate);
            ps.setDate(4, endDate);
            ps.setString(5, empjob.getRemarks());
            ps.setString(6, empjob.getStatus());
            ps.setString(7, empjob.getJobDesc());

            int val = ps.executeUpdate();

            if (val != 0) {
                return true;
            } else {
                return false;
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConn();
        }

    }

    @Override
    public List<Client> getClients() {

        List<Client> clients = new ArrayList<Client>();

        Integer maxID = 0;
        String sql = "SELECT * FROM client where isactive=true";
        PreparedStatement pstmt;

        Statement st = null;
        ResultSet rs = null;
        try {
            if (conn == null) {
                getConnection();
            }

            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Client client = new Client();
                client.setClient_id(rs.getString("client_id"));
                client.setClient_name(rs.getString("client_name"));
                client.setClient_mob1(rs.getString("client_mob1"));
                client.setClient_mob2(rs.getString("client_mob2"));
                client.setClient_add(rs.getString("client_add"));

                clients.add(client);

            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            closeConn();

        }

        return clients;

    }

    @Override
    public Boolean updateJobDetail(EmpJob modifiedData, EmpJob oldData) {

        if (conn == null) {
            getConnection();
        }

        String updateSQL = "update client_emp set start_dt=? , end_dt=? , remark=? , status=? , job_desc=?"
                + " where client_id=? AND emp_id=? AND start_dt=? AND status=?";



        try {
            PreparedStatement ps = conn.prepareStatement(updateSQL);

            ps.setDate(1, new java.sql.Date(modifiedData.getStart_date().getTime()));

            java.sql.Date end_date = null;

            if (modifiedData.getEnd_date() != null) {
                end_date = new java.sql.Date(modifiedData.getEnd_date().getTime());
            }

            ps.setDate(2, end_date);
            ps.setString(3, modifiedData.getRemarks());
            ps.setString(4, modifiedData.getStatus());
            ps.setString(5, modifiedData.getJobDesc());
            ps.setString(6, oldData.getClient_id());
            ps.setString(7, oldData.getEmp_id());
            ps.setDate(8, new java.sql.Date(oldData.getStart_date().getTime()));
            ps.setString(9, oldData.getStatus());
            int val = ps.executeUpdate();

            if (val != 0) {
                return true;
            } else {
                return false;
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConn();
        }



    }

    @Override
    public Client getClient(String clientId) {
        String sql = "SELECT * FROM client where client_id = '" + clientId + "'";
        PreparedStatement pstmt;

        Statement st = null;
        ResultSet rs = null;
        try {
            if (conn == null) {
                getConnection();
            }

            st = conn.createStatement();
            rs = st.executeQuery(sql);

            Client client = null;
            while (rs.next()) {
                client = new Client();
                client.setClient_id(rs.getString("client_id"));
                client.setClient_name(rs.getString("client_name"));
                client.setClient_mob1(rs.getString("client_mob1"));
                client.setClient_mob2(rs.getString("client_mob2"));
                client.setClient_add(rs.getString("client_add"));

            }


            return client;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        } finally {

            closeConn();
        }
    }

    @Override
    public Boolean updateClient(Client client) {

        if (conn == null) {
            getConnection();
        }

        String updateSQL = "update client set client_name = ?,client_mob1 = ?,client_mob2 = ?,client_add = ? where client_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(updateSQL);

            ps.setString(1, client.getClient_name());
            ps.setString(2, client.getClient_mob1());
            ps.setString(3, client.getClient_mob2());
            ps.setString(4, client.getClient_add());

            ps.setString(5, client.getClient_id());

            int val = ps.executeUpdate();

            if (val != 0) {
                return true;
            } else {
                return false;
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConn();
        }
    }

    @Override
    public List<Client> getClients(List<String> params) {
        List<Client> clientlist = new ArrayList<Client>();

        Statement stmt = null;
        ResultSet rs = null;

        String name = params.get(0);
        String mobile = params.get(1);

        String sql = "SELECT * FROM client ";
        String WEHERCLAUSE = "WHERE ";
        String ANDCLAUSE = "AND ";
        String NAMECLAUSE = "(" + "client_name like '" + name + "%') ";
        String PHONECLAUSE = "(client_mob1 like '%" + mobile + "%' or client_mob2 like '%" + mobile + "%'" + ") ";

        if (name.trim().equalsIgnoreCase("")
                && mobile.trim().equalsIgnoreCase("")) {
            sql += WEHERCLAUSE + "isactive=true";
        } else if (name.trim().equalsIgnoreCase("")
                && !mobile.trim().equalsIgnoreCase("")) {
            sql += WEHERCLAUSE + PHONECLAUSE + ANDCLAUSE + "isactive=true";
        } else if (!name.trim().equalsIgnoreCase("")
                && mobile.trim().equalsIgnoreCase("")) {
            sql += WEHERCLAUSE + NAMECLAUSE + ANDCLAUSE + "isactive=true";
        } else if (!name.trim().equalsIgnoreCase("")
                && !mobile.trim().equalsIgnoreCase("")) {
            sql += WEHERCLAUSE + NAMECLAUSE + ANDCLAUSE + PHONECLAUSE + ANDCLAUSE + "isactive=true";
        }
        try {
            if (conn == null) {
                getConnection();
            }
            stmt = conn.createStatement();
            System.out.println(">>>>>>>> " + sql);
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Client client = new Client();
                client.setClient_id(rs.getString("client_id"));
                client.setClient_name(rs.getString("client_name"));
                client.setClient_mob1(rs.getString("client_mob1"));
                client.setClient_mob2(rs.getString("client_mob2"));
                client.setClient_add(rs.getString("client_add"));

                clientlist.add(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }

        return clientlist;
    }

    public List<Employee> getAssignedEmployeeByClient(String clientID, String empPost) {

        String status = "Work in progress";
        String post = "";
        String sql = "SELECT e.*"
                + " FROM emp e,client_emp ce where ce.client_id='" + clientID + "' "
                + " AND e.emp_id=ce.emp_id"
                + " And ce.status='" + status + "' AND e.isActive=true";

        if (!empPost.equalsIgnoreCase("All")) {
            post = " And post='" + empPost + "'";
        }

        sql += post;

        System.out.println("sql -------------------------");
        System.err.println(sql);
        List<Employee> employees = new ArrayList<Employee>();

        Statement st = null;
        ResultSet rs = null;
        try {
            if (conn == null) {
                getConnection();
            }

            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {

                Employee emp3 = new Employee();

                emp3.setEmp_id(rs.getString("emp_id"));
                emp3.setF_name(rs.getString("f_name"));
                emp3.setM_name(rs.getString("m_name"));
                emp3.setL_name(rs.getString("l_name"));
                emp3.setAddress(rs.getString("address"));
                emp3.setWayToGo(rs.getString("way"));
                emp3.setGuardian_name(rs.getString("gurdian_name"));
                emp3.setAge(rs.getInt("age"));
                emp3.setMob1(rs.getString("mob_no1"));
                emp3.setMob2(rs.getString("mob_no2"));
                emp3.setPost(rs.getString("post"));
                emp3.setPhoto_ID(rs.getString("photo_id"));
                emp3.setVoter_ID(rs.getString("voter_id"));
                emp3.setPan_ID(rs.getString("pan_id"));
                emp3.setPanchayat(rs.getString("panchayet_id"));
                emp3.setAdm_dt(rs.getDate("adminssion_dt"));
                emp3.setVoterBack_ID(rs.getString("voter_id_back"));
                emp3.setOtherDoc(rs.getString("other"));
                emp3.setPermanentAddress(rs.getString("permanent_add"));
                employees.add(emp3);

                System.out.println("Emp ID : " + emp3.getEmp_id());
                System.out.println("Emp FName : " + emp3.getF_name());
                System.out.println("Emp LName : " + emp3.getL_name());
                System.out.println("Emp MName : " + emp3.getM_name());
                System.out.println("Emp Address : " + emp3.getAddress());
                System.out.println("Emp Photo : " + emp3.getPhoto_ID());
                System.out.println("Emp Mob1 : " + emp3.getMob1());
                System.out.println("Emp Mob1 : " + emp3.getMob1());
                System.err.println("-------------------------------");
            }


        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            closeConn();
        }
        return employees;

    }

    public Boolean deleteEmployee(String empID) {


        if (conn == null) {
            getConnection();
        }

        String updateSQL = "update emp set isActive=false where emp_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(updateSQL);

            ps.setString(1, empID);

            int val = ps.executeUpdate();

            if (val != 0) {
                return true;
            } else {
                return false;
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConn();
        }

    }

    public Boolean deleteClient(String clientID) {


        if (conn == null) {
            getConnection();
        }

        String updateSQL = "update client set isactive=false where client_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(updateSQL);

            ps.setString(1, clientID);

            int val = ps.executeUpdate();

            if (val != 0) {
                return true;
            } else {
                return false;
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConn();
        }

    }

    public Boolean deleteClient(List<String> clientIDs) {

        for (int i = 0; i < clientIDs.size(); i++) {
            String id = clientIDs.get(i);
            id = "'" + id + "'";
            clientIDs.set(i, id);
        }
        String ids = StringUtils.join(clientIDs.toArray(), ",");


        if (conn == null) {
            getConnection();
        }

        String updateSQL = "update client set isactive=false where client_id IN (" + ids + ")";

        try {
            PreparedStatement ps = conn.prepareStatement(updateSQL);

            int val = ps.executeUpdate();

            if (val != 0) {
                return true;
            } else {
                return false;
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
            // Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConn();
        }
    }

    public Boolean deleteEmployee(List<String> empIDs) {

        for (int i = 0; i < empIDs.size(); i++) {
            String id = empIDs.get(i);
            id = "'" + id + "'";
            empIDs.set(i, id);
        }
        String ids = StringUtils.join(empIDs.toArray(), ",");


        if (conn == null) {
            getConnection();
        }

        String updateSQL = "update emp set isActive=false where emp_id IN (" + ids + ")";

        try {
            PreparedStatement ps = conn.prepareStatement(updateSQL);

            int val = ps.executeUpdate();

            if (val != 0) {
                return true;
            } else {
                return false;
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
            // Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConn();
        }
    }

    @Override
    public Boolean cleanDatabase() {
        Statement stmt;
        Boolean flag = true;
        String sql = "";
        if (conn == null) {
            getConnection();
        }


        try {
            sql = "Delete from emp";
            stmt = conn.createStatement();
            stmt.execute(sql);


            sql = "Delete from client";
            stmt = conn.createStatement();
            stmt.execute(sql);

            sql = "Delete from client_emp";
            stmt = conn.createStatement();
            stmt.execute(sql);


        } catch (SQLException ex) {
            System.out.println("catch flag : " + flag);
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            System.out.println("finally flag : " + flag);
            closeConn();

        }

        return flag;
    }

    @Override
    public List<Employee> getWorkingEmployee(String post) {

        List<Employee> employees = new ArrayList<Employee>();
        String status = "Work in Progress";
        Statement stmt;
        Boolean flag = true;
        String postClause = "";
        postClause = " And post='" + post + "'";


        if (post.equalsIgnoreCase("All")) {
            postClause = "";
        }


        String sql = "Select Distinct(e.emp_id), e.* from emp e,client_emp ce where ce.status='" + status + "' AND ce.emp_id=e.emp_id" + postClause;
        System.out.println("get working employee ----------------------------\n" + sql);


        if (conn == null) {
            getConnection();
        }

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);


            while (rs.next()) {

                Employee emp3 = new Employee();

                emp3.setEmp_id(rs.getString("emp_id"));
                emp3.setF_name(rs.getString("f_name"));
                emp3.setM_name(rs.getString("m_name"));
                emp3.setL_name(rs.getString("l_name"));
                emp3.setAddress(rs.getString("address"));
                emp3.setWayToGo(rs.getString("way"));
                emp3.setGuardian_name(rs.getString("gurdian_name"));
                emp3.setAge(rs.getInt("age"));
                emp3.setMob1(rs.getString("mob_no1"));
                emp3.setMob2(rs.getString("mob_no2"));
                emp3.setPost(rs.getString("post"));
                emp3.setPhoto_ID(rs.getString("photo_id"));
                emp3.setVoter_ID(rs.getString("voter_id"));
                emp3.setPan_ID(rs.getString("pan_id"));
                emp3.setPanchayat(rs.getString("panchayet_id"));
                emp3.setAdm_dt(rs.getDate("adminssion_dt"));
                emp3.setVoterBack_ID(rs.getString("voter_id_back"));
                emp3.setOtherDoc(rs.getString("other"));
                emp3.setPermanentAddress(rs.getString("permanent_add"));
                employees.add(emp3);

            }
        } catch (Exception e) {
            e.printStackTrace();

        }



        return employees;
    }

    @Override
    public List<Client> getWorkingClient(String empID) {

        List<Client> clientlist = new ArrayList<Client>();
        String status = "Work in Progress";
        Statement stmt;

        String sql = "Select c.* from client c, client_emp ce where ce.status='" + status + "' AND ce.emp_id='" + empID + "' AND  ce.client_id=c.client_id";
        System.out.println("get working client by ----------------------------\n" + sql);


        if (conn == null) {
            getConnection();
        }

        try {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);


            while (rs.next()) {
                Client client = new Client();
                client.setClient_id(rs.getString("client_id"));
                client.setClient_name(rs.getString("client_name"));
                client.setClient_mob1(rs.getString("client_mob1"));
                client.setClient_mob2(rs.getString("client_mob2"));
                client.setClient_add(rs.getString("client_add"));
                clientlist.add(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientlist;

    }

    public static void main(String args[]) {

        IPersistence p = Persistence.getInstance();
        List<Client> emp = p.getWorkingClient("RISEMP42");
        System.out.println(emp.size());

    }

    @Override
    public String getEmployeeID(String post) {

        String initial = Common.getEmployeeIDInitial(post);
        String empID = "";

        Integer maxID = 0;
        String sql = "Select count(*) from emp_management.emp where emp_id like '" + initial + "%'";

        System.out.print(sql);
        PreparedStatement pstmt;


        try {

            if (conn == null) {
                getConnection();
            }

            Statement statemen = conn.createStatement();
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = statemen.executeQuery(sql);


            while (rs.next()) {

                maxID = rs.getInt(1);
                System.out.println("max ID : " + maxID);
            }

            Integer totalEmpByPost = maxID + 1;

            empID = initial + String.valueOf(totalEmpByPost);

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            closeConn();
        }

        return empID;
    }

    @Override
    public List<Employee> getEmployies(String name, String post) {
        name = name.trim();
        List<Employee> employees = new ArrayList<Employee>();

        String condition = "";
        condition = "where isActive=true";

        if (!name.isEmpty()) {
            condition += " And f_name like '" + name + "%'";
        }


        if (!post.equalsIgnoreCase("All")) {
            condition += " And post='" + post + "'";

        }



        String sql = "SELECT * FROM emp " + condition;


        sql = sql.trim();

        System.err.println("Sql : " + sql);
        PreparedStatement pstmt;

        Statement st = null;
        ResultSet rs = null;
        try {
            if (conn == null) {
                getConnection();
            }


            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Employee emp3 = new Employee();
                emp3.setEmp_id(rs.getString("emp_id"));
                emp3.setF_name(rs.getString("f_name"));
                emp3.setM_name(rs.getString("m_name"));
                emp3.setL_name(rs.getString("l_name"));
                emp3.setAddress(rs.getString("address"));
                emp3.setWayToGo(rs.getString("way"));
                emp3.setGuardian_name(rs.getString("gurdian_name"));
                emp3.setAge(rs.getInt("age"));
                emp3.setAdm_dt(rs.getDate("adminssion_dt"));
                emp3.setMob1(rs.getString("mob_no1"));
                emp3.setMob2(rs.getString("mob_no2"));
                emp3.setPost(rs.getString("post"));
                emp3.setPhoto_ID(rs.getString("photo_id"));
                emp3.setVoter_ID(rs.getString("voter_id"));
                emp3.setPan_ID(rs.getString("pan_id"));
                emp3.setPanchayat(rs.getString("panchayet_id"));
                emp3.setVoterBack_ID(rs.getString("voter_id_back"));
                emp3.setOtherDoc(rs.getString("other"));
                emp3.setPermanentAddress(rs.getString("permanent_add"));
                employees.add(emp3);

            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            closeConn();

        }

        return employees;
    }

    @Override
    public List<Client> getClients(String name) {
        List<Client> clients = new ArrayList<Client>();

        Integer maxID = 0;
        String sql = "SELECT * FROM client where isactive=true ";
        
        if(!name.equalsIgnoreCase("")){
        sql += "AND client_name like '"+name+"%'";
        
        }
        
        
        PreparedStatement pstmt;

        Statement st = null;
        ResultSet rs = null;
        try {
            if (conn == null) {
                getConnection();
            }

            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Client client = new Client();
                client.setClient_id(rs.getString("client_id"));
                client.setClient_name(rs.getString("client_name"));
                client.setClient_mob1(rs.getString("client_mob1"));
                client.setClient_mob2(rs.getString("client_mob2"));
                client.setClient_add(rs.getString("client_add"));

                clients.add(client);

            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            closeConn();

        }

        return clients;
    }
}
