package com.quantitymeasurement.entity;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {

    private String operation;
    private String result;
    private String errorMessage;

    public QuantityMeasurementEntity(String operation,String result){
        this.operation = operation;
        this.result = result;
    }

    public QuantityMeasurementEntity(String operation,String result,String error){
        this.operation = operation;
        this.result = result;
        this.errorMessage = error;
    }
}