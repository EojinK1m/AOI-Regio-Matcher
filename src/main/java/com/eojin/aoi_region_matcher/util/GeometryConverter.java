package com.eojin.aoi_region_matcher.util;

import com.eojin.aoi_region_matcher.payload.request.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GeometryConverter {

    public Polygon convertCoordinatesToPolygon(List<Coordinate> area){
        GeometryFactory fact = new GeometryFactory(new PrecisionModel(), 4326);
        org.locationtech.jts.geom.Coordinate[] coordinates =
                new org.locationtech.jts.geom.Coordinate[area.size()];

        for(int i=0; i<area.size(); i++){
            Coordinate coordinate = area.get(i);
            coordinates[i] = new org.locationtech.jts.geom.Coordinate(coordinate.getX(), coordinate.getY());
        }

        return fact.createPolygon(coordinates);
    }

    public List<Coordinate> convertPolygonToCoordinateList(Polygon polygon){
        List<Coordinate> coordinates = new ArrayList<>();

        for(org.locationtech.jts.geom.Coordinate coordinate: polygon.getCoordinates()){
            coordinates.add(
                    Coordinate.builder().x(coordinate.getX()).y(coordinate.getY()).build()
            );
        }

        return coordinates;
    }
}