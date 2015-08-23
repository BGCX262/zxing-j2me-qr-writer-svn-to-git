/*
 * TestCompress.java
 * JMUnit based test
 *
 * Created on Dec 26, 2012, 5:36:01 PM
 */
package test;

import pngencoder.PngEncoder;
import com.makeit.zxingwriter.ZxingHelper;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.Image;
import jmunit.framework.cldc10.*;

/**
 * @author makeit
 */
public class TestCompress extends TestCase {

    ZxingHelper writor = new ZxingHelper();

    public TestCompress() {
        //The first parameter of inherited constructor is the number of test cases
        super(1, "TestCompress");
    }

    public void setUp() {
    }

    public void tearDown() {
    }
    String content = "好";

    public void test(int testNumber) throws Throwable {
        long start = System.currentTimeMillis();
        Image image = writor.writeQR(content, 0x000000);
        long end = System.currentTimeMillis();
        log("gen barcode time: " + (end - start));
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <= 9; i++) {
            start = System.currentTimeMillis();
            int len = writor.save2pngCompress(image, "_" + i + "_nofilter_", i, null);
            end = System.currentTimeMillis();
//            log("write time: " + (end - start));
            sb.append(i).append("\t").append("nofilter").append("\t").append(end - start).append("\t").append(len).append("\n");

            start = System.currentTimeMillis();
            len = writor.save2pngCompress(image, "_" + i + "_AVERAGE_FILTER_", i, PngEncoder.AVERAGE_FILTER);
            end = System.currentTimeMillis();
//            log("write time: " + (end - start));
            sb.append(i).append("\t").append("_AVERAGE_FILTER_").append("\t").append(end - start).append("\t").append(len).append("\n");

            start = System.currentTimeMillis();
            len = writor.save2pngCompress(image, "_" + i + "_PAETH_FILTER_", i, PngEncoder.PAETH_FILTER);
            end = System.currentTimeMillis();
//            log("write time: " + (end - start));
            sb.append(i).append("\t").append("_PAETH_FILTER_").append("\t").append(end - start).append("\t").append(len).append("\n");

            start = System.currentTimeMillis();
            len = writor.save2pngCompress(image, "_" + i + "_SUB_FILTER_", i, PngEncoder.SUB_FILTER);
            end = System.currentTimeMillis();
//            log("write time: " + (end - start));
            sb.append(i).append("\t").append("_SUB_FILTER_").append("\t").append(end - start).append("\t").append(len).append("\n");

            start = System.currentTimeMillis();
            len = writor.save2pngCompress(image, "_" + i + "_UP_FILTER_", i, PngEncoder.UP_FILTER);
            end = System.currentTimeMillis();
//            log("write time: " + (end - start));
            sb.append(i).append("\t").append("_UP_FILTER_").append("\t").append(end - start).append("\t").append(len).append("\n");
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(Runtime.getRuntime().freeMemory());
        System.out.println(Runtime.getRuntime().totalMemory());
        System.out.println(sb.toString());
        saveTestLog(sb, "");

    }
    private static final String roodDir = "file:///e:/barcode/";

    int saveTestLog(StringBuffer sb, String fileName) {
        FileConnection fc = null;
        String name = fileName == null ? "" : fileName;
        int len = 0;
        try {
            if (name.length() > 16) {
                name = name.substring(0, 16);
            }

            name = roodDir + name + System.currentTimeMillis() + ".txt";
            fc = (FileConnection) Connector.open(name);

            if (!fc.exists()) {
                fc.create();
            }

            DataOutputStream dos = fc.openDataOutputStream();
            dos.writeChars(sb.toString());

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
