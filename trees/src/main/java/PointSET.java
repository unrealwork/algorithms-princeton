
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.LinkedList;
import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> set;

    /* construct an empty set of points */
    public PointSET() {
        set = new TreeSet<>();
    }

    /**
     * Is the set empty?
     *
     * @return true if empty
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Number of points in the set
     *
     * @return
     */
    public int size() {
        return set.size();
    }


    /**
     * Add the point to the set (if it is not already in the set)
     *
     * @param p
     */
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        set.add(p);
    }

    /**
     * Does the set contain point p?
     *
     * @param p -point on the plane
     * @return
     */
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return set.contains(p);
    }

    /**
     * Draw all points to standard draw.
     */
    public void draw() {
        for (Point2D p : set) {
            StdDraw.point(p.x(), p.y());
        }
    }

    /**
     * All points that are inside the rectangle (or on the boundary)
     *
     * @param rect - rectangle range for search.
     * @return Iterable<Point2D>
     */
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        LinkedList<Point2D> result = new LinkedList<>();
        double xmax = rect.xmax();
        double xmin = rect.xmin();
        double ymin = rect.ymin();
        double ymax = rect.ymax();

        for (Point2D p : set) {
            double x = p.x();
            double y = p.y();
            if (xmax >= x && xmin <= x &&
                ymax >= y && ymin <= y) {
                result.add(p);
            }
        }
        return result;
    }

    /**
     * A nearest neighbor in the set to point p; null if the set is empty.
     *
     * @param p point
     * @return nearest point to p
     */
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if (set.isEmpty()) {
            return null;
        }
        Point2D minPoint = set.first();
        double minD = minPoint.distanceTo(p);
        for (Point2D setPoint : set) {
            double currentDistance = p.distanceTo(setPoint);
            if (currentDistance < minD) {
                minPoint = setPoint;
                minD = currentDistance;
            }
        }
        return minPoint;
    }

    /**
     * Unit testing of the methods (optional).
     *
     * @param args
     */
    public static void main(String[] args) {
        throw new IllegalStateException();
    }
}

