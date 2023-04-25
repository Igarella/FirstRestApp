package ru.alishev.springcourse.FirstRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.FirstRestApp.models.Measure;

import java.util.List;

@Repository
public interface MeasureRepository extends JpaRepository<Measure, Integer> {
    @Query("SELECT count (u) FROM Measure u WHERE u.raining = true ")
    public int getRainyDaysViaJPA();

    @Query(value = "select u.value from Measure u")
    public List<Double> getAllTemperatures();
}
