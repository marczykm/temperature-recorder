package pl.marczyk.temperaturerecorder.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by mm on 13.07.2017.
 */
@Entity
public class Temperature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    private double temperature;

    public Temperature() {
    }

    public Temperature(Date date, double temperature) {
        this.date = date;
        this.temperature = temperature;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public double getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "id=" + id +
                ", date=" + date +
                ", temperature=" + temperature +
                '}';
    }
}
