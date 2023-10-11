package com.mygdx.game.weapon;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.screen.Ball;

public class Fusils extends Ball {

    Fusils fusils;
    private Texture texture;
    private Circle circle;
    private Array<Fusils> balls;
    public Fusils(String path,float x,float y,float size,float ySpeed){
        super(path,x,y,size,ySpeed);
        circle = new Circle();
        balls = new Array<>();
        texture = new Texture(Gdx.files.internal("M.png"));
    }


}



