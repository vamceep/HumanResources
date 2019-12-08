package org.iiitb.service.impl;

import org.iiitb.service.FileService;

import java.io.*;
import java.util.Properties;

public class FileServiceImpl implements FileService {

    @Override
    public boolean upload(InputStream uploadedInputStream, String fileName) {
        int count = 0;
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            String uploadPath = properties.getProperty("upload.path");

            String uploadedFileLocation = uploadPath + "/" + fileName;

            OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                count++;
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return count != 0;
    }

    public boolean deletePhoto(String fileName){
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = properties.getProperty("upload.path");
        path = path + "/" + fileName;
        File file = new File(path);
        return file.delete();
    }

    public boolean rename(String fileName) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = properties.getProperty("upload.path") +"/";
//        String path = "/Users/vamceep/IdeaProjects/HumanResours/src/main/webapp/images/employee/";
        File f1 = new File(path+fileName);
        File f2 = new File(path+fileName.substring(1));
        return f1.renameTo(f2);
    }
}
