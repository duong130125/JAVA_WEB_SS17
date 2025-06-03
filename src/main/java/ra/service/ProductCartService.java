package ra.service;

import ra.entity.ProductCart;

import java.util.List;

public interface ProductCartService {
    List<ProductCart> findCart(int customerId, int productId);
    void save(ProductCart productCart);
    void update(ProductCart productCart);
    List<ProductCart> findCartByCustomerId(int customerId);
    ProductCart findById(int id);
    void delete(int id);
    void clearCartByCustomerId(int customerId);
}
