/*
 * TestCompress.java
 * JMUnit based test
 *
 * Created on Dec 26, 2012, 5:36:01 PM
 */
package test;

import com.google.zxing.Result;
import com.makeit.zxingwriter.ZxingHelper;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import jmunit.framework.cldc10.*;

/**
 * @author makeit
 */
public class TestRead extends TestCase {

    ZxingHelper reader = new ZxingHelper();
    private static final String rootDir = "file:///e:/barcode/";

    public TestRead() {
        //The first parameter of inherited constructor is the number of test cases
        super(1, "TestRead");
    }

    public void setUp() {
    }

    public void tearDown() {
    }
    String content = "好";

    public void test(int testNumber) throws Throwable {

        StringBuffer sb = new StringBuffer();

        FileConnection root = null;
        FileConnection file = null;
        try {

            root = (FileConnection) Connector.open(rootDir);
            Enumeration en = root.list("*.png", false);
            while (en.hasMoreElements()) {
                long start = System.currentTimeMillis();
                String filename = rootDir + en.nextElement();
                log("decoding " + filename);
                Result result = reader.decode(filename);
                long end = System.currentTimeMillis();
                if (result != null) {
                    log(filename + "\t" + result.getText());
                    sb.append(filename).append("\t").append(result.getText()).append("\t")
                            .append(result.getBarcodeFormat().toString()).append("\t").append(end - start).append("\n");
                }
            }



            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(Runtime.getRuntime().freeMemory());
            System.out.println(Runtime.getRuntime().totalMemory());
            System.out.println(sb.toString());
            saveTestLog(sb, "");




        } catch (IOException ex) {
            log("open failed： " + ex.getMessage());
        } finally {
            if (root != null) {
                try {
                    root.close();
                } catch (IOException ex) {
                }
            }
            if (file != null) {
                try {
                    file.close();
                } catch (IOException ex) {
                }
            }
        }





    }

    int saveTestLog(StringBuffer sb, String fileName) {
        FileConnection fc = null;
        String name = fileName == null ? "" : fileName;
        int len = 0;
        try {
            if (name.length() > 16) {
                name = name.substring(0, 16);
            }

            name = rootDir + name + System.currentTimeMillis() + ".txt";
            fc = (FileConnection) Connector.open(name);

            if (!fc.exists()) {
                fc.create();
            }

            DataOutputStream dos = fc.openDataOutputStream();
            dos.writeUTF(sb.toString());

            dos.close();
            log(name + " saved");
        } catch (IOException ex) {
            log("保存失败： " + ex.getMessage());
        } finally {
            if (fc != null) {
                try {
                    fc.close();
                } catch (IOException ex) {
                }
            }
        }
        return len;
    }

    private void log(String msg) {
        System.out.println(msg);
    }
}
