package com.cinexpress.videofriend.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinexpress.videofriend.models.Company;
import com.cinexpress.videofriend.repository.CompanyRepository;
import com.cinexpress.videofriend.repository.CustomerRepository;
import com.cinexpress.videofriend.services.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{

     @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void deleteCompany(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if(!company.isEmpty()){
            companyRepository.delete(company.get());
        }  
    }

    @Override
    public void updateCompany(Company datos) {
        Optional<Company> optionalEntity = companyRepository.findById(datos.getId());
        if(!optionalEntity.isEmpty()){
            Company company = optionalEntity.get();
            company.setName(datos.getName());
            company.setDescription(datos.getDescription());
            company.setCustomers(datos.getCustomers());
            company.setMovies(datos.getMovies());
           companyRepository.save(company);
        }
    }
    
}
