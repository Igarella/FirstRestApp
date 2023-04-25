package ru.alishev.springcourse.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.FirstRestApp.models.Measure;
import ru.alishev.springcourse.FirstRestApp.repositories.MeasureRepository;

import java.util.List;

@Service
public class MeasureService {
    private final MeasureRepository measureRepository;

    @Autowired
    public MeasureService(MeasureRepository measureRepository) {
        this.measureRepository = measureRepository;
    }


    public List<Measure> fndAll() {
        return measureRepository.findAll();
    }

    public void register(Measure measure) {
        measureRepository.save(measure);
    }

    public int getRainyDaysCount() {
       return (int) measureRepository.findAll()
                .stream()
               .filter(Measure::isRaining)
                .count();
    }

    public List<Double> getAllTemperatures() {
       return measureRepository.getAllTemperatures();
    }

    public int getRainyDaysCountFromViaJPA() {
        return measureRepository.getRainyDaysViaJPA();
    }
}
