package com.onebox.sgomez.railroad.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onebox.sgomez.railroad.models.services.RoutingService;

@Controller
@RequestMapping("route")
public class RouteController {
	
	@Autowired 
	private RoutingService routingService;
    
	@PostMapping("/distance")
	public Integer getRouteDistance(@RequestBody List<String> towns) {
		return routingService.calculateRouteDistance(towns);
	}
}