package com.example.barberbookingapp;

public class TempData {
    String CustomerUserName,UserName,Password,ServiceProviderName,CustomerName,CustomerPhone,NoOfServedServices,NodeNo,ServiceProviderLocation;
    public TempData()
    {

    }

    public TempData(String UserName,String Password)
    {
        this.UserName=UserName;
        this.Password=Password;
    }

    public String getCustomerUserName() {
        return CustomerUserName;
    }

    public void setCustomerUserName(String customerUserName) {
        CustomerUserName = customerUserName;
    }

    public String getCustomerPhone() {
        return CustomerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        CustomerPhone = customerPhone;
    }

    public String getServiceProviderLocation() {
        return ServiceProviderLocation;
    }

    public void setServiceProviderLocation(String serviceProviderLocation) {
        ServiceProviderLocation = serviceProviderLocation;
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
