package se.kronansapotek.personalidentitynumberservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import se.kronansapotek.personalidentitynumberservice.repository.DataRepository;
import se.kronansapotek.personalidentitynumberservice.service.PersonalIdentityNumberService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class PersonalIdentityNumberController {

    @Autowired
    private DataRepository repository;
    private PersonalIdentityNumberService personalIdentityNumberService;

    @Transactional
    @GetMapping(value = "/val/{in}")
    public boolean validate(@PathVariable("in") String personalIdentityNumberString) {
        boolean validated = personalIdentityNumberService.isValid(personalIdentityNumberString);
        int persistedCount = repository.persist(personalIdentityNumberString, validated);
        return validated;
    }
   }