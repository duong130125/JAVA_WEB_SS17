package ra.service;

import ra.entity.ProductCart;
import ra.repository.ProductCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCartServiceImp implements ProductCartService {
    @Autowired
    private ProductCartRepository productCartRepository;

    @Override
    public List<ProductCart> findCart(int customerId, int productId) {
        return productCartRepository.findCart(customerId, productId);
    }

    @Override
    public void save(ProductCart productCart) {
        productCartRepository.save(productCart);
    }

    @Override
    public void update(ProductCart productCart) {
        productCartRepository.update(productCart);
    }

    @Override
    public List<ProductCart> findCartByCustomerId(int customerId) {
        return productCartRepository.findCartByCustomerId(customerId);
    }

    @Override
    public ProductCart findById(int id) {
        return productCartRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        productCartRepository.delete(id);
    }

    @Override
    public void clearCartByCustomerId(int customerId) {
        productCartRepository.clearCartByCustomerId(customerId);
    }
}
