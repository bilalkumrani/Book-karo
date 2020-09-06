package com.example.barberbookingapp;

public class TempData {
    String UserName,Password,ServiceProviderName,CustomerName,NoOfServedServices,NodeNo;
    public TempData()
    {

    }

    public TempData(String UserName,String Password)
    {
        this.UserName=UserName;
        this.Password=Password;
    }

    public String getNodeNo() {
        return NodeNo;
    }

    public void setNodeNo(String nodeNo) {
        NodeNo = nodeNo;
    }

    public String getNoOfServedServices() {
        return NoOfServedServices;
    }

    public void setNoOfServedServices(String noOfServedServices) {
        NoOfServedServices = noOfServedServices;
    }

    public String getServiceProviderName() {
        return ServiceProviderName;
    }

    public void setServiceProviderName(String serviceProviderName) {
        ServiceProviderName = serviceProviderName;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
