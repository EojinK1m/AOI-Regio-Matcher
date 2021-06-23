package com.eojin.aoi_region_matcher.util;


import com.eojin.aoi_region_matcher.dto.Coordinate;
import com.eojin.aoi_region_matcher.exception.BadAreaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.PrecisionModel;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GeometryConverterTest {
    @InjectMocks
    GeometryConverter geometryConverter;

    @Test
    public void convertCoordinatesToPolygon_returnPolygon_correctData(){
        List<Coordinate> coordinateList = new ArrayList<>(
                Arrays.asList(
                        new Coordinate(126.835, 37.688),
                        new Coordinate(127.155, 37.702),
                        new Coordinate(127.184, 37.474),
                        new Coordinate(126.821, 37.454),
                        new Coordinate(126.835, 37.688)
                )
        );
        org.locationtech.jts.geom.Coordinate[] coordinates = {
                new org.locationtech.jts.geom.Coordinate(126.835, 37.688),
                new org.locationtech.jts.geom.Coordinate(127.155, 37.702),
                new org.locationtech.jts.geom.Coordinate(127.184, 37.474),
                new org.locationtech.jts.geom.Coordinate(126.821, 37.454),
                new org.locationtech.jts.geom.Coordinate(126.835, 37.688)
        };

        Polygon expectedPolygon =
                new GeometryFactory(new PrecisionModel(), 4326).createPolygon(
                        coordinates
                );

        Assertions.assertEquals(
                expectedPolygon,
                geometryConverter.convertCoordinatesToPolygon(coordinateList)
        );
    }

    @Test
    public void convertCoordinatesToPolygon_throwBadRequestException_cantBePolygonCoordinates(){
        List<Coordinate> coordinates = new ArrayList<>(
                Arrays.asList(
                        new Coordinate(126.835, 37.688),
                        new Coordinate(127.155, 37.702),
                        new Coordinate(127.184, 37.474),
                        new Coordinate(126.821, 37.454)
                )
        );

        Assertions.assertThrows(
                BadAreaException.class,
                ()->{geometryConverter.convertCoordinatesToPolygon(coordinates);}
        );
    }

}
