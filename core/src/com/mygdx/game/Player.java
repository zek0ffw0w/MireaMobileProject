package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;
import java.nio.channels.spi.SelectorProvider;

public class Player {
    Texture img;
    Vector2 position;


   public Player(){
    img = new Texture("player.png");
    position = new Vector2(200, 740);

    }
    public void render(SpriteBatch batch){

       batch.draw(img,position.x,position.y);
    }

    public void carmoveup(){
    position.y = 740;
    }
    public void carmovedown(){
        position.y = 270;
    }
    public void update(){



    if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP))
    {
    carmoveup();
    }
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN))
        {
            carmovedown();
        }
    }
    public void restart(){
       position = new Vector2(200,270);
    }
}
