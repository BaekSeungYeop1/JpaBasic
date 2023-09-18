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

            Member member = new Member();
            member.setName("user1");
            member.setCreateBy("kim");
            member.setCreateDate(LocalDateTime.now());

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
