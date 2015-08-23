/*
 * Copyright 2007 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.makeit.zxingwriter;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.VideoControl;

/**
 * The main {@link Canvas} onto which the camera's field of view is painted.
 * This class manages decoding via {@link SnapshotThread}.
 *
 * @author Sean Owen
 * @author Simon Flannery
 */
final class VideoCanvas extends Canvas implements CommandListener {

    private static final Command exit = new Command("Exit", Command.EXIT, 1);
    private static final Command history = new Command("History", Command.ITEM, 0);
    private Player player;
    private String bestEncoding;
    ZxingWriter midlet;
    private VideoControl videoControl;

    VideoCanvas(ZxingWriter mdilet) {
        this.midlet = mdilet;
//        addCommand(exit);
//        addCommand(history);
//        setCommandListener(this);
//        try {
//            this.player = MultimediaManager.createPlayer();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } catch (MediaException ex) {
//            ex.printStackTrace();
//        }
//        if (player != null) {
//            snapshotThread = new SnapshotThread(player);
//            new Thread(snapshotThread).start();
//        }

    }

    protected void paint(Graphics graphics) {
        // do nothing
    }

    protected void keyPressed(int keyCode) {
        // Any valid game key will trigger a capture
        if (getGameAction(keyCode) != 0) {
//            try {
//                //            snapshotThread.continueRun();
//                
////                MultimediaManager.setFocus(player);
////                byte[] snapshot = takeSnapshot();
////                Image capturedImage = Image.createImage(snapshot, 0, snapshot.length);
////                LuminanceSource source = new LCDUIImageLuminanceSource(capturedImage);
////                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
////                Reader reader = new MultiFormatReader();
////                Result result = reader.decode(bitmap);
////                Alert alert = new Alert("result", "", null, AlertType.INFO);
////                alert.setString(result.getText());
////                alert.setTimeout(Alert.FOREVER);
//
//            } catch (MediaException ex) {
//                ex.printStackTrace();
//            } catch (NotFoundException ex) {
//                ex.printStackTrace();
//            } catch (ChecksumException ex) {
//                ex.printStackTrace();
//            } catch (FormatException ex) {
//                ex.printStackTrace();
//            }
        } else {
            super.keyPressed(keyCode);
        }
    }

    public byte[] takeSnapshot() throws MediaException{

        String bestEncoding = guessBestEncoding();
 
        byte[] snapshot = null;
        snapshot = videoControl.getSnapshot("".equals(bestEncoding) ? null : bestEncoding);
        if (snapshot == null) {
            // Fall back on JPEG; seems that some cameras default to PNG even
            // when PNG isn't supported!
            snapshot = videoControl.getSnapshot("encoding=jpeg");
            if (snapshot == null) {
                throw new MediaException("Can't obtain a snapshot");
            }
        }
        videoControl=null;
        return snapshot;
    }

    private String guessBestEncoding() throws MediaException {
        if (bestEncoding == null) {
            // Check this property, present on some Nokias?
            String supportsVideoCapture = System.getProperty("supports.video.capture");
            if ("false".equals(supportsVideoCapture)) {
                throw new MediaException("supports.video.capture is false");
            }

            bestEncoding = "";
            String videoSnapshotEncodings = System.getProperty("video.snapshot.encodings");
            if (videoSnapshotEncodings != null) {
                // We know explicitly what the camera supports; see if PNG is among them since
                // Image.createImage() should always support it
                int pngEncodingStart = videoSnapshotEncodings.indexOf("encoding=png");
                if (pngEncodingStart >= 0) {
                    int space = videoSnapshotEncodings.indexOf(' ', pngEncodingStart);
                    bestEncoding = space >= 0
                            ? videoSnapshotEncodings.substring(pngEncodingStart, space)
                            : videoSnapshotEncodings.substring(pngEncodingStart);
                }
            }
        }
        return bestEncoding;
    }

    public void commandAction(Command command, Displayable displayable) {
        int type = command.getCommandType();
        if (command == history) {
//            zXingMIDlet.historyRequest();
        } else if (type == Command.EXIT || type == Command.STOP || type == Command.BACK || type == Command.CANCEL) {
//            snapshotThread.stop();
//            zXingMIDlet.stop();
        }
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return the videoControl
     */
    public VideoControl getVideoControl() {
        return videoControl;
    }

    /**
     * @param videoControl the videoControl to set
     */
    public void setVideoControl(VideoControl videoControl) {
        this.videoControl = videoControl;
    }
}
