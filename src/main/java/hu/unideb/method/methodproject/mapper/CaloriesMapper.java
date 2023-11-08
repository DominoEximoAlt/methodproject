package hu.unideb.method.methodproject.mapper;

import hu.unideb.method.methodproject.dto.CaloriesDTO;
import hu.unideb.method.methodproject.entities.Calories;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CaloriesMapper {

    CaloriesMapper INSTANCE = Mappers.getMapper(CaloriesMapper.class);

    CaloriesDTO caloriesToCaloriesDto(Calories calories);

    Calories caloriesDtoToCalories(CaloriesDTO caloriesDTO);

    List<CaloriesDTO> caloriesListToCaloriesDtoList(List<Calories> caloriesList);

    List<Calories> caloriesDtoListToCaloriesList(List<CaloriesDTO> caloriesDTOList);
}
