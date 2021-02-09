
package cz.educanet.tranformations.logic;

import cz.educanet.tranformations.logic.models.Coordinate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class ScreenManager {

    private final Set<Coordinate> selectedPoints = new HashSet<>();

    public void select(Coordinate coordinate) {
        selectedPoints.add(coordinate);
        System.out.println(coordinate.getX() + ", " + coordinate.getY());
    }

    public void unselect(Coordinate coordinate) {
        selectedPoints.remove(coordinate);
    }

    public Coordinate[] sortCoordinatesByYasc() {
        return selectedPoints.stream().sorted(Comparator.comparingInt(Coordinate::getY)).toArray(Coordinate[]::new);
    }

    public Coordinate[] getCoordinates() {
        return selectedPoints.toArray(Coordinate[]::new);
    }

    public boolean isSelected(Coordinate coordinate) {
        return selectedPoints.contains(coordinate);
    }

    public Set<Coordinate> getSelectedPoints() {
        return selectedPoints;
    }

    public int orient2d(Coordinate a, Coordinate b, Coordinate c)
    {
        return (b.getY() - c.getY()) * (a.getX() - c.getX()) - (c.getX() - b.getX()) * (c.getY() - a.getY());
    }

    public boolean isFilledIn(Coordinate coordinate) {
        if (selectedPoints.size() != 3) {
            return false;

        } else {
            ArrayList<Coordinate> points = new ArrayList<>();

            for (Coordinate point : selectedPoints) {
                    points.add(point);
            }
            Coordinate v0 = points.get(0);
            Coordinate v1 = points.get(1);
            Coordinate v2 = points.get(2);

            int aDet = ((coordinate.getX() - v2.getX()) * (v1.getY() - v2.getY())) + ((coordinate.getY() - v2.getY()) * (v2.getX() - v1.getX()));
            int bDet = ((coordinate.getX() - v2.getX()) * (v2.getY() - v0.getY())) + ((coordinate.getY() - v2.getY()) * (v0.getX() - v2.getX()));
            int cD = orient2d(v0, v1, v2) - aDet - bDet;

            int min = Math.min(orient2d(v0, v1, v2), 0);
            int max = Math.max(orient2d(v0, v1, v2), 0);

            if (aDet < min || aDet > max){
                return false;
            }else if (bDet < min || bDet > max){
                return false;
            }else if (cD < min || cD > max){
                return false;
            }
            return true;
        }
    }

}
