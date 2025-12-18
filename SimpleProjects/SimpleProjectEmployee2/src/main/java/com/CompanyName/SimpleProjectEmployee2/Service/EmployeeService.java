package com.CompanyName.SimpleProjectEmployee2.Service;

import com.CompanyName.SimpleProjectEmployee2.Dto.EmployeeDto;
import com.CompanyName.SimpleProjectEmployee2.Exception.ResourceNotFoundException;
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
public class EmployeeService {
    private final EmployeeRepository empRepo;
    private final DepartmentRepository deptRepo;

   // Construction dependency Injection
   public EmployeeService(EmployeeRepository empRepo, DepartmentRepository deptRepo){
       this.empRepo = empRepo;
       this.deptRepo = deptRepo;
   }

   //GET OPERATIONS
   //Retrieve all Employees
   public ResponseEntity<ServiceResponse<List<EmployeeDto>>> getAllEmp(){
       return new ResponseEntity<>(new ServiceResponse<>(
               HttpStatus.OK.value(),
               "Retrieval Successful",
               listToDto(empRepo.findAll())
       ), HttpStatus.OK);
   }

   // Retrieving employee by employee Id
   public ResponseEntity<ServiceResponse<EmployeeDto>> getEmpById(Integer id){
       return new ResponseEntity<>(new ServiceResponse<>(
               HttpStatus.OK.value(),
               "Retrieval Successful!",
               toDto(empRepo.findById(id)
                       .orElseThrow(() -> new ResourceNotFoundException("Employee Id: " + id + " does not exist!")))
       ), HttpStatus.OK);
   }

   //Retrieving employees by name
    public ResponseEntity<ServiceResponse<List<EmployeeDto>>> getEmpByName(String name){
       List<EmployeeDto> empDtoList = listToDto(empRepo.findByEmpName(name));
       if(empDtoList.isEmpty()){
           throw new ResourceNotFoundException("Employee name: " + name + " does not exist!");
       }
       return new ResponseEntity<>(new ServiceResponse<>(
               HttpStatus.OK.value(),
               "Retrieval Successful!",
               empDtoList
       ), HttpStatus.OK);
    }

    //POST OPERATIONS
    //Adding an employee
    public ResponseEntity<ServiceResponse<EmployeeDto>> addEmployee(EmployeeDto empDto){
       Employee emp = toModel(empDto);
       empRepo.save(emp);
       return new ResponseEntity<>(new ServiceResponse<>(
               HttpStatus.CREATED.value(),
               "Successfully Inserted 1 Employee",
               toDto(emp)
       ), HttpStatus.CREATED);
    }

    //Adding multiple employees
    public ResponseEntity<ServiceResponse<List<EmployeeDto>>> addEmployees(List<EmployeeDto> empDtoList){

        List<Employee> empList = listToModel(empDtoList);
        empRepo.saveAll(empList);

        return new ResponseEntity<>(new ServiceResponse<>(
            HttpStatus.CREATED.value(),
            "Successfully Inserted " + empDtoList.size() + " Employees",
            listToDto(empList)
        ), HttpStatus.CREATED);
    }

    //PUT OPERATIONS
    //Updating an employee
    public ResponseEntity<ServiceResponse<EmployeeDto>> updateEmp(Integer id, EmployeeDto updatedEmpDto){
       Employee emp = empRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee ID: " + id + " Does not exist!"));
       emp.setEmpName(updatedEmpDto.getEmpName());
       emp.setEmpDept(deptRepo.findById(updatedEmpDto.getDeptId())
               .orElseThrow(() -> new ResourceNotFoundException("Department Id: " + updatedEmpDto.getDeptId() + " does not exist!")));
       emp.setEmpNum(updatedEmpDto.getEmpNum());
       emp.setEmpEmail(updatedEmpDto.getEmpEmail());
       empRepo.save(emp);
       return new ResponseEntity<>(new ServiceResponse<>(
               HttpStatus.OK.value(),
               "Modification Successful!",
               updatedEmpDto
       ), HttpStatus.OK);
    }

    //DELETE OPERATION
    //Deleting an employee
    public ResponseEntity<ServiceResponse<EmployeeDto>> deleteEmp(Integer id){
       Employee emp = empRepo.findById(id)
                       .orElseThrow(() -> new ResourceNotFoundException("Employee Id: " + id + " does not exist!"));
       empRepo.deleteById(id);
       return new ResponseEntity<>(new ServiceResponse<>(
               HttpStatus.OK.value(),
               "Deletion Successful!",
               toDto(emp)
       ), HttpStatus.OK);
    }

    //HELPER METHODS
    public Employee toModel(EmployeeDto empDto){
       Employee emp = new Employee();
       emp.setEmpName(empDto.getEmpName());
       emp.setEmpDept(deptRepo.findById(empDto.getDeptId())
               .orElseThrow(() -> new ResourceNotFoundException("Department Id: " + empDto.getDeptId() + " does not exist!")));
       emp.setEmpNum(empDto.getEmpNum());
       emp.setEmpEmail(empDto.getEmpEmail());

       return emp;
    }

    public List<Employee> listToModel(List<EmployeeDto> empDtoList){
       List<Employee> empList = new ArrayList<>();

       for(EmployeeDto empDto : empDtoList){
           Employee emp = new Employee();
           emp.setEmpName(empDto.getEmpName());
           emp.setEmpDept(deptRepo.findById(empDto.getDeptId())
                   .orElseThrow(() -> new ResourceNotFoundException("Department Id: " + empDto.getDeptId() + " does not exist!")));
           emp.setEmpNum(empDto.getEmpNum());
           emp.setEmpEmail(empDto.getEmpEmail());
           empList.add(emp);
       }
       return empList;
    }

    public EmployeeDto toDto(Employee emp){
       EmployeeDto empDto = new EmployeeDto();
       empDto.setEmpId(emp.getEmpId());
       empDto.setEmpName(emp.getEmpName());
       empDto.setDeptId(emp.getEmpDept().getDeptId());
       empDto.setEmpNum(emp.getEmpNum());
       empDto.setEmpEmail(emp.getEmpEmail());

       return empDto;
    }

    public List<EmployeeDto> listToDto(List<Employee> empList){
       List<EmployeeDto> empDtoList = new ArrayList<>();

       for(Employee emp : empList){
           EmployeeDto empDto = new EmployeeDto();
           empDto.setEmpId(emp.getEmpId());
           empDto.setEmpName(emp.getEmpName());
           empDto.setDeptId(emp.getEmpDept().getDeptId());
           empDto.setEmpNum(emp.getEmpNum());
           empDto.setEmpEmail(emp.getEmpEmail());
           empDtoList.add(empDto);
       }
       return empDtoList;
    }
 }
