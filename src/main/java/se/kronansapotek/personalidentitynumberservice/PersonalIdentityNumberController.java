package se.kronansapotek.personalidentitynumberservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class PersonalIdentityNumberController {

    @Autowired
    public DataRepository repository;

    @Transactional
    @GetMapping(value = "/val/{in}")
    public boolean validate(@PathVariable("in") String personalIdentityNumberString) {
        boolean validated = isValid(personalIdentityNumberString);
        int persistedCount = repository.persist(personalIdentityNumberString, validated);
        return validated;
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