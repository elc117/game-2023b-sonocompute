package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class PlayerLives {
    public Vector2 position;
    public Sprite sprite;
    public Boolean Active = true;

    public Vector2 position_initial;

    public PlayerLives(Vector2 _position, Texture img, Color color){
        position = _position;
        position_initial = position;
        sprite = new Sprite(img);
        sprite.setColor(color);
        sprite.setScale(1);

    }

    public void Draw(SpriteBatch batch){
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }
}
