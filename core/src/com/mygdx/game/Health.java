package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Health {
    public class HealthPair {
        Vector2 position;

        public HealthPair(Vector2 pos) {
            position = pos;

        }
    }


    static HealthPair[] hp;
    Texture texture;
    int distanceBetween;

    public Health() {
        texture = new Texture("health.png");
        hp = new HealthPair[1];
        distanceBetween = 30;
        int startPosX = 1500;
        int startPosY = 900;
        for (int i = 0; i < hp.length; i++) {
            hp[i] = new HealthPair(new Vector2(startPosX, startPosY));
            startPosY += distanceBetween;

        }
    }

    public void render (SpriteBatch batch) {
        for (int i = 0; i < hp.length; i++) {
            batch.draw(texture, hp[i].position.x, hp[i].position.y);
            batch.draw(texture, hp[i].position.x + 150, hp[i].position.y);
            batch.draw(texture, hp[i].position.x + 300, hp[i].position.y);
        }
    }
}