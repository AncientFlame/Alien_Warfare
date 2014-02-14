package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;

public class Mob 
{
    Spatial model;
    Material mat;
    Texture texture;
    boolean alive;
    float vel;
    
    Mob(AssetManager man)
    {
       model=man.loadModel("Models/cubo_base/cubo.j3o");
       mat=new Material(man,"Common/MatDefs/Misc/Unshaded.j3md");
       mat.setColor("Color", ColorRGBA.Red);
        //texture=man.loadTexture("percorso");
        //mat.setTexture("ColorMap",texture);
       model.setMaterial(mat);
       vel=0.005f;
       alive=true;
    }
}
