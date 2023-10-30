package hu.unideb.method.methodproject.services.impl;

import hu.unideb.method.methodproject.dto.ProfileDto;
import hu.unideb.method.methodproject.entities.Profile;
import hu.unideb.method.methodproject.entities.User;
import hu.unideb.method.methodproject.mapper.ProfileMapper;
import hu.unideb.method.methodproject.repositories.ProfileRepository;
import hu.unideb.method.methodproject.repositories.UserRepository;
import hu.unideb.method.methodproject.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfileMapper profileMapper;

    @Override
    public List<ProfileDto> getAll() {
        return profileMapper.profileListToProfileDtoList(profileRepository.findAll());
    }

    @Override
    public void save(ProfileDto profileDto) {
        profileRepository.save(profileMapper.profileDtoToProfile(profileDto));
    }

    @Override
    public void delete(ProfileDto profileDto) {
        profileRepository.deleteById(profileDto.getId());
    }

    @Override
    public ProfileDto findById(Long id) {
        Optional<Profile> searched = profileRepository.findById(id);
        Profile found = searched.get();
        found.setFirstName(searched.get().getFirstName());
        found.setLastName(searched.get().getLastName());
        found.setHeight(searched.get().getHeight());
        found.setWeight(searched.get().getWeight());
        found.setUser(searched.get().getUser());
        return profileMapper.profileToProfileDto(found);
    }

    @Override
    public ProfileDto findByUserId(String id) {
        User user = userRepository.findById(id).get();
        Profile found = profileRepository.findProfileByUser(user);
        if(found != null){
            return profileMapper.profileToProfileDto(found);
        }
        Profile profile = new Profile();
        profile.setUser(user);
        return profileMapper.profileToProfileDto(profile);

    }
}
