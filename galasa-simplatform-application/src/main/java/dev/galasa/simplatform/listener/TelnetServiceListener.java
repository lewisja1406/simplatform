/*
 * Licensed Materials - Property of IBM
 * 
 * (c) Copyright IBM Corp. 2019.
 */
package dev.galasa.simplatform.listener;

import java.net.Socket;

import dev.galasa.simplatform.t3270.screens.IScreen;
import dev.galasa.simplatform.t3270.screens.SessionManagerLogon;
import dev.galasa.zos3270.internal.comms.NetworkServer;

public class TelnetServiceListener implements IListener {
    private Socket socket;

    public void run() {
        try {
            NetworkServer network = new NetworkServer(socket);

            IScreen screen = new SessionManagerLogon(network);
            while (true) {
                screen = screen.run();
                if (screen == null) {
                    break;
                }
            }

            socket.close();

        } catch (Exception e) {
            System.err.println("Stuff went really wrong");
            e.printStackTrace();
        }

    }

    public void setSocket(Socket socket) {
        this.socket = socket;

    }

}
