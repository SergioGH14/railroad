package com.onebox.sgomez.railroad.dao;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.onebox.sgomez.railroad.models.dao.RailRoadDao;
import com.onebox.sgomez.railroad.models.entity.Town;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RailRoadDaoTest {

	@Autowired
	private RailRoadDao railRoadDao;

	@Test
	public void findOutPutRailRodesTest() {
		Assert.assertNotNull("", railRoadDao.findOutPutRailRodes(new Town("A")));
		Assert.assertEquals("" ,new Town("C").getName(), railRoadDao.findOutPutRailRodes(new Town("B")).get(0).getDestination().getName());
	}
	
	@Test
	public void findInPutRailRodesTest() {
		Assert.assertNotNull("", railRoadDao.findInPutRailRodes(new Town("B")));
		Assert.assertEquals("" ,new Town("C").getName(), railRoadDao.findInPutRailRodes(new Town("D")).get(0).getOrigin().getName());
	}
	
	@Test
	public void getRailRoadTest() {
		Assert.assertNotNull("", railRoadDao.getRailRoad(new Town("A"), new Town("B")));
		Assert.assertEquals("" ,new Town("A").getName(), railRoadDao.getRailRoad(new Town("A"), new Town("B")).getOrigin().getName());
	}
	
	@Test
	public void areConectedTest() {
		Assert.assertTrue("", railRoadDao.areConected(new Town("A"), new Town("B")));
	}
	
}
