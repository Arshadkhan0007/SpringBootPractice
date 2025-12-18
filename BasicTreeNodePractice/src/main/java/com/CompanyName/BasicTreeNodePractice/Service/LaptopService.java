package com.CompanyName.BasicTreeNodePractice.Service;

import com.CompanyName.BasicTreeNodePractice.Dto.LaptopRequestDto;
import com.CompanyName.BasicTreeNodePractice.Dto.LaptopResponseDto;
import com.CompanyName.BasicTreeNodePractice.Exception.LaptopAlreadyExistsException;
import com.CompanyName.BasicTreeNodePractice.Mapper.LaptopMapper;
import com.CompanyName.BasicTreeNodePractice.Repository.LaptopRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaptopService {
    private final LaptopRepository laptopRepo;
    private final LaptopMapper mapper;

    public LaptopService(LaptopRepository laptopRepo, LaptopMapper mapper) {
        this.laptopRepo = laptopRepo;
        this.mapper = mapper;
    }

    //GET OPERATIONS
    //Retrieving all laptops
    public List<LaptopResponseDto> getAllLaptops(){
        return mapper.listToDto(laptopRepo.findAll());
    }

    //POST OPERATIONS
    //Adding a laptop
    public LaptopResponseDto addLaptop(LaptopRequestDto laptopRequestDto){
        try{
            return mapper.toDto(laptopRepo.save(mapper.toEntity(laptopRequestDto)));
        } catch (DataIntegrityViolationException ex){
            throw new LaptopAlreadyExistsException("Laptop already Exists!!");
        }
    }


}
