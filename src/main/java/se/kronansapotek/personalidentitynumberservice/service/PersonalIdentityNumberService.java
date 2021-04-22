package se.kronansapotek.personalidentitynumberservice.service;

import org.apache.coyote.Response;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonalIdentityNumberService {

    public HttpResponse validateIdentityNumber(String personalIdentityNumberString) {
        return  {
        };
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
