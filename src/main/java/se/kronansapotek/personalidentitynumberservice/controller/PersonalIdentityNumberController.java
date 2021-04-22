package se.kronansapotek.personalidentitynumberservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import se.kronansapotek.personalidentitynumberservice.service.PersonalIdentityNumberService;

@RestController
public class PersonalIdentityNumberController {

    @Autowired
    private PersonalIdentityNumberService personalIdentityNumberService;

    @Transactional
    @GetMapping(value = "/val/{in}")
    public ResponseEntity<Boolean> validate(@PathVariable("in") String personalIdentityNumberString) {
        return personalIdentityNumberService.validateAndPersistIdentityInput(personalIdentityNumberString);
    }
}