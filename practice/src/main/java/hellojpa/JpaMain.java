package hellojpa;

import jdk.swing.interop.SwingInterOpUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{

            Address address = new Address("city", "street", "10000");

            Member member1 = new Member();
            member1.setName("member1");
            member1.setHomeAddress(address);
            member1.setWorkPeriod(new Period());
            em.persist(member1);

            Member member2 = new Member();
            member2.setName("member2");
            member2.setHomeAddress(address);
            member2.setWorkPeriod(new Period());
            em.persist(member2);

            em.flush();
            em.clear();

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }

}
