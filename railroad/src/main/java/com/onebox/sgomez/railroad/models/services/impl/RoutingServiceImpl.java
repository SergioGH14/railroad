package com.onebox.sgomez.railroad.models.services.impl;

import java.util.ArrayList;
import java.util.List;

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
	public String calculateRouteDistance(List<String> towns) {
		Integer distance = 0;
		Boolean areConnection = true;
		List<Town> townsToVisit = generateTowns(towns);
		Town backTown = null;

		for (Town town : townsToVisit) {
			if (backTown != null && areConnection) {
				areConnection &= (distanceBetweenTowns(backTown, town) == 0) ? false : true;
				distance += distanceBetweenTowns(backTown, town);
			}
			backTown = town;
		}
		return (areConnection) ? String.valueOf(distance) : "NO SUCH ROUTE";
	}

	private List<Town> generateTowns(List<String> towns) {
		List<Town> townsList = new ArrayList<>();
		towns.forEach(x -> townsList.add(townService.findById(x).get()));
		return townsList;
	}

	private Integer distanceBetweenTowns(Town origin, Town destination) {
		List<RailRoad> routeBetweenTowns = shortestRouteBetweenTowns(origin, destination);
		return routeBetweenTowns.stream().mapToInt(x -> x.getDistance()).sum();
	}

	private List<RailRoad> shortestRouteBetweenTowns(Town origin, Town destination) {
		List<RailRoad> shortestRoute = new ArrayList<>();

		if (railRoadService.areConected(origin, destination)) {
			RailRoad directConnect = railRoadService.getRailRoad(origin, destination);
			shortestRoute.add(directConnect);
		} else {
			List<RailRoad> originOutPutTowns = railRoadService.findOutPutRailRodes(origin);
			List<RailRoad> destinationInputTowns = railRoadService.findInPutRailRodes(destination);

			for (RailRoad destinationRailRoad : destinationInputTowns) {
				for (RailRoad originRailRoad : originOutPutTowns) {
					if (destinationRailRoad.getOrigin().equals(originRailRoad.getDestination())
							&& shortestRoute.isEmpty()) {
						shortestRoute.add(destinationRailRoad);
						shortestRoute.add(originRailRoad);
					}
				}
			}
		}
		return shortestRoute;
	}

}
