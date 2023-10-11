package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.actors.Attacker;
import com.mygdx.game.actors.Target;

public class Ball {

    private float x, y, size, yspeed;
    Color color = Color.BLUE;
    Color color1 = Color.RED;

    private Circle circle;
    Texture texture;
    public Rectangle shape;
    public static final String PATH="M.png";

    public Ball(String path,float x, float y, float size,float yspeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.yspeed = yspeed;
        this.circle = new Circle();

        texture= new Texture(Gdx.files.internal(path));
    }


    public void update() {
        y += yspeed;
        circle.set(x,y, size/2);
    }
    public void update1() {
        y += 5;
        x+=yspeed;

        if(x>= Gdx.graphics.getWidth()) {
            // Verrouillez le y de la balle vers le bas de l'écran
            x = Gdx.graphics.getWidth()-size;

            //y = Gdx.graphics.getWidth() - size;
            // Inverser la ySpeed
            //y= this.getY()+yspeed;
            yspeed=-yspeed;
        }
        if(x<=0){
            x=0;
            yspeed=-yspeed;
        }

        circle.set(x,y, size/2);
    }
    /*public void update2() {
        y += yspeed;
        if (y < 0) {
            yspeed = -yspeed;
        } else if(y > Gdx.graphics.getHeight()) {
            // Verrouillez le y de la balle vers le bas de l'écran
            y = Gdx.graphics.getHeight() - size;
            // Inverser la ySpeed
            yspeed = -yspeed;
        }

        circle.set(x,y, size/2);
    }*/



    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture,x,y,15,15);

    }


    public Rectangle getCollisionRectangle() {
        return new Rectangle(x - size / 2, y - size / 2, size, size);
    }

    public Circle getCirlce(){
        return this.circle;
    }

    
 
    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
