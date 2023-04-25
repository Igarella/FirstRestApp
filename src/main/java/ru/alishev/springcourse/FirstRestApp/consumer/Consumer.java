package ru.alishev.springcourse.FirstRestApp.consumer;

import org.knowm.xchart.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.alishev.springcourse.FirstRestApp.models.Measure;
import ru.alishev.springcourse.FirstRestApp.repositories.MeasureRepository;
import ru.alishev.springcourse.FirstRestApp.services.MeasureService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Consumer {

    public static void main(String[] args) {
        double[] xData = new double[]{0.0, 1.0, 2.0, 3.0, 4.0, 5.0};
        double[] yData = new double[]{2.0, 4.0, 3.0, 5.0, 6.0, 4.0};
//        Consumer consumer = new Consumer();
//        List<Double> temperatures = new ArrayList<>();
//        temperatures.addAll(consumer.measureService.getAllTemperatures());
//        System.out.println(temperatures);
//        XYSeries series = new XYSeries("Line Chart", xData, yData, );
//        XYChart chart = new XYChartBuilder().width(8000).height(6000).build();
//        new SwingWrapper<>(chart).displayChart();
//
//        double[] xData = new double[] { 0.0, 1.0, 2.0 };
//        double[] yData = new double[] { 2.0, 1.0, 0.0 };

//         Create Chart
        XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);

        // Show it
        new SwingWrapper(chart).displayChart();
    }
}
