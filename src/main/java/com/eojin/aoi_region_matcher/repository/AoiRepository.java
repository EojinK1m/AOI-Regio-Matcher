package com.eojin.aoi_region_matcher.repository;

import com.eojin.aoi_region_matcher.model.AOI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AoiRepository extends JpaRepository<AOI, Integer> {

    @Query(value = "select aa.id, aa.name, aa.area from aoi aa where ST_Overlaps(aa.area, (select area from region where id=:id))", nativeQuery = true)
    List<AOI> findOverlappedAoiByRegionId(Integer id);

    AOI getByName(String name);
}
