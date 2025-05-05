package com.backendspringboot.usermanagementbackend.controller;

import com.backendspringboot.usermanagementbackend.dto.EmployeeDto;
import com.backendspringboot.usermanagementbackend.dto.ResponseDto;
import com.backendspringboot.usermanagementbackend.model.Employee;
import com.backendspringboot.usermanagementbackend.service.EmployeeService;
import com.backendspringboot.usermanagementbackend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private  EmployeeService employeeService;
    @Autowired
    private  ResponseDto responseDto;


    @PostMapping("/saveEmployee")
    public ResponseEntity saveEmployee(@RequestBody EmployeeDto employeeDto){
        try{
            String res= employeeService.saveEmployee(employeeDto);
            if(res.equals("00")){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Employee saved successfully");
                responseDto.setContent(employeeDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            }else if(res.equals("06")){
                responseDto.setCode(VarList.RSP_DUPLICATE);
                responseDto.setMessage("Employee Registered");
                responseDto.setContent(employeeDto);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }else{
                responseDto.setCode(VarList.RSP_FAIL);
                responseDto.setMessage("Error");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updateEmployee")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDto employeeDto){
        try{
            String res= employeeService.updateEmployee(employeeDto);
            if(res.equals("00")){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Employee saved successfully");
                responseDto.setContent(employeeDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            }else if(res.equals("01")){
                responseDto.setCode(VarList.RSP_DUPLICATE);
                responseDto.setMessage("Not a Registered Employee");
                responseDto.setContent(employeeDto);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }else{
                responseDto.setCode(VarList.RSP_FAIL);
                responseDto.setMessage("Error");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getAllEmployees")
    public ResponseEntity getAllEmployee(){

        try{
            List<EmployeeDto> employeeDtoListList=employeeService.getAllEmployees();
            responseDto.setCode(VarList.RSP_SUCCESS);
            responseDto.setMessage("Employees List");
            responseDto.setContent(employeeDtoListList);
            return new ResponseEntity(responseDto, HttpStatus.OK);
        }catch (Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/searchEmployeeById/{empId}")
    public ResponseEntity searchEmployeeById(@PathVariable int empId){
        try{
            EmployeeDto employeeDto=employeeService.searchEmployeeById(empId);
            if(employeeDto!=null){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Employee Found");
                responseDto.setContent(employeeDto);
                return new ResponseEntity(responseDto, HttpStatus.OK);
            }else{
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Employee Not Found");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteEmployeeById/{empId}")
    public ResponseEntity deleteEmployeeById(@PathVariable int empId){
        try{
            String res= employeeService.deleteEmployeeById(empId);
            if(res.equals("00")){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Employee Deleted Successfully");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            }
            else{
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Employee Not Found");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.NOT_FOUND);
            }
        } catch (RuntimeException e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
