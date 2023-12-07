package org.dasultra.api.value;

import net.md_5.bungee.api.ChatColor;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {

    private static final Pattern pattern = Pattern.compile("#[a-fA-F\\d]{6}");

    public static String getString(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public static String encodeString(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    public static String decodeString(String input) {
        String text = new String(Base64.getDecoder().decode(input));
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            output.append(text.charAt(i));
        }
        return output.toString();
    }

    public static String getColorCode(String input) {
        Matcher match = pattern.matcher(input);
        while (match.find()) {
            String color = input.substring(match.start(), match.end());
            input = input.replace(color, ChatColor.of(color) + "");
            match = pattern.matcher(input);
        }
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public static double convertDouble(String value) {
        return new BigDecimal(value).doubleValue();
    }

}