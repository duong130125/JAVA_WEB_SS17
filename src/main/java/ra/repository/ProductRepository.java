package ra.repository;

import ra.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll(int pageNumber, int pageSize);
    Product findById(int id);
    long count();
}
