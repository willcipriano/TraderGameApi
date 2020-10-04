package com.wcipriano.TraderAPI.repository;

import com.wcipriano.TraderAPI.entities.Commodity.Commodity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommodityRepositoryTest {

    @Autowired
    CommodityRepository commodityRepository;

    @Test
    public void saveTest() {
        Commodity saveMe = new Commodity();
        saveMe.setName("fake name");
        saveMe.setDescription("fake description");

        commodityRepository.save(saveMe);

    }
}
