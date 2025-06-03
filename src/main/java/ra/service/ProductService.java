package ra.service;

import ra.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll(int pageNumber, int pageSize);
    Product findById(int id);
    long count();
}
