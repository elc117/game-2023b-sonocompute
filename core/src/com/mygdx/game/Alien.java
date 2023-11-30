package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Alien {

    public Vector2 position;
    public Sprite sprite;
    public Boolean Active = true;

    public Vector2 position_initial;

    public Alien(Vector2 _position, Texture img, Color color){
        position = _position;
        position_initial = position;
        sprite = new Sprite(img);
        //sprite.setColor(color);
        sprite.setScale(0.1F);

    }

    public void Draw(SpriteBatch batch){
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }
}
