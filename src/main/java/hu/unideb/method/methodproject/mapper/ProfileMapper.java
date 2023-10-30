package hu.unideb.method.methodproject.mapper;

import hu.unideb.method.methodproject.dto.ProfileDto;
import hu.unideb.method.methodproject.entities.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    ProfileDto profileToProfileDto(Profile profile);

    Profile profileDtoToProfile(ProfileDto profileDto);

    List<ProfileDto> profileListToProfileDtoList(List<Profile> profileList);

    List<Profile> profileDtoListToProfileList(List<ProfileDto> profileDtoList);
}
