package com.example.sapient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SapientApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testgetcall() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("https://localhost:8081/country/england/league/championship/team/brentford")).andExpect(status().isOk()).andReturn();
		Assertions.assertEquals(mvcResult.getResponse().getContentAsString(), "{\"country_id\":\"41\",\"country_name\":\"England\",\"league_id\":\"149\",\"league_name\":\"Championship\",\"team_id\":\"2644\",\"team_name\":\"Brentford\",\"ranking\":\"3\"}");
	}

}
