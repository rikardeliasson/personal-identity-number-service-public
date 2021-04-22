
package se.kronansapotek.personalidentitynumberservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;

@Repository
public class DataRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public int persist(String in, boolean res) {
        return this.entityManager
                .createNativeQuery("INSERT INTO data VALUES(?1, ?2)")
                .setParameter(1, in)
                .setParameter(2, res)
//                .setParameter(3, new Date(System.currentTimeMillis()))
                .executeUpdate();
    }


}
