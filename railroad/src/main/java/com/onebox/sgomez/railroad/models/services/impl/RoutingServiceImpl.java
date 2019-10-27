package com.onebox.sgomez.railroad.models.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onebox.sgomez.railroad.models.dao.RailRoadDao;
import com.onebox.sgomez.railroad.models.dao.TownDao;
import com.onebox.sgomez.railroad.models.entity.RailRoad;
import com.onebox.sgomez.railroad.models.entity.Town;
import com.onebox.sgomez.railroad.models.services.RoutingService;

@Service
public class RoutingServiceImpl implements RoutingService {

	@Autowired
	private RailRoadDao railRoadService;

	@Autowired
	private TownDao townService;

	@Override
	public Integer calculateRouteDistance(List<String> towns) {
		List<Town> townsList = generateTowns(towns);
		List<Town> visitedTowns = new ArrayList<>();
		Map<Long,RailRoad> railRoadsList = new HashMap<>();
	
		return 0;
	}

	private List<Town> generateTowns(List<String> towns) {
		List<Town> townsList = new ArrayList<>();
		towns.forEach(x -> townsList.add(townService.findById(x).get()));
		return townsList;
	}

}
