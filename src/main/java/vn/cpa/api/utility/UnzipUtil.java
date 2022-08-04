package vn.cpa.api.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipUtil {

    private static final Logger logger = LoggerFactory.getLogger(UnzipUtil.class);

    public static boolean unzipFile(String zipFilePath) {
        String cmdStr = "unzip -d " + zipFilePath.replace(".zip", "") + " " + zipFilePath;

        System.out.println("==============================================================================================================");
        System.out.println(cmdStr);
        System.out.println("==============================================================================================================");

        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            cmdStr = "cmd /c " + cmdStr;
        }
        try {
            Process process = Runtime.getRuntime().exec(cmdStr);
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                return true;
            } else {
                logger.info(String.valueOf(output));
                logger.error("Can not resize file: " + zipFilePath);
                return false;
            }

        } catch (Exception e) {
            logger.warn("Unzip file error: ", e);
            return false;
        }
    }

    public static boolean unzipFile2(String zipFilePath, String pathDir, int fileSize) throws IOException {
        File destDir = new File(pathDir);
        byte[] buffer = new byte[fileSize];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry zipEntry = zis.getNextEntry();

        while (zipEntry != null) {
            File newFile = newFile(destDir, zipEntry);
            if (zipEntry.isDirectory()) {
                if (!newFile.isDirectory() && !newFile.mkdirs()) {
                    throw new IOException("Failed to create directory " + newFile);
                }
            } else {
                // fix for Windows-created archives
                File parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    throw new IOException("Failed to create directory " + parent);
                }

                // write file content
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();

        return true;
    }

    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }
}
