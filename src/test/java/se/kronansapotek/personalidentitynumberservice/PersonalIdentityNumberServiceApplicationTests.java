package se.kronansapotek.personalidentitynumberservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.kronansapotek.personalidentitynumberservice.controller.PersonalIdentityNumberController;
import se.kronansapotek.personalidentitynumberservice.service.PersonalIdentityNumberService;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
class PersonalIdentityNumberServiceControllerTest {
	@Mock
	PersonalIdentityNumberService service;
	@InjectMocks
	PersonalIdentityNumberController controller = new PersonalIdentityNumberController();

	private static final String INVALID_STRING = "123456-7890";
	private static final String VALID_STRING = "670919-9530";

	@BeforeEach
	void init() {
		when(service.validateAndPersistIdentityInput(VALID_STRING)).thenReturn(new ResponseEntity<>(true, HttpStatus.OK));
		when(service.validateAndPersistIdentityInput(INVALID_STRING)).thenReturn(new ResponseEntity<>(false, HttpStatus.OK));
	}

	@Test
	void callingValidateWithValidPersonalIdentityNumberString() {
		ResponseEntity<Boolean> response = controller.validate(VALID_STRING);
		assertTrue("Validation threw exception: ", response.getStatusCode().is2xxSuccessful());
		assertTrue("The validation should have passed", response.getBody());
	}

	@Test
	void callingValidateWithInvalidPersonalIdentityNumberString() {
		ResponseEntity<Boolean> response = controller.validate(INVALID_STRING);
		assertTrue("Validation threw exception: ", response.getStatusCode().is2xxSuccessful());
		assertFalse("The validation should have failed", response.getBody());
	}
}