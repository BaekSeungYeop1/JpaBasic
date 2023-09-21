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

           Member member = new Member();
           member.setName("member1");
           member.setHomeAddress(new Address("city1", "street", "zipcode" ));
           member.getFavoriteFoods().add("치킨");
           member.getFavoriteFoods().add("피자");
           member.getFavoriteFoods().add("족발");

           member.getAddressesHistory().add(new AddressEntity("old1", "street", "zipcode"));
           member.getAddressesHistory().add(new AddressEntity("old2", "street", "zipcode"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("================ START ===========");
            Member findMember = em.find(Member.class, member.getId());

            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));

            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            findMember.getAddressesHistory().remove(new Address("old1", "street", "zipcode"));
            findMember.getAddressesHistory().add(new AddressEntity("newCity1", "street", "zipcode"));


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }

}
