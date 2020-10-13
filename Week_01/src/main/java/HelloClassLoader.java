import java.io.*;

public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) {
        try {
            Object helloInstance = new HelloClassLoader().findClass("Hello").newInstance();
        } catch (InstantiationException e) {
            System.out.println("InstantiationException:" + e);
        } catch (IllegalAccessException e) {
            System.out.println("IllegalAccessException:" + e);
        }
    }

    @Override
    protected Class<?> findClass(String name) {
        byte[] data = new byte[0];
        try {
            data = readBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass("Hello", data, 0, data.length);
    }

    private byte[] readBytes() throws IOException {
        String path = "D:\\GeekTimeHomeWork\\JAVA-000\\Week_01\\src\\main\\java\\Hello.xlass";
        File classFile = new File(path);
        InputStream fis = null;
        ByteArrayOutputStream bos = null;
        byte[] bytes = new byte[1024];
        byte[] readBytes = null;

        try {
            bos = new ByteArrayOutputStream();
            fis = new FileInputStream(classFile);
            int len;

            while ((len = fis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
            readBytes = bos.toByteArray();
            for (int i = 0; i < readBytes.length; i++) {
                Byte t = readBytes[i];
                Byte t1 = (byte) (255 - t);
                readBytes[i] = t1;
            }

        } catch (Exception e) {
            System.out.println("error happened!");
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (bos != null) {
                bos.close();
            }
        }
        return readBytes;
    }
}