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

    private static final String INVALID_STRING_INCORRECT_CONTROL_NUMBER = "19123456-7890";
    private static final String VALID_STRING = "19670919-9531";
    private static final String INVALID_STRING_INCORRECT_FORMAT = "BBGGFF-7893";

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
    void callingValidateWithInvalidPersonalIdentityNumberString_Incorrect_ControlNumber() {
        ResponseEntity<Boolean> response = service.validateAndPersistIdentityInput(INVALID_STRING_INCORRECT_CONTROL_NUMBER);
        assertTrue("Validation threw exception: ", response.getStatusCode().is2xxSuccessful());
        assertFalse("The validation should have failed", response.getBody());
    }

    @Test
    void callingValidateWithInvalidPersonalIdentityNumberString_Incorrect_Format() {
        ResponseEntity<Boolean> response = service.validateAndPersistIdentityInput(INVALID_STRING_INCORRECT_FORMAT);
        assertTrue("Validation did not throw exception: ", response.getStatusCode().is4xxClientError());
        assertFalse("The validation should have failed", response.getBody());
    }
}