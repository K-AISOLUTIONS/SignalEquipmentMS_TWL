import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ISLPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener, ActionListener {

    // Variables for all data from Excel
    public static String[] trackHeaders; public static String[] trackLines; public static String[] trackStations; public static String[] trackSides;
    public static String[] trackIds; public static double[] trackLocationsLeft; public static double[] trackLocationsRight; public static double[] trackDistances;

    public static String[] platformHeaders; public static String[] platformLines; public static String[] platformStations;
    public static double[] platformMidPtsUp; public static double[] platformMidPtsDn; public static String[] platformNames; public static String[] platformIsSingle; public static String[] platformNumberUp; public static String[] platformNumberDn;

    public static String[] signalHeaders; public static String[] signalLines; public static String[] signalStations; public static String[] signalSides;
    public static String[] signalIds; public static String[] signalTypes; public static double[] signalLocations; public static String[] signalLeftOrRights;

    public static String[] switchHeaders; public static String[] switchLines; public static String[] switchStations; public static String[] switchSides;
    public static String[] switchIds; public static double[] switchLocations;

    public static String[] crossingHeaders; public static String[] crossingLines; public static String[] crossingStations; public static String[] crossingSides;
    public static String[] crossingIds; public static double[] crossingInterceptsUp; public static double[] crossingInterceptsDn; public static String[] crossingSwitchesConnected;
    public static double[] crossingTopLeftUp; public static double[] crossingTopRightUp; public static double[] crossingBottomLeftUp; public static double[] crossingBottomRightUp;
    public static double[] crossingTopLeftDn; public static double[] crossingTopRightDn; public static double[] crossingBottomLeftDn; public static double[] crossingBottomRightDn;
    public static double[] crossingInterceptForSearchX; public static double[] crossingInterceptForSearchY; public static double[][] crossingSwitchCoordinates;

    public static String[] switchTrackHeaders; public static String[] switchTrackLines; public static String[] switchTrackFromStations; public static String[] switchTrackToStations;
    public static String[] switchTrackFromSwitches; public static String[] switchTrackToSwitches; public static double[] switchTrackBoundaryUp; public static double[] switchTrackBoundaryDn;

    public static String[] floodgateHeaders; public static String[] floodgateLines; public static String[] floodgateStations; public static String[] floodgateSides;
    public static double[] floodgateLocationsUp; public static double[] floodgateLocationsDn; public static String[] floodgateNames; public static String[] floodgateNorthSouth;

    public static String[] impedanceBondHeaders; public static String[] impedanceBondLines; public static String[] impedanceBondStations; public static String[] impedanceBondSides;
    public static String[] impedanceBondIds; public static double[] impedanceBondLocations; public static int[] impedanceBondQuantities;

    public static String[] beaconHeaders; public static String[] beaconLines; public static String[] beaconStations; public static String[] beaconSides; public static String[] beaconIds;
    public static String[] beaconTypes; public static double[] beaconLocations; public static String[] beaconLabels; public static String[] beaconReverses;

    public static String[] additionalHeaders; public static String[] additionalLines; public static String[] additionalStations; public static String[] additionalSides;
    public static double[] additionalLocations; public static String[] additionalNames;

    public static String[] sabHeaders; public static String[] sabLines; public static String[] sabStations; public static String[] sabSides;
    public static String[] sabIds; public static double[] sabLocations;

    public static String[] boxHeaders; public static String[] boxLines; public static String[] boxStations; public static String[] boxSides;
    public static String[] boxIds; public static double[] boxLocations; public static String[] boxTypes;



    // Booleans for display control
    static boolean isShowCrossingData = false;
    static boolean isShowSwitchData = false;
    static boolean isShowFloodgateData = false;
    static boolean isShowSignal = false; static boolean isShowSignalData = false;
    static boolean isShowImpedanceBond = false; static boolean isShowImpedanceBondData = false;
    static boolean isShowBeacon = false; static boolean isShowBeaconData = false;
    static boolean isShowOther = false; static boolean isShowOtherData = false;
    static boolean isShowSab = false; static boolean isShowSabData = false;
    static boolean isShowBox = false; static boolean isShowBoxData = false;


    // Variables for search
    static String selectedStation = ""; static String selectedEquipment = ""; static String selectedId = "";


    // Painting setup
    public static int upDnDistance = 200; public static int upTrack = 200; public static int dnTrack = upTrack + upDnDistance;
    int trackRefDistance = (int)(upDnDistance * 0.5);
    int equipmentRefDistance = (int)(upDnDistance * 0.7);


    // Offsets + Panning + Zooming
    public static double offsetX = -700; public static double offsetY = -250;
    public static double startPanX = 0; public static double startPanY = 0;
    public static double scaleX = 1; public static double scaleY = 1;
    public double worldToScreenConverter(double worldCoordinate, double offset, double scale) {
        return (worldCoordinate - offset) * scale;
    }
    public double screenToWorldConverter(double screenCoordinate, double offset, double scale) {
        return screenCoordinate / scale + offset;
    }


    // Function for adding extra size
    public int paddingToScale(double padding, char axis) {
        double scaledPadding = padding;
        if (axis == 'X') {
            scaledPadding = padding * scaleX;
        }
        if (axis == 'Y') {
            scaledPadding = padding * scaleY;
        }
        return (int)scaledPadding;
    }


    // Draw the reference line for different reference
    public void drawReference(Graphics2D g, int upTrack, int dnTrack, double start, double end, int refFromTrack, String refName){
        int lineExtend = 100;
        int x1 = (int)worldToScreenConverter(start - lineExtend, offsetX, scaleX);
        int x2 = (int)worldToScreenConverter(end + lineExtend, offsetX, scaleX);
        int y1 = (int)worldToScreenConverter(upTrack-refFromTrack, offsetY, scaleY);
        int y2 = (int)worldToScreenConverter(dnTrack+refFromTrack, offsetY, scaleY);

        g.drawLine(x1, y1, x2, y1);
        g.drawLine(x1, y2, x2, y2);

        Font originalFont = g.getFont();
        Font newFont = originalFont.deriveFont(originalFont.getSize() / 0.5F);
        g.setFont(newFont);

        int nameWidth = g.getFontMetrics().stringWidth(refName + "    ");
        int nameHeight = g.getFontMetrics().getHeight();
        g.drawString(refName + " UP ", x1 - nameWidth - paddingToScale(10, 'X'), y1 + nameHeight/4);
        g.drawString(refName + " DN ", x1 - nameWidth - paddingToScale(10, 'X'), y2 + nameHeight/4);

        g.drawString(refName + " UP ", x2 + paddingToScale(10, 'X'), y1 + nameHeight/4);
        g.drawString(refName + " DN ", x2 + paddingToScale(10, 'X'), y2 + nameHeight/4);

        g.setFont(originalFont);
    }


    // Draw horizontal track circuit with optional boundaries either side
    public void drawHorizontalTrack(Graphics2D g, double trackX1, double trackX2, int trackY, boolean hasTrack, boolean hasX1Boundary, boolean hasX2Boundary, int trackRefFromTrack, String upOrDn, String name, boolean isSelected){
        int x1 = (int)worldToScreenConverter(trackX1, offsetX, scaleX);
        int x2 = (int)worldToScreenConverter(trackX2, offsetX, scaleX);
        int y = (int)worldToScreenConverter(trackY, offsetY, scaleY);

        if (isSelected) {
            g.setColor(Color.RED);
        }

        if (hasTrack) {
            g.drawLine(x1, y, x2, y);
            int nameWidth = g.getFontMetrics().stringWidth(name);
            if (upOrDn.equals("UP")) {
                g.drawString(name, (x1 + x2 - nameWidth)/2, y + paddingToScale(10, 'Y'));
            }
            if (upOrDn.equals("DN")) {
                g.drawString(name, (x1 + x2 - nameWidth)/2, y - paddingToScale(5, 'Y'));
            }
        }

        if (isSelected) {
            g.setColor(Color.BLACK);
        }

        if (hasX1Boundary) {
            g.drawLine(x1, y - paddingToScale(5, 'Y'), x1, y + paddingToScale(5, 'Y'));
            int numberWidth = g.getFontMetrics().stringWidth(String.valueOf(trackX1));
            if (upOrDn.equals("UP")) {
                g.drawLine(x1, y - paddingToScale(trackRefFromTrack, 'Y'), x1, y - paddingToScale(trackRefFromTrack * 0.7, 'Y'));
                g.drawString(String.valueOf(trackX1), x1-numberWidth/2, y - paddingToScale(trackRefFromTrack + 5, 'Y'));
            }
            if (upOrDn.equals("DN")) {
                g.drawLine(x1, y + paddingToScale(trackRefFromTrack * 0.7, 'Y'), x1, y + paddingToScale(trackRefFromTrack, 'Y'));
                g.drawString(String.valueOf(trackX1), x1-numberWidth/2, y + paddingToScale(trackRefFromTrack + 10, 'Y'));
            }
        }

        if (hasX2Boundary) {
            g.drawLine(x2, y - paddingToScale(5, 'Y'), x2, y + paddingToScale(5, 'Y'));
            int numberWidth = g.getFontMetrics().stringWidth(String.valueOf(trackX2));
            if (upOrDn.equals("UP")) {
                g.drawLine(x2, y - paddingToScale(trackRefFromTrack, 'Y'), x2, y - paddingToScale(trackRefFromTrack * 0.7, 'Y'));
                g.drawString(String.valueOf(trackX2), x2-numberWidth/2, y - paddingToScale(trackRefFromTrack + 5, 'Y'));
            }
            if (upOrDn.equals("DN")) {
                g.drawLine(x2, y + paddingToScale(trackRefFromTrack * 0.7, 'Y'), x2, y + paddingToScale(trackRefFromTrack, 'Y'));
                g.drawString(String.valueOf(trackX2), x2-numberWidth/2, y + paddingToScale(trackRefFromTrack + 10, 'Y'));
            }
        }
    }


    // Draw platform with platform numbers
    public void drawPlatform(Graphics2D g, double platformMidPtUp, double platformMidPtDn, int upTrack, int dnTrack, int equipRefFromTrack, int trackRefFromTrack, String isSinglePlat, String platNumUp, String platNumDn, String name, boolean isSelected){
        int platformHeight = (int)((dnTrack - upTrack) * 0.6);

        int x1 = (int)worldToScreenConverter((platformMidPtUp + platformMidPtDn) / 2 - 90, offsetX, scaleX);
        int x2 = (int)worldToScreenConverter((platformMidPtUp + platformMidPtDn) / 2 + 90, offsetX, scaleX);
        int y = (int)worldToScreenConverter((float)(upTrack + dnTrack) / 2 - (float)platformHeight / 2, offsetY, scaleY);
        int up = (int)worldToScreenConverter(upTrack, offsetY, scaleY);
        int dn = (int)worldToScreenConverter(dnTrack, offsetY, scaleY);

        if (isSelected) {
            g.setColor(Color.RED);
        }

        if (isSinglePlat.equals("Y")) {
            g.drawRect(x1, y, x2 - x1, paddingToScale(platformHeight, 'Y'));

            Font originalFont = g.getFont();
            Font newFont = originalFont.deriveFont(originalFont.getSize() / 0.5F);
            g.setFont(newFont);

            int nameWidth = g.getFontMetrics().stringWidth(name);
            int nameHeight = g.getFontMetrics().getHeight();
            g.drawString(name, x1 + (x2 - x1 - nameWidth) / 2, y + nameHeight / 2 + paddingToScale((float)platformHeight / 2, 'Y'));

            int platNumUpWidth = g.getFontMetrics().stringWidth(platNumUp);
            int platNumDnWidth = g.getFontMetrics().stringWidth(platNumDn);
            g.drawString(platNumUp, x1 + (x2 - x1 - platNumUpWidth) / 2, y + paddingToScale(15, 'Y'));
            g.drawString(platNumDn, x1 + (x2 - x1 - platNumDnWidth) / 2, y + paddingToScale(platformHeight - 5, 'Y'));

            g.setFont(originalFont);
        }

        if (isSinglePlat.equals("N")) {
            g.drawRect(x1, y, x2 - x1, paddingToScale((int)((dnTrack - upTrack) * 0.25), 'Y'));
            g.drawRect(x1, y + paddingToScale((int)((dnTrack - upTrack) * 0.35), 'Y'), x2 - x1, paddingToScale((int)((dnTrack - upTrack) * 0.25), 'Y'));

            Font originalFont = g.getFont();
            Font newFont = originalFont.deriveFont(originalFont.getSize() / 0.5F);
            g.setFont(newFont);

            int nameWidth = g.getFontMetrics().stringWidth(name);
            int nameHeight = g.getFontMetrics().getHeight();
            g.drawString(name, x1 + (x2 - x1 - nameWidth) / 2, y + nameHeight / 2 + paddingToScale((int)((dnTrack - upTrack) * 0.125), 'Y'));
            g.drawString(name, x1 + (x2 - x1 - nameWidth) / 2, y + nameHeight / 2 + paddingToScale((int)((dnTrack - upTrack) * 0.475), 'Y'));

            int platNumUpWidth = g.getFontMetrics().stringWidth(platNumUp);
            int platNumDnWidth = g.getFontMetrics().stringWidth(platNumDn);
            g.drawString(platNumUp, x1 + (x2 - x1 - platNumUpWidth) / 2, y + paddingToScale(15, 'Y'));
            g.drawString(platNumDn, x1 + (x2 - x1 - platNumDnWidth) / 2, y + paddingToScale(platformHeight - 5, 'Y'));

            g.setFont(originalFont);
        }

        int midPtUpWidth = g.getFontMetrics().stringWidth(String.valueOf(platformMidPtUp));
        int midPtDnWidth = g.getFontMetrics().stringWidth(String.valueOf(platformMidPtDn));

        g.drawString(String.valueOf(platformMidPtUp), x1 + paddingToScale(90, 'X') - midPtUpWidth / 2, up - paddingToScale((float)(trackRefFromTrack + equipRefFromTrack)/2, 'Y'));
        g.drawString(String.valueOf(platformMidPtDn), x1 + paddingToScale(90, 'X') - midPtDnWidth / 2, dn + paddingToScale((float)(trackRefFromTrack + equipRefFromTrack)/2 + 5, 'Y'));

        Stroke defaultStroke = g.getStroke();
        float[] dash = { 2f, 0f, 2f };
        BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash, 2f);
        g.setStroke(bs1);
        g.drawLine(x1 + paddingToScale(90, 'X'), up, x1 + paddingToScale(90, 'X'), up - paddingToScale((float)(trackRefFromTrack + equipRefFromTrack)/2 - 5, 'Y'));
        g.drawLine(x1 + paddingToScale(90, 'X'), dn, x1 + paddingToScale(90, 'X'), dn + paddingToScale((float)(trackRefFromTrack + equipRefFromTrack)/2 - 5, 'Y'));

        g.drawLine(x1 + paddingToScale(90, 'X'), up - paddingToScale((float)(trackRefFromTrack + equipRefFromTrack)/2 + 10, 'Y'), x1 + paddingToScale(90, 'X'), up - paddingToScale(equipRefFromTrack * 2, 'Y'));
        g.drawLine(x1 + paddingToScale(90, 'X'), dn + paddingToScale((float)(trackRefFromTrack + equipRefFromTrack)/2 + 10, 'Y'), x1 + paddingToScale(90, 'X'), dn + paddingToScale(equipRefFromTrack * 2, 'Y'));
        g.setStroke(defaultStroke);

        if (isSelected) {
            g.setColor(Color.BLACK);
        }
    }


    // Draw crossing with boundaries locations
    public void drawCrossing(Graphics2D g, double[] switchCoordinates, double[][] boundaryCoordinates, double[] intercept, String[] switchName, int upTrack, int dnTrack, String name, boolean isCrossing, boolean isSwitch, int switchNum){
        boolean switchZeroIsNA = switchCoordinates[0] == -1.0;
        boolean switchOneIsNA = switchCoordinates[1] == -1.0;
        boolean switchTwoIsNA = switchCoordinates[2] == -1.0;
        boolean switchThreeIsNA = switchCoordinates[3] == -1.0;
        switchCoordinates[0] = (switchCoordinates[0] == -1.0? switchCoordinates[2]: switchCoordinates[0]);
        switchCoordinates[1] = (switchCoordinates[1] == -1.0? switchCoordinates[3]: switchCoordinates[1]);
        switchCoordinates[2] = (switchCoordinates[2] == -1.0? switchCoordinates[0]: switchCoordinates[2]);
        switchCoordinates[3] = (switchCoordinates[3] == -1.0? switchCoordinates[1]: switchCoordinates[3]);

        int x1 = (int)worldToScreenConverter(switchCoordinates[0], offsetX, scaleX);
        int x2 = (int)worldToScreenConverter(switchCoordinates[1], offsetX, scaleX);
        int x3 = (int)worldToScreenConverter(switchCoordinates[2], offsetX, scaleX);
        int x4 = (int)worldToScreenConverter(switchCoordinates[3], offsetX, scaleX);
        int y1 = (int)worldToScreenConverter(upTrack + 5, offsetY, scaleY);
        int y2 = (int)worldToScreenConverter(dnTrack - 5, offsetY, scaleY);

        if (isShowSwitchData) {
            int switchZeroLocationWidth = g.getFontMetrics().stringWidth(String.valueOf(switchCoordinates[0]));
            int switchOneLocationWidth = g.getFontMetrics().stringWidth(String.valueOf(switchCoordinates[1]));
            int switchTwoLocationWidth = g.getFontMetrics().stringWidth(String.valueOf(switchCoordinates[2]));
            int switchThreeLocationWidth = g.getFontMetrics().stringWidth(String.valueOf(switchCoordinates[3]));

            if (isSwitch && switchNum == 0) {
                g.setColor(Color.RED);
            }

            g.drawString(switchZeroIsNA? "": String.valueOf(switchCoordinates[0]), x1 - switchZeroLocationWidth/2, y1 + paddingToScale(20, 'Y'));

            if (isSwitch && switchNum == 0) {
                g.setColor(Color.BLACK);
            }

            if (isSwitch && switchNum == 1) {
                g.setColor(Color.RED);
            }

            g.drawString(switchOneIsNA? "": String.valueOf(switchCoordinates[1]), x2 - switchOneLocationWidth/2, y1 + paddingToScale(20, 'Y'));

            if (isSwitch && switchNum == 1) {
                g.setColor(Color.BLACK);
            }

            if (isSwitch && switchNum == 2) {
                g.setColor(Color.RED);
            }
            g.drawString(switchTwoIsNA? "": String.valueOf(switchCoordinates[2]), x3 - switchTwoLocationWidth/2, y2 - paddingToScale(15, 'Y'));
            if (isSwitch && switchNum == 2) {
                g.setColor(Color.BLACK);
            }

            if (isSwitch && switchNum == 3) {
                g.setColor(Color.RED);
            }
            g.drawString(switchThreeIsNA? "": String.valueOf(switchCoordinates[3]), x4 - switchThreeLocationWidth/2, y2 - paddingToScale(15, 'Y'));
            if (isSwitch && switchNum == 3) {
                g.setColor(Color.BLACK);
            }
        }

        double slope1 = (double) (upTrack - dnTrack + 10) / (switchCoordinates[1] - switchCoordinates[2] - 10);
        double yIntercept1 = dnTrack - 5 - (slope1 * (switchCoordinates[2] + 5));
        double slope2 = (double) (dnTrack - upTrack - 10) / (switchCoordinates[3] - switchCoordinates[0] - 10);
        double yIntercept2 = upTrack + 5 - (slope2 * (switchCoordinates[0] + 5));

        double xIntersection = (yIntercept2 - yIntercept1) / (slope1 - slope2);
        double yIntersection = slope1 * xIntersection + yIntercept1;

        int ix = (int)worldToScreenConverter(xIntersection, offsetX, scaleX);
        int iy = (int)worldToScreenConverter(yIntersection, offsetY, scaleY);

        double boundaryX1 = (switchCoordinates[0] + 5 + xIntersection)/ 2;
        double boundaryY1 = (upTrack + 5 + yIntersection)/ 2;
        double boundaryX2 = (switchCoordinates[1] - 5 + xIntersection)/ 2;
        double boundaryY2 = (upTrack + 5 + yIntersection)/ 2;
        double boundaryX3 = (switchCoordinates[2] + 5 + xIntersection)/ 2;
        double boundaryY3 = (dnTrack - 5 + yIntersection)/ 2;
        double boundaryX4 = (switchCoordinates[3] - 5 + xIntersection)/ 2;
        double boundaryY4 = (dnTrack - 5 + yIntersection)/ 2;

        int drawbx1 = (int)worldToScreenConverter(boundaryX1, offsetX, scaleX);
        int drawby1 = (int)worldToScreenConverter(boundaryY1, offsetY, scaleY);
        int drawbx4 = (int)worldToScreenConverter(boundaryX4, offsetX, scaleX);
        int drawby4 = (int)worldToScreenConverter(boundaryY4, offsetY, scaleY);

        double slopeOfBoundaryX1X4 = -((switchCoordinates[0] + 5) - (switchCoordinates[3] - 5)) / ((upTrack + 5) - (dnTrack - 5));

        double xDiffForX1X4 = Math.abs(Math.sqrt(25 / (1 + slopeOfBoundaryX1X4 * slopeOfBoundaryX1X4)));
        double yDiffForX1X4 = Math.abs(xDiffForX1X4 * slopeOfBoundaryX1X4);

        g.drawLine(drawbx1 + paddingToScale(xDiffForX1X4, 'X'), drawby1 - paddingToScale(yDiffForX1X4, 'Y'), drawbx1 - paddingToScale(xDiffForX1X4, 'X'), drawby1 + paddingToScale(yDiffForX1X4, 'Y'));
        int bx1NameWidthUP = g.getFontMetrics().stringWidth(boundaryCoordinates[0][0] == -1.0? "": String.valueOf(boundaryCoordinates[0][0]) + "(UP)");
        int bx1NameWidthDN = g.getFontMetrics().stringWidth(boundaryCoordinates[0][1] == -1.0? "": String.valueOf(boundaryCoordinates[0][1]) + "(DN)");

        g.drawLine(drawbx4 + paddingToScale(xDiffForX1X4, 'X'), drawby4 - paddingToScale(yDiffForX1X4, 'Y'), drawbx4 - paddingToScale(xDiffForX1X4, 'X'), drawby4 + paddingToScale(yDiffForX1X4, 'Y'));

        int drawbx2 = (int)worldToScreenConverter(boundaryX2, offsetX, scaleX);
        int drawby2 = (int)worldToScreenConverter(boundaryY2, offsetY, scaleY);
        int drawbx3 = (int)worldToScreenConverter(boundaryX3, offsetX, scaleX);
        int drawby3 = (int)worldToScreenConverter(boundaryY3, offsetY, scaleY);

        double slopeOfBoundaryX2X3 = -((switchCoordinates[1] - 5) - (switchCoordinates[2] + 5)) / ((upTrack + 5) - (dnTrack - 5));

        double xDiffForX2X3 = Math.abs(Math.sqrt(25 / (1 + slopeOfBoundaryX2X3 * slopeOfBoundaryX2X3)));
        double yDiffForX2X3 = Math.abs(xDiffForX2X3 * slopeOfBoundaryX2X3);

        g.drawLine(drawbx2 + paddingToScale(xDiffForX2X3, 'X'), drawby2 + paddingToScale(yDiffForX2X3, 'Y'), drawbx2 - paddingToScale(xDiffForX2X3, 'X'), drawby2 - paddingToScale(yDiffForX2X3, 'Y'));

        g.drawLine(drawbx3 + paddingToScale(xDiffForX2X3, 'X'), drawby3 + paddingToScale(yDiffForX2X3, 'Y'), drawbx3 - paddingToScale(xDiffForX2X3, 'X'), drawby3 - paddingToScale(yDiffForX2X3, 'Y'));
        int bx3NameWidthUP = g.getFontMetrics().stringWidth(boundaryCoordinates[2][0] == -1.0? "": String.valueOf(boundaryCoordinates[2][0]) + "(UP)");
        int bx3NameWidthDN = g.getFontMetrics().stringWidth(boundaryCoordinates[2][1] == -1.0? "": String.valueOf(boundaryCoordinates[2][1]) + "(DN)");

        if (isShowCrossingData) {
            g.drawString((boundaryCoordinates[0][0] == -1.0? "": String.valueOf(boundaryCoordinates[0][0]) + "(UP)"), drawbx1 - bx1NameWidthUP  , drawby1 - paddingToScale(2, 'Y'));
            g.drawString((boundaryCoordinates[0][1] == -1.0? "": String.valueOf(boundaryCoordinates[0][1]) + "(DN)"), drawbx1 - bx1NameWidthDN  , drawby1 + paddingToScale(7, 'Y'));

            g.drawString((boundaryCoordinates[3][0] == -1.0? "": String.valueOf(boundaryCoordinates[3][0]) + "(UP)"), drawbx4, drawby4 - paddingToScale(2, 'Y'));
            g.drawString((boundaryCoordinates[3][1] == -1.0? "": String.valueOf(boundaryCoordinates[3][1]) + "(DN)"), drawbx4, drawby4 + paddingToScale(7, 'Y'));

            g.drawString((boundaryCoordinates[1][0] == -1.0? "": String.valueOf(boundaryCoordinates[1][0]) + "(UP)"), drawbx2, drawby2 - paddingToScale(2, 'Y'));
            g.drawString((boundaryCoordinates[1][1] == -1.0? "": String.valueOf(boundaryCoordinates[1][1]) + "(DN)"), drawbx2, drawby2 + paddingToScale(7, 'Y'));

            g.drawString((boundaryCoordinates[2][0] == -1.0? "": String.valueOf(boundaryCoordinates[2][0]) + "(UP)"), drawbx3 - bx3NameWidthUP  , drawby3 - paddingToScale(2, 'Y'));
            g.drawString((boundaryCoordinates[2][1] == -1.0? "": String.valueOf(boundaryCoordinates[2][1]) + "(DN)"), drawbx3 - bx3NameWidthDN  , drawby3 + paddingToScale(7, 'Y'));

            int intersectWidthUp = g.getFontMetrics().stringWidth(intercept[0] == -1.0? "": String.valueOf(intercept[0]) + "(UP)");
            int intersectWidthDn = g.getFontMetrics().stringWidth(intercept[1] == -1.0? "": String.valueOf(intercept[1]) + "(DN)");

            if (isCrossing) {
                g.setColor(Color.RED);
            }
            g.drawString((intercept[0] == -1.0? "": String.valueOf(intercept[0]) + "(UP)"), ix - intersectWidthUp/2 , iy + paddingToScale(20, 'Y'));
            g.drawString((intercept[1] == -1.0? "": String.valueOf(intercept[1]) + "(DN)"), ix - intersectWidthDn/2 , iy + paddingToScale(30, 'Y'));
            if (isCrossing) {
                g.setColor(Color.BLACK);
            }
        }

        // draw crossing without boundaries

        if (isSwitch && switchNum == 0) {
            g.setColor(Color.RED);
        }

        g.drawLine(x1, y1, x1 + paddingToScale(5, 'X'), y1);
        int switchNameWidth1 = g.getFontMetrics().stringWidth(switchName[0]);
        g.drawString(switchName[0], (x1 + x1 + paddingToScale(5, 'X') - switchNameWidth1)/2, y1 + paddingToScale(10, 'Y'));

        g.drawLine(x1 + paddingToScale(5, 'X'), y1, drawbx1, drawby1);

        if (isSwitch && switchNum == 0) {
            g.setColor(Color.BLACK);
        }

        if (isSwitch && switchNum == 1) {
            g.setColor(Color.RED);
        }
        g.drawLine(drawbx4, drawby4, x4 - paddingToScale(5, 'X'), y2);

        g.drawLine(x4 - paddingToScale(5, 'X'), y2, x4, y2);
        int switchNameWidth4 = g.getFontMetrics().stringWidth(switchName[3]);
        g.drawString(switchName[3], (x4 - paddingToScale(5, 'X') + x4 - switchNameWidth4)/2, y2 - paddingToScale(5, 'Y'));

        if (isSwitch && switchNum == 1) {
            g.setColor(Color.BLACK);
        }

        if (isSwitch && switchNum == 2) {
            g.setColor(Color.RED);
        }
        g.drawLine(x3, y2, x3 + paddingToScale(5, 'X'), y2);
        int switchNameWidth3 = g.getFontMetrics().stringWidth(switchName[2]);
        g.drawString(switchName[2], (x3 + x3 + paddingToScale(5, 'X') - switchNameWidth3)/2, y2 - paddingToScale(5, 'Y'));

        g.drawLine(x3 + paddingToScale(5, 'X'), y2, drawbx3, drawby3);
        if (isSwitch && switchNum == 2) {
            g.setColor(Color.BLACK);
        }

        if (isSwitch && switchNum == 3) {
            g.setColor(Color.RED);
        }
        g.drawLine(drawbx2, drawby2, x2 - paddingToScale(5, 'X'), y1);

        g.drawLine(x2 - paddingToScale(5, 'X'), y1, x2, y1);
        int switchNameWidth2 = g.getFontMetrics().stringWidth(switchName[1]);
        g.drawString(switchName[1], (x2 - paddingToScale(5, 'X') + x2 - switchNameWidth2)/2, y1 + paddingToScale(10, 'Y'));

        if (isSwitch && switchNum == 3) {
            g.setColor(Color.BLACK);
        }

        if (isCrossing) {
            g.setColor(Color.RED);
        }

        g.drawLine(drawbx1, drawby1, drawbx4, drawby4);
        g.drawLine(drawbx3, drawby3, drawbx2, drawby2);

        int nameWidth = g.getFontMetrics().stringWidth(name);
        int nameHeight = g.getFontMetrics().getHeight();

        g.drawString(name, ix - nameWidth/2, iy + paddingToScale(10, 'Y'));

        if (isCrossing) {
            g.setColor(Color.BLACK);
        }
    }


    // Draw light bulbs part of signal
    public void drawSignalLightBulb(Graphics2D g, int x, int y, char colour){
        // x and y is the center of the circle
        g.drawOval(x - paddingToScale(8, 'X'), y - paddingToScale(8, 'Y'), paddingToScale(16, 'X'), paddingToScale(16, 'Y'));

        if (colour == 'R') { //for red light(2 vertical lines)
            g.drawLine(x - paddingToScale(2, 'X'), y - paddingToScale(Math.sqrt(60), 'Y'), x - paddingToScale(2, 'X'), y + paddingToScale(Math.sqrt(60), 'Y'));
            g.drawLine(x + paddingToScale(2, 'X'), y - paddingToScale(Math.sqrt(60), 'Y'), x + paddingToScale(2, 'X'), y + paddingToScale(Math.sqrt(60), 'Y'));
        }

        if (colour == 'B') { //for blue light (1 horizontal lines)
            g.drawLine(x - paddingToScale(8, 'X'), y, x + paddingToScale(8, 'X'), y);
        }

        if (colour == 'Y') { //for yellow light(2 incline lines)
            g.drawLine(x + paddingToScale(4.063, 'X'), y - paddingToScale(6.891, 'Y'), x - paddingToScale(6.891, 'X'), y + paddingToScale(4.063, 'Y'));
            g.drawLine(x + paddingToScale(6.891, 'X'), y - paddingToScale(4.063, 'Y'), x - paddingToScale(4.063, 'X'), y + paddingToScale(6.891, 'Y'));
        }
    }


    public void drawSignal(Graphics2D g, double xCoordinate, int yCoordinate, String type, String upOrDn, String side, int equipRefFromTrack, int trackRefFromTrack, String name, boolean isSelected){
        int x = (int)worldToScreenConverter(xCoordinate, offsetX, scaleX);
        int y = (int)worldToScreenConverter(yCoordinate, offsetY, scaleY);

        String tempName = name;
        if (tempName.equals("")) {
            tempName = "0";
        }
        if (tempName.equals(" ")) {
            tempName = "1";
        }

        if (tempName.endsWith("A")) {
            tempName = tempName.substring(0, tempName.length()-1);
        }

        if (isSelected) {
            g.setColor(Color.RED);
        }

        if ((upOrDn.equals("UP") && side.equals("L")) || (upOrDn.equals("DN") && side.equals("R"))) {

            g.fillPolygon(new int[] {x, x - paddingToScale(2, 'X'), x + paddingToScale(2, 'X')}, new int[] {y - paddingToScale(2, 'Y'), y - paddingToScale(10, 'Y'), y - paddingToScale(10, 'Y')}, 3);
            g.drawLine(x, y - paddingToScale(10, 'Y'), x, y - paddingToScale(30, 'Y'));

            int nameWidth = g.getFontMetrics().stringWidth(name);
            g.drawString(name, x - nameWidth/2  , y - paddingToScale(32, 'Y'));

            if (Integer.parseInt(tempName) % 2 == 0) {
                g.drawLine(x - paddingToScale(7, 'X'), y - paddingToScale(30, 'Y'), x, y - paddingToScale(30, 'Y'));
                for (int i = 0; i < type.length(); i++) {
                    drawSignalLightBulb(g, x - paddingToScale(15 + i*16, 'X'), y - paddingToScale(30, 'Y'), type.charAt(i));
                }
            }

            if (Integer.parseInt(tempName) % 2 == 1) {
                g.drawLine(x, y - paddingToScale(30, 'Y'), x + paddingToScale(7, 'X'), y - paddingToScale(30, 'Y'));
                for (int i = 0; i < type.length(); i++) {
                    drawSignalLightBulb(g, x + paddingToScale(15 + i*16, 'X'), y - paddingToScale(30, 'Y'), type.charAt(i));
                }
            }

            if (isShowSignalData) {
                int numberWidth = g.getFontMetrics().stringWidth(String.valueOf(xCoordinate));
                if (upOrDn.equals("UP")) {
                    g.drawLine(x, y - paddingToScale(equipRefFromTrack, 'Y'), x, y - paddingToScale(trackRefFromTrack, 'Y'));
                    g.drawString(String.valueOf(xCoordinate), x - numberWidth/2, y - paddingToScale(equipRefFromTrack + 5, 'Y'));
                }
                if (upOrDn.equals("DN")) {
                    g.drawLine(x, y + paddingToScale(equipRefFromTrack, 'Y'), x, y + paddingToScale(trackRefFromTrack, 'Y'));
                    g.drawString(String.valueOf(xCoordinate), x - numberWidth/2, y + paddingToScale(equipRefFromTrack + 10, 'Y'));
                }
            }
        }

        if ((upOrDn.equals("UP") && side.equals("R")) || (upOrDn.equals("DN") && side.equals("L"))) {

            g.fillPolygon(new int[] {x, x - paddingToScale(2, 'X'), x + paddingToScale(2, 'X')}, new int[] {y + paddingToScale(2, 'Y'), y + paddingToScale(10, 'Y'), y + paddingToScale(10, 'Y')}, 3);
            g.drawLine(x, y + paddingToScale(10, 'Y'), x, y + paddingToScale(30, 'Y'));
            int nameWidth = g.getFontMetrics().stringWidth(name);
            g.drawString(name, x - nameWidth/2  , y + paddingToScale(37, 'Y'));

            if (Integer.parseInt(tempName) % 2 == 0) {
                g.drawLine(x - paddingToScale(7, 'X'), y + paddingToScale(30, 'Y'), x, y + paddingToScale(30, 'Y'));
                for (int i = 0; i < type.length(); i++) {
                    drawSignalLightBulb(g, x - paddingToScale(15 + i*16, 'X'), y + paddingToScale(30, 'Y'), type.charAt(i));
                }
            }

            if (Integer.parseInt(tempName) % 2 == 1) {
                g.drawLine(x, y + paddingToScale(30, 'Y'), x + paddingToScale(7, 'X'), y + paddingToScale(30, 'Y'));
                for (int i = 0; i < type.length(); i++) {
                    drawSignalLightBulb(g, x + paddingToScale(15 + i*16, 'X'), y + paddingToScale(30, 'Y'), type.charAt(i));
                }
            }

            if (isShowSignalData) {
                int numberWidth = g.getFontMetrics().stringWidth(String.valueOf(xCoordinate));
                if (upOrDn.equals("UP")) {
                    g.drawLine(x, y - paddingToScale(equipRefFromTrack, 'Y'), x, y - paddingToScale(trackRefFromTrack, 'Y'));
                    g.drawString(String.valueOf(xCoordinate), x - numberWidth/2, y - paddingToScale(equipRefFromTrack + 5, 'Y'));
                }
                if (upOrDn.equals("DN")) {
                    g.drawLine(x, y + paddingToScale(equipRefFromTrack, 'Y'), x, y + paddingToScale(trackRefFromTrack, 'Y'));
                    g.drawString(String.valueOf(xCoordinate), x - numberWidth/2, y + paddingToScale(equipRefFromTrack + 10, 'Y'));
                }
            }
        }

        if (isSelected) {
            g.setColor(Color.BLACK);
        }
    }


    public void drawSwitchingTrack(Graphics2D g, double fromSwitchCoordinate, double toSwitchCoordinate, double boundaryUp, double boundaryDn, int upTrack, int dnTrack, String direction, String fromSwitchName, String toSwitchName, boolean isSwitch, String switchType){
        int x1 = (int)worldToScreenConverter(fromSwitchCoordinate, offsetX, scaleX);
        int x2 = (int)worldToScreenConverter(toSwitchCoordinate, offsetX, scaleX);

        if (direction.equals("upToDn")) {
            int y1 = (int)worldToScreenConverter(upTrack + 5, offsetY, scaleY);
            int y2 = (int)worldToScreenConverter(dnTrack - 5, offsetY, scaleY);

            double boundaryX = (fromSwitchCoordinate + toSwitchCoordinate) / 2;
            double boundaryY = (upTrack + dnTrack) / 2;

            int bx = (int)worldToScreenConverter(boundaryX, offsetX, scaleX);
            int by = (int)worldToScreenConverter(boundaryY, offsetY, scaleY);

            if (isSwitch && switchType.equals("From")) {
                g.setColor(Color.RED);
            }

            g.drawLine(x1, y1,x1 + paddingToScale(5, 'X'), y1);
            int nameWidthx1 = g.getFontMetrics().stringWidth(fromSwitchName);
            g.drawString(fromSwitchName, (x1 + x1 + paddingToScale(5, 'X') - nameWidthx1)/2, y1 + paddingToScale(10, 'Y'));

            g.drawLine(x1 + paddingToScale(5, 'X'), y1, bx, by);

            if (isSwitch && switchType.equals("From")) {
                g.setColor(Color.BLACK);
            }

            if (isSwitch && switchType.equals("To")) {
                g.setColor(Color.RED);
            }

            g.drawLine(bx, by,x2 - paddingToScale(5, 'X'), y2);

            g.drawLine(x2 - paddingToScale(5, 'X'), y2, x2, y2);
            int nameWidthx4 = g.getFontMetrics().stringWidth(toSwitchName);
            g.drawString(toSwitchName, (x2 - paddingToScale(5, 'X') + x2 - nameWidthx4)/2, y2 - paddingToScale(5, 'Y'));

            if (isSwitch && switchType.equals("To")) {
                g.setColor(Color.BLACK);
            }

            double slopeOfBoundary = -(float)((fromSwitchCoordinate + 10) - (toSwitchCoordinate - 10)) / (float)((upTrack + 5) - (dnTrack - 5));

            double xDiff = Math.abs(Math.sqrt(25 / (1 + slopeOfBoundary * slopeOfBoundary)));
            double yDiff = Math.abs(xDiff * slopeOfBoundary);

            g.drawLine(bx + paddingToScale(xDiff, 'X'), by - paddingToScale(yDiff, 'Y'), bx - paddingToScale(xDiff, 'X'), by + paddingToScale(yDiff, 'Y'));

            if (isShowCrossingData) {
                int bx1FontWidthUP = g.getFontMetrics().stringWidth(String.valueOf(boundaryUp) + "(UP)");
                int bx1FontWidthDN = g.getFontMetrics().stringWidth(String.valueOf(boundaryDn) + "(DN)");
                g.drawString(String.valueOf(boundaryUp) + "(UP)", bx - bx1FontWidthUP, by - paddingToScale(2, 'Y'));
                g.drawString(String.valueOf(boundaryDn) + "(DN)", bx - bx1FontWidthDN, by + paddingToScale(7, 'Y'));
            }

            if (isShowSwitchData) {
                int fromSwitchWidth = g.getFontMetrics().stringWidth(String.valueOf(fromSwitchCoordinate));
                int toSwitchWidth = g.getFontMetrics().stringWidth(String.valueOf(toSwitchCoordinate));

                if (isSwitch && switchType.equals("From")) {
                    g.setColor(Color.RED);
                }
                g.drawString(String.valueOf(fromSwitchCoordinate), x1 - fromSwitchWidth/2 + paddingToScale(30, 'X'), y1 + paddingToScale(20, 'Y'));
                if (isSwitch && switchType.equals("From")) {
                    g.setColor(Color.BLACK);
                }

                if (isSwitch && switchType.equals("To")) {
                    g.setColor(Color.RED);
                }
                g.drawString(String.valueOf(toSwitchCoordinate), x2 - toSwitchWidth/2 - paddingToScale(30, 'X'), y2 - paddingToScale(15, 'Y'));

                if (isSwitch && switchType.equals("To")) {
                    g.setColor(Color.BLACK);
                }
            }
        }

        if (direction.equals("dnToUp")) {
            int y1 = (int)worldToScreenConverter(dnTrack - 5, offsetY, scaleY);
            int y2 = (int)worldToScreenConverter(upTrack + 5, offsetY, scaleY);

            double boundaryX = (fromSwitchCoordinate + toSwitchCoordinate) / 2;
            double boundaryY = (upTrack + dnTrack) / 2;

            int bx = (int)worldToScreenConverter(boundaryX, offsetX, scaleX);
            int by = (int)worldToScreenConverter(boundaryY, offsetY, scaleY);

            if (isSwitch && switchType.equals("From")) {
                g.setColor(Color.RED);
            }

            g.drawLine(x1, y1,x1 + paddingToScale(5, 'X'), y1);
            int nameWidthx3 = g.getFontMetrics().stringWidth(fromSwitchName);
            g.drawString(fromSwitchName, (x1 + x1 + paddingToScale(5, 'X') - nameWidthx3)/2, y1 - paddingToScale(5, 'Y'));

            g.drawLine(x1 + paddingToScale(5, 'X'), y1,bx, by);

            if (isSwitch && switchType.equals("From")) {
                g.setColor(Color.BLACK);
            }

            if (isSwitch && switchType.equals("To")) {
                g.setColor(Color.RED);
            }

            g.drawLine(bx, by,x2 - paddingToScale(5, 'X'), y2);

            g.drawLine(x2 - paddingToScale(5, 'X'), y2, x2, y2);
            int fontWidthx2 = g.getFontMetrics().stringWidth(toSwitchName);
            g.drawString(toSwitchName, (x2 - paddingToScale(5, 'X') + x2 - fontWidthx2)/2, y2 + paddingToScale(10, 'Y'));

            if (isSwitch && switchType.equals("To")) {
                g.setColor(Color.BLACK);
            }

            double slopeOfBoundary = -(float)((toSwitchCoordinate - 10) - (fromSwitchCoordinate + 10)) / (float)((upTrack + 5) - (dnTrack - 5));

            double xDiff = Math.abs(Math.sqrt(25 / (1 + slopeOfBoundary*slopeOfBoundary)));
            double yDiff = Math.abs(xDiff * slopeOfBoundary);

            g.drawLine(bx + paddingToScale(xDiff, 'X'), by + paddingToScale(yDiff, 'Y'), bx - paddingToScale(xDiff, 'X'), by - paddingToScale(yDiff, 'Y'));

            if (isShowCrossingData) {
                g.drawString(String.valueOf(boundaryUp) + "(UP)", bx, by - paddingToScale(2, 'Y'));
                g.drawString(String.valueOf(boundaryDn) + "(DN)", bx, by + paddingToScale(7, 'Y'));
            }

            if (isShowSwitchData) {
                int fromSwitchWidth = g.getFontMetrics().stringWidth(String.valueOf(fromSwitchCoordinate));
                int toSwitchWidth = g.getFontMetrics().stringWidth(String.valueOf(toSwitchCoordinate));

                if (isSwitch && switchType.equals("From")) {
                    g.setColor(Color.RED);
                }
                g.drawString(String.valueOf(fromSwitchCoordinate), x1 - fromSwitchWidth/2 + paddingToScale(30, 'X'), y1 - paddingToScale(15, 'Y'));
                if (isSwitch && switchType.equals("From")) {
                    g.setColor(Color.BLACK);
                }

                if (isSwitch && switchType.equals("To")) {
                    g.setColor(Color.RED);
                }
                g.drawString(String.valueOf(toSwitchCoordinate), x2 - toSwitchWidth/2 - paddingToScale(30, 'X'), y2 + paddingToScale(20, 'Y'));
                if (isSwitch && switchType.equals("To")) {
                    g.setColor(Color.BLACK);
                }
            }
        }
    }


    public void drawFloodgate(Graphics2D g, double locationUp, double locationDn, int upTrack, int dnTrack, String name, String northOrSouth, int equipRefFromTrack, int trackRefFromTrack){
        int floodgateHeight = (int)((dnTrack - upTrack) * 0.4);
        int floodgateWidth = 40;

        int x = (int)worldToScreenConverter((locationUp + locationDn) / 2 - (float)floodgateWidth / 2, offsetX, scaleX);
        int y = (int)worldToScreenConverter((float)(upTrack + dnTrack) / 2  - (float)floodgateHeight / 2, offsetY, scaleY);

        g.drawRect(x, y, paddingToScale(floodgateWidth, 'X'), paddingToScale(floodgateHeight, 'Y'));
        int nameWidth = g.getFontMetrics().stringWidth(name);
        int nameHeight = g.getFontMetrics().getHeight();
        g.drawString(name, x + paddingToScale((float)floodgateWidth / 2, 'X') - nameWidth / 2, y + paddingToScale((float)floodgateHeight/2, 'Y') + nameHeight / 2);

        g.fillRect(x + paddingToScale((float)floodgateWidth * 3 / 8, 'X'), y - paddingToScale((float)((dnTrack - upTrack) * 0.225), 'Y'), paddingToScale((float)floodgateWidth / 4, 'X'), paddingToScale((float)((dnTrack - upTrack) * 0.15), 'Y'));
        g.fillRect(x + paddingToScale((float)floodgateWidth * 3 / 8, 'X'), y + paddingToScale((float)((dnTrack - upTrack) * 0.475), 'Y'), paddingToScale((float)floodgateWidth / 4, 'X'), paddingToScale((float)((dnTrack - upTrack) * 0.15), 'Y'));

        if (isShowFloodgateData) {
            int up = (int)worldToScreenConverter(upTrack, offsetY, scaleY);
            int dn = (int)worldToScreenConverter(dnTrack, offsetY, scaleY);

            int numberWidthUp = g.getFontMetrics().stringWidth(String.valueOf(locationUp));
            g.drawLine(x + paddingToScale((float)floodgateWidth / 2, 'X'), up - paddingToScale(equipRefFromTrack, 'Y'), x + paddingToScale((float)floodgateWidth / 2, 'X'), up - paddingToScale(trackRefFromTrack, 'Y'));
            g.drawString(String.valueOf(locationUp), x + paddingToScale((float)floodgateWidth / 2, 'X') - numberWidthUp/2, up - paddingToScale(equipRefFromTrack + 5, 'Y'));

            int numberWidthDn = g.getFontMetrics().stringWidth(String.valueOf(locationDn));
            g.drawLine(x + paddingToScale((float)floodgateWidth / 2, 'X'), dn + paddingToScale(equipRefFromTrack, 'Y'), x + paddingToScale((float)floodgateWidth / 2, 'X'), dn + paddingToScale(trackRefFromTrack, 'Y'));
            g.drawString(String.valueOf(locationDn), x + paddingToScale((float)floodgateWidth / 2, 'X') - numberWidthDn/2, dn + paddingToScale(equipRefFromTrack + 10, 'Y'));
        }
    }


    public void drawImpedanceBond(Graphics2D g, double boundary, int yCoordinate, String id, String upOrDn, int equipRefFromTrack, int trackRefFromTrack, int quantity, boolean isSelected){
        int x = (int)worldToScreenConverter(boundary, offsetX, scaleX);
        int y = (int)worldToScreenConverter(yCoordinate, offsetY, scaleY);

        if (isSelected) {
            g.setColor(Color.RED);
        }

        if (upOrDn.equals("UP")) {
            if (quantity == 1) {

                g.fillOval(x - paddingToScale(2, 'X'), y - paddingToScale(20 + 2 + 4, 'Y'), paddingToScale(4, 'X'), paddingToScale(4, 'Y'));
                g.drawLine(x, y - paddingToScale(20 + 2, 'Y'), x, y - paddingToScale(20, 'Y'));
                g.fillRect(x - paddingToScale(4, 'X'), y - paddingToScale(20, 'Y'), paddingToScale(8, 'X'), paddingToScale(5, 'Y'));
                g.drawLine(x, y - paddingToScale(15, 'Y'), x, y - paddingToScale(13, 'Y'));
                g.fillOval(x - paddingToScale(2, 'X'), y - paddingToScale(13, 'Y'), paddingToScale(4, 'X'), paddingToScale(4, 'Y'));

                if (isShowImpedanceBondData) {
                    int numberWidth = g.getFontMetrics().stringWidth(String.valueOf(boundary));
                    g.drawLine(x, y - paddingToScale(equipRefFromTrack, 'Y'), x, y - paddingToScale(trackRefFromTrack, 'Y'));
                    g.drawString(String.valueOf(boundary), x - numberWidth/2, y - paddingToScale(equipRefFromTrack + 5, 'Y'));
                }

            } else if (quantity == 2) {

                g.fillOval(x - paddingToScale(6 + 2, 'X'), y - paddingToScale(20 + 2 + 4, 'Y'), paddingToScale(4, 'X'), paddingToScale(4, 'Y'));
                g.drawLine(x - paddingToScale(6, 'X'), y - paddingToScale(20 + 2, 'Y'), x - paddingToScale(6, 'X'), y - paddingToScale(20, 'Y'));
                g.fillRect(x - paddingToScale(6 + 4, 'X'), y - paddingToScale(20, 'Y'), paddingToScale(8, 'X'), paddingToScale(5, 'Y'));
                g.drawLine(x - paddingToScale(6, 'X'), y - paddingToScale(15, 'Y'), x - paddingToScale(6, 'X'), y - paddingToScale(13, 'Y'));
                g.fillOval(x - paddingToScale(6 + 2, 'X'), y - paddingToScale(13, 'Y'), paddingToScale(4, 'X'), paddingToScale(4, 'Y'));

                g.drawLine(x - paddingToScale(2, 'X'), y - paddingToScale(17.5, 'Y'), x + paddingToScale(2, 'X'), y - paddingToScale(17.5, 'Y'));

                g.fillOval(x + paddingToScale(6 - 2, 'X'), y - paddingToScale(20 + 2 + 4, 'Y'), paddingToScale(4, 'X'), paddingToScale(4, 'Y'));
                g.drawLine(x + paddingToScale(6, 'X'), y - paddingToScale(20 + 2, 'Y'), x + paddingToScale(6, 'X'), y - paddingToScale(20, 'Y'));
                g.fillRect(x + paddingToScale(6 - 4, 'X'), y - paddingToScale(20, 'Y'), paddingToScale(8, 'X'), paddingToScale(5, 'Y'));
                g.drawLine(x + paddingToScale(6, 'X'), y - paddingToScale(15, 'Y'), x + paddingToScale(6, 'X'), y - paddingToScale(13, 'Y'));
                g.fillOval(x + paddingToScale(6 - 2, 'X'), y - paddingToScale(13, 'Y'), paddingToScale(4, 'X'), paddingToScale(4, 'Y'));

                if (isShowImpedanceBondData) {
                    int numberWidth = g.getFontMetrics().stringWidth(String.valueOf(boundary));
                    g.drawLine(x, y - paddingToScale(equipRefFromTrack, 'Y'), x, y - paddingToScale(trackRefFromTrack, 'Y'));
                    g.drawString(String.valueOf(boundary), x - numberWidth/2, y - paddingToScale(equipRefFromTrack + 5, 'Y'));
                }
            }

            int nameWidth = g.getFontMetrics().stringWidth(id);
            g.drawString(String.valueOf(id), x - nameWidth/2, y - paddingToScale(20 + 2 + 4 + 5, 'Y'));
        }

        if (upOrDn.equals("DN")) {
            if (quantity == 1) {

                g.fillOval(x - paddingToScale(2, 'X'), y + paddingToScale(15 - 2 - 4, 'Y'), paddingToScale(4, 'X'), paddingToScale(4, 'Y'));
                g.drawLine(x, y + paddingToScale(15 - 2, 'Y'), x, y + paddingToScale(15, 'Y'));
                g.fillRect(x - paddingToScale(4, 'X'), y + paddingToScale(15, 'Y'), paddingToScale(8, 'X'), paddingToScale(5, 'Y'));
                g.drawLine(x, y + paddingToScale(20, 'Y'), x, y + paddingToScale(20 + 2, 'Y'));
                g.fillOval(x - paddingToScale(2, 'X'), y + paddingToScale(20 + 2, 'Y'), paddingToScale(4, 'X'), paddingToScale(4, 'Y'));

                if (isShowImpedanceBondData) {
                    int numberWidth = g.getFontMetrics().stringWidth(String.valueOf(boundary));
                    g.drawLine(x, y + paddingToScale(equipRefFromTrack, 'Y'), x, y + paddingToScale(trackRefFromTrack, 'Y'));
                    g.drawString(String.valueOf(boundary), x-numberWidth/2, y + paddingToScale(equipRefFromTrack + 10, 'Y'));
                }

            } else if (quantity == 2) {

                g.fillOval(x - paddingToScale(6 + 2, 'X'), y + paddingToScale(15 - 2 - 4, 'Y'), paddingToScale(4, 'X'), paddingToScale(4, 'Y'));
                g.drawLine(x - paddingToScale(6, 'X'), y + paddingToScale(15 - 2, 'Y'), x - paddingToScale(6, 'X'), y + paddingToScale(15, 'Y'));
                g.fillRect(x - paddingToScale(6 + 4, 'X'), y + paddingToScale(15, 'Y'), paddingToScale(8, 'X'), paddingToScale(5, 'Y'));
                g.drawLine(x - paddingToScale(6, 'X'), y + paddingToScale(20, 'Y'), x - paddingToScale(6, 'X'), y + paddingToScale(20 + 2, 'Y'));
                g.fillOval(x - paddingToScale(6 + 2, 'X'), y + paddingToScale(20 + 2, 'Y'), paddingToScale(4, 'X'), paddingToScale(4, 'Y'));

                g.drawLine(x - paddingToScale(2, 'X'), y + paddingToScale(17.5, 'Y'), x + paddingToScale(2, 'X'), y + paddingToScale(17.5, 'Y'));

                g.fillOval(x + paddingToScale(6 - 2, 'X'), y + paddingToScale(15 - 2 - 4, 'Y'), paddingToScale(4, 'X'), paddingToScale(4, 'Y'));
                g.drawLine(x + paddingToScale(6, 'X'), y + paddingToScale(15 - 2, 'Y'), x + paddingToScale(6, 'X'), y + paddingToScale(15, 'Y'));
                g.fillRect(x + paddingToScale(6 - 4, 'X'), y + paddingToScale(15, 'Y'), paddingToScale(8, 'X'), paddingToScale(5, 'Y'));
                g.drawLine(x + paddingToScale(6, 'X'), y + paddingToScale(20, 'Y'), x + paddingToScale(6, 'X'), y + paddingToScale(20 + 2, 'Y'));
                g.fillOval(x + paddingToScale(6 - 2, 'X'), y + paddingToScale(20 + 2, 'Y'), paddingToScale(4, 'X'), paddingToScale(4, 'Y'));

                if (isShowImpedanceBondData) {
                    int numberWidth = g.getFontMetrics().stringWidth(String.valueOf(boundary));
                    g.drawLine(x, y + paddingToScale(equipRefFromTrack, 'Y'), x, y + paddingToScale(trackRefFromTrack, 'Y'));
                    g.drawString(String.valueOf(boundary), x-numberWidth/2, y + paddingToScale(equipRefFromTrack + 10, 'Y'));
                }
            }

            int nameWidth = g.getFontMetrics().stringWidth(id);
            int nameHeight = g.getFontMetrics().getHeight();
            g.drawString(String.valueOf(id), x - nameWidth/2, y + paddingToScale(20 + 2 + 4 + 5, 'Y') + (int)(nameHeight * 0.5));
        }

        if (isSelected) {
            g.setColor(Color.BLACK);
        }
    }

    public void drawBeacon(Graphics2D g, double xCoordinate, int yCoordinate, String upOrDn, String id, String type, String label, int equipRefFromTrack, int trackRefFromTrack, String reverse, boolean isSelected){
        int x = (int)worldToScreenConverter(xCoordinate, offsetX, scaleX);
        int y = (int)worldToScreenConverter(yCoordinate, offsetY, scaleY);

        if (isSelected) {
            g.setColor(Color.RED);
        }

        if (upOrDn.equals("UP")) {
            switch(type) {
                case "R1":

                    g.drawRect(x, y - paddingToScale(10 + 5, 'Y'), paddingToScale(2, 'X'), paddingToScale(5, 'Y'));
                    g.fillRect(x, y - paddingToScale(10 + 5, 'Y'), paddingToScale(2, 'X'), paddingToScale(5, 'Y'));
                    g.drawRect(x + paddingToScale(2, 'X'), y - paddingToScale(10 + 5, 'Y'), paddingToScale(8, 'X'), paddingToScale(5, 'Y'));

                    int idWidth = g.getFontMetrics().stringWidth(id.substring(0, id.length()-3));
                    int idHeight = g.getFontMetrics().getHeight();
                    g.drawString(id.substring(0, id.length()-3), x + paddingToScale(5, 'X') - idWidth / 2, y - paddingToScale(10 + 10 + 1, 'Y') + idHeight / 2);

                    if (isShowBeaconData) {
                        int numberWidth = g.getFontMetrics().stringWidth(String.valueOf(xCoordinate));
                        g.drawLine(x, y - paddingToScale(equipRefFromTrack, 'Y'), x, y - paddingToScale(trackRefFromTrack, 'Y'));
                        g.drawString(String.valueOf(xCoordinate), x-numberWidth/2, y - paddingToScale(equipRefFromTrack + 5, 'Y'));
                    }

                    break;
                case "R2":

                    g.drawRect(x - paddingToScale(2, 'X'), y - paddingToScale(10 + 5, 'Y'), paddingToScale(2, 'X'), paddingToScale(5, 'Y'));
                    g.fillRect(x - paddingToScale(2, 'X'), y - paddingToScale(10 + 5, 'Y'), paddingToScale(2, 'X'), paddingToScale(5, 'Y'));
                    g.drawRect(x - paddingToScale(2 + 8, 'X'), y - paddingToScale(10 + 5, 'Y'), paddingToScale(8, 'X'), paddingToScale(5, 'Y'));

                    idWidth = g.getFontMetrics().stringWidth(id.substring(0, id.length()-3));
                    idHeight = g.getFontMetrics().getHeight();
                    g.drawString(id.substring(0, id.length()-3), x - paddingToScale(5, 'X') - idWidth / 2, y - paddingToScale(10 + 10 + 1, 'Y') + idHeight / 2);

                    if (isShowBeaconData) {
                        int numberWidth = g.getFontMetrics().stringWidth(String.valueOf(xCoordinate));
                        g.drawLine(x, y - paddingToScale(equipRefFromTrack, 'Y'), x, y - paddingToScale(trackRefFromTrack, 'Y'));
                        g.drawString(String.valueOf(xCoordinate), x-numberWidth/2, y - paddingToScale(equipRefFromTrack + 5, 'Y'));
                    }

                    break;
                case "M":

                    if (reverse.equals("Y")) {
                        g.drawRect(x + paddingToScale(8, 'X'), y - paddingToScale(10 + 20, 'Y'), paddingToScale(1, 'X'), paddingToScale(5, 'Y'));
                        g.fillRect(x + paddingToScale(8, 'X'), y - paddingToScale(10 + 20, 'Y'), paddingToScale(1, 'X'), paddingToScale(5, 'Y'));
                        g.drawPolygon(new int[] {x + paddingToScale(8, 'X'), x + paddingToScale(3, 'X'), x + paddingToScale(1, 'X'), x + paddingToScale(3, 'X'), x + paddingToScale(8, 'X')}, new int[] {y - paddingToScale(10 + 20, 'Y'), y - paddingToScale(10 + 20, 'Y'), y - paddingToScale(10 + 17.5, 'Y'), y - paddingToScale(10 + 15, 'Y'), y - paddingToScale(10 + 15, 'Y')}, 5);

                        g.drawRect(x - paddingToScale(1, 'X'), y - paddingToScale(10 + 20, 'Y'), paddingToScale(1, 'X'), paddingToScale(5, 'Y'));
                        g.fillRect(x - paddingToScale(1, 'X'), y - paddingToScale(10 + 20, 'Y'), paddingToScale(1, 'X'), paddingToScale(5, 'Y'));
                        g.drawPolygon(new int[] {x - paddingToScale(1, 'X'), x - paddingToScale(6, 'X'), x - paddingToScale(8, 'X'), x - paddingToScale(6, 'X'), x - paddingToScale(1, 'X')}, new int[] {y - paddingToScale(10 + 20, 'Y'), y - paddingToScale(10 + 20, 'Y'), y - paddingToScale(10 + 17.5, 'Y'), y - paddingToScale(10 + 15, 'Y'), y - paddingToScale(10 + 15, 'Y')}, 5);

                        Font originalFont = g.getFont();
                        Font newFont = originalFont.deriveFont(originalFont.getSize() * 0.6F);
                        g.setFont(newFont);
                        int labelWidth = g.getFontMetrics().stringWidth(label);
                        int labelHeight = g.getFontMetrics().getHeight();
                        g.drawString(label, x + paddingToScale(5, 'X') - labelWidth / 2, y - paddingToScale(10 + 18, 'Y') + labelHeight / 2);
                        g.drawString(label, x - paddingToScale(4, 'X') - labelWidth / 2, y - paddingToScale(10 + 18, 'Y') + labelHeight / 2);
                        g.setFont(originalFont);

                        idWidth = g.getFontMetrics().stringWidth(id.substring(0, id.length()-3));
                        idHeight = g.getFontMetrics().getHeight();
                        g.drawString(id.substring(0, id.length()-3), x + paddingToScale(1, 'X') - idWidth / 2, y - paddingToScale(10 + 25 + 1, 'Y') + idHeight / 2);

                    } else if (reverse.equals("N")) {
                        g.drawRect(x, y - paddingToScale(10 + 20, 'Y'), paddingToScale(1, 'X'), paddingToScale(5, 'Y'));
                        g.fillRect(x, y - paddingToScale(10 + 20, 'Y'), paddingToScale(1, 'X'), paddingToScale(5, 'Y'));
                        g.drawPolygon(new int[] {x + paddingToScale(1, 'X'), x + paddingToScale(6, 'X'), x + paddingToScale(8, 'X'), x + paddingToScale(6, 'X'), x + paddingToScale(1, 'X')}, new int[] {y - paddingToScale(10 + 20, 'Y'), y - paddingToScale(10 + 20, 'Y'), y - paddingToScale(10 + 17.5, 'Y'), y - paddingToScale(10 + 15, 'Y'), y - paddingToScale(10 + 15, 'Y')}, 5);

                        g.drawRect(x - paddingToScale(9, 'X'), y - paddingToScale(10 + 20, 'Y'), paddingToScale(1, 'X'), paddingToScale(5, 'Y'));
                        g.fillRect(x - paddingToScale(9, 'X'), y - paddingToScale(10 + 20, 'Y'), paddingToScale(1, 'X'), paddingToScale(5, 'Y'));
                        g.drawPolygon(new int[] {x - paddingToScale(8, 'X'), x - paddingToScale(3, 'X'), x - paddingToScale(1, 'X'), x - paddingToScale(3, 'X'), x - paddingToScale(8, 'X')}, new int[] {y - paddingToScale(10 + 20, 'Y'), y - paddingToScale(10 + 20, 'Y'), y - paddingToScale(10 + 17.5, 'Y'), y - paddingToScale(10 + 15, 'Y'), y - paddingToScale(10 + 15, 'Y')}, 5);

                        Font originalFont = g.getFont();
                        Font newFont = originalFont.deriveFont(originalFont.getSize() * 0.6F);
                        g.setFont(newFont);
                        int labelWidth = g.getFontMetrics().stringWidth(label);
                        int labelHeight = g.getFontMetrics().getHeight();
                        g.drawString(label, x + paddingToScale(4, 'X') - labelWidth / 2, y - paddingToScale(10 + 18, 'Y') + labelHeight / 2);
                        g.drawString(label, x - paddingToScale(5, 'X') - labelWidth / 2, y - paddingToScale(10 + 18, 'Y') + labelHeight / 2);
                        g.setFont(originalFont);

                        idWidth = g.getFontMetrics().stringWidth(id.substring(0, id.length()-3));
                        idHeight = g.getFontMetrics().getHeight();
                        g.drawString(id.substring(0, id.length()-3), x - paddingToScale(1, 'X') - idWidth / 2, y - paddingToScale(10 + 25 + 1, 'Y') + idHeight / 2);
                    }

                    if (isShowBeaconData) {
                        int numberWidth = g.getFontMetrics().stringWidth(String.valueOf(xCoordinate));
                        g.drawLine(x, y - paddingToScale(equipRefFromTrack, 'Y'), x, y - paddingToScale(trackRefFromTrack, 'Y'));
                        g.drawString(String.valueOf(xCoordinate), x-numberWidth/2, y - paddingToScale(equipRefFromTrack + 15, 'Y'));
                    }

                    break;
                case "S":

                    if (reverse.equals("Y")) {
                        g.drawRect(x - paddingToScale(1, 'X'), y - paddingToScale(10 + 20, 'Y'), paddingToScale(1, 'X'), paddingToScale(5, 'Y'));
                        g.fillRect(x - paddingToScale(1, 'X'), y - paddingToScale(10 + 20, 'Y'), paddingToScale(1, 'X'), paddingToScale(5, 'Y'));
                        g.drawPolygon(new int[] {x - paddingToScale(1, 'X'), x - paddingToScale(10, 'X'), x - paddingToScale(12, 'X'), x - paddingToScale(10, 'X'), x - paddingToScale(1, 'X')}, new int[] {y - paddingToScale(10 + 20, 'Y'), y - paddingToScale(10 + 20, 'Y'), y - paddingToScale(10 + 17.5, 'Y'), y - paddingToScale(10 + 15, 'Y'), y - paddingToScale(10 + 15, 'Y')}, 5);
                        g.drawLine(x - paddingToScale(1, 'X'), y - paddingToScale(10 + 20, 'Y'), x - paddingToScale(10, 'X'), y - paddingToScale(10 + 15, 'Y'));
                        g.drawLine(x - paddingToScale(1, 'X'), y - paddingToScale(10 + 15, 'Y'), x - paddingToScale(10, 'X'), y - paddingToScale(10 + 20, 'Y'));

                        idWidth = g.getFontMetrics().stringWidth(id.substring(0, id.length()-3));
                        idHeight = g.getFontMetrics().getHeight();
                        g.drawString(id.substring(0, id.length()-3), x - paddingToScale(6, 'X') - idWidth / 2, y - paddingToScale(10 + 25 + 1, 'Y') + idHeight / 2);

                    } else if (reverse.equals("N")) {
                        g.drawRect(x, y - paddingToScale(10 + 20, 'Y'), paddingToScale(1, 'X'), paddingToScale(5, 'Y'));
                        g.fillRect(x, y - paddingToScale(10 + 20, 'Y'), paddingToScale(1, 'X'), paddingToScale(5, 'Y'));
                        g.drawPolygon(new int[] {x + paddingToScale(1, 'X'), x + paddingToScale(10, 'X'), x + paddingToScale(12, 'X'), x + paddingToScale(10, 'X'), x + paddingToScale(1, 'X')}, new int[] {y - paddingToScale(10 + 20, 'Y'), y - paddingToScale(10 + 20, 'Y'), y - paddingToScale(10 + 17.5, 'Y'), y - paddingToScale(10 + 15, 'Y'), y - paddingToScale(10 + 15, 'Y')}, 5);
                        g.drawLine(x + paddingToScale(1, 'X'), y - paddingToScale(10 + 20, 'Y'), x + paddingToScale(10, 'X'), y - paddingToScale(10 + 15, 'Y'));
                        g.drawLine(x + paddingToScale(1, 'X'), y - paddingToScale(10 + 15, 'Y'), x + paddingToScale(10, 'X'), y - paddingToScale(10 + 20, 'Y'));

                        idWidth = g.getFontMetrics().stringWidth(id.substring(0, id.length()-3));
                        idHeight = g.getFontMetrics().getHeight();
                        g.drawString(id.substring(0, id.length()-3), x + paddingToScale(6, 'X') - idWidth / 2, y - paddingToScale(10 + 25 + 1, 'Y') + idHeight / 2);

                    }

                    if (isShowBeaconData) {
                        int numberWidth = g.getFontMetrics().stringWidth(String.valueOf(xCoordinate));
                        g.drawLine(x, y - paddingToScale(equipRefFromTrack, 'Y'), x, y - paddingToScale(trackRefFromTrack, 'Y'));
                        g.drawString(String.valueOf(xCoordinate), x-numberWidth/2, y - paddingToScale(equipRefFromTrack + 15, 'Y'));
                    }

                    break;
                default:
                    break;
            }
        }
        if (upOrDn.equals("DN")) {
            switch(type) {
                case "R1":

                    g.drawRect(x - paddingToScale(2, 'X'), y + paddingToScale(10, 'Y'), paddingToScale(2, 'X'), paddingToScale(5, 'Y'));
                    g.fillRect(x - paddingToScale(2, 'X'), y + paddingToScale(10, 'Y'), paddingToScale(2, 'X'), paddingToScale(5, 'Y'));
                    g.drawRect(x - paddingToScale(2 + 8, 'X'), y + paddingToScale(10, 'Y'), paddingToScale(8, 'X'), paddingToScale(5, 'Y'));

                    int idWidth = g.getFontMetrics().stringWidth(id.substring(0, id.length()-3));
                    int idHeight = g.getFontMetrics().getHeight();
                    g.drawString(id.substring(0, id.length()-3), x - paddingToScale(5, 'X') - idWidth / 2, y + paddingToScale(10 + 5 + 2, 'Y') + idHeight / 2);

                    if (isShowBeaconData) {
                        int numberWidth = g.getFontMetrics().stringWidth(String.valueOf(xCoordinate));
                        g.drawLine(x, y + paddingToScale(equipRefFromTrack, 'Y'), x, y + paddingToScale(trackRefFromTrack, 'Y'));
                        g.drawString(String.valueOf(xCoordinate), x-numberWidth/2, y + paddingToScale(equipRefFromTrack + 10, 'Y'));
                    }

                    break;
                case "R2":

                    g.drawRect(x, y + paddingToScale(10, 'Y'), paddingToScale(2, 'X'), paddingToScale(5, 'Y'));
                    g.fillRect(x, y + paddingToScale(10, 'Y'), paddingToScale(2, 'X'), paddingToScale(5, 'Y'));
                    g.drawRect(x + paddingToScale(2, 'X'), y + paddingToScale(10, 'Y'), paddingToScale(8, 'X'), paddingToScale(5, 'Y'));


                    idWidth = g.getFontMetrics().stringWidth(id.substring(0, id.length()-3));
                    idHeight = g.getFontMetrics().getHeight();
                    g.drawString(id.substring(0, id.length()-3), x + paddingToScale(5, 'X') - idWidth / 2, y + paddingToScale(10 + 5 + 2, 'Y') + idHeight / 2);

                    if (isShowBeaconData) {
                        int numberWidth = g.getFontMetrics().stringWidth(String.valueOf(xCoordinate));
                        g.drawLine(x, y + paddingToScale(equipRefFromTrack, 'Y'), x, y + paddingToScale(trackRefFromTrack, 'Y'));
                        g.drawString(String.valueOf(xCoordinate), x-numberWidth/2, y + paddingToScale(equipRefFromTrack + 10, 'Y'));
                    }

                    break;
                case "M":

                    if (reverse.equals("Y")) {
                        g.drawRect(x, y + paddingToScale(10 + 15, 'Y'), paddingToScale(1, 'X'), paddingToScale(5, 'Y'));
                        g.fillRect(x, y + paddingToScale(10 + 15, 'Y'), paddingToScale(1, 'X'), paddingToScale(5, 'Y'));
                        g.drawPolygon(new int[] {x + paddingToScale(1, 'X'), x + paddingToScale(6, 'X'), x + paddingToScale(8, 'X'), x + paddingToScale(6, 'X'), x + paddingToScale(1, 'X')}, new int[] {y + paddingToScale(10 + 15, 'Y'), y + paddingToScale(10 + 15, 'Y'), y + paddingToScale(10 + 17.5, 'Y'), y + paddingToScale(10 + 20, 'Y'), y + paddingToScale(10 + 20, 'Y')}, 5);

                        g.drawRect(x - paddingToScale(9, 'X'), y + paddingToScale(10 + 15, 'Y'), paddingToScale(1, 'X'), paddingToScale(5, 'Y'));
                        g.fillRect(x - paddingToScale(9, 'X'), y + paddingToScale(10 + 15, 'Y'), paddingToScale(1, 'X'), paddingToScale(5, 'Y'));
                        g.drawPolygon(new int[] {x - paddingToScale(8, 'X'), x - paddingToScale(3, 'X'), x - paddingToScale(1, 'X'), x - paddingToScale(3, 'X'), x - paddingToScale(8, 'X')}, new int[] {y + paddingToScale(10 + 15, 'Y'), y + paddingToScale(10 + 15, 'Y'), y + paddingToScale(10 + 17.5, 'Y'), y + paddingToScale(10 + 20, 'Y'), y + paddingToScale(10 + 20, 'Y')}, 5);

                        Font originalFont = g.getFont();
                        Font newFont = originalFont.deriveFont(originalFont.getSize() * 0.6F);
                        g.setFont(newFont);
                        int labelWidth = g.getFontMetrics().stringWidth(label);
                        int labelHeight = g.getFontMetrics().getHeight();
                        g.drawString(label, x + paddingToScale(4, 'X') - labelWidth / 2, y + paddingToScale(10 + 17, 'Y') + labelHeight / 2);
                        g.drawString(label, x - paddingToScale(5, 'X') - labelWidth / 2, y + paddingToScale(10 + 17, 'Y') + labelHeight / 2);
                        g.setFont(originalFont);

                        idWidth = g.getFontMetrics().stringWidth(id.substring(0, id.length()-3));
                        idHeight = g.getFontMetrics().getHeight();
                        g.drawString(id.substring(0, id.length()-3), x - paddingToScale(1, 'X') - idWidth / 2, y + paddingToScale(10 + 20 + 2, 'Y') + idHeight / 2);

                    } else if (reverse.equals("N")) {
                        g.drawRect(x + paddingToScale(8, 'X'), y + paddingToScale(10 + 15, 'Y'), paddingToScale(1, 'X'), paddingToScale(5, 'Y'));
                        g.fillRect(x + paddingToScale(8, 'X'), y + paddingToScale(10 + 15, 'Y'), paddingToScale(1, 'X'), paddingToScale(5, 'Y'));
                        g.drawPolygon(new int[] {x + paddingToScale(8, 'X'), x + paddingToScale(3, 'X'), x + paddingToScale(1, 'X'), x + paddingToScale(3, 'X'), x + paddingToScale(8, 'X')}, new int[] {y + paddingToScale(10 + 15, 'Y'), y + paddingToScale(10 + 15, 'Y'), y + paddingToScale(10 + 17.5, 'Y'), y + paddingToScale(10 + 20, 'Y'), y + paddingToScale(10 + 20, 'Y')}, 5);

                        g.drawRect(x - paddingToScale(1, 'X'), y + paddingToScale(10 + 15, 'Y'), paddingToScale(1, 'X'), paddingToScale(5, 'Y'));
                        g.fillRect(x - paddingToScale(1, 'X'), y + paddingToScale(10 + 15, 'Y'), paddingToScale(1, 'X'), paddingToScale(5, 'Y'));
                        g.drawPolygon(new int[] {x - paddingToScale(1, 'X'), x - paddingToScale(6, 'X'), x - paddingToScale(8, 'X'), x - paddingToScale(6, 'X'), x - paddingToScale(1, 'X')}, new int[] {y + paddingToScale(10 + 15, 'Y'), y + paddingToScale(10 + 15, 'Y'), y + paddingToScale(10 + 17.5, 'Y'), y + paddingToScale(10 + 20, 'Y'), y + paddingToScale(10 + 20, 'Y')}, 5);

                        Font originalFont = g.getFont();
                        Font newFont = originalFont.deriveFont(originalFont.getSize() * 0.6F);
                        g.setFont(newFont);
                        int labelWidth = g.getFontMetrics().stringWidth(label);
                        int labelHeight = g.getFontMetrics().getHeight();
                        g.drawString(label, x + paddingToScale(5, 'X') - labelWidth / 2, y + paddingToScale(10 + 17, 'Y') + labelHeight / 2);
                        g.drawString(label, x - paddingToScale(4, 'X') - labelWidth / 2, y + paddingToScale(10 + 17, 'Y') + labelHeight / 2);
                        g.setFont(originalFont);

                        idWidth = g.getFontMetrics().stringWidth(id.substring(0, id.length()-3));
                        idHeight = g.getFontMetrics().getHeight();
                        g.drawString(id.substring(0, id.length()-3), x + paddingToScale(1, 'X') - idWidth / 2, y + paddingToScale(10 + 20 + 2, 'Y') + idHeight / 2);
                    }

                    if (isShowBeaconData) {
                        int numberWidth = g.getFontMetrics().stringWidth(String.valueOf(xCoordinate));
                        g.drawLine(x, y + paddingToScale(equipRefFromTrack, 'Y'), x, y + paddingToScale(trackRefFromTrack, 'Y'));
                        g.drawString(String.valueOf(xCoordinate), x-numberWidth/2, y + paddingToScale(equipRefFromTrack + 20, 'Y'));
                    }

                    break;
                case "S":

                    if (reverse.equals("Y")) {
                        g.drawRect(x, y + paddingToScale(10 + 15, 'Y'), paddingToScale(1, 'X'), paddingToScale(5, 'Y'));
                        g.fillRect(x, y + paddingToScale(10 + 15, 'Y'), paddingToScale(1, 'X'), paddingToScale(5, 'Y'));
                        g.drawPolygon(new int[] {x + paddingToScale(1, 'X'), x + paddingToScale(10, 'X'), x + paddingToScale(12, 'X'), x + paddingToScale(10, 'X'), x + paddingToScale(1, 'X')}, new int[] {y + paddingToScale(10 + 15, 'Y'), y + paddingToScale(10 + 15, 'Y'), y + paddingToScale(10 + 17.5, 'Y'), y + paddingToScale(10 + 20, 'Y'), y + paddingToScale(10 + 20, 'Y')}, 5);
                        g.drawLine(x + paddingToScale(1, 'X'), y + paddingToScale(10 + 20, 'Y'), x + paddingToScale(10, 'X'), y + paddingToScale(10 + 15, 'Y'));
                        g.drawLine(x + paddingToScale(1, 'X'), y + paddingToScale(10 + 15, 'Y'), x + paddingToScale(10, 'X'), y + paddingToScale(10 + 20, 'Y'));

                        idWidth = g.getFontMetrics().stringWidth(id.substring(0, id.length()-3));
                        idHeight = g.getFontMetrics().getHeight();
                        g.drawString(id.substring(0, id.length()-3), x + paddingToScale(6, 'X') - idWidth / 2, y + paddingToScale(10 + 20 + 2, 'Y') + idHeight / 2);

                    } else if (reverse.equals("N")) {
                        g.drawRect(x - paddingToScale(1, 'X'), y + paddingToScale(10 + 15, 'Y'), paddingToScale(1, 'X'), paddingToScale(5, 'Y'));
                        g.fillRect(x - paddingToScale(1, 'X'), y + paddingToScale(10 + 15, 'Y'), paddingToScale(1, 'X'), paddingToScale(5, 'Y'));
                        g.drawPolygon(new int[] {x - paddingToScale(1, 'X'), x - paddingToScale(10, 'X'), x - paddingToScale(12, 'X'), x - paddingToScale(10, 'X'), x - paddingToScale(1, 'X')}, new int[] {y + paddingToScale(10 + 15, 'Y'), y + paddingToScale(10 + 15, 'Y'), y + paddingToScale(10 + 17.5, 'Y'), y + paddingToScale(10 + 20, 'Y'), y + paddingToScale(10 + 20, 'Y')}, 5);
                        g.drawLine(x - paddingToScale(1, 'X'), y + paddingToScale(10 + 20, 'Y'), x - paddingToScale(10, 'X'), y + paddingToScale(10 + 15, 'Y'));
                        g.drawLine(x - paddingToScale(1, 'X'), y + paddingToScale(10 + 15, 'Y'), x - paddingToScale(10, 'X'), y + paddingToScale(10 + 20, 'Y'));

                        idWidth = g.getFontMetrics().stringWidth(id.substring(0, id.length()-3));
                        idHeight = g.getFontMetrics().getHeight();
                        g.drawString(id.substring(0, id.length()-3), x - paddingToScale(6, 'X') - idWidth / 2, y + paddingToScale(10 + 20 + 2, 'Y') + idHeight / 2);
                    }

                    if (isShowBeaconData) {
                        int numberWidth = g.getFontMetrics().stringWidth(String.valueOf(xCoordinate));
                        g.drawLine(x, y + paddingToScale(equipRefFromTrack, 'Y'), x, y + paddingToScale(trackRefFromTrack, 'Y'));
                        g.drawString(String.valueOf(xCoordinate), x-numberWidth/2, y + paddingToScale(equipRefFromTrack + 20, 'Y'));
                    }

                    break;
                default:
                    break;
            }
        }

        if (isSelected) {
            g.setColor(Color.BLACK);
        }
    }






    public void drawAdditionalEquipment(Graphics2D g, double trackX, int trackY, String upOrDn, int equipRefFromTrack, int trackRefFromTrack, String name, boolean isSelected){
        int x = (int)worldToScreenConverter(trackX, offsetX, scaleX);
        int y = (int)worldToScreenConverter(trackY, offsetY, scaleY);

        int nameWidth = g.getFontMetrics().stringWidth(name);
        int nameHeight = g.getFontMetrics().getHeight();
        int dataWidth = g.getFontMetrics().stringWidth(String.valueOf(trackX));

        if (isSelected) {
            g.setColor(Color.RED);
        }

        if (upOrDn.equals("UP")) {

            g.fillPolygon(new int[] {x, x - (int)(2 * scaleX), x + (int)(2 * scaleX)}, new int[] {y - (int)(2 * scaleY), y - (int)(10 * scaleY), y - (int)(10 * scaleY)}, 3);
            g.drawLine(x, y - (int)(10 * scaleY), x, y - (int)(30 * scaleY));
            g.drawRect(x - nameWidth / 2 - (int)(2 * scaleX), y - nameHeight - (int)((30 + 4) * scaleY), nameWidth + (int)(4 * scaleX), nameHeight + (int)(4 * scaleY));

            g.drawString(name, x - nameWidth / 2, y - nameHeight / 2 - (int)(30 * scaleY));

            if (isShowOtherData) {

                g.drawLine(x, y - paddingToScale(equipRefFromTrack, 'Y'), x, y - paddingToScale(trackRefFromTrack, 'Y'));
                g.drawString(String.valueOf(trackX), x - dataWidth / 2, y - paddingToScale(equipRefFromTrack + 5, 'Y'));
            }
        }

        if (upOrDn.equals("DN")) {

            g.fillPolygon(new int[] {x, x - (int)(2 * scaleX), x + (int)(2 * scaleX)}, new int[] {y + (int)(2 * scaleY), y + (int)(10 * scaleY), y + (int)(10 * scaleY)}, 3);
            g.drawLine(x, y + (int)(10 * scaleY), x, y + (int)(30 * scaleY));
            g.drawRect(x - nameWidth / 2 - (int)(2 * scaleX), y + (int)(30 * scaleY), nameWidth + (int)(4 * scaleX), nameHeight + (int)(4 * scaleY));

            g.drawString(name, x - nameWidth / 2, y + nameHeight + (int)(30 * scaleY));

            if (isShowOtherData) {
                g.drawLine(x, y + paddingToScale(equipRefFromTrack, 'Y'), x, y + paddingToScale(trackRefFromTrack, 'Y'));
                g.drawString(String.valueOf(trackX), x - dataWidth / 2, y + paddingToScale(equipRefFromTrack + 10, 'Y'));
            }
        }

        if (isSelected) {
            g.setColor(Color.BLACK);
        }
    }


    public void drawSAB(Graphics2D g, double trackX, int trackY, String upOrDn, int equipRefFromTrack, int trackRefFromTrack, String id, boolean isSelected){
        int x = (int)worldToScreenConverter(trackX, offsetX, scaleX);
        int y = (int)worldToScreenConverter(trackY, offsetY, scaleY);

        String[] idList={}; int nameWidth;
        if (id.contains(",")) {
            idList = id.split(",");
            nameWidth = g.getFontMetrics().stringWidth(idList[0]);
        } else {
            nameWidth = g.getFontMetrics().stringWidth(id);
        }

        int nameHeight = g.getFontMetrics().getHeight();
        int dataWidth = g.getFontMetrics().stringWidth(String.valueOf(trackX));

        if (isSelected) {
            g.setColor(Color.RED);
        }

        if (upOrDn.equals("UP")) {

            Stroke defaultStroke = g.getStroke();
            float[] dash = { 2f, 0f, 2f };
            BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash, 2f);
            g.setStroke(bs1);

            g.drawLine(x, y - (int)(5 * scaleY), x, y - paddingToScale(equipRefFromTrack * 1.25, 'Y'));

            g.setStroke(defaultStroke);

            if (idList.length > 0) {
                if (idList.length == 2) {
                    g.drawRect(x - (int)(35 * scaleX), y - paddingToScale(equipRefFromTrack * 1.25, 'Y') - (int)(30 * scaleY), paddingToScale(30, 'X'), paddingToScale(30, 'Y'));
                    g.drawString(idList[0], x - nameWidth / 2 - (int)(20 * scaleX), y + nameHeight / 2 - paddingToScale(equipRefFromTrack * 1.25, 'Y') - (int)(15 * scaleY));

                    g.drawRect(x + (int)(5 * scaleX), y - paddingToScale(equipRefFromTrack * 1.25, 'Y') - (int)(30 * scaleY), paddingToScale(30, 'X'), paddingToScale(30, 'Y'));
                    g.drawString(idList[1], x - nameWidth / 2 + (int)(20 * scaleX), y + nameHeight / 2 - paddingToScale(equipRefFromTrack * 1.25, 'Y') - (int)(15 * scaleY));
                }

                if (idList.length == 3) {
                    g.drawRect(x - (int)(55 * scaleX), y - paddingToScale(equipRefFromTrack * 1.25, 'Y') - (int)(30 * scaleY), paddingToScale(30, 'X'), paddingToScale(30, 'Y'));
                    g.drawString(idList[0], x - nameWidth / 2 - (int)(40 * scaleX), y + nameHeight / 2 - paddingToScale(equipRefFromTrack * 1.25, 'Y') - (int)(15 * scaleY));

                    g.drawRect(x - (int)(15 * scaleX), y - paddingToScale(equipRefFromTrack * 1.25, 'Y') - (int)(30 * scaleY), paddingToScale(30, 'X'), paddingToScale(30, 'Y'));
                    g.drawString(idList[1], x - nameWidth / 2, y + nameHeight / 2 - paddingToScale(equipRefFromTrack * 1.25, 'Y') - (int)(15 * scaleY));

                    g.drawRect(x + (int)(25 * scaleX), y - paddingToScale(equipRefFromTrack * 1.25, 'Y') - (int)(30 * scaleY), paddingToScale(30, 'X'), paddingToScale(30, 'Y'));
                    g.drawString(idList[2], x - nameWidth / 2 + (int)(40 * scaleX), y + nameHeight / 2 - paddingToScale(equipRefFromTrack * 1.25, 'Y') - (int)(15 * scaleY));
                }
            } else {
                g.drawRect(x - (int)(15 * scaleX), y - paddingToScale(equipRefFromTrack * 1.25, 'Y') - (int)(30 * scaleY), paddingToScale(30, 'X'), paddingToScale(30, 'Y'));
                g.drawString(id, x - nameWidth / 2, y + nameHeight / 2 - paddingToScale(equipRefFromTrack * 1.25, 'Y') - (int)(15 * scaleY));
            }

            if (isShowSabData) {
                g.drawString(String.valueOf(trackX), x - dataWidth / 2, y - paddingToScale(equipRefFromTrack * 1.25, 'Y') - (int)(35 * scaleY));
            }
        }

        if (upOrDn.equals("DN")) {

            Stroke defaultStroke = g.getStroke();
            float[] dash = { 2f, 0f, 2f };
            BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash, 2f);
            g.setStroke(bs1);

            g.drawLine(x, y + (int)(5 * scaleY), x, y + paddingToScale(equipRefFromTrack * 1.25, 'Y'));

            g.setStroke(defaultStroke);

            if (idList.length > 0) {
                if (idList.length == 2) {
                    g.drawRect(x - (int)(35 * scaleX), y + paddingToScale(equipRefFromTrack * 1.25, 'Y'), paddingToScale(30, 'X'), paddingToScale(30, 'Y'));
                    g.drawString(idList[0], x - nameWidth / 2 - (int)(20 * scaleX), y + nameHeight / 2 + paddingToScale(equipRefFromTrack * 1.25, 'Y') + (int)(15 * scaleY));

                    g.drawRect(x + (int)(5 * scaleX), y + paddingToScale(equipRefFromTrack * 1.25, 'Y'), paddingToScale(30, 'X'), paddingToScale(30, 'Y'));
                    g.drawString(idList[1], x - nameWidth / 2 + (int)(20 * scaleX), y + nameHeight / 2 + paddingToScale(equipRefFromTrack * 1.25, 'Y') + (int)(15 * scaleY));
                }

                if (idList.length == 3) {
                    g.drawRect(x - (int)(55 * scaleX), y + paddingToScale(equipRefFromTrack * 1.25, 'Y'), paddingToScale(30, 'X'), paddingToScale(30, 'Y'));
                    g.drawString(idList[0], x - nameWidth / 2 - (int)(40 * scaleX), y + nameHeight / 2 + paddingToScale(equipRefFromTrack * 1.25, 'Y') + (int)(15 * scaleY));

                    g.drawRect(x - (int)(15 * scaleX), y + paddingToScale(equipRefFromTrack * 1.25, 'Y'), paddingToScale(30, 'X'), paddingToScale(30, 'Y'));
                    g.drawString(idList[1], x - nameWidth / 2, y + nameHeight / 2 + paddingToScale(equipRefFromTrack * 1.25, 'Y') + (int)(15 * scaleY));

                    g.drawRect(x + (int)(25 * scaleX), y + paddingToScale(equipRefFromTrack * 1.25, 'Y'), paddingToScale(30, 'X'), paddingToScale(30, 'Y'));
                    g.drawString(idList[2], x - nameWidth / 2 + (int)(40 * scaleX), y + nameHeight / 2 + paddingToScale(equipRefFromTrack * 1.25, 'Y') + (int)(15 * scaleY));
                }
            } else {
                g.drawRect(x - (int)(15 * scaleX), y + paddingToScale(equipRefFromTrack * 1.25, 'Y'), paddingToScale(30, 'X'), paddingToScale(30, 'Y'));
                g.drawString(id, x - nameWidth / 2, y + nameHeight / 2 + paddingToScale(equipRefFromTrack * 1.25, 'Y') + (int)(15 * scaleY));
            }

            if (isShowSabData) {
                g.drawString(String.valueOf(trackX), x - dataWidth / 2, y + paddingToScale(equipRefFromTrack * 1.25, 'Y') + (int)(40 * scaleY));
            }
        }

        if (isSelected) {
            g.setColor(Color.BLACK);
        }
    }


    public void drawBox(Graphics2D g, int x, int y, String type){
        g.drawRect(x, y, paddingToScale(5, 'X'), paddingToScale(5, 'Y'));
        switch(type) {
            case "TT":
                break;
            case "RA":
                g.fillRect(x, y, paddingToScale(5, 'X'), paddingToScale(5, 'Y'));
                break;
            case "CTI":
                g.drawLine(x, y, x + paddingToScale(5, 'X'), y + paddingToScale(5, 'Y'));
                g.drawLine(x, y + paddingToScale(5, 'Y'), x + paddingToScale(5, 'X'), y);
                break;
            case "CTA":
                g.drawLine(x, y + paddingToScale(5, 'Y'), x + paddingToScale(5, 'X'), y);
                break;
            case "RC":
                //g.drawLine(x, y + paddingToScale(5, 'Y'), x + paddingToScale(5, 'X'), y);
                g.fillPolygon(new int[] {x, x, x + paddingToScale(5, 'X')}, new int[] {y, y + paddingToScale(5, 'Y'), y}, 3);
                break;
            case "STIB":
                g.fillPolygon(new int[] {x, x + paddingToScale(2.5, 'X'), x + paddingToScale(5, 'X'), x + paddingToScale(2.5, 'X')}, new int[] {y + paddingToScale(2.5, 'Y'), y, y + paddingToScale(2.5, 'Y'), y + paddingToScale(5, 'Y')}, 4);
                break;
            default:
                break;
        }
    }


    public void drawBoxes(Graphics2D g, double trackX, int trackY, String upOrDn, int equipRefFromTrack, int trackRefFromTrack, String id, String types, boolean isSelected){
        int x = (int)worldToScreenConverter(trackX, offsetX, scaleX);
        int y = (int)worldToScreenConverter(trackY, offsetY, scaleY);

        String[] typeList={};
        if (types.contains(",")) {
            typeList = types.split(",");
        }
        int idWidth = g.getFontMetrics().stringWidth(id);
        int idHeight = g.getFontMetrics().getHeight();
        int dataWidth = g.getFontMetrics().stringWidth(String.valueOf(trackX));

        if (isSelected) {
            g.setColor(Color.RED);
        }

        if (upOrDn.equals("UP")) {

            if (typeList.length > 0) {
                if (typeList.length % 2 == 0) {
                    for (int i = 0; i < typeList.length; i++) {
                        //System.out.println(idList[i]);
                        if (i == 0) {
                            g.drawLine(x - paddingToScale(6 + 7 * ((int)(typeList.length/2) - 1 - i) + 2, 'X'),y - paddingToScale(10 + 2.5, 'Y'), x - paddingToScale(6 + 7 * ((int)(typeList.length/2) - 1 - i) + 2, 'X'), y - paddingToScale(10 - 2, 'Y'));
                            g.drawLine(x - paddingToScale(6 + 7 * ((int)(typeList.length/2) - 1 - i) + 2, 'X'),y - paddingToScale(10 - 2, 'Y'), x, y - paddingToScale(10 - 2, 'Y'));
                        }

                        if (i < typeList.length/2) {
                            drawBox(g, x - paddingToScale(6 + 7 * ((int)(typeList.length/2) - 1 - i), 'X'), y - paddingToScale(10 + 5, 'Y'), typeList[i]);
                        } else {
                            drawBox(g, x + paddingToScale(1 + 7 * (i - (int)(typeList.length/2)), 'X'), y - paddingToScale(10 + 5, 'Y'), typeList[i]);
                        }

                        if (i == typeList.length - 1) {
                            g.drawLine(x,y - paddingToScale(10 - 2, 'Y'), x + paddingToScale(1 + 7 * (i - (int)(typeList.length/2)) + 7, 'X'), y - paddingToScale(10 - 2, 'Y'));
                            g.drawLine(x + paddingToScale(1 + 7 * (i - (int)(typeList.length/2)) + 7, 'X'),y - paddingToScale(10 + 2.5, 'Y'), x + paddingToScale(1 + 7 * (i - (int)(typeList.length/2)) + 7, 'X'), y - paddingToScale(10 - 2, 'Y'));
                        }
                    }
                }

                if (typeList.length % 2 == 1) {
                    for (int i = 0; i < typeList.length; i++) {
                        if (i == 0) {
                            g.drawLine(x - paddingToScale(2.5 + 7 * ((int)(typeList.length/2) - i) + 2, 'X'),y - paddingToScale(10 + 2.5, 'Y'), x - paddingToScale(2.5 + 7 * ((int)(typeList.length/2) - i) + 2, 'X'), y - paddingToScale(10 - 2, 'Y'));
                            g.drawLine(x - paddingToScale(2.5 + 7 * ((int)(typeList.length/2) - i) + 2, 'X'),y - paddingToScale(10 - 2, 'Y'), x, y - paddingToScale(10 - 2, 'Y'));
                        }

                        if (i < (typeList.length + 1) / 2) {
                            drawBox(g, x - paddingToScale(2.5 + 7 * ((int)(typeList.length/2) - i), 'X'), y - paddingToScale(10 + 5, 'Y'), typeList[i]);
                        } else {
                            drawBox(g, x + paddingToScale(4.5 + 7 * (i - (int)(typeList.length/2) - 1), 'X'), y - paddingToScale(10 + 5, 'Y'), typeList[i]);
                        }

                        if (i == typeList.length - 1) {
                            g.drawLine(x,y - paddingToScale(10 - 2, 'Y'), x + paddingToScale(4.5 + 7 * (i - (int)(typeList.length/2) - 1) + 7, 'X'), y - paddingToScale(10 - 2, 'Y'));
                            g.drawLine(x + paddingToScale(4.5 + 7 * (i - (int)(typeList.length/2) - 1) + 7, 'X'),y - paddingToScale(10 + 2.5, 'Y'), x + paddingToScale(4.5 + 7 * (i - (int)(typeList.length/2) - 1) + 7, 'X'), y - paddingToScale(10 - 2, 'Y'));
                        }
                    }
                }
            } else {
                drawBox(g, x - paddingToScale(2.5, 'X'), y - paddingToScale(10 + 5, 'Y'), types);

            }

            g.drawString(id, x - idWidth / 2, y - paddingToScale(10 + 10 + 2, 'Y') + idHeight / 2);

            if (isShowBoxData) {

                Stroke defaultStroke = g.getStroke();
                float[] dash = { 2f, 0f, 2f };
                BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash, 2f);
                g.setStroke(bs1);

                g.drawLine(x, y - (int)(25 * scaleY), x, y - paddingToScale(equipRefFromTrack, 'Y'));

                g.setStroke(defaultStroke);

                //g.drawLine(x, y - paddingToScale(equipRefFromTrack, 'Y'), x, y - paddingToScale(trackRefFromTrack, 'Y'));
                g.drawString(String.valueOf(trackX), x - dataWidth / 2, y - paddingToScale(equipRefFromTrack + 5, 'Y'));
            }
        }

        if (upOrDn.equals("DN")) {

            if (typeList.length > 0) {
                if (typeList.length % 2 == 0) {
                    for (int i = 0; i < typeList.length; i++) {
                        if (i == 0) {
                            g.drawLine(x - paddingToScale(6 + 7 * ((int)(typeList.length/2) - 1 - i) + 2, 'X'),y + paddingToScale(10 + 2.5, 'Y'), x - paddingToScale(6 + 7 * ((int)(typeList.length/2) - 1 - i) + 2, 'X'), y + paddingToScale(10 - 2, 'Y'));
                            g.drawLine(x - paddingToScale(6 + 7 * ((int)(typeList.length/2) - 1 - i) + 2, 'X'),y + paddingToScale(10 - 2, 'Y'), x, y + paddingToScale(10 - 2, 'Y'));
                        }

                        if (i < typeList.length/2) {
                            drawBox(g, x - paddingToScale(6 + 7 * ((int)(typeList.length/2) - 1 - i), 'X'), y + paddingToScale(10, 'Y'), typeList[i]);
                        } else {
                            drawBox(g, x + paddingToScale(1 + 7 * (i - (int)(typeList.length/2)), 'X'), y + paddingToScale(10, 'Y'), typeList[i]);
                        }

                        if (i == typeList.length - 1) {
                            g.drawLine(x,y + paddingToScale(10 - 2, 'Y'), x + paddingToScale(1 + 7 * (i - (int)(typeList.length/2)) + 7, 'X'), y + paddingToScale(10 - 2, 'Y'));
                            g.drawLine(x + paddingToScale(1 + 7 * (i - (int)(typeList.length/2)) + 7, 'X'),y + paddingToScale(10 + 2.5, 'Y'), x + paddingToScale(1 + 7 * (i - (int)(typeList.length/2)) + 7, 'X'), y + paddingToScale(10 - 2, 'Y'));
                        }
                    }
                }

                if (typeList.length % 2 == 1) {
                    for (int i = 0; i < typeList.length; i++) {
                        if (i == 0) {
                            g.drawLine(x - paddingToScale(2.5 + 7 * ((int)(typeList.length/2) - i) + 2, 'X'),y + paddingToScale(10 + 2.5, 'Y'), x - paddingToScale(2.5 + 7 * ((int)(typeList.length/2) - i) + 2, 'X'), y + paddingToScale(10 - 2, 'Y'));
                            g.drawLine(x - paddingToScale(2.5 + 7 * ((int)(typeList.length/2) - i) + 2, 'X'),y + paddingToScale(10 - 2, 'Y'), x, y + paddingToScale(10 - 2, 'Y'));
                        }

                        if (i < (typeList.length + 1) / 2) {
                            drawBox(g, x - paddingToScale(2.5 + 7 * ((int)(typeList.length/2) - i), 'X'), y + paddingToScale(10, 'Y'), typeList[i]);
                        } else {
                            drawBox(g, x + paddingToScale(4.5 + 7 * (i - (int)(typeList.length/2) - 1), 'X'), y + paddingToScale(10, 'Y'), typeList[i]);
                        }

                        if (i == typeList.length - 1) {
                            g.drawLine(x,y + paddingToScale(10 - 2, 'Y'), x + paddingToScale(4.5 + 7 * (i - (int)(typeList.length/2) - 1) + 7, 'X'), y + paddingToScale(10 - 2, 'Y'));
                            g.drawLine(x + paddingToScale(4.5 + 7 * (i - (int)(typeList.length/2) - 1) + 7, 'X'),y + paddingToScale(10 + 2.5, 'Y'), x + paddingToScale(4.5 + 7 * (i - (int)(typeList.length/2) - 1) + 7, 'X'), y + paddingToScale(10 - 2, 'Y'));
                        }
                    }
                }
            } else {
                drawBox(g, x - paddingToScale(2.5, 'X'), y + paddingToScale(10, 'Y'), types);

            }

            g.drawString(id, x - idWidth / 2, y + paddingToScale(10 + 5 + 2, 'Y') + idHeight / 2);

            if (isShowBoxData) {

                Stroke defaultStroke = g.getStroke();
                float[] dash = { 2f, 0f, 2f };
                BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash, 2f);
                g.setStroke(bs1);

                g.drawLine(x, y + (int)(25 * scaleY), x, y + paddingToScale(equipRefFromTrack, 'Y'));

                g.setStroke(defaultStroke);

                //g.drawLine(x, y - paddingToScale(equipRefFromTrack, 'Y'), x, y - paddingToScale(trackRefFromTrack, 'Y'));
                g.drawString(String.valueOf(trackX), x - dataWidth / 2, y + paddingToScale(equipRefFromTrack + 10, 'Y'));
            }
        }

        if (isSelected) {
            g.setColor(Color.BLACK);
        }
    }


    // paint all components using the customized painting functions above
    public void paint(Graphics graphics){
        super.paint(graphics);

        Graphics2D g = (Graphics2D) graphics;
        g.translate(0, 0);
        g.scale(1, 1);

        Font currentFont = g.getFont();
        g.setFont(new Font("", Font.PLAIN, (int)(currentFont.getSize() * 0.5F * scaleX)));
        g.setStroke(new BasicStroke((int)(1 * scaleX)));


        try {

            drawReference(g, upTrack, dnTrack, trackLocationsLeft[0], trackLocationsRight[trackLines.length - 1], trackRefDistance, "Track Circuit Reference");
            drawReference(g, upTrack, dnTrack, trackLocationsLeft[0], trackLocationsRight[trackLines.length - 1], equipmentRefDistance, "Equipment Reference");


            for (int i = 0; i < trackLines.length; i++) {
                if (trackSides[i].equals("UP")) {
                    drawHorizontalTrack(g, trackLocationsLeft[i], trackLocationsRight[i], upTrack, true, true, true, trackRefDistance, trackSides[i], trackIds[i], (selectedEquipment.equals("Track Circuit") && trackStations[i].equals(selectedStation) && trackIds[i].equals(selectedId)));
                }

                if (trackSides[i].equals("DN")) {
                    drawHorizontalTrack(g, trackLocationsLeft[i], trackLocationsRight[i], dnTrack, true, true, true, trackRefDistance, trackSides[i], trackIds[i], (selectedEquipment.equals("Track Circuit") && trackStations[i].equals(selectedStation) && trackIds[i].equals(selectedId)));
                }
            }


            for (int i = 0; i < platformLines.length; i++) {
                drawPlatform(g, platformMidPtsUp[i], platformMidPtsDn[i], upTrack, dnTrack, equipmentRefDistance, trackRefDistance, platformIsSingle[i], platformNumberUp[i], platformNumberDn[i], platformNames[i], (selectedEquipment.equals("Platform") && platformStations[i].equals(selectedStation) && platformNames[i].equals(selectedId)));
            }


            for (int i = 0; i < crossingLines.length; i++) {
                double[][] boundaries = {{crossingTopLeftUp[i], crossingTopLeftDn[i]}, {crossingTopRightUp[i], crossingTopRightDn[i]}, {crossingBottomLeftUp[i], crossingBottomLeftDn[i]}, {crossingBottomRightUp[i], crossingBottomRightDn[i]}};
                double[] intercepts = {crossingInterceptsUp[i], crossingInterceptsDn[i]};
                String[] switchesNames = crossingSwitchesConnected[i].split(",");
                double[] switches = new double[switchesNames.length];

                for (int j = 0; j < switchesNames.length; j++) {
                    for (int k = 0; k < switchLines.length; k++) {
                        if (switchLines[k].equals(crossingLines[i]) && switchStations[k].equals(crossingStations[i]) && switchIds[k].equals(switchesNames[j])) {
                            switches[j] = switchLocations[k];
                            break;
                        }
                    }
                    if (switches[j] == 0.0) {
                        switches[j] = -1.0;
                    }
                }

                boolean switchSelected = false;
                int switchNum = 99;
                for (int k = 0; k < switchesNames.length; k++) {
                    if (switchesNames[k].equals(selectedId)) {
                        switchNum = k;
                        switchSelected = true;
                    }
                }


                if (crossingSides[i].equals("UPDN")) {
                    drawCrossing(g, switches, boundaries, intercepts, switchesNames, upTrack, dnTrack, crossingIds[i], (selectedEquipment.equals("Crossing") && crossingStations[i].equals(selectedStation) && crossingIds[i].equals(selectedId)), (selectedEquipment.equals("Switch") && crossingStations[i].equals(selectedStation) && switchSelected), switchNum);
                }
                if (crossingSides[i].equals("UP")) {
                    double tempSwitchZero = switches[0];
                    switches[0] = switches[2];
                    switches[2] = tempSwitchZero;
                    double tempSwitchOne = switches[1];
                    switches[1] = switches[3];
                    switches[3] = tempSwitchOne;

                    double[] tempBoundaryZero = boundaries[0];
                    boundaries[0] = boundaries[2];
                    boundaries[2] = tempBoundaryZero;
                    double[] tempBoundaryOne = boundaries[1];
                    boundaries[1] = boundaries[3];
                    boundaries[3] = tempBoundaryOne;

                    String tempSwitchNameZero = switchesNames[0];
                    switchesNames[0] = switchesNames[2];
                    switchesNames[2] = tempSwitchNameZero;
                    String tempSwitchNameOne = switchesNames[1];
                    switchesNames[1] = switchesNames[3];
                    switchesNames[3] = tempSwitchNameOne;

                    drawCrossing(g, switches, boundaries, intercepts, switchesNames, upTrack - upDnDistance / 4, upTrack, crossingIds[i], (selectedEquipment.equals("Crossing") && crossingStations[i].equals(selectedStation) && crossingIds[i].equals(selectedId)), (selectedEquipment.equals("Switch") && crossingStations[i].equals(selectedStation) && switchSelected), switchNum);
                }
                if (crossingSides[i].equals("DN")) {
                    drawCrossing(g, switches, boundaries, intercepts, switchesNames, dnTrack, dnTrack + upDnDistance / 4, crossingIds[i], (selectedEquipment.equals("Crossing") && crossingStations[i].equals(selectedStation) && crossingIds[i].equals(selectedId)), (selectedEquipment.equals("Switch") && crossingStations[i].equals(selectedStation) && switchSelected), switchNum);
                }
            }


            for (int i = 0; i < switchTrackLines.length; i++) {
                double switchCoordinateX1 = 0.0;
                double switchCoordinateX2 = 0.0;
                String sideX1 = null;
                String sideX2 = null;

                for (int j = 0; j < switchLines.length; j++) {
                    if ((switchCoordinateX1 != 0.0) && (switchCoordinateX2 != 0.0) && (sideX1 != null) && (sideX2 != null)) {
                        break;
                    }
                    if (switchLines[j].equals(switchTrackLines[i]) && switchStations[j].equals(switchTrackFromStations[i]) && switchIds[j].equals(switchTrackFromSwitches[i])) {
                        switchCoordinateX1 = switchLocations[j];
                        sideX1 = switchSides[j];
                    }
                    if (switchLines[j].equals(switchTrackLines[i]) && switchStations[j].equals(switchTrackToStations[i]) && switchIds[j].equals(switchTrackToSwitches[i])) {
                        switchCoordinateX2 = switchLocations[j];
                        sideX2 = switchSides[j];
                    }
                }

                boolean switchSelected = false;
                String switchType = "";
                if (switchTrackFromSwitches[i].equals(selectedId)) {
                    switchSelected = true;
                    switchType = "From";
                }
                if (switchTrackToSwitches[i].equals(selectedId)) {
                    switchSelected = true;
                    switchType = "To";
                }

                if ((switchCoordinateX1 != 0.0) && (switchCoordinateX2 != 0.0) && (sideX1 != null) && (sideX2 != null)) {
                    drawSwitchingTrack(g, switchCoordinateX1, switchCoordinateX2, switchTrackBoundaryUp[i], switchTrackBoundaryDn[i], upTrack, dnTrack, sideX1.equals("UP") ? "upToDn" : "dnToUp", switchTrackFromSwitches[i], switchTrackToSwitches[i], (selectedEquipment.equals("Switch") && (switchTrackFromStations[i].equals(selectedStation) || switchTrackToStations[i].equals(selectedStation)) && switchSelected), switchType);
                }
            }


            for (int i = 0; i < floodgateLines.length; i++) {
                drawFloodgate(g, floodgateLocationsUp[i], floodgateLocationsDn[i], upTrack, dnTrack, floodgateNames[i], floodgateNorthSouth[i], equipmentRefDistance, trackRefDistance);
            }


            if (isShowSignal) {
                for (int i = 0; i < signalLines.length; i++) {
                    if (signalSides[i].equals("UP")) {
                        drawSignal(g, signalLocations[i], upTrack, signalTypes[i], signalSides[i], signalLeftOrRights[i], equipmentRefDistance, trackRefDistance, signalIds[i], (selectedEquipment.equals("Signal") && signalStations[i].equals(selectedStation) && signalIds[i].equals(selectedId)));
                    }

                    if (signalSides[i].equals("DN")) {
                        drawSignal(g, signalLocations[i], dnTrack, signalTypes[i], signalSides[i], signalLeftOrRights[i], equipmentRefDistance, trackRefDistance, signalIds[i], (selectedEquipment.equals("Signal") && signalStations[i].equals(selectedStation) && signalIds[i].equals(selectedId)));
                    }
                }
            }


            if (isShowImpedanceBond) {
                for (int i = 0; i < impedanceBondLines.length; i++) {

                    if (impedanceBondSides[i].equals("UP")) {
                        drawImpedanceBond(g, impedanceBondLocations[i], upTrack, impedanceBondIds[i], impedanceBondSides[i], equipmentRefDistance, trackRefDistance, impedanceBondQuantities[i], (selectedEquipment.equals("Impedance Bond") && impedanceBondStations[i].equals(selectedStation) && impedanceBondIds[i].equals(selectedId)));
                    }
                    if (impedanceBondSides[i].equals("DN")) {
                        drawImpedanceBond(g, impedanceBondLocations[i], dnTrack, impedanceBondIds[i], impedanceBondSides[i], equipmentRefDistance, trackRefDistance, impedanceBondQuantities[i], (selectedEquipment.equals("Impedance Bond") && impedanceBondStations[i].equals(selectedStation) && impedanceBondIds[i].equals(selectedId)));
                    }
                }
            }


            if (isShowBeacon) {
                for (int i = 0; i < beaconLines.length; i++) {
                    //System.out.println(beaconLocations);
                    if (beaconSides[i].equals("UP")) {
                        drawBeacon(g, beaconLocations[i], upTrack, beaconSides[i], beaconIds[i], beaconTypes[i], beaconLabels[i], equipmentRefDistance, trackRefDistance, beaconReverses[i], (selectedEquipment.equals("Beacon") && beaconStations[i].equals(selectedStation) && beaconIds[i].equals(selectedId)));
                    }
                    if (beaconSides[i].equals("DN")) {
                        drawBeacon(g, beaconLocations[i], dnTrack, beaconSides[i], beaconIds[i], beaconTypes[i], beaconLabels[i], equipmentRefDistance, trackRefDistance, beaconReverses[i], (selectedEquipment.equals("Beacon") && beaconStations[i].equals(selectedStation) && beaconIds[i].equals(selectedId)));
                    }
                }
            }


            if (isShowSab) {
                for (int i = 0; i < sabLines.length; i++) {
                    if (sabSides[i].equals("UP")) {
                        drawSAB(g, sabLocations[i], upTrack, sabSides[i], equipmentRefDistance, trackRefDistance, sabIds[i], (selectedEquipment.equals("SAB") && sabStations[i].equals(selectedStation) && sabIds[i].equals(selectedId)));
                    }
                    if (sabSides[i].equals("DN")) {
                        drawSAB(g, sabLocations[i], dnTrack, sabSides[i], equipmentRefDistance, trackRefDistance, sabIds[i], (selectedEquipment.equals("SAB") && sabStations[i].equals(selectedStation) && sabIds[i].equals(selectedId)));
                    }
                }
            }

            if (isShowBox) {
                for (int i = 0; i < boxLines.length; i++) {
                    if (boxSides[i].equals("UP")) {
                        drawBoxes(g, boxLocations[i], upTrack, boxSides[i], equipmentRefDistance, trackRefDistance, boxIds[i], boxTypes[i], (selectedEquipment.equals("Box") && boxStations[i].equals(selectedStation) && boxIds[i].equals(selectedId)));
                    }
                    if (boxSides[i].equals("DN")) {
                        drawBoxes(g, boxLocations[i], dnTrack, boxSides[i], equipmentRefDistance, trackRefDistance, boxIds[i], boxTypes[i], (selectedEquipment.equals("Box") && boxStations[i].equals(selectedStation) && boxIds[i].equals(selectedId)));
                    }
                }
            }


            if (isShowOther) {
                for (int i = 0; i < additionalLines.length; i++) {
                    if (additionalSides[i].equals("UP")) {
                        drawAdditionalEquipment(g, additionalLocations[i], upTrack, additionalSides[i], equipmentRefDistance, trackRefDistance, additionalNames[i], (selectedEquipment.equals("Others") && additionalStations[i].equals(selectedStation) && additionalNames[i].equals(selectedId)));
                    }
                    if (additionalSides[i].equals("DN")) {
                        drawAdditionalEquipment(g, additionalLocations[i], dnTrack, additionalSides[i], equipmentRefDistance, trackRefDistance, additionalNames[i], (selectedEquipment.equals("Others") && additionalStations[i].equals(selectedStation) && additionalNames[i].equals(selectedId)));
                    }
                }
            }
        }  catch (Exception e) {

        }
    }




    ISLPanel(){

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(18000, 1200));

        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);

        this.setVisible(true);

    }
    //    @Override
    public void mouseDragged(MouseEvent e) {
        double mouseX = e.getX();
        double mouseY = e.getY();

        offsetX = offsetX - (int)((mouseX - startPanX)/scaleX);
        offsetY = offsetY - (int)((mouseY - startPanY)/scaleY);

        startPanX = (int)mouseX;
        startPanY = (int)mouseY;

        repaint();

    }
    public void mouseMoved(MouseEvent e) {
        //repaint();
    }

    //    @Override
    public void mousePressed(MouseEvent e) {

        double mouseX = e.getX();
        double mouseY = e.getY();

        startPanX = (int)mouseX;
        startPanY = (int)mouseY;

        //repaint();
    }
    //    @Override
    public void mouseReleased(MouseEvent e) {
        //MouseHeld = false;

    }

    public void mouseEntered(MouseEvent e) {
        //repaint();
    }

    public void mouseExited(MouseEvent e) {
        //repaint();
    }

    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2){
            JScrollBar verticalScrollBar = MyFrame.ISLScrollPanel.getVerticalScrollBar();
            JScrollBar horizontalScrollBar = MyFrame.ISLScrollPanel.getHorizontalScrollBar();

            offsetX = -700; offsetY= -350;
            startPanX = 0; startPanY = 0;
            scaleX = 1; scaleY = 1;

            verticalScrollBar.setValue(verticalScrollBar.getMinimum());
            horizontalScrollBar.setValue(horizontalScrollBar.getMinimum());

            MyFrame.changeDisplay("");
            MyFrame.changeDisplay(MyFrame.currentPanel);
        }
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        //Zooming function
        int scroll = e.getWheelRotation();

        double mouseXBefore = screenToWorldConverter(e.getX(), offsetX, scaleX);
        double mouseYBefore = screenToWorldConverter(e.getY(), offsetY, scaleY);

        //Zoom in
        if ((scroll < 0 ) && (scaleX <= 5) && (scaleY <= 5)) {
            scaleX = scaleX * 1.1;
            scaleY = scaleY * 1.1;
        }

        //Zoom out
        if (scroll > 0 && (scaleX >= 0.5) && (scaleY >= 0.5)) {
            scaleX = scaleX / 1.1;
            scaleY = scaleY / 1.1;
        }

        double mouseXAfter = screenToWorldConverter(e.getX(), offsetX, scaleX);
        double mouseYAfter = screenToWorldConverter(e.getY(), offsetY, scaleY);

        offsetX = offsetX + (mouseXBefore - mouseXAfter);
        offsetY = offsetY + (mouseYBefore - mouseYAfter);

        repaint();
        //^Zooming function^
    }

    public void actionPerformed(ActionEvent e) {

    }
}
