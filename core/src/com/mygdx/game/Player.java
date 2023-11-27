package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player {
    public Vector2 position;
    public Sprite sprite;

    public Player(Texture img) {
        sprite = new Sprite(img);
        position = new Vector2(Gdx.graphics.getWidth()/2,0);
    }

    public void Draw(SpriteBatch batch ){
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }


}
