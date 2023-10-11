package com.mygdx.game.obstacle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.screen.KillScreen;

public class Lazer extends Time {

    private Texture texture;
    private TextureRegion gifRegion;
    private Sprite gifSprite;
    Timer timer;
        public Lazer(float duration) {
            super(duration);
            texture=new Texture(Gdx.files.internal("lazer.jpg"));
            timer=new Timer();
        }

        public void draw(SpriteBatch batch){
                batch.draw(texture,0, Gdx.graphics.getHeight() / 2,Gdx.graphics.getWidth(), 80);
        }

        @Override
        public void onExpired() {
            texture.dispose();
        }


}
