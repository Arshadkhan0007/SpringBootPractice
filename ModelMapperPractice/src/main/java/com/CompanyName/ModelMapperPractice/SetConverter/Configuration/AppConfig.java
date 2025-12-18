package com.CompanyName.ModelMapperPractice.SetConverter.Configuration;

import com.CompanyName.ModelMapperPractice.SetConverter.Dto.DepartmentDto;
import com.CompanyName.ModelMapperPractice.SetConverter.Entity.Department;
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
                .setConverter(context -> {
                   Department department = context.getSource();
                   DepartmentDto departmentDto = context.getDestination(); // Empty Object
                   departmentDto.setId(department.getDepartmentId());
                   departmentDto.setName(department.getDepartmentName());
                   departmentDto.setTotalStudents(department.getStudentList().size());
                   return departmentDto;
                });

        return modelMapper;
    }

}
