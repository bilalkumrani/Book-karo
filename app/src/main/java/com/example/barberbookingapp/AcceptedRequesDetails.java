package com.example.barberbookingapp;

public class AcceptedRequesDetails {
    String ServiceProvciderName,ServiceProvciderIndex,ServiceProvciderLocation,ServiceProvciderAvgRating,ServiceName,ServiceIndex;

    public AcceptedRequesDetails(String ServiceName, String RequestIndex, String ServiceProvciderName, String ServiceProvciderIndex, String ServiceProvciderLocation, String ServiceProvciderAvgRating)
    {
        this.ServiceIndex=RequestIndex;
        this.ServiceName=ServiceName;
        this.ServiceProvciderName=ServiceProvciderName;
        this.ServiceProvciderIndex=ServiceProvciderIndex;
        this.ServiceProvciderLocation=ServiceProvciderLocation;
        this.ServiceProvciderAvgRating=ServiceProvciderAvgRating;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public String getServiceIndex() {
        return ServiceIndex;
    }

    public void setServiceIndex(String serviceIndex) {
        ServiceIndex = serviceIndex;
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
