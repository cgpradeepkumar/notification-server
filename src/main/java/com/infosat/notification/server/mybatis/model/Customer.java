package com.infosat.notification.server.mybatis.model;

import java.util.Date;

/**
 * @author pradeepcg
 * @created 07/11/2020 - 15:31
 */

public class Customer {

    private Integer id;
    private String customerName;
    private String email;
    private Date nextService;
    private String notificationStatus;

    public Customer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getNextService() {
        return nextService;
    }

    public void setNextService(Date nextService) {
        this.nextService = nextService;
    }

    public String getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(String notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", email='" + email + '\'' +
                ", nextService=" + nextService +
                ", notificationStatus=" + notificationStatus +
                '}';
    }
}
