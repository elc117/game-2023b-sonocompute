package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
    final Invaders game;

    public OrthographicCamera camera;

     public Texture menu_img;

     public Stage stage;


    public MainMenuScreen(final Invaders game) {
        this.game = game;


        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1100, 880);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        menu_img = new Texture("Menu.png");

        //setting image size
        Image image = new Image(menu_img);
        image.setSize(1080, 720);
        stage.addActor(image);
    }

    @Override
    public void render(float delta) {
        //ScreenUtils.clear(0, 0, 0.2f, 1);

        Gdx.gl.glClearColor(0, 0, 0, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act();
        //initializing menu
        stage.draw();

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Space Invaders!!! ", 100, 150);
        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.end();

        if (Gdx.input.isTouched()) {
                game.setScreen(new InvadersGameScreen(game));
                dispose();
        }
    }

    @Override
    public void show() {

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
        stage.dispose();

        menu_img.dispose();

    }
}
