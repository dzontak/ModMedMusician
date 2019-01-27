package com.modmed.musician.dao;

import com.modmed.musician.model.Place;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(Transactional.TxType.SUPPORTS)
@Slf4j
public class PlaceRepositoryTest {

  @Autowired PlaceRepository placeRepository;

  @Test
  public void shouldFindPlaceByTownName() {

    Place place = placeRepository.findPlaceByTown("Manchester");
    Assert.assertNotNull(place);
    Assert.assertEquals("Manchester", place.getTown());
    Assert.assertEquals("England", place.getCountry());
  }

  @Test
  public void shouldFindPlacesByCountry() {

    Set<Place> places = placeRepository.findPlacesByCountry("London");
    Assert.assertNotNull(places);
  }
}
