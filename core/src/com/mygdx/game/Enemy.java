package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class Enemy {
    class EnemyPair {
        Vector2 position;
        float speed;
        int offset;
       Rectangle emptySpace;


        public EnemyPair(Vector2 pos) {
            position = pos;
            speed = 15;
            offset = new Random().nextInt(250);
            emptySpace = new Rectangle(position.x + offset + 300, position.y + distanceBetween, 100 , 500);
        }

        public void update() {
            position.x -= speed;
            if (position.x < -1800) {
                position.x = 1800;
                int speed = MathUtils.random(270,740);
                if (speed > 506){
                    position.y = 740;
                } else {
                    position.y = 270;
                }
            }
            emptySpace.y = position.y;
            emptySpace.x = position.x;
        }
    }

    static EnemyPair[] ens;
    Texture texture;
    int distanceBetween;

    public Enemy() {
        texture = new Texture("enemy.png");
         ens = new EnemyPair[1];
        distanceBetween = 235;
        int startPosX = 1800;
        int startPosY = 270;
         for (int i = 0; i < ens.length ; i++) {
          ens[i] = new EnemyPair(new Vector2(startPosX, startPosY));
      startPosX += 520;
   }
}
    public void render(SpriteBatch batch){
        for (int i = 0; i < ens.length ; i++) {
         batch.draw(texture,ens[i].position.x,ens[i].position.y);

        }

    }
public void update(){
    for (int i = 0; i < ens.length ; i++) {
        ens[i].update();

    }
}
    public void restart() {
        int startPosX = 1600;
        int startPosY = 270;
        for (int i = 0; i < ens.length; i++) {
            ens[i] = new EnemyPair(new Vector2(startPosX, startPosY));
            startPosX += 220;
        }
    }
}
