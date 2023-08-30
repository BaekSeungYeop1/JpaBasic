package hellojpa;

import jdk.swing.interop.SwingInterOpUtils;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
//
            // 비영속
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");

            // 영속
            // 1차 캐시에 저장됨
            System.out.println("=======BEFORE========");
            em.persist(member);
            System.out.println("=======AFTER========");

            //1차 캐시에서 조회
            Member findMember1 = em.find(Member.class, 100L);
            Member findMember2 = em.find(Member.class, 100L);

            // 영속 엔티티의 영속성 보장
            System.out.println("result = " + (findMember1==findMember2));
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }

}
