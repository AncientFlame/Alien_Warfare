/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;

import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;

/**
 *
 * @author Lorenzo
 */
public class Wall{
   Spatial models[];
   Material mat;
   Texture texture; 
   private final AssetManager assetmanager;
   
   Wall(AssetManager man)
   {
      assetmanager = man; 
      models=new Spatial[6]; 
      texture = assetmanager.loadTexture("Textures/SeamlessBrick10.JPG");
      mat = new Material(assetmanager, "Common/MatDefs/Misc/Unshaded.j3md");
      mat.setTexture("ColorMap" ,texture);
      
      mat.setColor("Color", new ColorRGBA(0.6f,0.6f,0.6f,1.0f));
      mat.setTransparent(false);
      
   }
 
}
