package ru.alishev.springcourse.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.FirstRestApp.models.Sensor;
import ru.alishev.springcourse.FirstRestApp.repositories.SensorRepository;
import ru.alishev.springcourse.FirstRestApp.util.SensorNotFoundException;

@Service
public class RegistrationService {
    private final SensorRepository sensorRepository;

    @Autowired
    public RegistrationService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public void register(Sensor sensor) {
        sensorRepository.save(sensor);

    }

    public Sensor findSensorByName(String name) {
        return sensorRepository.findFirstBySensorName(name).orElseThrow(SensorNotFoundException::new);
    }
}
