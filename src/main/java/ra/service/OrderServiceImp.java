package ra.service;

import ra.entity.Order;
import ra.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Order findById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findByCustomerId(int customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public void update(Order order) {
        orderRepository.update(order);
    }

    @Override
    public void delete(int id) {
        orderRepository.delete(id);
    }
}
