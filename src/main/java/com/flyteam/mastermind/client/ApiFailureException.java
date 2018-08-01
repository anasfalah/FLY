package com.flyteam.mastermind.client;

/**
 *
 * @author jtapiat
 */
public class ApiFailureException extends Exception {

    public ApiFailureException() {
    }

    public ApiFailureException(String string) {
        super(string);
    }

    public ApiFailureException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public ApiFailureException(Throwable thrwbl) {
        super(thrwbl);
    }

    public ApiFailureException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
    
}
