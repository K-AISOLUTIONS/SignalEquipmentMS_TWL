public class MyRectangle {
    public int[] floatToIntConverter(double dx1, double dy1, double dx2, double dy2) {
        int ix1 = (int)dx1;
        int iy1 = (int)dy1;
        int ix2 = (int)dx2;
        int iy2 = (int)dy2;

        return new int[]{ix1, iy1, ix2, iy2};
    }


}
