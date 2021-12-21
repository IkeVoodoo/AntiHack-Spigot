package me.ikevoodoo.antihack;

import me.ikevoodoo.antihack.listeners.PlayerListener;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.message.Message;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

public final class Main extends JavaPlugin {

    public static Main INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        addFilter();
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }


    private static void addFilter() {
        ((org.apache.logging.log4j.core.Logger) LogManager.getRootLogger()).addFilter(new Filter() {
            @Override
            public Result getOnMismatch() {
                return Result.NEUTRAL;
            }

            @Override
            public Result getOnMatch() {
                return Result.NEUTRAL;
            }

            @Override
            public Result filter(Logger logger, Level level, Marker marker, String msg, Object... params) {
                return shouldDeny(msg) ? Result.DENY : Result.NEUTRAL;
            }

            @Override
            public Result filter(Logger logger, Level level, Marker marker, String message, Object p0) {
                return filter(logger, level, marker, message, p0, null, null, null, null, null, null, null, null, null, null);
            }

            @Override
            public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1) {
                return filter(logger, level, marker, message, p0, p1, null, null, null, null, null, null, null, null, null);
            }

            @Override
            public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2) {
                return filter(logger, level, marker, message, p0, p1, p2, null, null, null, null, null, null, null, null);
            }

            @Override
            public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
                return filter(logger, level, marker, message, p0, p1, p2, p3, null, null, null, null, null, null, null);
            }

            @Override
            public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
                return filter(logger, level, marker, message, p0, p1, p2, p3, p4, null, null, null, null, null, null, null, null);
            }

            @Override
            public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
                return filter(logger, level, marker, message, p0, p1, p2, p3, p4, p5, null, null, null, null, null);
            }

            @Override
            public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
                return filter(logger, level, marker, message, p0, p1, p2, p3, p4, p5, p6, null, null, null, null, null);
            }

            @Override
            public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
                return filter(logger, level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, null, null, null);
            }

            @Override
            public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
                return filter(logger, level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, null, null);
            }

            @Override
            public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
                return filter(logger, level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, null);
            }

            @Override
            public Result filter(Logger logger, Level level, Marker marker, Object msg, Throwable t) {
                return filter(logger, level, marker, String.valueOf(msg));
            }

            @Override
            public Result filter(Logger logger, Level level, Marker marker, Message msg, Throwable t) {
                return filter(logger, level, marker, String.valueOf(msg));
            }

            @Override
            public Result filter(LogEvent event) {
                return filter(null, event.getLevel(), event.getMarker(), event.getMessage().getFormattedMessage());
            }

            @Override
            public State getState() {
                return State.STARTED;
            }

            @Override
            public void initialize() {

            }

            @Override
            public void start() {

            }

            @Override
            public void stop() {

            }

            @Override
            public boolean isStarted() {
                return true;
            }

            @Override
            public boolean isStopped() {
                return false;
            }
        });
    }

    public static boolean shouldDeny(String message) {
        message = new String(message.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8).toLowerCase(Locale.ROOT);
        message = message.replace("%24", "$")
                .replace("%7b", "{")
                .replace("%7d", "}");
        message = message
                .replaceAll("\\s*", "")
                .replaceAll("\n", "")
                .replace("${}", "");
        return
                message.contains("jndi") || message.contains("jnd") || (
                        (message.contains("${")
                                && message.contains("}") || message.contains("${::-")));
    }
}
