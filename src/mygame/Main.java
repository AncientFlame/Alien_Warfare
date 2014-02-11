package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;


public class Main extends SimpleApplication 
{
    Spaceship spaceship;
    Wall walls[]=new Wall[4];
    
    public static void main(String[] args)
    {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() 
    {
      //flyCam.setMoveSpeed(15.0f);
      flyCam.setEnabled(false);
      inizialize_spaceship(0.0f,0.0f,0.0f);
      init_walls(0);
      initKeys();
    }

    @Override
    public void simpleUpdate(float tpf) 
    {
       set_camera();
       listener.setLocation(cam.getLocation());
       listener.setRotation(cam.getRotation());
    }

    @Override
    public void simpleRender(RenderManager rm) 
    {
        
    }
    
    void inizialize_spaceship(float x,float y,float z)
    {
        spaceship=new Spaceship();
        spaceship.model=assetManager.loadModel("Models/cubo_base/cubo.j3o");
        spaceship.mat=new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
        spaceship.mat.setColor("Color", ColorRGBA.Blue);
        //spaceship.texture=assetManager.loadTexture("percorso");
        //spaceship.mat.setTexture("textship", spaceship.texture);
        spaceship.model.setMaterial(spaceship.mat);
        spaceship.model.setLocalTranslation(x,y,z);
        rootNode.attachChild(spaceship.model);
    }
    
    private void initKeys()
    {
      inputManager.addMapping("A", new KeyTrigger( KeyInput.KEY_A));
      inputManager.addMapping("D", new KeyTrigger( KeyInput.KEY_D));
      inputManager.addListener(spaceship_keys,"A","D");
    }
    
    private AnalogListener spaceship_keys = new AnalogListener() 
    {
       public void onAnalog(String name, float value, float tpf) 
       {
             if(name.equals("D"))
             {
               Vector3f v = spaceship.model.getLocalTranslation();
               spaceship.model.setLocalTranslation(v.x-spaceship.vel, v.y, v.z);
             }
             if(name.equals("A")) 
             {
               Vector3f v =  spaceship.model.getLocalTranslation();
               spaceship.model.setLocalTranslation(v.x+spaceship.vel, v.y,v.z);
             }
      }
    };
    
    private void set_camera()
    {
       Vector3f v=spaceship.model.getLocalTranslation();
       cam.setLocation(new Vector3f(v.x-0.5f,v.y+2,v.z-8));
       cam.setRotation(spaceship.model.getLocalRotation());
    }

    private void init_walls(int num_wall)
    {
      for(int ite=0; ite<6; ite++)
      {
        walls[num_wall]=new Wall();
        walls[num_wall].models[ite]=assetManager.loadModel("Models/cubo_base/cubo.j3o");
        walls[num_wall].mat=new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
        walls[num_wall].mat.setColor("Color", ColorRGBA.Green);
        //walls[num_wall].texture=assetManager.loadTexture("percorso");
        //walls[num_wall].mat.setTexture("textship", walls[num_wall].texture);
        walls[num_wall].models[ite].setMaterial(spaceship.mat);
        switch(ite)
        {
            case 0: { walls[num_wall].models[ite].setLocalTranslation(0.0f-6*num_wall,0.0f,1.0f); } break;
            case 1: { walls[num_wall].models[ite].setLocalTranslation(0.0f-6*num_wall,0.0f,2.0f); } break;
            case 2: { walls[num_wall].models[ite].setLocalTranslation(-1.0f-6*num_wall,0.0f,2.0f); } break;
            case 3: { walls[num_wall].models[ite].setLocalTranslation(-1.0f-6*num_wall,0.0f,3.0f); } break;
            case 4: { walls[num_wall].models[ite].setLocalTranslation(-2.0f-6*num_wall,0.0f,2.0f); } break;
            case 5: { walls[num_wall].models[ite].setLocalTranslation(-2.0f-6*num_wall,0.0f,1.0f); } break;
        }
        rootNode.attachChild(walls[num_wall].models[ite]);
       }
       num_wall++;
       if(num_wall<4) { init_walls(num_wall); }
    }
    
};

class Spaceship
{
  Spatial model;
  Material mat;
  Texture texture;
  boolean alive;
  float vel;
  Spaceship()
  {
     vel=0.01f; 
     alive=true;
  }
};

class Wall
{
   Spatial models[];
   Material mat;
   Texture texture;
   Wall()
   {
      models=new Spatial[6]; 
   }
};
