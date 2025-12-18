package com.CompanyName.BasicTreeNodePractice.Mapper;

import com.CompanyName.BasicTreeNodePractice.Dto.LaptopRequestDto;
import com.CompanyName.BasicTreeNodePractice.Dto.LaptopResponseDto;
import com.CompanyName.BasicTreeNodePractice.Entity.Laptop;
import com.CompanyName.BasicTreeNodePractice.Entity.Student;
import com.CompanyName.BasicTreeNodePractice.Repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LaptopMapper {
    private final ObjectMapper mapper;
    private final StudentRepository studentRepo;

    public LaptopMapper(ObjectMapper mapper, StudentRepository studentRepo) {
        this.mapper = mapper;
        this.studentRepo = studentRepo;
    }

    public Laptop toEntity(LaptopRequestDto laptopRequestDto){
        Laptop laptop = mapper.convertValue(laptopRequestDto, Laptop.class);

        if(laptopRequestDto.getStudentIdList() != null){
            List<Student> studentList = new ArrayList<>();
            for(Integer id : laptopRequestDto.getStudentIdList()){
                studentList.add(studentRepo.findById(id)
                        .orElseThrow(() -> new RuntimeException()));
            }
            laptop.setStudentList(studentList);
        }
        return laptop;
    }

    public LaptopResponseDto toDto(Laptop laptop){
        return mapper.convertValue(laptop, LaptopResponseDto.class);
    }

    public List<LaptopResponseDto> listToDto(List<Laptop> laptopList){
        List<LaptopResponseDto> laptopResponseDtoList = new ArrayList<>();
        for(Laptop laptop : laptopList){
            laptopResponseDtoList.add(mapper.convertValue(laptop, LaptopResponseDto.class));
        }
        return laptopResponseDtoList;
    }
}
