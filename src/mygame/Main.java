package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.audio.AudioNode;
import com.jme3.collision.CollisionResults;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.util.SkyFactory;
<<<<<<< HEAD

=======
import java.util.Random;
>>>>>>> origin/new

public class Main extends SimpleApplication 
{   
    AudioNode shot; 
    Spaceship spaceship;
    Bullet bulletofship;
    Wall walls[]=new Wall[4];
    Mob m[][]=new Mob[11][5];
    int x_m[]=new int[11];
    int y_m[]=new int[11];
    Bullet bulletofaliens[]=new Bullet[4];
    int bullets=0;
    float mob_vel,zeta_mob=24;
    CollisionResults collision;
    boolean first_person=false;
    boolean pressed=false;
    int ii;


    public static void main(String[] args)
    {
        Main app = new Main();
        Settings sys = new Settings();
        app.setSettings(sys.get_settings());
        app.setPauseOnLostFocus(true);
        app.setDisplayFps(false);
        app.setDisplayStatView(false);
        
        app.start();
    }

    @Override
    public void simpleInitApp() 
    {
            
      //flyCam.setMoveSpeed(15.0f);
      flyCam.setEnabled(false);
      
      bulletofship=new Bullet(assetManager,false);
      bulletofaliens[0]=new Bullet(assetManager,true); 
      bulletofaliens[1]=new Bullet(assetManager,true); 
      bulletofaliens[2]=new Bullet(assetManager,true); 
      bulletofaliens[3]=new Bullet(assetManager,true); 
      inizialize_spaceship(-10.0f,0.0f,-3f);
      init_walls(0);
      initKeys();
      init_audio();
      init_light();
      init_sky();
      init_aliens();
    }

    @Override
    public void simpleUpdate(float tpf) 
    {
       set_camera();
       move_aliens();
       bulletofship.fire=fire(bulletofship);
       if(bulletofship.fire==true)
       { 
         if(collisions_with_walls(bulletofship,spaceship)==false) collisions_with_aliens(bulletofship,spaceship);
       }
       if(bullets<3)
         alien_fire();
       if(bullets>0)
       { 
         for(ii=0; ii<4; ii++)
         {
           if(bulletofaliens[ii].fire==true)  
           {  
              bulletofaliens[ii].fire=fire(bulletofaliens[ii]);   
           }
         }
       }
       
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
      inputManager.addMapping("fire", new KeyTrigger(KeyInput.KEY_SPACE));
      inputManager.addMapping("visuale",new KeyTrigger(KeyInput.KEY_T));
      inputManager.addListener(spaceship_keys,"A","D","fire");
      inputManager.addListener(camera_keys,"visuale");
    }
    private ActionListener camera_keys=new ActionListener()
    {
        public void onAction(String name, boolean isPressed, float tpf) 
        {
             if(name.equals("visuale") && !isPressed)
             {
               if(pressed==false)
               {
                first_person=!first_person; 
                pressed=true;
               }
             } else pressed=false;  
        }
    };
    private AnalogListener spaceship_keys = new AnalogListener() 
    {
       public void onAnalog(String name, float value, float tpf) 
       {
             if(name.equals("D"))
             {
               Vector3f v = spaceship.model.getLocalTranslation();
               if(v.x-spaceship.vel>-25)
                spaceship.model.setLocalTranslation(v.x-spaceship.vel, v.y, v.z);
             }
             if(name.equals("A")) 
             {
               Vector3f v =  spaceship.model.getLocalTranslation();
               if(v.x+spaceship.vel<5)
                spaceship.model.setLocalTranslation(v.x+spaceship.vel, v.y,v.z);
             }
             if(name.equals("fire"))
             {
                if(bulletofship.fire==false) 
                {
                  shot.playInstance();  
                  bulletofship.fire=true;
                  rootNode.attachChild(bulletofship.model);
                } 
                
             }
      }
    };
    
    private void set_camera()
    {
       Vector3f v=spaceship.model.getLocalTranslation();
       if(first_person==false) cam.setLocation(new Vector3f(v.x,v.y+2,v.z-6)); else cam.setLocation(new Vector3f(v.x,v.y+1,v.z+0.05f));
       cam.setRotation(spaceship.model.getLocalRotation());
    }

    private void init_walls(int num_wall)
    {   
      walls[num_wall]=new mygame.Wall(assetManager);
      for(int ite=0; ite<6; ite++)
      {
        walls[num_wall].models[ite]=assetManager.loadModel("Models/cubo_base/cubo.j3o");
        walls[num_wall].broken[ite]=false;
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
    private void init_audio(){
        shot = new AudioNode(assetManager, "Sounds/Shot.wav", false);
        shot.setLooping(false);
        shot.setVolume(2);
        shot.setPositional(false);
        rootNode.attachChild(shot);
    }
    private void init_aliens()
    {
      //mob_vel=-0.005f;
        mob_vel=-0.01f;
      for(int linea=0; linea<11; linea++) 
      {
        for(int colonna=0; colonna<5; colonna++) 
        { 
           m[linea][colonna]=new mygame.Mob(assetManager);
           if(colonna==0)
           {
             m[linea][colonna].isLast=true;
             x_m[linea]=linea;
             y_m[linea]=colonna;
           }
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
              if(zeta_mob+6*j<=2.0f)
                this.stop();
            }
          }
       }
    }
    private boolean fire(Bullet s1)
    {
      if(s1.fire==true)
      {
<<<<<<< HEAD
        
        if(bulletofship.move()==false) { rootNode.detachChild(bulletofship.model); return false; } else return true;
        
      } else bulletofship.go_to_spaceship(spaceship);
=======
        if(s1.move()==false) { rootNode.detachChild(s1.model); if(s1.alien==true) { bullets--; } return false; } else return true;
      } else s1.go_to_spaceship(spaceship);
>>>>>>> origin/new
      return false;
    }
    
    private void collisions_with_aliens(Bullet s1,Spaceship s2)
    {
      for(int i=0; i<11; i++)
      {
        for(int j=0; j<5; j++)
        {
          if(m[i][j].alive==true) //mob ancora vivo e proiettile sparato
          { 
            collision=new CollisionResults();
            s1.model.collideWith(m[i][j].model.getWorldBound(),collision);
            if(collision.size()>0) //se è entrato in collisione
            {
               m[i][j].alive=false;
               if(m[i][j].isLast==true)
               {   
                 if(j<4)
                 {
                   m[i][j+1].isLast=true;
                   y_m[i]=j+1;
                 } else y_m[i]=-1;
               }
               rootNode.detachChild(m[i][j].model);
               bulletofship.fire=false;
               rootNode.detachChild(s1.model);
               s1.go_to_spaceship(s2);
               i=12;
               j=6;
            }
          }
        }
      }
    } 
    private boolean collisions_with_walls(Bullet s1,Spaceship s2)
    {
       Vector3f v=s1.model.getLocalTranslation();
       if(v.z<=3.5f)
       {
          for(int i=0; i<4; i++)
          {
             for(int j=0; j<6; j++)
             {
               if(walls[i].broken[j]==false)
               {
                collision=new CollisionResults();
                s1.model.collideWith(walls[i].models[j].getWorldBound(), collision);
                if(collision.size()>0)
                {
                   walls[i].broken[j]=true;
                   rootNode.detachChild(walls[i].models[j]);
                   s1.fire=false;
                   rootNode.detachChild(s1.model);
                   s1.go_to_spaceship(s2); 
                   return true;
                }
               }
             }
          }
       }
       return false; 
    }
    
<<<<<<< HEAD
    
=======
    private void alien_fire()
    {
      //int indice = (int)(Math.random()*10);
        Random random=new Random();
        int indice=random.nextInt(10);
       if(y_m[indice]!=-1)
       {
         for(int i=0; i<4; i++)
         {
           if(bulletofaliens[i].fire==false)
           { 
              bulletofaliens[i].model.setLocalTranslation(m[x_m[indice]][y_m[indice]].model.getLocalTranslation());
              bulletofaliens[i].fire=true;
              rootNode.attachChild(bulletofaliens[i].model);
              i=5;
              bullets++;
           }
         }
       }
    }
>>>>>>> origin/new
    
};

