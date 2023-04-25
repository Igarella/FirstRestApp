package ru.alishev.springcourse.FirstRestApp.dto;

import lombok.Data;
import ru.alishev.springcourse.FirstRestApp.models.Sensor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@Data
public class MeasureDTO {
    @Size(min = -100, max = 100, message = "value should be between -100 and 100 degrees")
    private double value;
    @NotEmpty(message = "field can't be empty")
    private boolean raining;

    private SensorDTO sensor;

}
