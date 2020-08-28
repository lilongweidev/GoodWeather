package com.llw.goodweather.utils;

public class TransUnitUtil {

    public static int getInt(String value) {
        try {
            int i = Integer.parseInt(value);
            return i;
        } catch (Exception e) {
            return 0;
        }
    }

    public static long getF(String value) {
        try {
            long i = Integer.parseInt(value);
            i = Math.round((i * 1.8 + 32));
            return i;
        } catch (Exception e) {
            return 0;
        }
    }

    public static double getDouble(String value) {
        try {
            double i = Double.parseDouble(value);
            return i;
        } catch (Exception e) {
            return 0.00;
        }
    }

    public static int getCNIAQI(double score, String aqiItem) {
        if (score <= 0) {
            return 0;
        }
        double iaqihi, iaqilo, bphi, bplo;
        switch (aqiItem) {

            case "so2":
                if (score <= 150) {
                    bphi = 150;
                    bplo = 0;
                    iaqihi = 50;
                    iaqilo = 0;
                } else if (score <= 500) {
                    bphi = 500;
                    bplo = 150;
                    iaqihi = 100;
                    iaqilo = 50;
                } else if (score <= 650) {
                    bphi = 650;
                    bplo = 500;
                    iaqihi = 150;
                    iaqilo = 100;
                } else if (score <= 800) {
                    bphi = 800;
                    bplo = 650;
                    iaqihi = 200;
                    iaqilo = 150;
                } else if (score <= 1600) {
                    bphi = 1600;
                    bplo = 800;
                    iaqihi = 300;
                    iaqilo = 200;
                } else if (score <= 2100) {
                    bphi = 2100;
                    bplo = 1600;
                    iaqihi = 400;
                    iaqilo = 300;
                } else {
                    bphi = 2620;
                    bplo = 2100;
                    iaqihi = 500;
                    iaqilo = 400;
                }
                break;

            case "no2":
                if (score <= 100) {
                    bphi = 100;
                    bplo = 0;
                    iaqihi = 50;
                    iaqilo = 0;
                } else if (score <= 200) {
                    bphi = 200;
                    bplo = 100;
                    iaqihi = 100;
                    iaqilo = 50;
                } else if (score <= 700) {
                    bphi = 700;
                    bplo = 200;
                    iaqihi = 150;
                    iaqilo = 100;
                } else if (score <= 1200) {
                    bphi = 1200;
                    bplo = 700;
                    iaqihi = 200;
                    iaqilo = 150;
                } else if (score <= 2340) {
                    bphi = 2340;
                    bplo = 1200;
                    iaqihi = 300;
                    iaqilo = 200;
                } else if (score <= 3090) {
                    bphi = 3090;
                    bplo = 2340;
                    iaqihi = 400;
                    iaqilo = 300;
                } else {
                    bphi = 3840;
                    bplo = 3090;
                    iaqihi = 500;
                    iaqilo = 400;
                }
                break;
            case "pm10":
                if (score <= 50) {
                    bphi = 50;
                    bplo = 0;
                    iaqihi = 50;
                    iaqilo = 0;
                } else if (score <= 150) {
                    bphi = 150;
                    bplo = 50;
                    iaqihi = 100;
                    iaqilo = 50;
                } else if (score <= 250) {
                    bphi = 250;
                    bplo = 150;
                    iaqihi = 150;
                    iaqilo = 100;
                } else if (score <= 350) {
                    bphi = 350;
                    bplo = 250;
                    iaqihi = 200;
                    iaqilo = 150;
                } else if (score <= 420) {
                    bphi = 420;
                    bplo = 350;
                    iaqihi = 300;
                    iaqilo = 200;
                } else if (score <= 500) {
                    bphi = 500;
                    bplo = 420;
                    iaqihi = 400;
                    iaqilo = 300;
                } else {
                    bphi = 600;
                    bplo = 500;
                    iaqihi = 500;
                    iaqilo = 400;
                }

                break;
            case "co":
                if (score <= 5) {
                    bphi = 5;
                    bplo = 0;
                    iaqihi = 50;
                    iaqilo = 0;
                } else if (score <= 10) {
                    bphi = 10;
                    bplo = 5;
                    iaqihi = 100;
                    iaqilo = 50;
                } else if (score <= 35) {
                    bphi = 35;
                    bplo = 10;
                    iaqihi = 150;
                    iaqilo = 100;
                } else if (score <= 60) {
                    bphi = 60;
                    bplo = 35;
                    iaqihi = 200;
                    iaqilo = 150;
                } else if (score <= 90) {
                    bphi = 90;
                    bplo = 60;
                    iaqihi = 300;
                    iaqilo = 200;
                } else if (score <= 120) {
                    bphi = 120;
                    bplo = 90;
                    iaqihi = 400;
                    iaqilo = 300;
                } else {
                    bphi = 150;
                    bplo = 120;
                    iaqihi = 500;
                    iaqilo = 400;
                }
                break;
            case "o3":
                if (score <= 160) {
                    bphi = 160;
                    bplo = 0;
                    iaqihi = 50;
                    iaqilo = 0;
                } else if (score <= 200) {
                    bphi = 200;
                    bplo = 160;
                    iaqihi = 100;
                    iaqilo = 50;
                } else if (score <= 300) {
                    bphi = 300;
                    bplo = 200;
                    iaqihi = 150;
                    iaqilo = 100;
                } else if (score <= 400) {
                    bphi = 400;
                    bplo = 300;
                    iaqihi = 200;
                    iaqilo = 150;
                } else if (score <= 800) {
                    bphi = 800;
                    bplo = 400;
                    iaqihi = 300;
                    iaqilo = 200;
                } else if (score <= 1000) {
                    bphi = 1000;
                    bplo = 800;
                    iaqihi = 400;
                    iaqilo = 300;
                } else {
                    bphi = 1200;
                    bplo = 1000;
                    iaqihi = 500;
                    iaqilo = 400;
                }
                break;
            case "pm25":
                if (score <= 35) {
                    bphi = 35;
                    bplo = 0;
                    iaqihi = 50;
                    iaqilo = 0;
                } else if (score <= 75) {
                    bphi = 75;
                    bplo = 35;
                    iaqihi = 100;
                    iaqilo = 50;
                } else if (score <= 115) {
                    bphi = 115;
                    bplo = 75;
                    iaqihi = 150;
                    iaqilo = 100;
                } else if (score <= 150) {
                    bphi = 150;
                    bplo = 115;
                    iaqihi = 200;
                    iaqilo = 150;
                } else if (score <= 250) {
                    bphi = 250;
                    bplo = 150;
                    iaqihi = 300;
                    iaqilo = 200;
                } else if (score <= 350) {
                    bphi = 350;
                    bplo = 250;
                    iaqihi = 400;
                    iaqilo = 300;
                } else {
                    bphi = 500;
                    bplo = 350;
                    iaqihi = 500;
                    iaqilo = 400;
                }
                break;
            default:
                return 0;
        }
        return Math.min((int) Math.ceil((iaqihi - iaqilo) * (score - bplo) / (bphi - bplo) + iaqilo), 500);
    }

}
