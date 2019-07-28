package me.rndstad.hubforce.utils;

import net.md_5.bungee.api.ChatColor;

public class StringUtils {

    public static String format(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}

