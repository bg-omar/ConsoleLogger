package com.github.bgomar.bgconsolelogger.tools;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;


public class Svg2css {

    private static final String SINGLE_QUOTE = "'";
    private static final String DOUBLE_QUOTE = "\"";

    private static final String QUOTES_LEVEL1 = DOUBLE_QUOTE;
    private static final String QUOTES_LEVEL2 = SINGLE_QUOTE;

    private static final Pattern SYMBOLS = Pattern.compile("[\\r\\n%#()<>?\\[\\\\\\]^`{|}]");


    public static String encodeURL(String decoded) {
        try {
            if (decoded.isBlank()) {
                return "";
            }
            String trimmed = decoded.trim();
            String namespaced = addNameSpace(trimmed);
            namespaced = encodeSVG(namespaced);
            String encoded = URLEncoder.encode(namespaced, StandardCharsets.UTF_8);
            encoded = encoded.replaceAll("\\++", " ");
            return encoded;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public static String svg2cssURL(String encoded) {
        try {
            if (encoded.isBlank()) {
                return "";
            }
            String escaped = encodeSVG(encoded);
            return "background-image: url(" + QUOTES_LEVEL1 + "data:image/svg+xml," + escaped.replace("+", "%2B") + QUOTES_LEVEL1 + ");";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public static String preview(String svg) {
        // Use single quotes instead of double to avoid encoding.
        try {
            if (svg.isBlank()) {
                return "";
            }

            return svg;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }



    public static String css2svgURL(String text) {
        try {
            if (text.isBlank()) {
                return "";
            }
            String trimmed = text.trim();
            String encoded = trimmed.substring(trimmed.indexOf(",") + 1); // Extract the encoded SVG string
            if (encoded.endsWith("\");")) {
                encoded = encoded.substring(0, encoded.length() - 3); // Remove the last ");
            }
            return encoded;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }


    public static String decodeURL(String encoded) {
        try {
            if (encoded.isBlank()) {
                return "";
            }

            return URLDecoder.decode(encoded, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }



    private static String addNameSpace(String decoded) {
        if (!decoded.contains("http://www.w3.org/2000/svg")) {
            decoded = decoded.replace("<svg", "<svg xmlns=" + QUOTES_LEVEL2 + "http://www.w3.org/2000/svg" + QUOTES_LEVEL2);
        }
        return decoded;
    }

    private static String encodeSVG(String data) {
        // Use single quotes instead of double to avoid encoding.
        if (QUOTES_LEVEL1.equals(DOUBLE_QUOTE)) {
            data = data.replace("\"", "'");
        } else {
            data = data.replace("'", "\"");
        }
        data = data.replace(">\s{1,}<", "><");
        data = data.replace("\\s{2,}", " ");
        data = data.replace("%20", " ");
        data = data.replace("%22", "\"");
        data = data.replace("%27", "'");
        data = data.replace("%3A", ":");
        data = data.replace("%2F", "/");
        data = data.replace("%3D", "=");
        return data;
    }

}