package com.example.SimpleRegistrationForm.Service;

import com.example.SimpleRegistrationForm.Dto.DistrictResponseDto;
import com.example.SimpleRegistrationForm.Dto.RegistrationDto;
import com.example.SimpleRegistrationForm.Dto.StateDistrictDto;
import com.example.SimpleRegistrationForm.Dto.StateResponseDto;
import com.example.SimpleRegistrationForm.Entity.District;
import com.example.SimpleRegistrationForm.Entity.State;
import com.example.SimpleRegistrationForm.Repository.DistrictRepository;
import com.example.SimpleRegistrationForm.Repository.StateRepository;
import jakarta.validation.Valid;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegistrationService {

    private final StateRepository stateRepository;
    private final DistrictRepository districtRepository;

    public RegistrationService(StateRepository stateRepository, DistrictRepository districtRepository) {
        this.stateRepository = stateRepository;
        this.districtRepository = districtRepository;
    }

    public String addStateDistrictsToDatabase(List<StateDistrictDto> stateDistrictDtoList) {
        List<State> stateList = new ArrayList<>();
        for(StateDistrictDto stateDistrictDto : stateDistrictDtoList) {
            State state = new State();
            state.setStateName(stateDistrictDto.getState());
            List<District> districtList = new ArrayList<>();
            for(String districtName : stateDistrictDto.getDistricts()){
                District district = new District(null, districtName, state);
                districtList.add(district);
            }
            state.setDistrictList(districtList);
            stateList.add(state);
        }

        stateRepository.saveAll(stateList);
        return "Successful";
    }

    public String register(RegistrationDto registrationDto, BindingResult result) {
        if(result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for(FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }

            if(registrationDto.getIdFile().isEmpty()) {
                errors.put("ID", "ID must be provided");
            }

            return errors.toString();
        }

        return "Registration Successful";
    }

    public String createXLSX(List<StateDistrictDto> stateDistrictDtoList){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("States and Districts");
        Row row = sheet.createRow(0);

        Cell cell = row.createCell(0);
        cell.setCellValue("STATE_ID");
        cell = row.createCell(1);
        cell.setCellValue("STATE_NAME");
        cell = row.createCell(2);
        cell.setCellValue("DISTRICT_ID");
        cell = row.createCell(3);
        cell.setCellValue("DISTRICT_NAME");

        int stateId = 101;
        int districtId = 1;

        int rowNum = 1;
        for(StateDistrictDto stateDistrictDto : stateDistrictDtoList) {
            Row row1 = sheet.createRow(rowNum);

            Cell cell1 = row1.createCell(0);
            cell1.setCellValue(stateId);
            cell1 = row1.createCell(1);
            cell1.setCellValue(stateDistrictDto.getState());

            for(String district : stateDistrictDto.getDistricts()) {
                cell1 = row1.createCell(2);
                cell1.setCellValue(districtId);
                cell1 = row1.createCell(3);
                cell1.setCellValue(district);

                row1 = sheet.createRow(++rowNum);
                districtId++;
            }
            districtId = 1;

            stateId++;


        }
        try(FileOutputStream out = new FileOutputStream("StatesNDistricts.xlsx")) {
            workbook.write(out);
            return "XLSX File has been written successfully...";
        } catch (IOException e) {
            return "An Exception has occurred.";
        }
    }

    public List<StateResponseDto> getAllStates() {
        return stateRepository.findAll().stream()
                .map(state -> {
                    StateResponseDto stateResponseDto = new StateResponseDto(state.getStateId(), state.getStateName());
                    return stateResponseDto;
                }).toList();
    }

    public List<DistrictResponseDto> getAllStateDistricts(int stateId) {
        return districtRepository.findByState_StateId(stateId).orElse(null)
                .stream()
                .map(district -> {
                    DistrictResponseDto districtResponseDto = new DistrictResponseDto(district.getDistrictId(), district.getDistrictName());
                    return districtResponseDto;
                }).toList();
    }

}
