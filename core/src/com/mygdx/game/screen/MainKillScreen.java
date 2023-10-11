package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainKillScreen implements Screen {
    final KillInCold kill;
    OrthographicCamera camera;
    Viewport viewport;
    SpriteBatch batch;


    public MainKillScreen(final KillInCold kill){

        this.kill = kill;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 800, 800);

    }

    public void render(float delta){
        ScreenUtils.clear(0,0,0.2f, 1);
        camera.update();
        kill.batch.setProjectionMatrix(camera.combined);

        kill.batch.begin();

        Texture texture = new Texture(Gdx.files.internal("im.jpeg"));
        kill.batch.draw(texture,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        kill.font.draw(kill.batch, "Welcome in the Kill in Cold!!! ", 100, 150);
        kill.font.draw(kill.batch, "Tap anywhere to begin!", 100, 100);
        kill.batch.end();

        if(Gdx.input.isTouched()){
            kill.setScreen(new KillScreen(kill));
            dispose();
        }
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.setToOrtho(false, width, height);
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
        kill.batch.dispose();
        kill.font.dispose();
    }

    @Override
    public void show(){
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
    }

}
