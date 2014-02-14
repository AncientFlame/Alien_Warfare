package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;
import org.lwjgl.opengl.Display;


public class Main extends SimpleApplication 
{   
     
    Spaceship spaceship;
    Wall walls[]=new Wall[4];
    Mob m[][]=new Mob[11][5];
    float mob_vel,zeta_mob=24;
    
    public static void main(String[] args)
    {
        
        Main app = new Main();
        Settings sys = new Settings();
        app.setSettings(sys.get_settings());
        app.setPauseOnLostFocus(true);
        app.start();
    }

    @Override
    public void simpleInitApp() 
    {
            
      //flyCam.setMoveSpeed(15.0f);
      flyCam.setEnabled(false);
      
      inizialize_spaceship(-10.0f,0.0f,0.0f);
      init_walls(0);
      initKeys();
      init_light();
      init_sky();
      init_aliens();
    }

    @Override
    public void simpleUpdate(float tpf) 
    {
       set_camera();
       move_aliens();
       listener.setLocation(cam.getLocation());
       listener.setRotation(cam.getRotation());
    }

    @Override
    public void simpleRender(RenderManager rm) 
    {
        
    }
    
    void inizialize_spaceship(float x,float y,float z)
    {
        spaceship=new Spaceship(assetManager);
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
               if(v.x-spaceship.vel>-23)
                spaceship.model.setLocalTranslation(v.x-spaceship.vel, v.y, v.z);
             }
             if(name.equals("A")) 
             {
               Vector3f v =  spaceship.model.getLocalTranslation();
               if(v.x+spaceship.vel<3)
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
      walls[num_wall]=new mygame.Wall(assetManager);
      for(int ite=0; ite<6; ite++)
      {
        walls[num_wall].models[ite]=assetManager.loadModel("Models/cubo_base/cubo.j3o");
        walls[num_wall].texture.setAnisotropicFilter(8);
        walls[num_wall].models[ite].setMaterial(walls[num_wall].mat);
        
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
    
    private void init_light()
    {
            /** A white ambient light source. */ 
            AmbientLight ambient = new AmbientLight();
            ambient.setColor(ColorRGBA.White);
            rootNode.addLight(ambient); 
    }
    private void init_sky()
    {
            rootNode.attachChild(SkyFactory.createSky(
                assetManager, "Textures/Skysphere.jpg", true));
    }
    
    private void init_aliens()
    {
      mob_vel=-0.005f;
      for(int linea=0; linea<11; linea++) 
      {
        for(int colonna=0; colonna<5; colonna++) 
        { 
           m[linea][colonna]=new mygame.Mob(assetManager);
           m[linea][colonna].model.setLocalTranslation(0+(-2.5f*linea),0,24+(3*colonna)); 
           /* 24: distanza su z minima dove creare mob
            aumentando 24 aumenta distanza, 0: serve a centrare la matrice di mob 
            (aumentandolo si sposta verso sx,diminuendo dx)*/
           rootNode.attachChild(m[linea][colonna].model);
        }
      }
    }
    
    private void move_aliens()
    {
      float vel_app;
       for(int i=0; i<11; i++)
       {
          for(int j=0; j<5; j++)
          {
            if(m[i][j].alive==true)
            {
              vel_app=mob_vel;
              mob_vel=m[i][j].motion(mob_vel,zeta_mob+(6*j));
              if(vel_app!=mob_vel) //sbattuto contro il muro
                 zeta_mob-=1;
            }
          }
       }
    }
};

