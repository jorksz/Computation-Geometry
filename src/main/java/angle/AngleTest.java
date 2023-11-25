package angle;

import cn.hutool.core.date.DateUtil;

import java.awt.geom.Point2D;

public class AngleTest {

    /**
     * 通过向量来计算夹角 B 的角度
     * @param pointA
     * @param pointB
     * @param pointC
     * @return
     */
    public static double calculateAngle(Point2D.Double pointA, Point2D.Double pointB, Point2D.Double pointC) {
        Point2D.Double vectorAB = new Point2D.Double(pointB.x - pointA.x, pointB.y - pointA.y);
        Point2D.Double vectorBC = new Point2D.Double(pointC.x - pointB.x, pointC.y - pointB.y);
        double dotProduct = vectorAB.x * vectorBC.x + vectorAB.y * vectorBC.y;
        double magnitudeAB = Math.sqrt(vectorAB.x * vectorAB.x + vectorAB.y * vectorAB.y);
        double magnitudeBC = Math.sqrt(vectorBC.x * vectorBC.x + vectorBC.y * vectorBC.y);
        double cosine = dotProduct / (magnitudeAB * magnitudeBC);
        double angleRad = Math.acos(cosine);
        return Math.toDegrees(angleRad);
    }

    public static void main(String[] args) {
        Point2D.Double pointA = new Point2D.Double(0, 0);
        Point2D.Double pointB = new Point2D.Double(1, 0);
        Point2D.Double pointC = new Point2D.Double(1, 1);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            double angle = calculateAngle(pointA, pointB, pointC);
          //  System.out.println("向量计算角度: " + angle);
        }
        System.out.println("循环1E次向量耗时:" + DateUtil.formatBetween(System.currentTimeMillis() - start));

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            double lengthAB = calculateDistance(pointA, pointB);
            double lengthBC = calculateDistance(pointB, pointC);
            double lengthCA = calculateDistance(pointC, pointA);
            double angle = calculateAngle(lengthAB, lengthBC, lengthCA);
            //System.out.println("边长计算角度:" + calculateAngle(lengthAB, lengthBC, lengthCA));
        }
        System.out.println("循环1E边长耗时:" + DateUtil.formatBetween(System.currentTimeMillis() - start2));
    }

    public static double calculateDistance(Point2D.Double pointA, Point2D.Double pointB) {
        double dx = pointB.x - pointA.x;
        double dy = pointB.y - pointA.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double calculateAngle(double a, double b, double c) {
        double cosC = (a * a + b * b - c * c) / (2 * a * b);
        double angleC = Math.acos(cosC);
        return Math.toDegrees(angleC);
    }
}
