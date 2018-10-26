package dao;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by raphael on 2017/5/8.
 */

public class Client extends DataSupport implements Serializable{

    private Integer clientId;
    private String clientName;
    private String clientBriefName;
    private String taxPayer;
    private String contacts;
    private String phone;
    private String address;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientBriefName() {
        return clientBriefName;
    }

    public void setClientBriefName(String clientBriefName) {
        this.clientBriefName = clientBriefName;
    }

    public String getTaxPayer() {
        return taxPayer;
    }

    public void setTaxPayer(String taxPayer) {
        this.taxPayer = taxPayer;
    }


    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddreess() {
        return address;
    }

    public void setAddreess(String addreess) {
        this.address = addreess;
    }


}
