package hu.unideb.method.methodproject.services;


import hu.unideb.method.methodproject.dto.ProfileDto;

import java.util.List;

public interface ProfileService{

    List<ProfileDto> getAll();

    void save(ProfileDto profileDto);

    void delete(ProfileDto profileDto);

    ProfileDto findById(Long id);

    ProfileDto findByUserId(String id);
}
