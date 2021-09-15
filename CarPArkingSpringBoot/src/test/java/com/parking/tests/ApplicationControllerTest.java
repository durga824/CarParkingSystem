package com.parking.tests;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parking.application.classes.Car;
import com.parking.application.controller.ApplicatioController;

import net.minidev.json.parser.JSONParser;


public class ApplicationControllerTest {
	
	ObjectMapper mapper = new ObjectMapper();
	
	private MockMvc mocMvc;
	
	
	@Test
	public void testParktheCarTest() throws Exception
	{	
		ApplicatioController appcontroller = new ApplicatioController();
		mocMvc = MockMvcBuilders.standaloneSetup(appcontroller).build();
		Car request  = new Car("RED","AP091122");
		
		
		MvcResult result = mocMvc.perform(MockMvcRequestBuilders.post("/ParkTheCar")
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(request))).andReturn();
		assert result.getResponse().getStatus() == 200;
		
		JSONObject responseColour = (JSONObject) new JSONParser().parse(result.getResponse().getContentAsString());
		JSONObject object = (JSONObject) responseColour.get("carDetails");
		assert (object.get("carColor")).equals("RED");
		JSONObject responseNumber = (JSONObject) new JSONParser().parse(result.getResponse().getContentAsString());
		JSONObject object1 = (JSONObject) responseNumber.get("carDetails");
		assert (object1.get("carNumber")).equals("AP091122");
		
	}
	
	@Test
	public void testUnParkTheCar() throws JsonProcessingException, Exception
	{
		ApplicatioController appcontroller = new ApplicatioController();
		mocMvc = MockMvcBuilders.standaloneSetup(appcontroller).build();
		Car request  = new Car("RED","AP091122");
		MvcResult result = mocMvc.perform(MockMvcRequestBuilders.get("UnParkCar?tokenNumber=1631529477746")).andReturn();
		assert result.getResponse().getStatus() == 200;		
			
	}
	
	@Test
	public void testSearchCarNumber() throws Exception
	{
		ApplicatioController appcontroller = new ApplicatioController();
		mocMvc = MockMvcBuilders.standaloneSetup(appcontroller).build();
		Car request  = new Car("RED","AP091122");
		MvcResult result = mocMvc.perform(MockMvcRequestBuilders.get("serchCar?carNumber=AP091122")).andReturn();
		assert result.getResponse().getStatus() == 200;
	}
	
	@Test
	public void testTotalNoOfSlots() throws Exception
	{
		ApplicatioController appcontroller = new ApplicatioController();
		mocMvc = MockMvcBuilders.standaloneSetup(appcontroller).build();
		Car request  = new Car("RED","AP091122");
		MvcResult result = mocMvc.perform(MockMvcRequestBuilders.get("totalnoOfslots?totalSlots=6")).andReturn();
		assert result.getResponse().getStatus() == 200;
	}

}
