package org.iiitb.service;

import org.iiitb.service.impl.FileServiceImpl;

import java.io.InputStream;

public interface FileService {
    static FileService fileService = new FileServiceImpl();
    boolean upload(InputStream uploadedInputStream, String fileName);

    boolean deletePhoto(String fileName);

    boolean rename(String path);
}
