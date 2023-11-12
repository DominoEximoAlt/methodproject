package hu.unideb.method.methodproject.services;

import hu.unideb.method.methodproject.dto.CaloriesDTO;
import hu.unideb.method.methodproject.dto.FoodDTO;

import java.util.List;

public interface FoodService {

    List<FoodDTO> getAll();

    FoodDTO findFoodById(Long id);
    List<FoodDTO> findByUserName(String name);
    void save(FoodDTO foodDTO);
    void delete(FoodDTO foodDTO);

}
