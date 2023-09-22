package jpql;

import javax.persistence.*;
import java.util.List;


public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);

            em.persist(member);

            // 반환 타입이 명확할 때
            TypedQuery<Member> query = em.createQuery("select m from Member  m", Member.class);
            // 반환 타입이 명확하지 않을때
//            Query query2 = em.createQuery("select m.age, m.username from Member as m",Member.class);

            // 엔티티 프로젝션
            List<Team> result = em.createQuery("select t from Member m join m.team t",Team.class)
                    .getResultList();

            // 임베디드 타입 프로젝션
            em.createQuery("select o.address from Order o", Address.class)
                    .getResultList();

            // 스칼라 타입 프로젝션
            em.createQuery("select new jpql.MemberDto(m.username,m.age) from Member m",MemberDto.class)
                            .getResultList();

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }

}
