package ru.alishev.springcourse.FirstRestApp.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Sensor")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "sensor_name")
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 5, max = 100, message = "Имя должно быть от 5 до 100 символов длиной")
    private String sensorName;

    @OneToMany(mappedBy = "sensor")
    private List<Measure> measures;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public Sensor() {
    }
}
