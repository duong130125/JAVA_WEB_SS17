package ra.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ra.entity.Customer;

import java.util.List;

@Repository
public class AuthRepoImpl implements AuthRepo {

    private final SessionFactory sessionFactory;

    public AuthRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Customer> findAll() {
        Session session = sessionFactory.openSession();
        Query<Customer> query = session.createQuery("FROM Customer", Customer.class);
        List<Customer> customers = query.getResultList();
        session.close();
        return customers;
    }

    @Override
    public Customer findById(int id) {
        Session session = sessionFactory.openSession();
        Customer customer = session.get(Customer.class, id);
        session.close();
        return customer;
    }

    @Override
    public Customer findByEmail(String email) {
        Session session = sessionFactory.openSession();
        Query<Customer> query = session.createQuery("FROM Customer WHERE email = :email", Customer.class);
        query.setParameter("email", email);
        Customer customer = query.uniqueResult();
        session.close();
        return customer;
    }

    @Override
    public Customer findByUsername(String username) {
        Session session = sessionFactory.openSession();
        Query<Customer> query = session.createQuery("FROM Customer WHERE username = :username", Customer.class);
        query.setParameter("username", username);
        Customer customer = query.uniqueResult();
        session.close();
        return customer;
    }

    @Override
    public Customer save(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
        session.close();
        return customer;
    }

    @Override
    public boolean delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        if (customer != null) {
            session.delete(customer);
            transaction.commit();
            session.close();
            return true;
        }
        session.close();
        return false;
    }
}
