package ru.kpfu.itis.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;

public class ImageUtil {

    public static String makeFile(Part part, String fileName, HttpServletRequest req) {
        try {
            String filePath = req.getServletContext().getRealPath("/") + "image";
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdirs(); // создадим директорию, если она отсутствует
            }
            File outputFile = new File(dir, fileName);
            try (InputStream inputStream = part.getInputStream();
                 OutputStream outputStream = new FileOutputStream(outputFile)) {
                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
            return req.getServletContext().getContextPath() + "/image/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
