package com.example.barberbookingapp;

public class ServiceAvailbleForCustomer {
    String ServiceProvciderName,ServiceProvciderIndex,ServiceProvciderLocation,ServiceProvciderAvgRating,ServiceName;

    public  ServiceAvailbleForCustomer(String ServiceName,String ServiceProvciderName,String ServiceProvciderIndex,String ServiceProvciderLocation,String ServiceProvciderAvgRating)
    {
        this.ServiceName=ServiceName;
        this.ServiceProvciderName=ServiceProvciderName;
        this.ServiceProvciderIndex=ServiceProvciderIndex;
        this.ServiceProvciderLocation=ServiceProvciderLocation;
        this.ServiceProvciderAvgRating=ServiceProvciderAvgRating;
    }

    public String getServiceProvciderName() {
        return ServiceProvciderName;
    }

    public void setServiceProvciderName(String serviceProvciderName) {
        ServiceProvciderName = serviceProvciderName;
    }

    public String getServiceProvciderIndex() {
        return ServiceProvciderIndex;
    }

    public void setServiceProvciderIndex(String serviceProvciderIndex) {
        ServiceProvciderIndex = serviceProvciderIndex;
    }

    public String getServiceProvciderLocation() {
        return ServiceProvciderLocation;
    }

    public void setServiceProvciderLocation(String serviceProvciderLocation) {
        ServiceProvciderLocation = serviceProvciderLocation;
    }

    public String getServiceProvciderAvgRating() {
        return ServiceProvciderAvgRating;
    }

    public void setServiceProvciderAvgRating(String serviceProvciderAvgRating) {
        ServiceProvciderAvgRating = serviceProvciderAvgRating;
    }
}
