package com.blinx.datamodels;

import java.util.ArrayList;

public class Server {

    private String hostName;
    private String ftpUserId;
    private String ftpPassword;
    private ArrayList<String> directories;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getFtpUserId() {
        return ftpUserId;
    }

    public void setFtpUserId(String ftpUserId) {
        this.ftpUserId = ftpUserId;
    }

    public String getFtpPassword() {
        return ftpPassword;
    }

    public void setFtpPassword(String ftpPassword) {
        this.ftpPassword = ftpPassword;
    }

    public ArrayList<String> getDirectories() {
        return directories;
    }

    public void setDirectories(ArrayList<String> directories) {
        this.directories = directories;
    }
}
