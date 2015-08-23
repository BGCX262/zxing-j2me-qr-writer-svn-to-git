/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makeit.zxingwriter;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.j2me.LCDUIImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.jack.ColorfulItem;
import example.chooser.ColorChooser;
import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.VideoControl;
import javax.microedition.midlet.*;

/**
 * @author jack
 */
public class ZxingWriter extends MIDlet implements CommandListener, ItemCommandListener, ItemStateListener {
    
    final static int COMPRESSLEVEL = 3;
    private boolean midletPaused = false;
    ZxingHelper helper;
    private Image barcodeImage;
    private VideoCanvas canvas;
    private Player player;
    private VideoControl videoControl;
    private Command takePhotoCommand2;
//<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Command exitCommand;
    private Command writeCommand;
    private Command readCommand;
    private Command saveCommand;
    private Command okColorChooserCommand;
    private Command setColorCommand;
    private Command aboutCommand;
    private Command backCommand;
    private Command okCommand;
    private Command takePhotoCommand;
    private Form form;
    private TextField textField;
    private ImageItem imageItem;
    private StringItem messageItem;
    private ColorfulItem colorfulItem;
    private ColorChooser colorChooser;
    private Form aboutForm;
    private StringItem aboutStringItem;
    private Ticker ticker;
//</editor-fold>//GEN-END:|fields|0|

    /**
     * The HelloMIDlet constructor.
     */
    public ZxingWriter() {
        helper = new ZxingHelper();
    }

//<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
//</editor-fold>//GEN-END:|methods|0|
//<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initializes the application. It is called only once when the MIDlet is
     * started. The method is called before the
     * <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
//</editor-fold>//GEN-END:|0-initialize|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, getForm());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
//</editor-fold>//GEN-END:|3-startMIDlet|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
//</editor-fold>//GEN-END:|4-resumeMIDlet|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The
     * <code>display</code> instance is taken from
     * <code>getDisplay</code> method. This method is used by all actions in the
     * design for switching displayable.
     *
     * @param alert the Alert which is temporarily set to the display;
     * if <code>null</code>, then <code>nextDisplayable</code> is set
     * immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
//</editor-fold>//GEN-END:|5-switchDisplayable|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a
     * particular displayable.
     *
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == aboutForm) {//GEN-BEGIN:|7-commandAction|1|50-preAction
            if (command == okCommand) {//GEN-END:|7-commandAction|1|50-preAction
                // write pre-action user code here
                switchDisplayable(null, getForm());//GEN-LINE:|7-commandAction|2|50-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|3|47-preAction
        } else if (displayable == colorChooser) {
            if (command == backCommand) {//GEN-END:|7-commandAction|3|47-preAction
                // write pre-action user code here
                switchDisplayable(null, getForm());//GEN-LINE:|7-commandAction|4|47-postAction
                // write post-action user code here
                getDisplay().setCurrentItem(getTextField());
            } else if (command == okColorChooserCommand) {//GEN-LINE:|7-commandAction|5|40-preAction
                getColorfulItem().setForeColor(getColorChooser().getColor());
                getDisplay().setCurrent(getForm());
                getDisplay().setCurrentItem(getTextField());
//GEN-LINE:|7-commandAction|6|40-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|7|43-preAction
        } else if (displayable == form) {
            if (command == aboutCommand) {//GEN-END:|7-commandAction|7|43-preAction
                // write pre-action user code here
                switchDisplayable(null, getAboutForm());//GEN-LINE:|7-commandAction|8|43-postAction
                // write post-action user code here
            } else if (command == exitCommand) {//GEN-LINE:|7-commandAction|9|19-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|10|19-postAction
                // write post-action user code here
            } else if (command == readCommand) {//GEN-LINE:|7-commandAction|11|29-preAction
                // write pre-action user code here
                StringBuffer sb = new StringBuffer();
                if (barcodeImage != null) {
                    Result result = helper.decode(barcodeImage);
                    if (result != null) {
                        sb.append("内容: ").append(result.getText()).append("\r\n");
                        
                        
                        getImageItem().setImage(helper.getImageWithPoints(barcodeImage,
                                result.getResultPoints()));
                        
                    } else {
                        sb.append("内容: not found");
                    }
                } else {
                    sb.append("请先生成条码");
                }
                log(sb.toString());
//GEN-LINE:|7-commandAction|12|29-postAction
                
            } else if (command == saveCommand) {//GEN-LINE:|7-commandAction|13|33-preAction
                if (barcodeImage != null) {
                    helper.save2pngCompress(barcodeImage, "", COMPRESSLEVEL, null);
                    log("保存成功。");
                }
//GEN-LINE:|7-commandAction|14|33-postAction
                // write post-action user code here
            } else if (command == takePhotoCommand) {//GEN-LINE:|7-commandAction|15|59-preAction
                try {
                    if (getPlayer().getState() != Player.STARTED) {
                        getPlayer().start();
                    }
                } catch (MediaException ex) {
                    ex.printStackTrace();
                }
                switchDisplayable(null, getVideoCanvas());
//GEN-LINE:|7-commandAction|16|59-postAction
                // write post-action user code here
            } else if (command == writeCommand) {//GEN-LINE:|7-commandAction|17|26-preAction
                // write pre-action user code here
                String value = textField.getString();
                if (value != null && !"".equals(value)) {
                    barcodeImage = helper.writeQR(value, getColorfulItem().getForeColor());
                    getImageItem().setImage(barcodeImage);
                    log("已成功生成。");
                } else {
                    log("请输入内容。");
                }

//GEN-LINE:|7-commandAction|18|26-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|19|7-postCommandAction
        }//GEN-END:|7-commandAction|19|7-postCommandAction
        // write post-action user code here
        else if (displayable == canvas) {
            System.out.println("cmd: " + command.getLabel());
            if (command == takePhotoCommand2) {
                Result result = null;
                String msg = null;
                try {                    
                    if (getPlayer().getState() != Player.STARTED) {
                        getPlayer().start();
                    }
                    MultimediaManager.setFocus(getPlayer());
                    
                    byte[] snapshot = getVideoCanvas().takeSnapshot();
                    barcodeImage = Image.createImage(snapshot, 0, snapshot.length);
                    
                    getImageItem().setImage(barcodeImage);
                    
                    LuminanceSource source = new LCDUIImageLuminanceSource(barcodeImage);
                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                    Reader reader = new MultiFormatReader();
                    result = reader.decode(bitmap);
                    
                } catch (MediaException ex) {
                    ex.printStackTrace();
                    msg = ex.getMessage();
                } catch (NotFoundException ex) {
                    ex.printStackTrace();
                    msg = ex.getMessage();
                } catch (ChecksumException ex) {
                    ex.printStackTrace();
                    msg = ex.getMessage();
                } catch (FormatException ex) {
                    ex.printStackTrace();
                    msg = ex.getMessage();
                } catch (RuntimeException ex) {
                    ex.printStackTrace();
                    msg = ex.getMessage();
                }
                
                Alert alert = new Alert("result", "", null, AlertType.CONFIRMATION);
                
                if (result != null) {
                    System.out.println("result: " + result.getText());
                    alert.setString(result.getText());
                } else if (msg != null) {
                    alert.setString("error: " + msg);
                } else {
                    alert.setString("not found.");
                }
                switchDisplayable(alert, getForm());
            }
        }
    }//GEN-BEGIN:|7-commandAction|20|
//</editor-fold>//GEN-END:|7-commandAction|20|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|18-getter|0|18-preInit
    /**
     * Returns an initialized instance of exitCommand component.
     *
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|18-getter|0|18-preInit
            // write pre-init user code here
            exitCommand = new Command("\u9000\u51FA", Command.EXIT, 0);//GEN-LINE:|18-getter|1|18-postInit
            // write post-init user code here
        }//GEN-BEGIN:|18-getter|2|
        return exitCommand;
    }
//</editor-fold>//GEN-END:|18-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: form ">//GEN-BEGIN:|14-getter|0|14-preInit
    /**
     * Returns an initialized instance of form component.
     *
     * @return the initialized component instance
     */
    public Form getForm() {
        if (form == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
            form = new Form("\u4E8C\u7EF4\u7801\u751F\u6210\u5DE5\u5177", new Item[]{getTextField(), getColorfulItem(), getMessageItem(), getImageItem()});//GEN-BEGIN:|14-getter|1|14-postInit
            form.addCommand(getExitCommand());
            form.addCommand(getWriteCommand());
            form.addCommand(getReadCommand());
            form.addCommand(getSaveCommand());
            form.addCommand(getTakePhotoCommand());
            form.addCommand(getAboutCommand());
            form.setCommandListener(this);//GEN-END:|14-getter|1|14-postInit
            // write post-init user code here     
            form.setItemStateListener(this);
        }//GEN-BEGIN:|14-getter|2|
        return form;
    }
//</editor-fold>//GEN-END:|14-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: writeCommand ">//GEN-BEGIN:|25-getter|0|25-preInit
    /**
     * Returns an initialized instance of writeCommand component.
     *
     * @return the initialized component instance
     */
    public Command getWriteCommand() {
        if (writeCommand == null) {//GEN-END:|25-getter|0|25-preInit
            // write pre-init user code here
            writeCommand = new Command("\u751F\u6210", Command.ITEM, 0);//GEN-LINE:|25-getter|1|25-postInit
            // write post-init user code here
        }//GEN-BEGIN:|25-getter|2|
        return writeCommand;
    }
//</editor-fold>//GEN-END:|25-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField ">//GEN-BEGIN:|24-getter|0|24-preInit
    /**
     * Returns an initialized instance of textField component.
     *
     * @return the initialized component instance
     */
    public TextField getTextField() {
        if (textField == null) {//GEN-END:|24-getter|0|24-preInit
            // write pre-init user code here
            textField = new TextField("\u5185\u5BB9:", "", 1024, TextField.ANY);//GEN-LINE:|24-getter|1|24-postInit
            // write post-init user code here
        }//GEN-BEGIN:|24-getter|2|
        return textField;
    }
//</editor-fold>//GEN-END:|24-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: imageItem ">//GEN-BEGIN:|27-getter|0|27-preInit
    /**
     * Returns an initialized instance of imageItem component.
     *
     * @return the initialized component instance
     */
    public ImageItem getImageItem() {
        if (imageItem == null) {//GEN-END:|27-getter|0|27-preInit
            // write pre-init user code here
            imageItem = new ImageItem("\u56FE\u7247", null, ImageItem.LAYOUT_DEFAULT, "<Missing Image>");//GEN-LINE:|27-getter|1|27-postInit
            // write post-init user code here
            imageItem.setLayout(ImageItem.LAYOUT_NEWLINE_BEFORE);
        }//GEN-BEGIN:|27-getter|2|
        return imageItem;
    }
//</editor-fold>//GEN-END:|27-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: readCommand ">//GEN-BEGIN:|28-getter|0|28-preInit
    /**
     * Returns an initialized instance of readCommand component.
     *
     * @return the initialized component instance
     */
    public Command getReadCommand() {
        if (readCommand == null) {//GEN-END:|28-getter|0|28-preInit
            // write pre-init user code here
            readCommand = new Command("\u8BFB\u53D6", Command.ITEM, 0);//GEN-LINE:|28-getter|1|28-postInit
            // write post-init user code here
        }//GEN-BEGIN:|28-getter|2|
        return readCommand;
    }
//</editor-fold>//GEN-END:|28-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: saveCommand ">//GEN-BEGIN:|32-getter|0|32-preInit
    /**
     * Returns an initialized instance of saveCommand component.
     *
     * @return the initialized component instance
     */
    public Command getSaveCommand() {
        if (saveCommand == null) {//GEN-END:|32-getter|0|32-preInit
            // write pre-init user code here
            saveCommand = new Command("\u4FDD\u5B58", Command.ITEM, 0);//GEN-LINE:|32-getter|1|32-postInit
            // write post-init user code here
        }//GEN-BEGIN:|32-getter|2|
        return saveCommand;
    }
//</editor-fold>//GEN-END:|32-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: messageItem ">//GEN-BEGIN:|34-getter|0|34-preInit
    /**
     * Returns an initialized instance of messageItem component.
     *
     * @return the initialized component instance
     */
    public StringItem getMessageItem() {
        if (messageItem == null) {//GEN-END:|34-getter|0|34-preInit
            // write pre-init user code here
            messageItem = new StringItem("\u6D88\u606F:", "");//GEN-LINE:|34-getter|1|34-postInit
            // write post-init user code here
            messageItem.setLayout(StringItem.LAYOUT_NEWLINE_AFTER);
        }//GEN-BEGIN:|34-getter|2|
        return messageItem;
    }
//</editor-fold>//GEN-END:|34-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: colorfulItem ">//GEN-BEGIN:|35-getter|0|35-preInit
    /**
     * Returns an initialized instance of colorfulItem component.
     *
     * @return the initialized component instance
     */
    public ColorfulItem getColorfulItem() {
        if (colorfulItem == null) {//GEN-END:|35-getter|0|35-preInit
            // write pre-init user code here
            colorfulItem = new ColorfulItem("\u989C\u8272", "\u989C\u8272");//GEN-BEGIN:|35-getter|1|35-postInit
            colorfulItem.addCommand(getSetColorCommand());
            colorfulItem.setItemCommandListener(this);
            colorfulItem.setDefaultCommand(getSetColorCommand());//GEN-END:|35-getter|1|35-postInit
            
            
            
        }//GEN-BEGIN:|35-getter|2|
        return colorfulItem;
    }
//</editor-fold>//GEN-END:|35-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Items ">//GEN-BEGIN:|17-itemCommandAction|0|17-preItemCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a
     * particular item.
     *
     * @param command the Command that was invoked
     * @param displayable the Item where the command was invoked
     */
    public void commandAction(Command command, Item item) {//GEN-END:|17-itemCommandAction|0|17-preItemCommandAction
        // write pre-action user code here
        System.out.println(command.getLabel());
        if (item == colorfulItem) {//GEN-BEGIN:|17-itemCommandAction|1|37-preAction
            if (command == setColorCommand) {//GEN-END:|17-itemCommandAction|1|37-preAction
                getDisplay().setCurrent(getColorChooser());
                switchDisplayable(null, getColorChooser());//GEN-LINE:|17-itemCommandAction|2|37-postAction
                // write post-action user code here
            }//GEN-BEGIN:|17-itemCommandAction|3|17-postItemCommandAction
        }//GEN-END:|17-itemCommandAction|3|17-postItemCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|17-itemCommandAction|4|
//</editor-fold>//GEN-END:|17-itemCommandAction|4|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: setColorCommand ">//GEN-BEGIN:|36-getter|0|36-preInit
    /**
     * Returns an initialized instance of setColorCommand component.
     *
     * @return the initialized component instance
     */
    public Command getSetColorCommand() {
        if (setColorCommand == null) {//GEN-END:|36-getter|0|36-preInit
            // write pre-init user code here
            setColorCommand = new Command("\u53D6\u8272", Command.ITEM, 0);//GEN-LINE:|36-getter|1|36-postInit
            // write post-init user code here
        }//GEN-BEGIN:|36-getter|2|
        return setColorCommand;
    }
//</editor-fold>//GEN-END:|36-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okColorChooserCommand ">//GEN-BEGIN:|39-getter|0|39-preInit
    /**
     * Returns an initialized instance of okColorChooserCommand component.
     *
     * @return the initialized component instance
     */
    public Command getOkColorChooserCommand() {
        if (okColorChooserCommand == null) {//GEN-END:|39-getter|0|39-preInit
            // write pre-init user code here
            okColorChooserCommand = new Command("\u786E\u5B9A", Command.ITEM, 0);//GEN-LINE:|39-getter|1|39-postInit
            // write post-init user code here
        }//GEN-BEGIN:|39-getter|2|
        return okColorChooserCommand;
    }
//</editor-fold>//GEN-END:|39-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: colorChooser ">//GEN-BEGIN:|38-getter|0|38-preInit
    /**
     * Returns an initialized instance of colorChooser component.
     *
     * @return the initialized component instance
     */
    public ColorChooser getColorChooser() {
        if (colorChooser == null) {//GEN-END:|38-getter|0|38-preInit
            // write pre-init user code here
            colorChooser = new ColorChooser(false);//GEN-BEGIN:|38-getter|1|38-postInit
            colorChooser.setTitle("\u9009\u62E9\u989C\u8272");
            colorChooser.addCommand(getOkColorChooserCommand());
            colorChooser.addCommand(getBackCommand());
            colorChooser.setCommandListener(this);
            colorChooser.setPaletteIndex(0);//GEN-END:|38-getter|1|38-postInit
            
            colorChooser.setIsColor(getDisplay().isColor());
            colorChooser.setColor(getColorfulItem().getForeColor());
            
        }//GEN-BEGIN:|38-getter|2|
        return colorChooser;
    }
//</editor-fold>//GEN-END:|38-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: aboutCommand ">//GEN-BEGIN:|42-getter|0|42-preInit
    /**
     * Returns an initialized instance of aboutCommand component.
     *
     * @return the initialized component instance
     */
    public Command getAboutCommand() {
        if (aboutCommand == null) {//GEN-END:|42-getter|0|42-preInit
            // write pre-init user code here
            aboutCommand = new Command("\u5173\u4E8E", Command.ITEM, 0);//GEN-LINE:|42-getter|1|42-postInit
            // write post-init user code here
        }//GEN-BEGIN:|42-getter|2|
        return aboutCommand;
    }
//</editor-fold>//GEN-END:|42-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand ">//GEN-BEGIN:|46-getter|0|46-preInit
    /**
     * Returns an initialized instance of backCommand component.
     *
     * @return the initialized component instance
     */
    public Command getBackCommand() {
        if (backCommand == null) {//GEN-END:|46-getter|0|46-preInit
            // write pre-init user code here
            backCommand = new Command("\u8FD4\u56DE", Command.BACK, 0);//GEN-LINE:|46-getter|1|46-postInit
            // write post-init user code here
        }//GEN-BEGIN:|46-getter|2|
        return backCommand;
    }
//</editor-fold>//GEN-END:|46-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|49-getter|0|49-preInit
    /**
     * Returns an initialized instance of okCommand component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand() {
        if (okCommand == null) {//GEN-END:|49-getter|0|49-preInit
            // write pre-init user code here
            okCommand = new Command("\u786E\u5B9A", Command.OK, 0);//GEN-LINE:|49-getter|1|49-postInit
            // write post-init user code here
        }//GEN-BEGIN:|49-getter|2|
        return okCommand;
    }
//</editor-fold>//GEN-END:|49-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: aboutForm ">//GEN-BEGIN:|48-getter|0|48-preInit
    /**
     * Returns an initialized instance of aboutForm component.
     *
     * @return the initialized component instance
     */
    public Form getAboutForm() {
        if (aboutForm == null) {//GEN-END:|48-getter|0|48-preInit
            // write pre-init user code here
            aboutForm = new Form("\u5173\u4E8E", new Item[]{getAboutStringItem()});//GEN-BEGIN:|48-getter|1|48-postInit
            aboutForm.setTicker(getTicker());
            aboutForm.addCommand(getOkCommand());
            aboutForm.setCommandListener(this);//GEN-END:|48-getter|1|48-postInit
            // write post-init user code here
        }//GEN-BEGIN:|48-getter|2|
        return aboutForm;
    }
//</editor-fold>//GEN-END:|48-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: ticker ">//GEN-BEGIN:|51-getter|0|51-preInit
    /**
     * Returns an initialized instance of ticker component.
     *
     * @return the initialized component instance
     */
    public Ticker getTicker() {
        if (ticker == null) {//GEN-END:|51-getter|0|51-preInit
            // write pre-init user code here
            ticker = new Ticker("\u8FD9\u662F\u4E00\u4E2A\u4E8C\u7EF4\u7801\u751F\u6210\u5DE5\u5177");//GEN-LINE:|51-getter|1|51-postInit
            // write post-init user code here
        }//GEN-BEGIN:|51-getter|2|
        return ticker;
    }
//</editor-fold>//GEN-END:|51-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: aboutStringItem ">//GEN-BEGIN:|52-getter|0|52-preInit
    /**
     * Returns an initialized instance of aboutStringItem component.
     *
     * @return the initialized component instance
     */
    public StringItem getAboutStringItem() {
        if (aboutStringItem == null) {//GEN-END:|52-getter|0|52-preInit
            // write pre-init user code here
            aboutStringItem = new StringItem("", "");//GEN-LINE:|52-getter|1|52-postInit
            // write post-init user code here
            aboutStringItem.setText("ZxingWriter V1.0\n"
                    + "功能说明:\n"
                    + "1. 支持保存条码到内存卡上；(PNG格式)\n"
                    + "2. 支持PNG压缩；\n"
                    + "3. 支持彩色条码；\n"
                    + "4. 支持测试生成的条码；\n"
                    + "5. 支持拍照功能。");
        }//GEN-BEGIN:|52-getter|2|
        return aboutStringItem;
    }
//</editor-fold>//GEN-END:|52-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: takePhotoCommand ">//GEN-BEGIN:|57-getter|0|57-preInit
    /**
     * Returns an initialized instance of takePhotoCommand component.
     *
     * @return the initialized component instance
     */
    public Command getTakePhotoCommand() {
        if (takePhotoCommand == null) {//GEN-END:|57-getter|0|57-preInit
            // write pre-init user code here
            takePhotoCommand = new Command("\u62CD\u7167", Command.ITEM, 0);//GEN-LINE:|57-getter|1|57-postInit
            // write post-init user code here
        }//GEN-BEGIN:|57-getter|2|
        return takePhotoCommand;
    }
//</editor-fold>//GEN-END:|57-getter|2|

    public Command getTakePhotoCommand2() {
        if (takePhotoCommand2 == null) {
            // write pre-init user code here
            takePhotoCommand2 = new Command("确定", Command.ITEM, 0);
            // write post-init user code here
        }
        return takePhotoCommand2;
    }

    /**
     * Returns a display instance.
     *
     * @return the display instance.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable(null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started. Checks whether the MIDlet have been
     * already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet();
        } else {
            initialize();
            startMIDlet();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
        if (player != null) {
            try {
                player.stop();
            } catch (MediaException me) {
                // continue?
            }
        }
    }

    /**
     * Called to signal the MIDlet to terminate.
     *
     * @param unconditional if true, then the MIDlet has to be unconditionally
     * terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
        if (player != null) {
            videoControl = null;
            try {
                player.stop();
            } catch (MediaException me) {
                // continue
            }
            player.deallocate();
            player.close();
            player = null;
        }
    }
    //todo: 2 remove debug info
    boolean debug = true;
    
    private void log(String msg) {
        if (debug) {
            System.out.println(msg);
        }
        getMessageItem().setText(msg);
    }
    
    public void itemStateChanged(Item item) {
        if (item == getColorfulItem()) {
            if (getColorfulItem().isClicked()) {
                getDisplay().setCurrent(getColorChooser());
                switchDisplayable(null, getColorChooser());
            }
        }
    }
    
    Player getPlayer() {
        if (player == null) {
            try {
                player = MultimediaManager.createPlayer();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (MediaException ex) {
                ex.printStackTrace();
            }
        }
        
        return player;
    }
    
    VideoCanvas getVideoCanvas() {
        if (canvas == null) {
            try {
                canvas = new VideoCanvas(this);
                canvas.setPlayer(getPlayer());
                
                player.realize();
                MultimediaManager.setZoom(player);
                MultimediaManager.setExposure(player);
                MultimediaManager.setFlash(player);
                
                canvas.setFullScreenMode(false);
                getVideoControl().initDisplayMode(VideoControl.USE_DIRECT_VIDEO, canvas);
                getVideoControl().setDisplayLocation(0, 0);
                getVideoControl().setDisplaySize(canvas.getWidth(), canvas.getHeight());
                
                canvas.setVideoControl(getVideoControl());
                getVideoControl().setVisible(true);
                canvas.addCommand(getTakePhotoCommand2());
                canvas.setCommandListener(this);
                
            } catch (MediaException ex) {
                ex.printStackTrace();
            }
            
        }
        return canvas;
    }

    /**
     * @return the videoControl
     */
    public VideoControl getVideoControl() {
        if (videoControl == null) {
            videoControl = (VideoControl) getPlayer().getControl("VideoControl");
        }
        return videoControl;
    }
}
