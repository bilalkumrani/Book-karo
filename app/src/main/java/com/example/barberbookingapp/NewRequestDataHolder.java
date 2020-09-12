package com.example.barberbookingapp;

public class NewRequestDataHolder {
    String CustomerName,RequestTimeDate,ServiceChoosed,requestIndex,phone;

    NewRequestDataHolder(String CustomerName, String RequestTimeDate, String ServiceChoosed,String phone,String requestIndex)
    {
        this.CustomerName=CustomerName;
        this.RequestTimeDate=RequestTimeDate;
        this.ServiceChoosed=ServiceChoosed;
        this.requestIndex=requestIndex;
        this.phone=phone;
    }

    public String getRequestIndex() {
        return requestIndex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRequestIndex(String requestIndex) {
        this.requestIndex = requestIndex;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getRequestTimeDate() {
        return RequestTimeDate;
    }

    public void setRequestTimeDate(String timeData) {
        RequestTimeDate = timeData;
    }

    public String getServiceChoosed() {
        return ServiceChoosed;
    }

    public void setServiceChoosed(String serviceChoosed) {
        ServiceChoosed = serviceChoosed;
    }
}
