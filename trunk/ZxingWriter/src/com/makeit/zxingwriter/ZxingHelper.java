/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makeit.zxingwriter;

import pngencoder.PngEncoder;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2me.LCDUIImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import th.co.yellowpages.javame.PNGEncoder;

/**
 *
 * @author makeit
 */
public class ZxingHelper {

    private static final String roodDir = "file:///e:/barcode/";

    public ZxingHelper() {
    }

    public Result decode(String path) {
        try {
            if (path.indexOf("file:///") >= 0) {
                return decode(getImageFromFile(path));
            } else {
                return decode(Image.createImage(Class.class.getResourceAsStream(path)));
            }
        } catch (IOException ex) {
            log(ex.toString());
        }
        return null;
    }

    /*
     * if pass fc.openDataInputStream() directly to Image.createImage
     * will cause too many file handler exception
     */
    private Image getImageFromFile(String fileName) {
        Image image = null;
        FileConnection fc = null;
        try {

            fc = (FileConnection) Connector.open(fileName);

            if (!fc.exists()) {
                return null;
            }
            DataInputStream dis = fc.openDataInputStream();

            image = Image.createImage(dis);
            dis.close();
            fc.close();
        } catch (IOException ex) {
            log("open failed： " + ex.getMessage());
        } finally {
            if (fc != null) {
                try {
                    fc.close();
                } catch (IOException ex) {
                }
            }
        }
        return image;
    }

    public Result decode(Image capturedImage) {
        Result result = null;
        if (capturedImage == null) {
            return null;
        }
        try {
//            int[] pixels = new int[capturedImage.getWidth() * capturedImage.getHeight()];
//
//            capturedImage.getRGB(pixels, 0, capturedImage.getWidth(), 0, 0, capturedImage.getWidth(), capturedImage.getHeight());

            //RGBLuminanceSource source = new RGBLuminanceSource(capturedImage.getWidth(), capturedImage.getHeight(), pixels);
            // source=new PlanarYUVLuminanceSource(yuvData, dataWidth, dataHeight, left, top, width, height, midletPaused);
            LuminanceSource source = new LCDUIImageLuminanceSource(capturedImage);
            //BinaryBitmap bitmap = new BinaryBitmap(new GlobalHistogramBinarizer(source));
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Reader reader = new QRCodeReader();
            Hashtable hints = new Hashtable();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            result = reader.decode(bitmap, hints);
        } catch (NotFoundException ex) {
            log(ex.toString());
        } catch (ChecksumException ex) {
            log(ex.toString());
        } catch (FormatException ex) {
            log(ex.toString());
        }
        return result;
    }

    public Image writeQR(String content, int color) {
        Image image = null;
        try {
            BarcodeFormat format = BarcodeFormat.QR_CODE;
            Hashtable hints = new Hashtable();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix byteMatrix = new MultiFormatWriter().encode(content, format, 240, 240, hints);
            image = byteMatrix.toImage(color);
        } catch (WriterException ex) {
            log(ex.toString());
        }

        return image;
    }

    public void save2pngUncompress(Image image, String fileName) {
        FileConnection fc = null;
//        String name = fileName;
        String name = "";
        try {
            if (name.length() > 16) {
                name = name.substring(0, 16);
            }

            fc = (FileConnection) Connector.open(roodDir);
            if (!fc.exists()) {
                fc.mkdir();
                log(roodDir + " created");
            }
            fc.close();
            name = roodDir + name + System.currentTimeMillis() + ".png";
            fc = (FileConnection) Connector.open(name);

            if (!fc.exists()) {
                fc.create();
            }

            byte[] data = PNGEncoder.toPNG(image, false);

            DataOutputStream dos = fc.openDataOutputStream();
            dos.write(data);
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
    }

    public int save2pngCompress(Image image, String fileName, int compressLevel, PngEncoder.Filter filter) {
        FileConnection fc = null;
        String name = fileName;
        int len = 0;
        try {
            if (name.length() > 16) {
                name = name.substring(0, 16);
            }

            fc = (FileConnection) Connector.open(roodDir);
            if (!fc.exists()) {
                fc.mkdir();
                log(roodDir + " created");
            }
            fc.close();
            name = roodDir + name + System.currentTimeMillis() + ".png";
            fc = (FileConnection) Connector.open(name);

            if (!fc.exists()) {
                fc.create();
            }


            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PngEncoder encoder = new PngEncoder(image, false, filter, compressLevel);
            encoder.encode(baos);
            baos.flush();

//            byte[] data = PNGEncoder.toPNG(image, false);

            DataOutputStream dos = fc.openDataOutputStream();
            byte[] data = baos.toByteArray();
            len = data.length;
            dos.write(data);
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

    Image getImageWithPoints(Image srcImage, ResultPoint[] points) {
        Image image = Image.createImage(srcImage.getWidth(), srcImage.getHeight());
        Graphics g = image.getGraphics();
        g.drawImage(srcImage, 0, 0, Graphics.TOP | Graphics.LEFT);
        g.setColor(0x00, 0xff, 0x00);
        for (int i = 0; i < points.length; i++) {
            int next = i + 1;
            if (next >= points.length) {
                next = 0;
            }
            g.drawLine((int) points[i].getX(), (int) points[i].getY(), 
                    (int) points[next].getX(), (int) points[next].getY());
        }
        return image;
    }

    private void log(String msg) {
        System.out.println(msg);
    }
}
