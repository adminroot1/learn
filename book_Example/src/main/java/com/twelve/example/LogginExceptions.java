package com.twelve.example;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

class loggingException extends Exception{
    private static Logger logger =Logger.getLogger("LoggingException");

    public loggingException() {
        StringWriter trace  = new StringWriter();
        printStackTrace(new PrintWriter(trace));
       logger.severe(trace.toString());
    }
}
public class LogginExceptions {
    public static void main(String[] args) {
        try {
            throw new loggingException();
        }catch (loggingException e){
            System.err.println("Caught "+e);
        }
        try {
            throw new loggingException();
        }catch (loggingException e){
            System.err.println("Caught "+e);
        }
        try {
            throw new loggingException();
        }catch (loggingException e){
            System.err.println("Caught "+e);
        }
    }
}
