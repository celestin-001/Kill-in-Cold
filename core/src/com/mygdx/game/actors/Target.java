package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.screen.Ball;

import java.util.ArrayList;
import java.util.Iterator;

public class Target extends Actors{

    public static String DEFAULT_NAME = "TARGET";
    public static int MAX_LIFE = 50;
    public static int MAX_STAMINA = 50;
    public boolean test=true;
    public static ArrayList<Ball> balls_Sup = new ArrayList<>();
    float x,y,size;
    public float speedX=MathUtils.random(-6,6); // Vitesse de déplacement horizontal
    public float speedY=MathUtils.random(-6,6); // Vitesse de déplacement vertical

    private static final String TEXTURE_FILE_NAME = "target0.png" ;
    private String path;
    private Texture texture  ;
    public Rectangle shape;
    OrthographicCamera camera;

    public Target(String path) {
        super(DEFAULT_NAME);
        this.path=path;
        texture = new Texture(Gdx.files.internal(path));
        shape = new Rectangle(0,0, texture.getWidth(), texture.getHeight());
        camera= new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, shape.x, shape.y, shape.width, shape.height);

    }

    public void update(float delta) {
        // Mettre à jour la position horizontale
        shape.x += speedX;

        // Mettre à jour la position verticale
        if(test){
            shape.y -=  200*Gdx.graphics.getDeltaTime();
        }else{
            shape.y +=  200*Gdx.graphics.getDeltaTime();
        }

        // Vérifier si l'ennemi est sorti de l'écran et le replacer de l'autre côté
        if(this.shape.y<=0){
            shape.y=0;
            speedY=-speedY;
            test=false;
        }
        if(this.shape.y>=Gdx.graphics.getHeight()){
            shape.y=Gdx.graphics.getHeight();
            test=true;
        }
        if (shape.x >= camera.viewportWidth - shape.width) {
            shape.x =camera.viewportWidth - shape.width;
            speedX=-speedX;
        } else if (shape.x <=0) {
            shape.x =0;
            speedX=-speedX;
        }

        if (shape.y > Gdx.graphics.getHeight()) {
            shape.y = -shape.height;
        } else if (shape.y <=Gdx.graphics.getHeight()/2-this.getHeigth() && this instanceof Boss) {


            test=false;
        }
    }



    public float getWidth(){
        return texture.getWidth();
    }

    public float getHeigth(){
        return texture.getHeight();
    }


    public  float attackCost(float cost){

        return 1;
    }

    public void target_Ball(ArrayList<Ball> balls){
        Ball b = new Ball(Ball.PATH,this.shape.x +this.shape.width/2,  this.shape.y, 5, -8);
        balls.add(b);
    }

    public static void updateBalle(SpriteBatch spriteBatch, Attacker attacker,ArrayList<Ball> balls){
        Iterator<Ball> ballIter = balls.iterator();
        while (ballIter.hasNext()) {
            Ball b=ballIter.next();

            if(b.getY()<=0){
                ballIter.remove();
                break;
            }

            if(Intersector.overlaps(b.getCirlce(),attacker.shape)) {
                ballIter.remove();
                attacker.setLife(attacker.getLife()-1);

            }
            b.draw(spriteBatch);
            b.update();


        }
    }

    public void dispose(){
        texture.dispose();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Rectangle getShape() {
        return shape;
    }

    public void setShape(Rectangle shape) {
        this.shape = shape;
    }

    public void ball_Sup(){
        Ball b1 = new Ball(Ball.PATH,this.shape.x,  this.shape.y+this.shape.height/2, 5, 10);
        Ball b2 = new Ball(Ball.PATH,this.shape.x+this.shape.width,  this.shape.y+this.shape.height/2, 5, -10);
        balls_Sup.add(b1);
        balls_Sup.add(b2);
    }


}
