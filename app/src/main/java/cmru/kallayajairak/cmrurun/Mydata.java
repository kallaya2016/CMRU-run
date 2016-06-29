package cmru.kallayajairak.cmrurun;

/**
 * Created by Admin on 28/6/2559.
 */
public class Mydata {
    //explicit
    private int[] avataInts = new int[]{R.drawable.bird48,
            R.drawable.doremon48, R.drawable.kon48,
            R.drawable.nobita48,R.drawable.rat48};
    private double[] latStationDoubles = new double[]{18.807643,18.806069,18.807897,18.807064,};
    private double[] lngStationDoubles = new double[]{98.985556,98.987541,98.987176,98.987219,};

    private int[] iconStationInts = new int[]{R.drawable.build1, R.drawable.build2,
            R.drawable.build3, R.drawable.build4};

    public int[] getIconStationInts() {
        return iconStationInts;
    }

    public int[] getAvataInts() {
        return avataInts;
    }

    public double[] getLatStationDoubles() {
        return latStationDoubles;
    }

    public double[] getLngStationDoubles() {
        return lngStationDoubles;
    }
}

