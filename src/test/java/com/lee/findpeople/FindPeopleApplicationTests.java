package com.lee.findpeople;

import com.lee.FindPeopleApplication;
import com.lee.beans.Organization;
import com.lee.service.OrganizationService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class FindPeopleApplicationTests {
	@Autowired
	private OrganizationService organizationService;
	@Test
	void testRegMatchEmail() {
		List<Organization> list = organizationService.list(null);
		// System.out.println(list); xxxx@ustr.eop.gov
		String mail = "ustr.eop.gov";
		for(Organization organization:list) {
			// System.out.println(organization.getOrgMail());
			boolean matches = organization.getOrgMail().contains(mail);
			if(matches) {
				System.out.println(organization.getOrgMail());
				// System.out.println(matches);
			}
		}
	}

}
