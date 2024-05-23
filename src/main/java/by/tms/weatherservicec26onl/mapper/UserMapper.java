package by.tms.weatherservicec26onl.mapper;

import by.tms.weatherservicec26onl.dto.RegistrationUserDto;
import by.tms.weatherservicec26onl.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User registrationUserDtoToUser(RegistrationUserDto registrationUserDto);
}