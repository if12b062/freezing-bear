package esc.plugins;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;
import java.util.HashSet;

/**
 * @author Alex
 */

@Entity
public class Contact {

    @Column(name="contactID")
    private int contactID;
    @Column(name="name")
    private String name;
    @Column(name="uid")
    private int uid;
    @Column(name="title")
    private String title;
    @Column(name="firstName")
    private String firstName;
    @Column(name="lastName")
    private String lastName;
    @Column(name="suffix")
    private String suffix;
    @Column(name="birthDate")
    private Date birthDate;
    @Column(name="address")
    private String address;
    @Column(name="invoiceAddress")
    private String invoiceAddress;
    @Column(name="shippingAddress")
    private String shippingAddress;
    @Column(name="isActive")
    private boolean isActive;
    private HashSet<Invoice> invoiceList;

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public HashSet<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(HashSet<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
