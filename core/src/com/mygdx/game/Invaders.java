package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;

public class Invaders extends ApplicationAdapter {

    SpriteBatch batch;
    Texture img;

    Texture img_bullet;
    Player player;


    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("Player.png");
        img_bullet = new Texture("Bullet.png");
        player = new Player(img,img_bullet, Color.GREEN);
    }
    @Override
    public void render() {
        ScreenUtils.clear(0,0,0,1);
        batch.begin();
        player.Draw(batch);
        batch.end();
    }

    @Override
    public void dispose(){
        batch.dispose();
        img.dispose();
    }

}
