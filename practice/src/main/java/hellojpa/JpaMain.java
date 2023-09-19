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
            Team team = new Team();
            team.setName("taemA");
            em.persist(team);

            Member member1 = new Member();
            member1.setName("user1");
            member1.changeTeam(team);
            em.persist(member1);

            em.flush();
            em.clear();

            Member m = em.find(Member.class,member1.getId());

            System.out.println("=============");
            System.out.println("teamName = " + m.getTeam().getName());
            System.out.println("=============");


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }

}
