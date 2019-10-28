package com.onebox.sgomez.railroad.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.onebox.sgomez.railroad.models.entity.RailRoad;
import com.onebox.sgomez.railroad.models.entity.Town;

public interface RailRoadDao extends CrudRepository<RailRoad, Long> {
	@Query("SELECT r FROM RailRoad r WHERE r.origin = :origin")
	List<RailRoad> findOutPutRailRodes(@Param("origin") Town town);

	@Query("SELECT r FROM RailRoad r WHERE r.destination = :destination")
	List<RailRoad> findInPutRailRodes(@Param("destination") Town town);
	
	@Query("SELECT r FROM RailRoad r WHERE r.origin = :origin AND r.destination = :destination")
	RailRoad getRailRoad(@Param("origin") Town originTown, @Param("destination") Town destinationTown);

	@Query("SELECT case WHEN count(r) > 0 THEN true ELSE false end FROM RailRoad r WHERE r.origin = :origin AND r.destination = :destination")
	Boolean areConected(@Param("origin") Town originTown, @Param("destination") Town destinationTown);

}
