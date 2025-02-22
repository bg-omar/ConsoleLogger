package com.github.bgomar.bgconsolelogger.tools;


public class Px2RemTool {

    private Px2RemTool() {
        throw new IllegalStateException("Utility class");
    }
    public static double rem2Px (Double px) {
        try {
            if (px.isNaN()) {
                return 0;
            }

            return  px/0.0625;
        } catch (Exception e) {
            return 0;
        }
    }

    public static double px2Rem(Double rem) {
        try {
            if (rem.isNaN()) {
                return 0;
            }
            return  rem * 0.0625;
        } catch (Exception e) {
            return 0;
        }
    }
}