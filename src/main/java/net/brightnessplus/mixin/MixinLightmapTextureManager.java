public static double minBrightness = -1.0;
public static double maxBrightness = 12.0;
public static double brightnessSliderInterval = 0.05;
private static double step = 0.1;

private static boolean isToggledOn = false;

public static ArrayList<Double> brightnesses;
private static int brightnessIndex = 0;

public static double getBrightness() {
    return brightnesses.get(brightnessIndex);
}

public static int getBrightnessIndex() {
    return brightnessIndex;
}

public static void setBrightnessIndex(int index) {
    brightnessIndex = index;
    client.options.getGamma().setValue(getBrightness());
}

public static boolean isFullbrightEnabled() {
    return isToggledOn;
}
