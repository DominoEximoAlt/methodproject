package hu.unideb.method.methodproject.services.impl;

import hu.unideb.method.methodproject.dto.FoodDTO;
import hu.unideb.method.methodproject.entities.Exercise;
import hu.unideb.method.methodproject.entities.Food;
import hu.unideb.method.methodproject.entities.User;
import hu.unideb.method.methodproject.mapper.FoodMapper;
import hu.unideb.method.methodproject.repositories.FoodRepository;
import hu.unideb.method.methodproject.repositories.UserRepository;
import hu.unideb.method.methodproject.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    FoodMapper foodMapper;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<FoodDTO> getAll() {
        return foodMapper.foodListToFoodDtoList(foodRepository.findAll());
    }

    @Override
    public FoodDTO findFoodById(Long id) {
        Optional<Food> searched = foodRepository.findById(id);
        Food found = searched.get();
        found.setFoodEnum(searched.get().getFoodEnum());
        found.setUser(searched.get().getUser());
        found.setWeight(searched.get().getWeight());
        return foodMapper.foodToFoodDto(found);
    }

    @Override
    public List<FoodDTO> findByUserName(String name) {
        User user = userRepository.findUserByUsername(name);
        List<Food> found = foodRepository.findFoodByUser(user);
        if(found != null){
            return foodMapper.foodListToFoodDtoList(found);
        }
        return null;
    }

    @Override
    public void save(FoodDTO foodDTO) {
        foodRepository.save(foodMapper.foodDtoToFood(foodDTO));
    }

    @Override
    public void delete(FoodDTO foodDTO) {
        foodRepository.deleteById(foodDTO.getId());
    }
}
