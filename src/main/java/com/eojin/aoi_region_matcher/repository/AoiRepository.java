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

    @Query(value =
            "SELECT * " +
            "FROM aoi " +
            "ORDER BY ST_Distance(aoi.area, ST_SetSRID(ST_Point(:x, :y), 4326)) LIMIT 1;",
            nativeQuery = true
    )
    AOI getNearestAoiByPoint(Double x, Double y);

    AOI getByName(String name);
}
