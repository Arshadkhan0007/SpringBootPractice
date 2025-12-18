package com.CompanyName.BasicTreeNodePractice.Controller;

import com.CompanyName.BasicTreeNodePractice.Dto.LaptopRequestDto;
import com.CompanyName.BasicTreeNodePractice.Dto.LaptopResponseDto;
import com.CompanyName.BasicTreeNodePractice.Service.LaptopService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LaptopController {
    private final LaptopService service;

    public LaptopController(LaptopService service) {
        this.service = service;
    }

    @GetMapping("/all_laptops")
    public List<LaptopResponseDto> getAllLaptops(){
        return service.getAllLaptops();
    }

    @PostMapping("/add_laptop")
    public LaptopResponseDto addLaptop(@RequestBody @Valid LaptopRequestDto laptopRequestDto){
        return service.addLaptop(laptopRequestDto);
    }
}
