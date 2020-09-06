package com.example.barberbookingapp;

public class ServicedRequestDataHolder {
    String CustomerName,AppointmentTimeData,ServiceChoosed;


    ServicedRequestDataHolder(String CustomerName, String AppointmentTimeDate, String ServiceChoosed)
    {
        this.CustomerName=CustomerName;
        this.AppointmentTimeData=AppointmentTimeDate;
        this.ServiceChoosed=ServiceChoosed;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getAppointmentTimeData() {
        return AppointmentTimeData;
    }

    public void setAppointmentTimeData(String appointmentTimeData) {
        AppointmentTimeData = appointmentTimeData;
    }

    public String getServiceChoosed() {
        return ServiceChoosed;
    }

    public void setServiceChoosed(String serviceChoosed) {
        ServiceChoosed = serviceChoosed;
    }
}

