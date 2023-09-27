package jpql.query;

public class SelectQuery {

    // 반환 타입이 명확할 때
    String query1 = "select m from Member  m";
    //TypedQuery<Member> query = em.createQuery("select m from Member  m", Member.class);

    // 반환 타입이 명확하지 않을때
    String query2 = "select m.age, m.username from Member as m";
    //Query query2 = em.createQuery("select m.age, m.username from Member as m",Member.class);

    // 엔티티 프로젝션
    String query3 = "select t from Member m join m.team t";
//    List<Team> result1 = em.createQuery("select t from Member m join m.team t",Team.class)
//                    .getResultList();

    // 임베디드 타입 프로젝션
    String query4 = "select o.address from Order o";
//    em.createQuery("select o.address from Order o", Address.class)
//            .getResultList();

    // 스칼라 타입 프로젝션
    String query5 = "select new jpql.MemberDto(m.username,m.age) from Member m";
//    em.createQuery("select new jpql.MemberDto(m.username,m.age) from Member m",MemberDto.class)
//                            .getResultList();
}
