package com.CompanyName.ModelMapperPractice.NestedObjects.Configuration;

import com.CompanyName.ModelMapperPractice.NestedObjects.Dto.StudentDto;
import com.CompanyName.ModelMapperPractice.NestedObjects.Entity.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper
                .getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setAmbiguityIgnored(true);

        modelMapper.createTypeMap(Student.class, StudentDto.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getDepartment().getDepartmentName(), StudentDto::setDepartmentName);
                });

        modelMapper.createTypeMap(StudentDto.class, Student.class)
                .addMappings(mapper -> {
                    mapper.skip(Student::setDepartment);
                });

        return modelMapper;
    }

}
