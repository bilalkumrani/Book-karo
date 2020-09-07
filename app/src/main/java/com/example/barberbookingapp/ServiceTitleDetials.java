package com.example.barberbookingapp;

public class ServiceTitleDetials {

    String ServiceName,ServiceStatus,Index;

    public ServiceTitleDetials(String ServiceName,String ServiceStatus,String Index) {
        this.ServiceName=ServiceName;
        this.ServiceStatus=ServiceStatus;
        this.Index=Index;
    }


    public String getIndex() {
        return Index;
    }

    public void setIndex(String index) {
        Index = index;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public String getServiceStatus() {
        return ServiceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        ServiceStatus = serviceStatus;
    }
}
