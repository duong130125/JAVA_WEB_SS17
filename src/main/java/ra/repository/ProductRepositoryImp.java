package ra.repository;

import ra.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImp implements ProductRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> findAll(int pageNumber, int pageSize) {
        Session session = sessionFactory.openSession();
        Query<Product> query = session.createQuery("FROM Product", Product.class);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public Product findById(int id) {
        Session session = sessionFactory.openSession();
        return session.get(Product.class, id);
    }

    @Override
    public long count() {
        Session session = sessionFactory.openSession();
        return session.createQuery("SELECT COUNT(p.id) FROM Product p", Long.class).uniqueResult();
    }
}
