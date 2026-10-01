package com.huyanh.util;

public class WebUtil_22145150 {
    public static int toInt(String s, int def) {
        try { return Integer.parseInt(s.trim()); } catch (Exception e) { return def; }
    }
}
