package hu.unideb.method.methodproject.services.impl;

import hu.unideb.method.methodproject.dto.CaloriesDTO;
import hu.unideb.method.methodproject.entities.Calories;
import hu.unideb.method.methodproject.entities.Exercise;
import hu.unideb.method.methodproject.entities.User;
import hu.unideb.method.methodproject.mapper.CaloriesMapper;
import hu.unideb.method.methodproject.repositories.CaloriesRepository;
import hu.unideb.method.methodproject.repositories.UserRepository;
import hu.unideb.method.methodproject.services.CaloriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CaloriesServiceImpl implements CaloriesService {

    @Autowired
    CaloriesMapper caloriesMapper;

    @Autowired
    CaloriesRepository caloriesRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<CaloriesDTO> getAll() {
        return caloriesMapper.caloriesListToCaloriesDtoList(caloriesRepository.findAll());
    }

    @Override
    public CaloriesDTO findCaloriesById(Long id) {
        return caloriesMapper.caloriesToCaloriesDto(caloriesRepository.findById(id).get());
    }

    @Override
    public List<CaloriesDTO> findByUserName(String name) {
        User user = userRepository.findUserByUsername(name);
        List<CaloriesDTO> found = caloriesMapper.caloriesListToCaloriesDtoList(caloriesRepository.findCaloriesByUser(user));
        if(found != null){
            return found;
        }
        return  null;
    }

    @Override
    public void save(CaloriesDTO caloriesDTO) {
        caloriesRepository.save(caloriesMapper.caloriesDtoToCalories(caloriesDTO));
    }

    @Override
    public void delete(CaloriesDTO caloriesDTO) {
        caloriesRepository.deleteById(caloriesDTO.getId());
    }

    @Override
    public void calculateOverall(CaloriesDTO caloriesDTO) {
        caloriesDTO.setOverall(caloriesDTO.getCaloriesFromDiet() - caloriesDTO.getCaloriesFromExercise());

    }


}
