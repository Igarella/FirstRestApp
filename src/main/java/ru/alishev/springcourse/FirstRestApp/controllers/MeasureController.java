package ru.alishev.springcourse.FirstRestApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstRestApp.dto.MeasureDTO;
import ru.alishev.springcourse.FirstRestApp.models.Measure;
import ru.alishev.springcourse.FirstRestApp.services.MeasureService;
import ru.alishev.springcourse.FirstRestApp.services.RegistrationService;
import ru.alishev.springcourse.FirstRestApp.services.SensorService;
import ru.alishev.springcourse.FirstRestApp.util.SensorErrorResponse;
import ru.alishev.springcourse.FirstRestApp.util.SensorNotFoundException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasureController {
    private final MeasureService measureService;
    private final SensorService sensorService;
    private final RegistrationService registrationService;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasureController(MeasureService measureService, SensorService sensorService, RegistrationService registrationService, ModelMapper modelMapper) {
        this.measureService = measureService;
        this.sensorService = sensorService;
        this.registrationService = registrationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<Measure> index() {
        return measureService.fndAll();
    }

    @PostMapping("/add")
    public String registerMeasurement(@RequestBody MeasureDTO measureDTO) {
        Measure measure = convertToMeasure(measureDTO);
        measure.getSensor().setId(registrationService.findSensorByName(measure.getSensor().getSensorName()).getId());
        measureService.register(measure);
        return "ok";
    }

    @GetMapping("/rainyDaysCount")
    public String  getRainyDaysCount() {
        return "Count of rainyDays is: " + measureService.getRainyDaysCount();
    }

    public Measure convertToMeasure(MeasureDTO measureDTO) {

        return this.modelMapper.map(measureDTO, Measure.class);
    }

    @GetMapping("/temperatures")
    public Double[] getAllTemperatures() {
        List<Double> temperatures = new ArrayList<>(measureService.getAllTemperatures());
//        double [] listAsArray = (double[]) Array.newInstance(Double.class, temperatures.size());
        return temperatures.toArray(new Double[0]);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotFoundException e) {
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(
                "Sensor with this name wasn't found", System.currentTimeMillis()
        );
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.NOT_FOUND);
    }




}
