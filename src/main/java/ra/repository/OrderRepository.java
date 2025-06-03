package ra.repository;

import ra.entity.Order;

import java.util.List;

public interface OrderRepository {
    void save(Order order);
    Order findById(int id);
    List<Order> findByCustomerId(int customerId);
    List<Order> findAll();
    void update(Order order);
    void delete(int id);
}
