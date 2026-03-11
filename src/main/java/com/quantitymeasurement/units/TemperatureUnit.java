package com.quantitymeasurement.units;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS{
        public double toBase(double value){
            return value;
        }

        public double fromBase(double base){
            return base;
        }
    },

    FAHRENHEIT{
        public double toBase(double value){
            return (value - 32) * 5/9;
        }

        public double fromBase(double base){
            return (base * 9/5) + 32;
        }
    },

    KELVIN{
        public double toBase(double value){
            return value - 273.15;
        }

        public double fromBase(double base){
            return base + 273.15;
        }
    };

    public String getMeasurementType(){
        return "TEMPERATURE";
    }
}