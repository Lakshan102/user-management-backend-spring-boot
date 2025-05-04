package com.backendspringboot.usermanagementbackend.service;

import com.backendspringboot.usermanagementbackend.dto.EmployeeDto;
import com.backendspringboot.usermanagementbackend.model.Employee;
import com.backendspringboot.usermanagementbackend.repository.EmployeeRepository;
import com.backendspringboot.usermanagementbackend.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public String saveEmployee(EmployeeDto employeeDto){

        if (employeeRepository.existsById(employeeDto.getEmpId())){
            return VarList.RSP_DUPLICATE;
        }else{
            employeeRepository.save(modelMapper.map(employeeDto, Employee.class));
            return VarList.RSP_SUCCESS;
        }
    }
    public String updateEmployee(EmployeeDto employeeDto){
        if (employeeRepository.existsById(employeeDto.getEmpId())){
            employeeRepository.save(modelMapper.map(employeeDto, Employee.class));
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
    public List<EmployeeDto> getAllEmployees(){
        List<Employee> employeesList = employeeRepository.findAll();
        return modelMapper.map(employeesList,new TypeToken<ArrayList<EmployeeDto>>(){}.getType());
    }
    public EmployeeDto searchEmployeeById(int empId){
        Employee employee = employeeRepository.findById(empId).orElse(null);
        if(employee!=null){
            return modelMapper.map(employee,EmployeeDto.class);
        }else{
            return null;
        }
    }
    public String deleteEmployeeById(int empId){
        if(employeeRepository.existsById(empId)){
            employeeRepository.deleteById(empId);
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
