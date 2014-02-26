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
    boolean alien;
    
    Bullet(AssetManager man,boolean b)
    {
       model=man.loadModel("Models/bullet/bullet.j3o");
       mat=new Material(man,"Common/MatDefs/Misc/Unshaded.j3md");
       mat.setColor("Color", ColorRGBA.Yellow);
        //texture=man.loadTexture("percorso");
        //mat.setTexture("ColorMap",texture);
       model.setMaterial(mat);
       fire=false;
       alien=b;
       if(b==false) vel=0.5f; else vel=-0.5f;
    }
    void go_to_spaceship(Spaceship sp)
    {
       model.setLocalTranslation(sp.model.getLocalTranslation());
    }
    boolean move()
    {
       Vector3f v=model.getLocalTranslation();
       model.setLocalTranslation(v.x,v.y,v.z+vel);
       if( (v.z>50 && alien==false) || (v.z<-6 && alien==true) ) //40 = dopo ultima linea di mob all'inizio del gioco
       {
           return false;
       }
       return true;
    }
};
