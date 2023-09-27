package jpql.query;

public class ConditionQuery {

    public String query1 =
            "select " +
                    "case when m.age <= 10 then '학생요금' " +
                    "     when m.age >= 60 then '경로요금' " +
                    "     else '일반요금' " +
                    "end " +
            "from Member m";

    //사용자 이름이 없으면 이름 없는 회원을 반환
    public String query2 = "select coalesce(m.username,'이름 없는 회원') from Member m";
    // 사용자 이름이 ‘관리자’면 null을 반환하고 나머지는 본인의 이름을 반환
    public String query3 = "select NULLIF(m.username, '관리자') from Member m";
}
