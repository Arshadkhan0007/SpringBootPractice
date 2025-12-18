package com.CompanyName.ModelMapperPractice.CustomMapping.Configuration;

import com.CompanyName.ModelMapperPractice.CustomMapping.Dto.StudentDto;
import com.CompanyName.ModelMapperPractice.CustomMapping.Entity.Student;
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
                .addMappings(mapper ->{
                    mapper.map(Student::getStudentId, StudentDto::setStudId);
                    mapper.map(Student::getStudentName, StudentDto::setStudName);
                });

        modelMapper.createTypeMap(StudentDto.class, Student.class)
                .addMappings(mapper -> {
                    mapper.map(StudentDto::getStudId, Student::setStudentId);
                    mapper.map(StudentDto::getStudName, Student::setStudentName);
                });

        return modelMapper;
    }

}
