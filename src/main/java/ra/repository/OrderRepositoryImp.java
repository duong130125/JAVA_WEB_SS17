package ra.repository;

import ra.entity.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryImp implements OrderRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Order order) {
        Transaction tx = null;
        Session session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.save(order);
        tx.commit();
    }

    @Override
    public Order findById(int id) {
        Session session = sessionFactory.openSession();
        Order order = session.get(Order.class, id);
        return order;
    }

    @Override
    public List<Order> findByCustomerId(int customerId) {
        Session session = sessionFactory.openSession();
        Query<Order> query = session.createQuery("FROM Order WHERE customerId = :cid", Order.class);
        query.setParameter("cid", customerId);
        return query.getResultList();
    }

    @Override
    public List<Order> findAll() {
        Session session = sessionFactory.openSession();
        Query<Order> query = session.createQuery("FROM Order", Order.class);
        return query.getResultList();
    }

    @Override
    public void update(Order order) {
        Transaction tx = null;
        Session session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.update(order);
        tx.commit();
    }

    @Override
    public void delete(int id) {
        Transaction tx = null;
        Session session = sessionFactory.openSession();
        tx = session.beginTransaction();
        Order order = session.get(Order.class, id);
        if (order != null) {
            session.delete(order);
        }
        tx.commit();
    }
}
