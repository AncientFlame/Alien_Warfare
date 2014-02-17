package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;

public class Spaceship {
    Spatial model;
    Material mat;
    Texture texture;
    boolean alive;
    float vel;
    Spaceship(AssetManager man)
    {
       model=man.loadModel("Models/spaceship/spaceship.j3o");
       model.setLocalScale(0.3f,0.3f,0.3f);
       mat=new Material(man,"Common/MatDefs/Misc/Unshaded.j3md");
       mat.setColor("Color", ColorRGBA.Blue);
        //texture=man.loadTexture("percorso");
        //mat.setTexture("ColorMap",texture);
       model.setMaterial(mat);
       vel=0.03f; 
       alive=true;
    }
}
