package lesson3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("files/text.txt");
////        file.mkdirs();
//        file.createNewFile();
//        file.renameTo(new File("files/test2.txt"));
//
//        System.out.println(file.getPath());
//        System.out.println(file.getAbsolutePath());
//        System.out.println(file.getCanonicalPath());
//
//        System.out.println(file.exists());
//
//        String[] list = file.list();
//        System.out.println(Arrays.toString(list));
//
//        File[] list2 = file.listFiles((dir, name) -> name.startsWith("Bob"));
//        File[] list1 = file.listFiles(new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                return name.startsWith("Bob");
//            }
//        });
        try (InputStream is = new FileInputStream("files/text.txt")) {
//            byte[] byte1 = is.readAllBytes();
            int readByte;
            while ((readByte = is.read()) != -1) {
                System.out.print((char) readByte);
            }
            System.out.println();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try(OutputStream os = new FileOutputStream("files/test2.txt", true)) {
            byte[] sendBytes = "Hello \r\n Java".getBytes(StandardCharsets.UTF_8);
            os.write(sendBytes);
            for (byte sendByte : sendBytes) {
                os.write(sendByte);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        byte[] readBytes = new byte[10];
        try (InputStream is = new FileInputStream("files/text.txt")) {
            int countReadBytes;
            while ((countReadBytes=is.read(readBytes))>0) {
                System.out.println(new String(readBytes,0, countReadBytes));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        char[] readBytes1 = new char[10];
        try (InputStreamReader is = new InputStreamReader(new FileInputStream("files/text.txt"),StandardCharsets.UTF_8)) {
            int countReadBytes;
            while ((countReadBytes=is.read(readBytes1))>0) {
                System.out.println(new String(readBytes1,0, countReadBytes));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        byte[] readBytes2 = new byte[30];
        try (InputStream is = new BufferedInputStream(new FileInputStream("files/text.txt"))) {
            while ((is.read(readBytes2))!=-1) {
                System.out.println(new String(readBytes2));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
