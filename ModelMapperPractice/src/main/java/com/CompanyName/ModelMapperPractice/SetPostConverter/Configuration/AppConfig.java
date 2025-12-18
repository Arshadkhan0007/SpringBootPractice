package com.CompanyName.ModelMapperPractice.SetPostConverter.Configuration;

import com.CompanyName.ModelMapperPractice.SetPostConverter.Dto.DepartmentDto;
import com.CompanyName.ModelMapperPractice.SetPostConverter.Entity.Department;
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

        modelMapper.createTypeMap(Department.class, DepartmentDto.class)
                //When you only want to set specific complex derived fields use setPostConverter
                .setPostConverter(context -> {
                   Department department = context.getSource();
                   DepartmentDto departmentDto = context.getDestination(); //Returns the object after implicit mapping
                   departmentDto.setTotalStudents(department.getStudentList().size());
                   return departmentDto;
                });

        return modelMapper;
    }

}
