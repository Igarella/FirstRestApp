package ru.alishev.springcourse.FirstRestApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstRestApp.dto.SensorDTO;
import ru.alishev.springcourse.FirstRestApp.models.Measure;
import ru.alishev.springcourse.FirstRestApp.models.Sensor;
import ru.alishev.springcourse.FirstRestApp.services.RegistrationService;
import ru.alishev.springcourse.FirstRestApp.services.SensorService;
import ru.alishev.springcourse.FirstRestApp.util.SensorErrorResponse;
import ru.alishev.springcourse.FirstRestApp.util.SensorNotFoundException;
import ru.alishev.springcourse.FirstRestApp.util.SensorValidator;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final RegistrationService registrationService;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorValidator sensorValidator, RegistrationService registrationService) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
        this.registrationService = registrationService;
    }

    @GetMapping()
    public List<Sensor> getSensors() {
        return sensorService.findAll();
    }

    @GetMapping("/{id}")
    public Sensor show(@PathVariable("id") int id) {
        return sensorService.findOne(id);
    }

    @PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        Sensor sensor = convertToSensor(sensorDTO);

        sensorValidator.validate(sensor, bindingResult);

        if (bindingResult.hasErrors()) {
            return Map.of("message", "Ошибка!");
        }
        registrationService.register(sensor);
//        String token = jwtUtil.generateToken(person.getUserName());
//        return Map.of("jwt-token", token);
        return Map.of("ok","ok");
    }

    public Sensor convertToSensor(SensorDTO sensorDTO) {
        return this.modelMapper.map(sensorDTO, Sensor.class);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotFoundException e) {
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(
                "Sensor with this id wasn't found", System.currentTimeMillis()
        );
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.NOT_FOUND);
    }
}
