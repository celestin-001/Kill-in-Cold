package com.mygdx.game.weapon;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import com.mygdx.game.screen.Ball;

public class Projectiles  {

    //private float x,y,size,ySpeed;
    private static int  count=0;
    private Texture texture;
    private float x, y, size, yspeed;
    private Rectangle rectangle;
    private Array<Projectiles> projectiles;
    public static final String PATH="missile.png";
    OrthographicCamera camera;
    public Projectiles(String path,float x,float y,float ySpeed){

        this.x = x;
        this.y = y;
        this.yspeed = yspeed;
        texture= new Texture(Gdx.files.internal(path));
        this.rectangle = new Rectangle(x,y,texture.getWidth(),texture.getHeight());
        camera= new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

    }
    public void draw(SpriteBatch batch) {
        batch.draw(texture, rectangle.x, rectangle.y, rectangle.width, rectangle.height);

    }
    public void update(float delta) {
        rectangle.y+=  1000*Gdx.graphics.getDeltaTime();
        // Mettre à jour la position horizontale
    }
    public Rectangle getShape() {
        return rectangle;
    }

    public float getWidth(){
        return texture.getWidth();
    }

    public float getHeigth(){
        return texture.getHeight();
    }
    public void dispose(){
        texture.dispose();
    }


}


