package hello;

import static org.junit.Assert.assertNotNull;

import java.nio.charset.Charset;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(GreetingController.class)
public class GreetingControllerTest {
	@Autowired
	private MockMvc mvc;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Test
	public void greeting() throws Exception {
		String helloFred = "Hello, World!";
		Greeting greeting = new Greeting(0, helloFred);
		String expected = "{\"id\":1,\"content\":\"Hello, World!\"}";
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/greeting").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		
		MockHttpServletRequestBuilder mm = MockMvcRequestBuilders.get("/greeting").contentType(MediaType.APPLICATION_JSON);
		mvc.perform(mm).andDo(MockMvcResultHandlers.print());
		ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/greeting").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.content", Is.is(greeting.getContent())));
		assertNotNull(resultActions);
	}
}
