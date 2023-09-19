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

            Member member1 = new Member();
            member1.setName("user1");

            Member member2 = new Member();
            member2.setName("user2");

            em.persist(member1);
            em.persist(member2);

            em.flush();
            em.clear();

            Member findMember1 = em.find(Member.class, member1.getId());
            Member findMember2 = em.getReference(Member.class,member1.getId());
            Member findMember3 = em.getReference(Member.class,member2.getId());
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());
            System.out.println("findMember1 == findMember2 : " + (findMember1==findMember2)); // true
            System.out.println("findMember1 == findMember3 : " + (findMember1==findMember3)); // false : 프록시 객체이므로 타입이 다름 -> 타입 비교는 instanceof 활용

            em.close();
            findMember3.getName();

            // 프록시 초기화 여부확인
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(findMember3));

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }

}
