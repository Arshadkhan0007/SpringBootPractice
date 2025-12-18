package com.example.SimpleRegistrationForm.Controller;

import com.example.SimpleRegistrationForm.Dto.DistrictResponseDto;
import com.example.SimpleRegistrationForm.Dto.RegistrationDto;
//import com.example.SimpleRegistrationForm.Dto.StateDistrictDto;
import com.example.SimpleRegistrationForm.Dto.StateDistrictDto;
import com.example.SimpleRegistrationForm.Dto.StateResponseDto;
import com.example.SimpleRegistrationForm.Entity.District;
import com.example.SimpleRegistrationForm.Entity.State;
import com.example.SimpleRegistrationForm.Repository.StateRepository;
import com.example.SimpleRegistrationForm.Service.RegistrationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class RegistrationController {

    private final StateRepository stateRepository;
    private final RegistrationService service;

    public RegistrationController(StateRepository stateRepository, RegistrationService service) {
        this.stateRepository = stateRepository;
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @ModelAttribute RegistrationDto registrationDto, BindingResult result) {
        return new ResponseEntity<>(service.register(registrationDto, result), HttpStatus.OK);
    }

    @GetMapping("/states-districts/XLSX")
    public ResponseEntity<String> createXLSX(@RequestBody List<StateDistrictDto> stateDistrictDtoList){
        return new ResponseEntity<>(service.createXLSX(stateDistrictDtoList), HttpStatus.OK);
    }

    @GetMapping("/states-districts/database")
    public ResponseEntity<String> addStateDistrictsToDatabase(@RequestBody List<StateDistrictDto> stateDistrictDtoList) {
        return new ResponseEntity<>(service.addStateDistrictsToDatabase(stateDistrictDtoList), HttpStatus.OK);
    }

    @GetMapping("/states")
    public ResponseEntity<List<StateResponseDto>> getAllStates() {
        log.info("Attempting to fetch all states");
        return new ResponseEntity<>(service.getAllStates(), HttpStatus.OK);
    }
    @GetMapping("/districtsOfState/{stateId}")
    public ResponseEntity<List<DistrictResponseDto>> getDistrictsOfState(@PathVariable int stateId) {
        log.info("Attempting to fetch districts");
        return new ResponseEntity<>(service.getAllStateDistricts(stateId), HttpStatus.OK);
    }
}
