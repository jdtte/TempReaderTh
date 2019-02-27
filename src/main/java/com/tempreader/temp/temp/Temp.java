package com.tempreader.temp.temp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Temperature")
public class Temp {
    //1,30.12f,22.2f,"27.02.19 15.11.44"
    @ApiModelProperty(example="1", readOnly = true)
    private int id;
    @ApiModelProperty(example="30.12", readOnly = true)
    private float humidity;
    @ApiModelProperty(example="22.2", readOnly = true)
    private float temperature;
    @ApiModelProperty(example="27.02.19 15.11.44", readOnly = true)
    private String date;

    public Temp(){

    }
    public Temp(int id, float humidity, float temperature, String date) {
        this.id = id;
        this.humidity = humidity;
        this.temperature = temperature;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
