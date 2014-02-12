
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.system.AppSettings;
import com.jme3.texture.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Lorenzo
 */
public final class Settings {
    public static AppSettings system = new AppSettings(true);
    private static String Title;
    
    Settings() {
        
        set_default_settings();
    }
    
    /** default impostazioni
     
    */
    public void set_default_settings(){
        //audio_settings();
        /*
        try {
            
            system.setIcons(new BufferedImage[]{
             ImageIO.read(getClass().getResourceAsStream("Interface/game_icon.bmp")),
             /*ImageIO.read(getClass().getResourceAsStream("/res/misc/icon128.png")),
             ImageIO.read(getClass().getResourceAsStream("/res/misc/icon32.png")),
             ImageIO.read(getClass().getResourceAsStream("/res/misc/icon16.png"))
                });
        }
        catch (IOException e) {
            System.out.println(e);
        }*/
        
        
        
        
        set_icon_image();
        Title = "Alien Warfare";
        system.setTitle(Title);
        system.setUseInput(true);
        system.setFrameRate(300);
    }
    private static void audio_settings(){
            system.setStereo3D(true);
    }
    public void set_resolution(int width, int height ){
        system.setResolution(width, height);
    }
    AppSettings get_settings(){
        return system;
    }
    private void set_icon_image(){
       
        try {
            system.setIcons(new BufferedImage[]{
                    ImageIO.read(getClass().getResourceAsStream("/Interface/game_icon256.bmp")),
                    ImageIO.read(getClass().getResourceAsStream("/Interface/game_icon128.bmp")),
                    ImageIO.read(getClass().getResourceAsStream("/Interface/game_icon32.bmp")),
                    ImageIO.read(getClass().getResourceAsStream("/Interface/game_icon16.bmp")),
            });
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
