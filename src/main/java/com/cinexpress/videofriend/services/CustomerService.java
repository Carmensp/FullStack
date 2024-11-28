package com.cinexpress.videofriend.services;

import java.util.List;
import java.util.Optional;

import com.cinexpress.videofriend.models.Customer;
import com.cinexpress.videofriend.models.Movie;

public interface CustomerService {
    void createCustomer(Customer customer);
    void addClientToCompany(Long customerId, Long companyId);
    Optional<List<Movie>> listAllCustomerMovies(Long customerId);
    boolean hasPremiumSubscription(Long customerId);
    void deactivatePremiumSubscription(Long customerId);
    List<Customer> listAllCustomers();
    void deleteCustomer(Long id);
    void updateCustomer(Customer datos);
}
