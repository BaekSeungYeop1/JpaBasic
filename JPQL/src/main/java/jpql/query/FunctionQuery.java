package jpql.query;

public class FunctionQuery {

    public String query1 = "select 'a' || 'b' From Member m";
    public String query2 = "select concat ('a', 'b') From member m";
    public String query3 = "select substring(m.username, 2.3) From member m";
    public String query4 = "select locate ('de','abcde') From member m";

}
