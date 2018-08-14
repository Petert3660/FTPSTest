package com.blinx.FtpManager;

import com.blinx.datamodels.Server;
import com.opencsv.CSVReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPSClient;

public class FtpManager extends Thread {

    Server server;

    private final String targetDirectory = "name of target directory";

    public FtpManager(Server server) {
        this.server = server;
    }

    @Override
    public void run() throws RuntimeException {

        InputStream in = null;

        try {
            FTPSClient ftpsClient = new FTPSClient();
            try {
                ftpsClient.connect(server.getHostName(), 21);
                if (!ftpsClient.login(server.getFtpUserId(), server.getFtpPassword())) {
                    throw new RuntimeException(ftpsClient.getReplyString());
                }
                if (!ftpsClient.changeWorkingDirectory("in")) {
                    throw new RuntimeException(ftpsClient.getReplyString());
                }

                for (String directory : server.getDirectories()) {
                    FTPFile[] files = ftpsClient.listFiles(directory);
                    for (FTPFile file : files) {
                        in = ftpsClient.retrieveFileStream(file.getName());
                        CSVReader csvReader = new CSVReader(new InputStreamReader(in));
                        saveToDatabase(csvReader.readAll());
                        ftpsClient.storeFile(targetDirectory, in);
                        ftpsClient.deleteFile(directory + "/" + file.getName());
                        updateLogFile(directory + "/" + file.getName());
                    }
                    if (!ftpsClient.logout()) {
                        throw new RuntimeException(ftpsClient.getReplyString());
                    }
                }
            } finally {
                if (in != null) {
                    in.close();
                }
                ftpsClient.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveToDatabase(List<String[]> input) {
        // Save input from file to new database table
    }

    private void updateLogFile(String name) {
        // Update the log file to the effect that the operation is complete for the named file
    }
}
