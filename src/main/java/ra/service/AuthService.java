package ra.service;

import ra.entity.Customer;

import java.util.List;

public interface AuthService {
    List<Customer> findAll();
    Customer findById(int id);
    Customer findByEmail(String email);
    Customer findByUsername(String username);
    Customer save(Customer customer);
    Customer update(Customer customer);
    boolean delete(int id);
}
