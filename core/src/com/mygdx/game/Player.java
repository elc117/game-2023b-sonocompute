package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player {
    public Vector2 position;
    public Vector2 position_bullet;

    public Vector2 position_player_lives;

    public Sprite sprite_bullet;
    public Sprite sprite_player;

    public Sprite sprite_player_lives;
    public float speed = 500;
    public float speed_bullet = 1000;

    public int numLives = 3;



    public Player(Texture img,Texture img_bullet, Color color, Texture img_player_lifes) {
            sprite_player = new Sprite(img);
            sprite_player.setScale(0.5F);
            //sprite_player.setColor(color);
            position = new Vector2(Gdx.graphics.getWidth()/2,sprite_player.getScaleY()*sprite_player.getHeight());//positionplayer

            sprite_bullet = new Sprite(img_bullet);
            sprite_bullet.setScale(0.5F);
            //sprite_bullet.setColor(color);
            position_bullet = new Vector2(Gdx.graphics.getWidth()/2,sprite_player.getScaleY()*sprite_player.getHeight()/2);


            sprite_player_lives = new Sprite(img_player_lifes);
            sprite_player_lives.setScale(0.2F);
            //sprite_player_lives.setColor(color);
            position_player_lives = new Vector2(-50, 691);

    }

    public void UpdatePosition(float deltaTime)
    {
        if(Gdx.input.isButtonJustPressed(0) && position_bullet.y >=Gdx.graphics.getHeight()) {
            position_bullet.x = position.x+14;
            position_bullet.y = 0;
        }
        if(Gdx.input.isKeyPressed(Keys.A)) position.x-=deltaTime*speed;
        if(Gdx.input.isKeyPressed(Keys.D)) position.x+=deltaTime*speed;

        if(position.x-(sprite_player.getWidth()*sprite_player.getScaleX()/2)<=0) position.x = (sprite_player.getWidth()*sprite_player.getScaleX()/2);
        if(position.x+(sprite_player.getWidth()*sprite_player.getScaleX()/2)>=Gdx.graphics.getWidth()) position.x = Gdx.graphics.getWidth()-(sprite_player.getWidth()*sprite_player.getScaleX()/2);

        position_bullet.y+=deltaTime*speed_bullet;
    }

    public Vector2 resetPosition(Vector2 position) {
         position.x = Gdx.graphics.getWidth()/2;
         position.y = sprite_player.getScaleY()*sprite_player.getHeight()/2;
                //new Vector2(Gdx.graphics.getWidth()/2,sprite_player.getScaleY()-sprite_player.getHeight()/4);//positionx and y  =

        //position_bullet = new Vector2(Gdx.graphics.getWidth()/2,sprite_player.getScaleY()*sprite_player.getHeight()/4);
        return position;
    }


    public void Draw(SpriteBatch batch){//here is where it happens with player
        UpdatePosition(Gdx.graphics.getDeltaTime());

        sprite_player.setPosition(position.x, position.y);
        sprite_player.draw(batch);

        sprite_bullet.setPosition(position_bullet.x, position_bullet.y);
        sprite_bullet.draw(batch);



        sprite_player_lives.setPosition(position_player_lives.x, position_player_lives.y);
        sprite_player_lives.draw(batch);

    }


}
