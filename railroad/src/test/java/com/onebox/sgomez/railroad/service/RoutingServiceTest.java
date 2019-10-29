package com.onebox.sgomez.railroad.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.onebox.sgomez.railroad.models.services.RoutingService;

@SpringBootTest
public class RoutingServiceTest {
	
	@Autowired
	private RoutingService routingService;
	
	@Test
	public void testCalculateRouteDistanceNull() {
		List<String> notNullList = new ArrayList<>();
		notNullList.add("A");
		notNullList.add("B");
		Assert.assertNotNull("testCalculateRouteDistance: Null return", routingService.calculateRouteDistance(notNullList));
	}
	@Test
	public void testCalculateRouteDistanceAD() {
		List<String> adList = new ArrayList<>();
		adList.add("A");
		adList.add("D");
		Assert.assertEquals("testCalculateRouteDistance: AD not equals to 5", "5", routingService.calculateRouteDistance(adList));
	}
	@Test
	public void testCalculateRouteDistanceADC() {
		List<String> adcList = new ArrayList<>();
		adcList.add("A");
		adcList.add("D");
		adcList.add("C");
		Assert.assertEquals("testCalculateRouteDistance: ADC not equals to 13", "13", routingService.calculateRouteDistance(adcList));
	}
	@Test
	public void testCalculateRouteDistanceABC() {
		List<String> abcList = new ArrayList<>();
		abcList.add("A");
		abcList.add("B");
		abcList.add("C");
		Assert.assertEquals("testCalculateRouteDistance: ABC not equals to 9", "9", routingService.calculateRouteDistance(abcList));
	}
	@Test
	public void testCalculateRouteDistanceAEBDC() {
		List<String> aebcdList = new ArrayList<>();
		aebcdList.add("A");
		aebcdList.add("E");
		aebcdList.add("B");
		aebcdList.add("C");
		aebcdList.add("D");
		Assert.assertEquals("testCalculateRouteDistance: AEBDC not equals to 22", "22", routingService.calculateRouteDistance(aebcdList));
	}
	@Test
	public void testCalculateRouteDistanceAED() {
		List<String> aedList = new ArrayList<>();
		aedList.add("A");
		aedList.add("E");
		aedList.add("D");
		Assert.assertEquals("testCalculateRouteDistance: AED not equals to NO SUCH ROUT", "NO SUCH ROUTE", routingService.calculateRouteDistance(aedList));
	}

}
