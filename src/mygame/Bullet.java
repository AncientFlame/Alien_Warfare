package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;

public class Bullet
{
    Spatial model;
    Material mat;
    Texture texture;
    boolean fire;
    float vel;
    
    Bullet(AssetManager man)
    {
       model=man.loadModel("Models/cubo_base/cubo.j3o");
       model.setLocalScale(0.4f,0.4f,0.4f);
       mat=new Material(man,"Common/MatDefs/Misc/Unshaded.j3md");
       mat.setColor("Color", ColorRGBA.Yellow);
        //texture=man.loadTexture("percorso");
        //mat.setTexture("ColorMap",texture);
       model.setMaterial(mat);
       fire=false;
       vel=0.08f;
    }
    void go_to_spaceship(Spaceship sp)
    {
       model.setLocalTranslation(sp.model.getLocalTranslation());
    }
    boolean move()
    {
       Vector3f v=model.getLocalTranslation();
       model.setLocalTranslation(v.x,v.y,v.z+vel);
       if(v.z>30) //30 = dopo ultima linea di mob all'inizio del gioco
         return false;
       return true;
    }
};
