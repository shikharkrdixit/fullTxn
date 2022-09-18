package com.shikhar.employeeManagement.service.implementation;

import com.shikhar.employeeManagement.entity.EmployeeEntity;
import com.shikhar.employeeManagement.repository.EmployeeRepository;
import com.shikhar.employeeManagement.service.EmployeeService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeEntity> findAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    @Cacheable(value = "employee",key = "{#id}")
    public Optional<EmployeeEntity> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public EmployeeEntity saveEmployee(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }

    @Override
    public EmployeeEntity updateEmployee(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }

    @Override
    @CacheEvict(value = "employee",key = "{#id}")
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
