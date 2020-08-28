package com.mygdx.BattleSystem;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.GUI.Button;

public class BattleGUI {
   private Texture background;
   private SpriteBatch batch;
   private Button button;

   public BattleGUI(){
       background = new Texture("./battle-gui/battle-gui2.jpg");
       batch = new SpriteBatch();
       button = new Button(700, 40, "dad");
   }

   public void draw(){
       batch.begin();
       batch.draw(background, 0 ,0, 1080, 268);
       batch.end();
       button.draw();
   }

   public boolean isButtonClicked(){
       return true;
   }

}
