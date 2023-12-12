package Week3.CollinearPoints;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private Point[] points;
    private final LineSegment[] cached;

    public BruteCollinearPoints(Point[] points) {
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
        int n = points.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int t = k + 1; t < n; t++) {
                        double a = points[i].slopeTo(points[j]);
                        double b = points[j].slopeTo(points[k]);
                        double c = points[k].slopeTo(points[t]);
                        if (a == b && b == c) {
                            list.add(new LineSegment(points[i], points[t]));
                        }
                    }
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}