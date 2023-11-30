package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class InvadersGameScreen implements Screen {

    final Invaders game;

    public Sound shotSound;
    Music AmbienceMusic;
    public Texture img;
    public Texture img_bullet;

    public Texture img_player_lives;

    public Texture img_alien;
    public Player player;

    public PlayerLives[] player_lives;

    int NumWidth_lifes = 6;
    int NumHeight_lifes = 5;

    public Alien[] aliens;
    int NumWidth_aliens = 6;
    int NumHeight_aliens = 8;

    int spacing_aliens = 60;

    int minX_aliens;

    int minY_aliens;

    int maxX_aliens;

    int maxY_aliens;
    //move aliens
    public Vector2 offset_aliens;

    int direction_aliens = 1;
    float speed_aliens = 300;

    int amount_alive_aliens = 0;

    int points = 0;

    int lives = 3;



    public InvadersGameScreen(Invaders game) {
        this.game = game;

        offset_aliens = Vector2.Zero;
        img = new Texture("Player.png");
        img_bullet = new Texture("bullet.png");
        img_alien = new Texture("real-dino.png");
        img_player_lives = new Texture("lige_space.png");
        player = new Player(img,img_bullet, Color.GREEN, img_player_lives);//pass life player texture in here and
        aliens = new Alien[NumWidth_aliens*NumHeight_aliens];
        //player_lives = new PlayerLives[NumWidth_lifes*NumHeight_lifes];

        int i = 0;
        for (int y = 0; y < NumHeight_aliens; y++){
            for (int x = 0; x<NumWidth_aliens; x++){
                Vector2 position = new Vector2(x*spacing_aliens, y*spacing_aliens);
                position.x+= Gdx.graphics.getWidth()/2;
                position.y+=Gdx.graphics.getHeight();
                position.x-=(NumWidth_aliens/2)*spacing_aliens;
                position.y-=(NumHeight_aliens)*spacing_aliens;
                aliens[i] = new Alien(position,img_alien,Color.GREEN );
                i++;
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        float deltaTime = Gdx.graphics.getDeltaTime();
        ScreenUtils.clear(0,0,0,1);
        game.batch.begin();
        game.font.setColor(Color.GREEN);
        game.font.draw(game.batch, "Points: " + points, 100, 150);
        game.font.draw(game.batch, "" + lives, 30, 830);
        player.Draw(game.batch);
        for(int i = 0; i<aliens.length; i++){
            if(aliens[i].Active) {//tests if alien is alive
                if (player.sprite_bullet.getBoundingRectangle().overlaps(aliens[i].sprite.getBoundingRectangle())) {//tests if the alien is being shot
                    player.position_bullet.y = 10000;
                    aliens[i].Active = false;
                    points++;
                    break;
                }
            }
        }

        minX_aliens = 10000;
        minY_aliens = 10000;
        maxX_aliens = 0;
        maxY_aliens = 0;
        amount_alive_aliens =0;
        for(int i = 0; i<aliens.length; i++){
            if(aliens[i].Active){
                int IndexX = i/NumWidth_aliens;
                int IndexY = i/NumHeight_aliens;
                if(IndexX>maxX_aliens)maxX_aliens = IndexX;
                if(IndexX<minX_aliens)minX_aliens = IndexX;
                if(IndexY>maxY_aliens)maxY_aliens = IndexY;
                if(IndexY<minY_aliens)minY_aliens = IndexY;
                amount_alive_aliens++;
            }
        }
        if(amount_alive_aliens == 0){
            for (int i = 0; i < aliens.length ; i++) {
                aliens[i].Active = true;
            }
            offset_aliens = new Vector2(0,0);
            game.batch.end();
            return;
        }
        offset_aliens.x+=direction_aliens*deltaTime*speed_aliens;
        if(aliens[maxX_aliens].position.x>= Gdx.graphics.getWidth()){ //if aliens hit right border they wil turn direction
            direction_aliens = -1;
            offset_aliens.y-=aliens[0].sprite.getHeight()*aliens[0].sprite.getScaleY()*0.25f;//makes alies move down
            speed_aliens+=5;
        }
        if(aliens[minX_aliens].position.x<=0){//if aliens hit left border they wil turn direction
            direction_aliens = 1;
            offset_aliens.y-=aliens[0].sprite.getHeight()*aliens[0].sprite.getScaleY()*0.25f;
            speed_aliens+=5;
        }
        if(aliens[minY_aliens].position.y<=0){
            //Gdx.app.exit();
        }

        for(int i = 0; i<aliens.length; i++) {
            aliens[i].position = new Vector2(aliens[i].position_initial.x+offset_aliens.x,aliens[i].position_initial.y+offset_aliens.y);
            if(aliens[i].Active){
                aliens[i].Draw(game.batch);
                if(aliens[i].sprite.getBoundingRectangle().overlaps(player.sprite_player.getBoundingRectangle())){//tests if the aliens hits player
                    lives--;
                    if(lives == 0){
                        Gdx.app.exit();
                    }

                }
            }
            //aliens[i].Draw(batch);
        }
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
