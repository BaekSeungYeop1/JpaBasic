package jpql.query;

public class JoinQuery {

    // 조인
    // 내부 조인 쿼리
    public String innerJoin = "select m from Member m inner join m.team t";
    // 외부 조인 쿼리
    public String outerJoin = "select m from Member m left outer join m.team t";
    // 세타 조인 쿼리
    public String thetaJoin = "select m from Member m, Team t where m.username = t.name";
    // 조인 대상 필터링
    public String onJoin = "SELECT m,t FROM Member m LEFT JOIN m.team t ON t.name='teamA'";
}
