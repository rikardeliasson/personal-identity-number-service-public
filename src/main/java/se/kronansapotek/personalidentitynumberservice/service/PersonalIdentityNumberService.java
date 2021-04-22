package se.kronansapotek.personalidentitynumberservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.kronansapotek.personalidentitynumberservice.repository.DataRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonalIdentityNumberService {

    @Autowired
    DataRepository dataRepository;

    public ResponseEntity<Boolean> validateAndPersistIdentityInput(String personalIdentityNumberString) {
        boolean isValid;
        try {
            isValid = isValid(personalIdentityNumberString);
            dataRepository.persist(personalIdentityNumberString, isValid);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
        return new ResponseEntity<>(isValid, HttpStatus.OK);
    }

    /*
   Implementation of Luhn Algorithm for validating personal identity numbers
   For reference https://en.wikipedia.org/wiki/Luhn_algorithm
    */
    public boolean isValid(String personalIdentityNumberString) {
        List<Integer> numbers = personalIdentityNumberString.chars().boxed().collect(Collectors.toList());

        List<Integer> result = new ArrayList<>();
        for (int i = numbers.size() - 1; i >= 0; i = i - 2) {
            int num = numbers.get(i);
            num = num * 2;
            if (num > 9) {
                num = num % 10 + num / 10;
            }
            result.add(num);
        }

        int sum = result.stream().mapToInt(Integer::intValue).sum();
        return sum % 10 == 0;
    }
}