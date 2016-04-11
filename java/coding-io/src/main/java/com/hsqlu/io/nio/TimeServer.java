package com.hsqlu.io.nio;

/**
 * Created: 2016/4/9.
 * Author: Qiannan Lu
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {

            }

        }
        MultiplexerTimeServer timerServer = new MultiplexerTimeServer(port);
    }
}
