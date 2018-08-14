package com.blinx;

import com.blinx.FtpManager.FtpManager;
import com.blinx.datamodels.Server;
import java.util.ArrayList;

public class Application {

    public static void main(String[] args){

        ArrayList<Server> listOfServers = new ArrayList<>();
        // Get list of servers and credentials from database and store in ArrayList<String>

        for (Server server : listOfServers) {

            FtpManager ftpManager = new FtpManager(server.getHostName(), server.getFtpUserId(), server.getFtpPassword(), server.getDirectories());
            try {
                ftpManager.start();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }
}
