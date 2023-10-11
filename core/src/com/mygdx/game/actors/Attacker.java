package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.screen.Ball;
import com.mygdx.game.screen.KillScreen;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.weapon.Projectiles;

import java.util.ArrayList;
import java.util.Iterator;

public class Attacker extends Actors{

    public static int MAX_LIFE = 100;
    public static int MAX_STAMINA = 100;
    public static ArrayList<Ball> balls_Sup = new ArrayList<>();
    public static ArrayList<Projectiles> projectiles = new ArrayList<>();
    public static String DEFAULT_NAME = "Iron Griffin";
    float x,y,size;
    private Texture texture1;
    private OrthographicCamera camera;

    private static final String TEXTURE_FILE_NAME = "attacker.png" ;
    static final Texture texture = new Texture(Gdx.files.internal(TEXTURE_FILE_NAME)) ;
    public Rectangle shape;

    public Attacker(String name) {
        super(name);
        shape = new Rectangle(0,0,texture.getWidth(), texture.getHeight());
        this.setMaxLife(MAX_LIFE);
        this.setLife(MAX_LIFE);
        this.setStamina(MAX_STAMINA);
        this.setMaxStamina(MAX_STAMINA);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, shape.x, shape.y, shape.width,shape.height);
    }


    public void update() {
        Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPos);

        // Déplacement horizontal de l'acteur en fonction de la souris
        shape.x = (int) touchPos.x ;

        // Déplacement vertical de l'acteur en fonction de la souris
        shape.y = (int) touchPos.y;

        Iterator<Ball> ballIterator = KillScreen.balls.iterator();
        while (ballIterator.hasNext()) {
            Ball ball = ballIterator.next();
            ball.setY((int)ball.getY() + 5);

            // Supprimer la balle si elle atteint la bordure supérieure (top)
            if (ball.getY() >= Gdx.graphics.getHeight()) {
                ballIterator.remove();
            }
        }


        // Limiter le déplacement de l'acteur dans les limites de la caméra
        if (shape.x < 0) {
            shape.x = 0;
        }
        if (shape.x >= camera.viewportWidth - shape.width) {
            shape.x = camera.viewportWidth - shape.width;
        }
        if (shape.y < 0) {
            shape.y = 0;
        }
        if (shape.y >= camera.viewportHeight - shape.height) {
            shape.y = camera.viewportHeight - shape.height;
        }



    }

    public void dispose(){
        texture.dispose();
    }

    
    public void setY(float y) {
        this.y = y;
    }

    public float getX(){
        return this.x;
    }
    public float getWidth(){
        return texture.getWidth();
    }
    public float getY(){
        return texture.getHeight();
    }

    public void ball_Sup(){
        Ball b1 = new Ball(Ball.PATH,this.shape.x,  this.shape.y+this.shape.height/2, 5, 10);
        Ball b2 = new Ball(Ball.PATH,this.shape.x+this.shape.width,  this.shape.y+this.shape.height/2, 5, -10);
        balls_Sup.add(b1);
        balls_Sup.add(b2);
    }





}
