package com.CompanyName.SimpleProjectEmployee2.Service;

import com.CompanyName.SimpleProjectEmployee2.Dto.DepartmentDto;
import com.CompanyName.SimpleProjectEmployee2.Exception.ResourceNotFoundException;
import com.CompanyName.SimpleProjectEmployee2.Model.Department;
import com.CompanyName.SimpleProjectEmployee2.Model.Employee;
import com.CompanyName.SimpleProjectEmployee2.Repository.DepartmentRepository;
import com.CompanyName.SimpleProjectEmployee2.Repository.EmployeeRepository;
import com.CompanyName.SimpleProjectEmployee2.Response.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository repo;
    private final EmployeeRepository empRepo;

    // Constructor Dependency Injection
    public DepartmentService(DepartmentRepository repo, EmployeeRepository empRepo){
        this.repo = repo;
        this.empRepo = empRepo;
    }

    //GET OPERATIONS
    //Retrieve all Departments
    public ResponseEntity<ServiceResponse<List<DepartmentDto>>> getALlDepts(){
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                "Retrieval Successful",
                listToDto(repo.findAll())
        ), HttpStatus.OK);
    }

    //Retrieve department by Id
    public ResponseEntity<ServiceResponse<DepartmentDto>> getDeptById(Integer id){
        Department dept = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department Id: " + id + " does not exist!"));
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                "Retrieval Successful!",
                toDto(dept)
        ), HttpStatus.OK);
    }

    //Retrieve Department by Name
    public ResponseEntity<ServiceResponse<List<DepartmentDto>>> getDeptByName(String name) {
        List<Department> deptList = repo.findByDeptName(name);
        if (deptList.isEmpty()) {
            throw new ResourceNotFoundException("Employee Name: " + name + " does not exist!");
        }
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                "Retrieval Successful!",
                listToDto(deptList)
        ), HttpStatus.OK);
    }

    //POST OPERATIONS
    //Adding a department
    public ResponseEntity<ServiceResponse<DepartmentDto>> addDept(DepartmentDto deptDto){
        Department dept = toModel(deptDto);

        repo.save(dept);

        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                "Successfully Inserted",
                toDto(dept)
        ), HttpStatus.OK);
    }

    //Adding multiple departments
    public ResponseEntity<ServiceResponse<List<DepartmentDto>>> addDepts(List<DepartmentDto> deptDtoList){
        List<Department> deptList = listToModel(deptDtoList);
        repo.saveAll(deptList);
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                "Successfully Inserted!",
                listToDto(deptList)
        ), HttpStatus.OK);
    }

    //PUT OPERATIONS
    //Updating a Department
    public ResponseEntity<ServiceResponse<DepartmentDto>> updateDept(Integer id, DepartmentDto updatedDeptDto){
        Department dept = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department ID: " + id + " Does not exist!"));
        dept.setDeptName(updatedDeptDto.getDeptName());

        List<Employee> empList = new ArrayList<>();
        for(Integer i : updatedDeptDto.getEmpIdList()){
            empList.add(empRepo.findById(i)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee ID: " + i + " does not exist!")));
        }
        dept.setEmpList(empList);

        repo.save(dept);
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                "Modification Successful!",
                toDto(dept)
        ), HttpStatus.OK);
    }

    //DELETE OPERATION
    //Deleting a Department
    public ResponseEntity<ServiceResponse<DepartmentDto>> deleteDept(Integer id){
        Department dept = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department ID: " + id + " Does not exist!"));
        repo.deleteById(id);
        return new ResponseEntity<>(new ServiceResponse<>(
                HttpStatus.OK.value(),
                "Deletion Successful!",
                toDto(dept)
        ), HttpStatus.OK);
    }

    //HELPER FUNCTIONS
    public DepartmentDto toDto(Department dept){
        DepartmentDto deptDto = new DepartmentDto();

        deptDto.setDeptId(dept.getDeptId());

        deptDto.setDeptName(dept.getDeptName());

        if(dept.getEmpList() != null){
            List<Integer> empIdList = new ArrayList<>();
            for(Employee emp : dept.getEmpList()){
                empIdList.add(emp.getEmpId());
            }
            deptDto.setEmpIdList(empIdList);
        }
        return deptDto;
    }

    public List<DepartmentDto> listToDto(List<Department> deptList){
        List<DepartmentDto> deptDtoList = new ArrayList<>();
        for(Department dept : deptList){
            DepartmentDto deptDto = new DepartmentDto();

            deptDto.setDeptId(dept.getDeptId());

            deptDto.setDeptName(dept.getDeptName());

            if(dept.getEmpList() != null){
                List<Integer> empIdList = new ArrayList<>();
                for(Employee emp : dept.getEmpList()){
                    empIdList.add(emp.getEmpId());
                }
                deptDto.setEmpIdList(empIdList);
            }

            deptDtoList.add(deptDto);
        }
        return deptDtoList;
    }

    public Department toModel(DepartmentDto deptDto){
        Department dept = new Department();

        dept.setDeptName(deptDto.getDeptName());

        if(deptDto.getEmpIdList() != null){
            List<Employee> empList = new ArrayList<>();
            for(Integer i : deptDto.getEmpIdList()){
                empList.add(empRepo.findById(i)
                        .orElseThrow(() -> new ResourceNotFoundException("Department Id: " + i + " does not exist!")));
            }
            dept.setEmpList(empList);
        }

        return dept;
    }

    public List<Department> listToModel(List<DepartmentDto> deptDtoList){
        List<Department> deptList = new ArrayList<>();

        for(DepartmentDto deptDto : deptDtoList){
            Department dept = new Department();

            dept.setDeptName(deptDto.getDeptName());

            if(deptDto.getEmpIdList() != null){
                List<Employee> empList = new ArrayList<>();
                for(Integer i : deptDto.getEmpIdList()){
                    empList.add(empRepo.findById(i)
                            .orElseThrow(() -> new ResourceNotFoundException("Department Id: " + i + " does not exist!")));
                }
                dept.setEmpList(empList);
            }

            deptList.add(dept);
        }

        return deptList;
    }
}
