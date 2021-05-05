package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import sun.security.util.Length;

public class Background {
    class BackGroundMas{
        private Texture texture;
        private Vector2 position;
        public BackGroundMas(Vector2 position){
            texture = new Texture("background.png");
            this.position = position;
        }
    }

    private int renderspeed;
    private BackGroundMas[] backs;
    public Background(){

       renderspeed = 4;
       backs = new BackGroundMas[2];
       backs[0] = new BackGroundMas(new Vector2(0,0));
       backs[1] = new BackGroundMas(new Vector2(800,0));
    }

    public void render(SpriteBatch batch){
        for (int i = 0; i < backs.length; i++) {
         batch.draw(backs[i].texture, backs[i].position.x, backs[i].position.y);
        }
    }

    public void updateImg(){
        for (int i = 0; i < backs.length ; i++) {
            backs[i].position.x -= renderspeed;
        }
    if(backs[0].position.x < -310)
    {
        backs[0].position.x = 0;
        backs[1].position.x = 800;
    }
    }
}
