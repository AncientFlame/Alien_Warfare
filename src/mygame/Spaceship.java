/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;

/**
 *
 * @author Lorenzo
 */
public class Spaceship {
    Spatial model;
    Material mat;
    Texture texture;
    private AssetManager assetmanager;
    boolean alive;
    float vel;
    Spaceship(AssetManager man)
    {
       assetmanager = man; 
       //model=assetmanager.loadModel("");
       
       
       vel=0.01f; 
       alive=true;
       
    }
}
