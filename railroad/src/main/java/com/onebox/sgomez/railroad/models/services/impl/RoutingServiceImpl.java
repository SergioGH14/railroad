package com.onebox.sgomez.railroad.models.services.impl;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onebox.sgomez.railroad.exceptions.RailRoadConnectionException;
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

	private static final String ERROR_MESSAGE = "NO SUCH ROUTE";

	@Override
	public String calculateRouteDistance(List<String> towns) {
		Integer distance = 0;
		Boolean areConnection = true;
		List<Town> townsToVisit;
		try {
			townsToVisit = generateTowns(towns);

			Town backTown = null;

			for (Town town : townsToVisit) {
				if (backTown != null) {
					distance += distanceBetweenTowns(backTown, town);
				}
				backTown = town;
			}
		} catch (InputMismatchException | RailRoadConnectionException err) {
			return ERROR_MESSAGE;
		} 
		return (areConnection) ? String.valueOf(distance) : ERROR_MESSAGE;
	}

	private List<Town> generateTowns(List<String> towns) throws InputMismatchException {
		List<Town> townsList = new ArrayList<>();
		towns.forEach(x -> {
			Town town = townService.findById(x).orElseThrow(() -> new InputMismatchException("City not found"));
			townsList.add(town);
		});
		return townsList;
	}

	private Integer distanceBetweenTowns(Town origin, Town destination) throws RailRoadConnectionException {
		List<RailRoad> routeBetweenTowns = shortestRouteBetweenTowns(origin, destination);
		routeBetweenTowns.forEach(x->System.out.println(x.getDistance()));
		Integer distance = routeBetweenTowns.stream().mapToInt(x -> x.getDistance()).sum();
		if (distance == 0) {
			throw new RailRoadConnectionException(ERROR_MESSAGE);
		}
		return distance;
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
