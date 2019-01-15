package com.matang28.restsqlexporter.utils;

import org.apache.log4j.Logger;

import java.text.MessageFormat;

/**
 * Wrapper to the log4j Logger that allow to print formatted log messages using
 * the {@link MessageFormat} convention.
 */
public class FLogger {

    private Logger logger;

    public FLogger(Logger logger) {
        this.logger = logger;
    }

    public static FLogger getLogger(String name){
        return new FLogger(Logger.getLogger(name));
    }

    public static FLogger getLogger(Class tClass){
        return new FLogger(Logger.getLogger(tClass));
    }

    public void fatal(String message, Object... args){
        this.logger.fatal(format(message, args));
    }

    public void fatal(String message, Throwable t, Object... args){
        this.logger.fatal(format(message,args), t);
    }

    public void fatal(String message, Throwable t){
        this.logger.fatal(message, t);
    }

    public void error(String message, Object... args){
        this.logger.error(format(message, args));
    }

    public void error(String message, Throwable t, Object... args){
        this.logger.error(format(message,args), t);
    }

    public void info(String message, Object... args){
        this.logger.info(format(message, args));
    }

    public void info(String message, Throwable t, Object... args){
        this.logger.info(format(message,args), t);
    }

    public void warn(String message, Object... args){
        this.logger.warn(format(message, args));
    }

    public void warn(String message, Throwable t, Object... args){
        this.logger.warn(format(message,args), t);
    }

    public void debug(String message){
        this.logger.debug(message);
    }

    public void debug(String message, Object... args){
        this.logger.debug(format(message, args));
    }

    public void debug(String message, Throwable t, Object... args){
        this.logger.debug(format(message,args), t);
    }

    private static String format(String message, Object... args){
        return MessageFormat.format(message, args);
    }

}
