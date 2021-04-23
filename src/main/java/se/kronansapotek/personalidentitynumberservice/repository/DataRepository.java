package se.kronansapotek.personalidentitynumberservice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.kronansapotek.personalidentitynumberservice.service.PersonalIdentityNumber;

import javax.persistence.EntityManager;
import java.util.Date;

@Repository
public class DataRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public int persist(PersonalIdentityNumber personalIdentityNumber) {
        return this.entityManager
                .createNativeQuery("INSERT INTO data VALUES(?1, ?2, ?3)")
                .setParameter(1, personalIdentityNumber.getInputString())
                .setParameter(2, personalIdentityNumber.getCalculatedControlNumber())
                .setParameter(3, new Date(System.currentTimeMillis()))
                .executeUpdate();
    }
}
