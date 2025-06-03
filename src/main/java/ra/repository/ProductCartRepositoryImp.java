package ra.repository;

import ra.entity.ProductCart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductCartRepositoryImp implements ProductCartRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ProductCart> findCart(int customerId, int productId) {
        Session session = sessionFactory.openSession();
        Query<ProductCart> query = session.createQuery("FROM ProductCart WHERE customerId = :cid AND productId = :pid", ProductCart.class);
        query.setParameter("cid", customerId);
        query.setParameter("pid", productId);
        return query.getResultList();
    }

    @Override
    public void save(ProductCart productCart) {
        Transaction tx = null;
        Session session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.save(productCart);
        tx.commit();
    }

    @Override
    public void update(ProductCart productCart) {
        Transaction tx = null;
        Session session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.update(productCart);
        tx.commit();
    }

    @Override
    public List<ProductCart> findCartByCustomerId(int customerId) {
        Session session = sessionFactory.openSession();
        Query<ProductCart> query = session.createQuery("FROM ProductCart WHERE customerId = :cid", ProductCart.class);
        query.setParameter("cid", customerId);
        return query.getResultList();
    }

    @Override
    public ProductCart findById(int id) {
        Session session = sessionFactory.openSession();
        ProductCart productCart = session.get(ProductCart.class, id);
        return productCart;
    }

    @Override
    public void delete(int id) {
        Transaction tx = null;
        Session session = sessionFactory.openSession();
        tx = session.beginTransaction();
        ProductCart cartItem = session.get(ProductCart.class, id);
        if (cartItem != null) {
            session.delete(cartItem);
        }
        tx.commit();
    }

    @Override
    public void clearCartByCustomerId(int customerId) {
        Transaction tx = null;
        Session session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.createQuery("DELETE FROM ProductCart WHERE customerId = :cid")
                .setParameter("cid", customerId)
                .executeUpdate();
        tx.commit();
    }
}
