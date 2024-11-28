package com.cinexpress.videofriend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cinexpress.videofriend.models.Company;
import com.cinexpress.videofriend.services.CompanyService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("'/company'")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @PostMapping("")
    public ResponseEntity<Void> createCompany(@RequestBody Company company) {
        try {
            companyService.createCompany(company);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PutMapping("")
    public ResponseEntity<Void> updateCompany(@RequestBody Company company) {
        try {
            companyService.updateCompany(company);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

     @DeleteMapping("")
    public ResponseEntity<Void> deleteCompany(@RequestParam(name = "company_id") Long id) {
        try {
            companyService.deleteCompany(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
