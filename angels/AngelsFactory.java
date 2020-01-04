package angels;

import java.util.ArrayList;

public final class AngelsFactory {

    private AngelsFactory() {
    }

    public static Angel createAngel(final String angelType, final int round, final int x, final int y) {
        switch (angelType) {
            case "DamageAngel":

            default:
                return null;
        }
    }

    public static ArrayList<Angel> createAngels(final ArrayList<String> angels,
                                               final Integer[][] angelsPosition) {
        ArrayList<Angel> angelsList = new ArrayList<Angel>();
        int i;
        for (i = 0; i < angels.size(); i++) {
            angelsList.add(createAngel(angels.get(i), angelsPosition[i][0], angelsPosition[i][1],
                    angelsPosition[i][2]));
        }
        return angelsList;
    }
}
