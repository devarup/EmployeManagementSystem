/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employeemanagement.models;

/**
 *
 * @author arup
 */
public class Client {
    private String client_id;
    private String client_name;
    private String client_mob1;
    private String client_mob2;
    private String client_add;
    private boolean isActive;

    /**
     * @return the client_id
     */
    public String getClient_id() {
        return client_id;
    }

    /**
     * @param client_id the client_id to set
     */
    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    /**
     * @return the client_name
     */
    public String getClient_name() {
        return client_name;
    }

    /**
     * @param client_name the client_name to set
     */
    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    /**
     * @return the client_mob1
     */
    public String getClient_mob1() {
        return client_mob1;
    }

    /**
     * @param client_mob1 the client_mob1 to set
     */
    public void setClient_mob1(String client_mob1) {
        this.client_mob1 = client_mob1;
    }

    /**
     * @return the client_mob2
     */
    public String getClient_mob2() {
        return client_mob2;
    }

    /**
     * @param client_mob2 the client_mob2 to set
     */
    public void setClient_mob2(String client_mob2) {
        this.client_mob2 = client_mob2;
    }

    /**
     * @return the client_add
     */
    public String getClient_add() {
        return client_add;
    }

    /**
     * @param client_add the client_add to set
     */
    public void setClient_add(String client_add) {
        this.client_add = client_add;
    }
        
    
    @Override
    public String toString(){
        return this.getClient_id()+" - "+this.getClient_name();
    }

    /**
     * @return the isActive
     */
    public boolean isIsActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
}
