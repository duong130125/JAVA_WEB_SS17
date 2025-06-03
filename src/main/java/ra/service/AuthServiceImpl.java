package ra.service;

import org.springframework.stereotype.Service;
import ra.entity.Customer;
import ra.repository.AuthRepo;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthRepo authRepo;


    public AuthServiceImpl(AuthRepo authRepo) {
        this.authRepo = authRepo;
    }

    @Override
    public List<Customer> findAll() {
        return authRepo.findAll();
    }

    @Override
    public Customer login(String username, String password) {
        return authRepo.login(username, password);
    }

    @Override
    public Customer findById(int id) {
        return authRepo.findById(id);
    }

    @Override
    public Customer findByEmail(String email) {
        return authRepo.findByEmail(email);
    }

    @Override
    public Customer findByUsername(String username) {
        return authRepo.findByUsername(username);
    }

    @Override
    public Customer save(Customer customer) {
        return authRepo.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return authRepo.update(customer);
    }

    @Override
    public boolean delete(int id) {
        return authRepo.delete(id);
    }
}
