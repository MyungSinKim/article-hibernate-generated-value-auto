package ymyoo;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ProductTest {
  EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

  @Test
  public void test() {
    Product product = new Product("My Bottle");
    Product product2 = new Product("My Bottle2");

    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = null;
    try {
      tx = em.getTransaction();
      tx.begin();

      em.persist(product);
      em.persist(product2);

      tx.commit();
    } catch (Exception e) {
      e.printStackTrace();
      if (tx != null) {
        tx.rollback();
      }
      throw new RuntimeException(e);
    } finally {
      em.close();
    }
  }
}
