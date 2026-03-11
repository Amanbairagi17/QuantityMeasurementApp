package com.quantitymeasurement.app;

import com.quantitymeasurement.controller.QuantityMeasurementController;
import com.quantitymeasurement.entity.QuantityDTO;
import com.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.quantitymeasurement.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args){

        QuantityMeasurementCacheRepository repository =
                QuantityMeasurementCacheRepository.getInstance();

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(repository);

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        QuantityDTO q1 = new QuantityDTO(10,"FEET");
        QuantityDTO q2 = new QuantityDTO(120,"INCH");

        controller.performCompare(q1,q2);
        controller.performAdd(q1,q2);
    }
}