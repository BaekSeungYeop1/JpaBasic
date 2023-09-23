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

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);



            em.persist(member);


            em.flush();
            em.clear();

            // 조인
            // 내부 조인 쿼리
            String innerJoin = "select m from Member m inner join m.team t";
            // 외부 조인 쿼리
            String outerJoin = "select m from Member m left outer join m.team t";
            // 세타 조인 쿼리
            String thetaJoin = "select m from Member m, Team t where m.username = t.name";
            // 조인 대상 필터링
            String onJoin = "SELECT m,t FROM Member m LEFT JOIN m.team t ON t.name='teamA'";

            List<Object[]> result = em.createQuery(onJoin)
                    .getResultList();



            //페이징 쿼리
            List<Member> resultList = em.createQuery("select m from Member m order by m.age desc", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            System.out.println("resultList.size() = " + resultList.size());
            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1);
            }


            // 반환 타입이 명확할 때
            TypedQuery<Member> query = em.createQuery("select m from Member  m", Member.class);
            // 반환 타입이 명확하지 않을때
//            Query query2 = em.createQuery("select m.age, m.username from Member as m",Member.class);

            // 엔티티 프로젝션
            List<Team> result1 = em.createQuery("select t from Member m join m.team t",Team.class)
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
