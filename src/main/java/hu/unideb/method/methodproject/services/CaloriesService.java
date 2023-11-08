package hu.unideb.method.methodproject.services;

import hu.unideb.method.methodproject.dto.CaloriesDTO;

import java.util.List;

public interface CaloriesService {

    List<CaloriesDTO> getAll();
    CaloriesDTO findCaloriesById(Long id);
    CaloriesDTO findByUserName(String name);
    void save(CaloriesDTO caloriesDTO);
    void delete(CaloriesDTO caloriesDTO);

    void calculateOverall(CaloriesDTO caloriesDTO);
}
