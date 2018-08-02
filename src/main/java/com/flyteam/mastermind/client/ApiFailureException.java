package com.flyteam.mastermind.client;

/**
 *
 * @author jtapiat
 */
public class ApiFailureException extends Exception {

    public ApiFailureException(String string) {
        super(string);
    }

    public ApiFailureException(Throwable thrwbl) {
        super(thrwbl);
    }

}
