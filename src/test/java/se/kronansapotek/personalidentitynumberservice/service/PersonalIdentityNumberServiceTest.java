package se.kronansapotek.personalidentitynumberservice.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import se.kronansapotek.personalidentitynumberservice.repository.DataRepository;

import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
class PersonalIdentityNumberServiceTest {

    private static final String INVALID_STRING = "123456-7890";
    private static final String VALID_STRING = "670919-9530";

    @Mock
    DataRepository repository;
    @InjectMocks
    PersonalIdentityNumberService service = new PersonalIdentityNumberService();

    @Test
    void callingValidateWithValidPersonalIdentityNumberString() {
        ResponseEntity<Boolean> response = service.validateAndPersistIdentityInput(VALID_STRING);
        assertTrue("Validation threw exception: ", response.getStatusCode().is2xxSuccessful());
        assertTrue("The validation should have passed", response.getBody());
    }

    @Test
    void callingValidateWithInvalidPersonalIdentityNumberString() {
        ResponseEntity<Boolean> response = service.validateAndPersistIdentityInput(INVALID_STRING);
        assertTrue("Validation threw exception: ", response.getStatusCode().is2xxSuccessful());
        assertFalse("The validation should have failed", response.getBody());
    }
}