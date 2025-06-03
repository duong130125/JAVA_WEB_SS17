package ra.service;

import ra.entity.Product;
import ra.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll(int pageNumber, int pageSize) {
        return productRepository.findAll(pageNumber, pageSize);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public long count() {
        return productRepository.count();
    }
}
