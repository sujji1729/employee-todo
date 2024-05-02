package com.example.employeeinformation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employeeinformation.Entity.employeeTable;

public interface employeerepo extends JpaRepository<employeeTable, Long> {

}
