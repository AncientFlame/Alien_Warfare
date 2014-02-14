package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;

public class Mob 
{
    Spatial model;
    Material mat;
    Texture texture;
    boolean alive;
    
    Mob(AssetManager man)
    {
       model=man.loadModel("Models/cubo_base/cubo.j3o");
       mat=new Material(man,"Common/MatDefs/Misc/Unshaded.j3md");
       mat.setColor("Color", ColorRGBA.Red);
        //texture=man.loadTexture("percorso");
        //mat.setTexture("ColorMap",texture);
       model.setMaterial(mat);
       alive=true;
    }
    float motion(float vel,float zeta)
    {
        Vector3f app=model.getLocalTranslation();
        if(app.x+vel<-23 || app.x+vel>3) //se sbatte contro il muro inverte la direzione
        {
           vel*=-1;
        }
         model.setLocalTranslation(app.x+vel,0,zeta);
         return vel;
    }
};
