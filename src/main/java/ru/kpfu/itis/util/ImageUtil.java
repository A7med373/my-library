package ru.kpfu.itis.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;

public class ImageUtil {

    public static String makeFile(Part part, String fileName, HttpServletRequest req) {
        try {
            String filePath = req.getServletContext().getRealPath("/") + "image";
            InputStream inputStream = part.getInputStream();
            OutputStream outputStream = new FileOutputStream(filePath + File.separator + fileName);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            outputStream.write(buffer);
            outputStream.close();
            inputStream.close();
            return req.getServletContext().getContextPath() + "/image/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
