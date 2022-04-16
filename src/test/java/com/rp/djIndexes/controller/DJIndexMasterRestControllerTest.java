package com.rp.djIndexes.controller;

import com.rp.djIndexes.model.DJIndex;
import com.rp.djIndexes.repository.DJIndexRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DJIndexMasterRestControllerTest {

    @Autowired
    DJIndexRepository djIndexRepository;


    @Test
    public void getIndexByStockTest(){
        List<DJIndex> djIndexes = djIndexRepository.findByStock("AA");
        if (!djIndexes.isEmpty())
         Assertions.assertThat(djIndexes.get(0).getStock()).isEqualTo("AA");

    }


    @Test
    public void addStockIndexTest(){
        DJIndex djIndex = new DJIndex();
        djIndex.setStock("AV");
        djIndex.setQuarter("1");
        djIndex.setHigh("$16.72");
        djIndexRepository.save(djIndex);
        List<DJIndex> djIndexes = djIndexRepository.findByStock("AV");
        if (!djIndexes.isEmpty())
            Assertions.assertThat(djIndexes.get(0).getStock()).isEqualTo("AV");

    }
/*
    @Test
    public void addStockIndexTest(){
        DJIndex djIndex = new DJIndex();
        djIndex.setStock("AA");
        djIndex.setQuarter("1");
        djIndex.setHigh("$16.72");


        djIndexRepository.save(djIndex);

        Assertions.assertThat(djIndex.getHigh().isGreaterThan(0);
    }


  @Test
    public void addStockIndex() {
        DJIndex tutorial = repository.save(new DJIndex("Tut title", "Tut desc", true));
        assertThat(tutorial).hasFieldOrPropertyWithValue("title", "Tut title");
        assertThat(tutorial).hasFieldOrPropertyWithValue("description", "Tut desc");
        assertThat(tutorial).hasFieldOrPropertyWithValue("published", true);
    }

    @Test
    public void testGetStockByTickerId() {
        DJIndex djIndex = new DJIndex();
        djIndex.setStock("AA");
        djIndex.setQuarter("1");
        djIndex.setHigh("$16.72");
        //entityManager.persist(djIndex);
        List<DJIndex> searchedDjIndex = repository.findByStock(djIndex.getStock());
        assertThat(searchedDjIndex.get(0).getStock()).isEqualTo(djIndex.getStock());
        assertThat(searchedDjIndex.get(0).getHigh()).isEqualTo(djIndex.getHigh());

    }

    /*
    @Test
    public void addStockIndexData() {
        Tutorial tut1 = new Tutorial("Tut#1", "Desc#1", true);
        entityManager.persist(tut1);
        Tutorial tut2 = new Tutorial("Tut#2", "Desc#2", false);
        entityManager.persist(tut2);
        Tutorial updatedTut = new Tutorial("updated Tut#2", "updated Desc#2", true);
        Tutorial tut = repository.findById(tut2.getId()).get();
        tut.setTitle(updatedTut.getTitle());
        tut.setDescription(updatedTut.getDescription());
        tut.setPublished(updatedTut.isPublished());
        repository.save(tut);
        Tutorial checkTut = repository.findById(tut2.getId()).get();

        assertThat(checkTut.getId()).isEqualTo(tut2.getId());
        assertThat(checkTut.getTitle()).isEqualTo(updatedTut.getTitle());
        assertThat(checkTut.getDescription()).isEqualTo(updatedTut.getDescription());
        assertThat(checkTut.isPublished()).isEqualTo(updatedTut.isPublished());
    }
    */
}
