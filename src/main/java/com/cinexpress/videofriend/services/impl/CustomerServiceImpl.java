package com.cinexpress.videofriend.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinexpress.videofriend.models.Company;
import com.cinexpress.videofriend.models.Customer;
import com.cinexpress.videofriend.models.Movie;
import com.cinexpress.videofriend.models.PremiumSubscription;
import com.cinexpress.videofriend.repository.CompanyRepository;
import com.cinexpress.videofriend.repository.CustomerRepository;
import com.cinexpress.videofriend.repository.PremiumSubscriptionRepository;
import com.cinexpress.videofriend.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    PremiumSubscriptionRepository premiumSubscriptionRepository;

    /*
     * Creo que este método para añadir un cliente a la compañía no es necesario ya que al crear un cliente le asignamos 
     * directamente la compañía.
     */
    @Override
    public void addClientToCompany(Long customerId, Long companyId) {
        Customer customer = customerRepository.findById(customerId).get();
        Optional<Company> company = companyRepository.findById(companyId);
        if(!company.isEmpty()){
            Company updateCompany = company.get();
            updateCompany.getCustomers().add(customer);
            companyRepository.save(updateCompany);
        }
    }

    @Override
    public Optional<List<Movie>> listAllCustomerMovies(Long customerId) {
        return customerRepository.findMoviesByCustomerId(customerId);
    }

    @Override
    public List<Customer> listAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public boolean hasPremiumSubscription(Long customerId) {
        Customer customer = customerRepository.getReferenceById(customerId);
        return customer.getPremiumSubscription().getExclusiveCatalog();
    }

    @Override
    public void deactivatePremiumSubscription(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            Customer niCustomer = customer.get();
            PremiumSubscription premium = new PremiumSubscription();
            premium.setDiscounts(false);
            premium.setExclusiveCatalog(false);
            premium.setPreReleases(false);
            premium.setCustomer(niCustomer);
            premiumSubscriptionRepository.save(premium);
        }
        
    }

    @Override
    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(!customer.isEmpty()){
            customerRepository.delete(customer.get());
        }   
    }

    @Override
    public void updateCustomer(Customer datos) {
        Optional<Customer> optionalEntity = customerRepository.findById(datos.getId());
        if(!optionalEntity.isEmpty()){
            Customer customer = optionalEntity.get();
            customer.setName(datos.getName());
            customer.setType(datos.getType());
            customer.setSubscription(datos.getSubscription());
            customer.setPreferences(datos.getPreferences());
            customer.setCompany(datos.getCompany());
            customer.setMovies(datos.getMovies());
           customerRepository.save(customer);
        }
    }
    
}
