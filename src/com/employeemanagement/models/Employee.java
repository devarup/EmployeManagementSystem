/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employeemanagement.models;

import java.util.Date;

/**
 *
 * @author arup
 */
public class Employee {
    
    private String emp_id;
    private String f_name;
    private String m_name;
    private String l_name;
    private String address;
    private String landmark;
    private String wayToGo;
    private String permanentAddress;
    private String guardian_name;
    private Integer age;
    private String post;
    private String photo_ID;
    private String voter_ID;
    private String voterBack_ID;
    private String otherDoc;    
    private String pan_ID;
    private Date adm_dt;
    private Boolean isActive;
    private String remark;  
    private String mob1; 
    private String mob2;  
    private String panchayat;
    
            
    
    /**
     * @return the emp_id
     */
    public String getEmp_id() {
        return emp_id;
    }

    /**
     * @param emp_id the emp_id to set
     */
    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    /**
     * @return the f_name
     */
    public String getF_name() {
        return f_name;
    }

    /**
     * @param f_name the f_name to set
     */
    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    /**
     * @return the m_name
     */
    public String getM_name() {
        return m_name;
    }

    /**
     * @param m_name the m_name to set
     */
    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    /**
     * @return the l_name
     */
    public String getL_name() {
        return l_name;
    }

    /**
     * @param l_name the l_name to set
     */
    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the landmark
     */
    public String getLandmark() {
        return landmark;
    }

    /**
     * @param landmark the landmark to set
     */
    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    /**
     * @return the wayToGo
     */
    public String getWayToGo() {
        return wayToGo;
    }

    /**
     * @param wayToGo the wayToGo to set
     */
    public void setWayToGo(String wayToGo) {
        this.wayToGo = wayToGo;
    }

    /**
     * @return the guardian_name
     */
    public String getGuardian_name() {
        return guardian_name;
    }

    /**
     * @param guardian_name the guardian_name to set
     */
    public void setGuardian_name(String guardian_name) {
        this.guardian_name = guardian_name;
    }

    /**
     * @return the age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return the post
     */
    public String getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(String post) {
        this.post = post;
    }

    /**
     * @return the photo_ID
     */
    public String getPhoto_ID() {
        return photo_ID;
    }

    /**
     * @param photo_ID the photo_ID to set
     */
    public void setPhoto_ID(String photo_ID) {
        this.photo_ID = photo_ID;
    }

    /**
     * @return the voter_ID
     */
    public String getVoter_ID() {
        return voter_ID;
    }

    /**
     * @param voter_ID the voter_ID to set
     */
    public void setVoter_ID(String voter_ID) {
        this.voter_ID = voter_ID;
    }

    /**
     * @return the pan_ID
     */
    public String getPan_ID() {
        return pan_ID;
    }

    /**
     * @param pan_ID the pan_ID to set
     */
    public void setPan_ID(String pan_ID) {
        this.pan_ID = pan_ID;
    }

    /**
     * @return the adm_dt
     */
    public Date getAdm_dt() {
        return adm_dt;
    }

    /**
     * @param adm_dt the adm_dt to set
     */
    public void setAdm_dt(Date adm_dt) {
        this.adm_dt = adm_dt;
    }

    /**
     * @return the isActive
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the mob1
     */
    public String getMob1() {
        return mob1;
    }

    /**
     * @param mob1 the mob1 to set
     */
    public void setMob1(String mob1) {
        this.mob1 = mob1;
    }

    /**
     * @return the mob2
     */
    public String getMob2() {
        return mob2;
    }

    /**
     * @param mob2 the mob2 to set
     */
    public void setMob2(String mob2) {
        this.mob2 = mob2;
    }
 
    /**
     * @return the panchayat
     */
    public String getPanchayat() {
        return panchayat;
    }

    /**
     * @param panchayat the panchayat to set
     */
    public void setPanchayat(String panchayat) {
        this.panchayat = panchayat;
    }
    
    
    @Override
    public String toString(){
        return this.getEmp_id()+" - "+this.getF_name()+" "+this.getM_name()+" "+this.getL_name();       
    }

    /**
     * @return the voterBack_ID
     */
    public String getVoterBack_ID() {
        return voterBack_ID;
    }

    /**
     * @param voterBack_ID the voterBack_ID to set
     */
    public void setVoterBack_ID(String voterBack_ID) {
        this.voterBack_ID = voterBack_ID;
    }

    /**
     * @return the otherDoc
     */
    public String getOtherDoc() {
        return otherDoc;
    }

    /**
     * @param otherDoc the otherDoc to set
     */
    public void setOtherDoc(String otherDoc) {
        this.otherDoc = otherDoc;
    }

    /**
     * @return the permanentAddress
     */
    public String getPermanentAddress() {
        return permanentAddress;
    }

    /**
     * @param permanentAddress the permanentAddress to set
     */
    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }
    
}
