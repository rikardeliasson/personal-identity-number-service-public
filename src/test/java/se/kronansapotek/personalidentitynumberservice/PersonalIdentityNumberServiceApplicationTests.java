package se.kronansapotek.personalidentitynumberservice;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest
class PersonalIdentityNumberServiceControllerTest {
	@Mock
	DataRepository repository;
	@InjectMocks
	PersonalIdentityNumberController controller = new PersonalIdentityNumberController();

	@Test
	void callingValidateWithValidPersonalIdentityNumberString() {
		String personalIdentityNumberString = "670919-9530";
		boolean valid = controller.validate(personalIdentityNumberString);
		assertTrue("The validation should have passed", valid);
	}

	@Test
	void callingValidateWithInvalidPersonalIdentityNumberString() {
		String personalIdentityNumberString = "123456-7890";
		boolean valid = controller.validate(personalIdentityNumberString);
		assertFalse("The validation should have failed", valid);
	}
}
