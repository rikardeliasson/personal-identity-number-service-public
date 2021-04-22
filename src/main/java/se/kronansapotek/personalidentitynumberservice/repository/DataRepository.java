
package se.kronansapotek.personalidentitynumberservice.repository;

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
    public int persist(String personalIdentityNumberString, boolean isValid) {
        return this.entityManager
                .createNativeQuery("INSERT INTO data VALUES(?1, ?2, ?3)")
                .setParameter(1, personalIdentityNumberString)
                .setParameter(2, isValid)
                .setParameter(3, new Date(System.currentTimeMillis()))
                .executeUpdate();
    }


}
