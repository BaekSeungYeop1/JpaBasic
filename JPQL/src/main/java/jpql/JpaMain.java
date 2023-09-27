package jpql;

import jpql.query.ConditionQuery;
import jpql.query.FunctionQuery;
import jpql.query.JoinQuery;

import javax.persistence.*;
import java.util.List;


public class JpaMain {
    public static void main(String[] args) {

        JoinQuery joinQuery = new JoinQuery();
        ConditionQuery conditionQuery = new ConditionQuery();
        FunctionQuery functionQuery = new FunctionQuery();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member1 = new Member();
            member1.setUsername("관리자1");
            member1.setAge(10);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            member2.setAge(10);
            em.persist(member2);


            em.flush();
            em.clear();

           // 조인 쿼리
//            List<Object[]> result = em.createQuery(joinQuery.onJoin)
//                    .getResultList();
            
            // (조건식) 쿼리
            List<String> caseResult = em.createQuery(functionQuery.query1, String.class)
                    .getResultList();

            for (String s : caseResult) {
                System.out.println("s = " + s);
            }
            
            //페이징 쿼리
//            List<Member> resultList = em.createQuery("select m from Member m order by m.age desc", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
//
//            System.out.println("resultList.size() = " + resultList.size());
//            for (Member member1 : resultList) {
//                System.out.println("member1 = " + member1);
//            }

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }

}
