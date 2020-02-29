package com.tsel.itmaker.starter.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void testNotFound() throws Exception {
		mockMvc.perform(get("/subscriber"))
			.andExpect(status().isNotFound());
	}

	@Test
	void testNotStartWith62() throws Exception {
		mockMvc.perform(get("/subscribers/811123455"))
			.andExpect(status().isBadRequest());
	}

	
	@Test
	void testPostpaid() throws Exception {
		mockMvc.perform(get("/subscribers/628119112345"))
			.andExpect(
				mvcResult -> {
					String contentResult = mvcResult.getResponse().getContentAsString();
					System.out.println(contentResult);
					ObjectMapper mapper = new ObjectMapper();
					JsonNode objRoot = mapper.readTree(contentResult);
					assertNotNull(objRoot);
					// assertNotNull(actualObj.;
					JsonNode objType = objRoot.get("type");
					assertNotNull(objType);
					assertEquals(objType.asText(), "postpaid");
				}
			);
	}

	@Test
	void testPrepaid() throws Exception {
		mockMvc.perform(get("/subscribers/628129112345"))
			.andExpect(
				mvcResult -> {
					String contentResult = mvcResult.getResponse().getContentAsString();
					System.out.println(contentResult);
					ObjectMapper mapper = new ObjectMapper();
					JsonNode objRoot = mapper.readTree(contentResult);
					assertNotNull(objRoot);
					// assertNotNull(actualObj.;
					JsonNode objType = objRoot.get("type");
					assertNotNull(objType);
					assertEquals(objType.asText(), "prepaid");
				}
			);
	}

}
