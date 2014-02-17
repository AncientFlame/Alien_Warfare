package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;

import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;


public class Wall{
   Spatial models[];
   boolean broken[];
   Material mat;
   Texture texture; 
   
   Wall(AssetManager man)
   {
      models=new Spatial[6]; 
      broken=new boolean[6];
      texture = man.loadTexture("Textures/SeamlessBrick10.JPG");
      mat = new Material(man, "Common/MatDefs/Misc/Unshaded.j3md");
      mat.setTexture("ColorMap" , texture);
      mat.setColor("Color", ColorRGBA.Green);
   }
 
}
