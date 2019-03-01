package com.tempreader.temp.temp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;



@Entity
public class Temp {
    //1,30.12f,22.2f,"27.02.19 15.11.44"
//    @ApiModelProperty(example = "1", readOnly = true)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @ApiModelProperty(notes = "The database generated Temp ID")
    private long id;
    @ApiModelProperty(notes="The humidity value",example = "30.12", readOnly = true)
    @Column(length = 6)
    private double humidity;
    @ApiModelProperty(notes="The Temperature value",example = "22.2", readOnly = true)
    @Column(length = 6)
    private double temperature;
    @ApiModelProperty(notes="The Date of the Temp",example = "27.02.19 15:11:44", readOnly = true)
    @Column(length = 20)
    private String date;

    public Temp() {

    }

    public Temp(int id, float humidity, float temperature, String date) {
        this.id = id;
        this.humidity = humidity;
        this.temperature = temperature;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
