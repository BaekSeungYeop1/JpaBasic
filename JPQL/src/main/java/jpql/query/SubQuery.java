package jpql.query;

public class SubQuery {

    // 팀A 소속인 회원
    String query1 = "Select m from Member m where exists (select t from m.team t where t.name = 'teamA')";
    // 나이가 평균보다 많은 회원
    String query2 = "select m from member  m where m.age > (select avg(m2.age) from member m2)";
    // 한건이라도 주문한 고객
    String query3 = "select m from member m where (select count(o) from Order o where m = o.member)  > 0";
    // 전체 상품 각각의 재고보다 주묺량이 많은 주문
    String query4 = "select o from Order o where o.orderAmount > ALL (select p.stockAmount from Product p) ";
    // 어떤 팀이든 팀에 소속된 회원
    String query5 = "select m from Member m where m.team = ANY (select t from Team t)";
}
