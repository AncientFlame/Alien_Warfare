/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.system.AppSettings;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Lorenzo
 */
public class Settings {
    public static AppSettings sys;
    
    Settings(){
        //audio_settings();
        sys.setMinResolution(640, 480);
        try{
        sys.setIcons(new BufferedImage[]{ImageIO.read(new File("Interface/game_image.bmp"))});
        }catch(IOException r){
            System.out.println(r);
        }
        sys.setTitle("Alien Warfare");
        sys.setUseInput(true);
    }
    
    /*private void audio_settings(){
            sys.setStereo3D(true);
    }
    public void set_resolution(int width, int height ){
        sys.setResolution(width, height);
    }*/
    AppSettings get_settings(){
        return sys;
    }
}
