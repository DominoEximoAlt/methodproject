package hu.unideb.method.methodproject.mapper;

import hu.unideb.method.methodproject.dto.FoodDTO;
import hu.unideb.method.methodproject.entities.Food;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FoodMapper {

    FoodMapper INSTANCE = Mappers.getMapper(FoodMapper.class);

    FoodDTO foodToFoodDto(Food food);

    Food foodDtoToFood(FoodDTO foodDTO);

    List<FoodDTO> foodListToFoodDtoList(List<Food> foodList);

    List<Food> foodDtoListToFoodList(List<FoodDTO> foodDTOList);
}
