package com.example.email;

import com.example.email.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class EmailApplicationTests {
	@Autowired
	EmailService emailService;

	@Test
	void EmailFilterTest() {
		List<String> emailTo = new ArrayList<>();
		Set<String> filterList = new HashSet<>();
		emailTo.add("bbq598@hotmail.com");
		emailTo.add("bbq598@gmail.com");
		filterList.add("hotmail.com");
		emailService.emailFilterHelper(emailTo,filterList);
		assertEquals("bbq598@hotmail.com", emailTo.get(0));
	}




}
