package com.example.dsproject.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
@Entity
public class Measurement implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "measurementid")
    private Long measurementId;

    @Column(name = "amount", nullable = false)
    private Float amount;

    @Column(name = "measurement_date", nullable = false)
    private Date measurementDate;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deviceid")
    private Device device;

    public Measurement(Float amount, Date measurementDate) {
        this.amount = amount;
        this.measurementDate = measurementDate;
    }

    public Measurement() {

    }

    public Long getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(Long measurementId) {
        this.measurementId = measurementId;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Date getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(Date measurementDate) {
        this.measurementDate = measurementDate;
    }
}
