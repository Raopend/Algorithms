package Week3.CollinearPoints;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private Point[] points;
    private final LineSegment[] cached;

    public FastCollinearPoints(Point[] points) {
        // check if the argument to the constructor is null
        if (points == null) throw new IllegalArgumentException();
        // check if any point in the array is null
        for (Point point : points) {
            if (point == null) throw new IllegalArgumentException();

        }
        // initialise the points array
        this.points = Arrays.copyOf(points, points.length);
        Arrays.sort(this.points);

        // check if the argument contains a repeated point
        for (int i = 0; i < this.points.length; i++) {
            if (i > 0 && Double.compare(this.points[i].slopeTo(this.points[i - 1]), Double.NEGATIVE_INFINITY) == 0) {
                throw new IllegalArgumentException();
            }
        }
        // calculate segments
        cached = cache();
    }

    private LineSegment[] cache() {
        ArrayList<LineSegment> list = new ArrayList<>();
        Arrays.sort(points);
        for (Point p : points) {
            Point[] pointsCopy = Arrays.copyOf(points, points.length);
            if (pointsCopy.length < 4) {
                continue;
            }
            Arrays.sort(pointsCopy, p.slopeOrder());
            int begin = 1;
            int end = 1;
            double prevSlope = p.slopeTo(pointsCopy[end]);
            for (int j = 2; j < pointsCopy.length; j++) {
                double currentSlope = p.slopeTo(pointsCopy[j]);
                if (Double.compare(prevSlope, currentSlope) != 0) {
                    if (end - begin >= 2) {
                        if (p.compareTo(pointsCopy[begin]) < 0) {
                            list.add(new LineSegment(p, pointsCopy[end]));
                        }
                    }
                    prevSlope = currentSlope;
                    begin = j;
                }
                end = j;
            }
            if (end - begin >= 2) {
                if (p.compareTo(pointsCopy[begin]) < 0) {
                    list.add(new LineSegment(p, pointsCopy[end]));
                }
            }
        }
        LineSegment[] segments = new LineSegment[list.size()];
        return list.toArray(segments);
    }

    public int numberOfSegments() {
        return cached.length;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(cached, cached.length);
    }


    public static void main(String[] args) {
        // read the n points from a file
        int n = StdIn.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}