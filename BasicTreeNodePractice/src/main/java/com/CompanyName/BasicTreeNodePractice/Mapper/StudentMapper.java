package com.CompanyName.BasicTreeNodePractice.Mapper;

import com.CompanyName.BasicTreeNodePractice.Dto.StudentRequestDto;
import com.CompanyName.BasicTreeNodePractice.Dto.StudentResponseDto;
import com.CompanyName.BasicTreeNodePractice.Entity.Laptop;
import com.CompanyName.BasicTreeNodePractice.Entity.Student;
import com.CompanyName.BasicTreeNodePractice.Repository.LaptopRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentMapper {

    private final ObjectMapper mapper;
    private final LaptopMapper laptopMapper;
    private final LaptopRepository laptopRepo;

    public StudentMapper(ObjectMapper mapper, LaptopMapper laptopMapper, LaptopRepository laptopRepo) {
        this.mapper = mapper;
        this.laptopMapper = laptopMapper;
        this.laptopRepo = laptopRepo;
    }

    //RequestDto to Entity
    public Student toEntity(StudentRequestDto studentRequestDto){
        Student student = mapper.convertValue(studentRequestDto, Student.class);
        if(studentRequestDto.getLaptopName() != null){
            if(laptopRepo.findByLaptopName(studentRequestDto.getLaptopName()) != null){
                student.setLaptop(laptopRepo.findByLaptopName(studentRequestDto.getLaptopName()));
            } else {
                Laptop laptop = new Laptop();
                laptop.setLaptopName(studentRequestDto.getLaptopName());
                student.setLaptop(laptop);
            }
        }
        return student;
    }

    //Entity to ResponseDto
    public StudentResponseDto toDto(Student student){
        StudentResponseDto studentResponseDto = mapper.convertValue(student, StudentResponseDto.class);
        studentResponseDto.setLaptopResponse(laptopMapper.toDto(student.getLaptop()));
        return studentResponseDto;
    }

    public List<StudentResponseDto> listToDto(List<Student> studentList){
        List<StudentResponseDto> studentResponseDtoList = new ArrayList<>();
        for(Student student : studentList){
            StudentResponseDto studentResponseDto = mapper.convertValue(student, StudentResponseDto.class);
            studentResponseDto.setLaptopResponse(laptopMapper.toDto(student.getLaptop()));
            studentResponseDtoList.add(studentResponseDto);
        }
        return studentResponseDtoList;
    }

}
