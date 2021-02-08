
package cz.educanet.tranformations.logic;

import cz.educanet.tranformations.logic.models.Coordinate;

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


    private boolean check(int u1, int u2, int u3, int a1, int a2, int b1, int b2) {
        int x = (u1 * a1 + u2 * a2 + u3);
        int y = (u1 * b1 + u2 * b2 + u3);
        return (x * y > 0);
    }


}