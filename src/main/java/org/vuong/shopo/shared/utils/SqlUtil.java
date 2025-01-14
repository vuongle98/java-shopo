package org.vuong.shopo.shared.utils;

public class SqlUtil {

    public static String getLikePattern(String keyword) {
        String sanitizedKeyword = keyword.toLowerCase().replace("%", "\\%");
        return "%" + sanitizedKeyword + "%";
    }
}
