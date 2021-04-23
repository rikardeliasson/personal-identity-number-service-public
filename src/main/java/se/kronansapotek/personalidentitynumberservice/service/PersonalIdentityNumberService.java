package se.kronansapotek.personalidentitynumberservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.kronansapotek.personalidentitynumberservice.repository.DataRepository;

import java.util.Arrays;
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
   Implementation of Luhn Algorithm for validating control number in personal identity number
   For reference https://en.wikipedia.org/wiki/Luhn_algorithm
    */
    public boolean isValid(String personalIdentityNumberString) {
        personalIdentityNumberString = personalIdentityNumberString.replaceAll("\\D+","");
        List<Integer> numbers = Arrays.stream(personalIdentityNumberString.split("")).map(Integer::parseInt).collect(Collectors.toList());
        int controlNumber = numbers.get(numbers.size() - 1);
        numbers.remove(numbers.size() - 1);
        for (int i = numbers.size() - 1; i >= 0; i = i - 2) {
            int num = numbers.get(i);
            num = num * 2;
            if (num > 9) {
                num =num - 9;
            }
            numbers.remove(i);
            numbers.add(num);
        }
        return controlNumber == numbers.stream().mapToInt(Integer::intValue).sum() % 10;
    }
}