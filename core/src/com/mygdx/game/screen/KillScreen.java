package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.actors.*;
import com.mygdx.game.animation.AudioMusic;
import com.mygdx.game.obstacle.Lazer;
import com.mygdx.game.weapon.Projectiles;


import java.util.ArrayList;
import java.util.Iterator;

public class KillScreen implements Screen {

    private final KillInCold kill;
    private OrthographicCamera camera;

    private Background background;
    private AudioMusic audioMusic;
    private int speed=5;
    Attacker attacker;
    Boss boss;
    private static Array<Petit> petits;
    private static Array<FauconNoir> faucons;
    private static Array<Lazer> lazers;
    float deltas=MathUtils.random(-5,5);
    public static ArrayList<Ball> balls = new ArrayList<>();
    private ArrayList<Ball> balls_petits = new ArrayList<>();
    private ArrayList<Ball> ball_boss=new ArrayList<>();
    private ArrayList<Ball> balls_faucons = new ArrayList<>();

    SpriteBatch batch;
    Lazer lazer;
    ShapeRenderer shapeRenderer;

    BitmapFont font;

    public static long lastKillTime;
    private static int  count=0;
    private static Array<Projectiles> projectiles;

    Ball ball;

    private int targetsDead;

    public KillScreen(final KillInCold kill){
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont();

        audioMusic = new AudioMusic("audio/music.mp3");
        attacker = new Attacker(Attacker.DEFAULT_NAME);
        boss=new Boss(Boss.PATH);

        this.kill = kill;
        this.kill.addScreen(this);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800,800);
        background = new Background(0,0,"Background/ground.jpeg");
        petits = new Array<Petit>();
        faucons=new Array<FauconNoir>();
        projectiles=new Array<Projectiles>();
        lazers=new Array<Lazer>();
        Lazer lazer=new Lazer(3);
        lazers.add(lazer);

        count = (int)attacker.shape.y + 10;

    }


    @Override
    public void render(float delta) {
        // Nettoyage de l'écran
        ScreenUtils.clear(0, 0, 0.2f, 1);

        attacker.update();
        boss.update(1);
        if (TimeUtils.nanoTime() - KillScreen.lastKillTime > 1000000000) {
            this.newPetit();
            this.newFaucon();
        }
        this.checkCollisionsTargets(faucons);
        this.checkCollisionsTargets(petits);
        camera.update();

        kill.batch.setProjectionMatrix(camera.combined);

        batch.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        background.draw(batch,delta);
        count = count+1;
        if(count%15==0){
            Ball ball1 = new Ball(Ball.PATH,attacker.shape.x +attacker.shape.width/2,  attacker.shape.y + attacker.shape.height, 5, 5);
            balls.add(ball1);
            attacker.ball_Sup();
        }
        if (count%30==0){
            boss.target_Ball(ball_boss);
            Projectiles projectiles1=new Projectiles(Projectiles.PATH,attacker.shape.x,0,20);
            projectiles.add(projectiles1);
        }

        if(count>Gdx.graphics.getHeight()){
            count=0;
        }

        for(Projectiles p:projectiles){
            p.draw(batch);
            p.update(1);
        }

        Iterator<Ball> ballIter = balls.iterator();

        while (ballIter.hasNext()) {
            Ball b=ballIter.next();
            b.draw(batch);
            b.update();
        }
        /*Iterator<Lazer> lazerIterator = lazers.iterator();
        while (lazerIterator.hasNext()) {
            Lazer lazer1=lazerIterator.next();
           lazer1.draw(batch);
            //lazer1.update(0);
        }*/

        audioMusic.play();
        attacker.draw(batch);
        for(Petit target : petits){
            target.update(deltas);
            if(count%20==0){
                target.target_Ball(balls_petits);
            }
            target.draw(batch);

        }
        Target.updateBalle(batch,attacker,balls_petits);
        for(FauconNoir target : faucons){
            target.update(deltas);
            if(count%20==0){
                target.target_Ball(balls_faucons);

            }
            target.draw(batch);

        }Target.updateBalle(batch,attacker,balls_faucons);
        boss.draw(batch);
        Target.updateBalle(batch,attacker,ball_boss);


        font.draw(batch, "Targets Dead: " + targetsDead, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 10);
        font.draw(batch, "Level : " + 1, 10, Gdx.graphics.getHeight() - 10);
        font.draw(batch, "Life : " + attacker.getLife() + " % ", Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 10);
        batch.end();
        shapeRenderer.end();
    }

    private void checkCollisionsTargets(Array <?extends Target> list) {
        Iterator<? extends Target> targetIter = list.iterator();
        while (targetIter.hasNext()) {
            Target target = targetIter.next();
                Iterator<Ball> ballIter = balls.iterator();
                if(target instanceof Petit && target.shape.y<=0){
                    targetIter.remove();
                }
                while (ballIter.hasNext()) {
                    Ball ball = ballIter.next();
                    if (Intersector.overlaps( ball.getCirlce(),  target.shape)) {
                        targetsDead++;
                        ballIter.remove();
                        targetIter.remove();
                        break;
                    }

            }
        }
    }

    public  void newPetit() {
        Petit petit = new Petit(Petit.PATH);
        petit.shape.x = MathUtils.random(0, Gdx.graphics.getWidth());
        petit.shape.y = Gdx.graphics.getHeight();
        petit.shape.width = 90;
        petit.shape.height = 90;
        petits.add(petit);
        lastKillTime = TimeUtils.nanoTime();
    }

    public  void newFaucon() {
        FauconNoir fauconNoir = new FauconNoir(FauconNoir.PATH);
        fauconNoir.shape.x = MathUtils.random(0, Gdx.graphics.getWidth());
        fauconNoir.shape.y = Gdx.graphics.getHeight();
        fauconNoir.shape.width = 150;
        fauconNoir.shape.height = 150;
        faucons.add(fauconNoir);
        lastKillTime = TimeUtils.nanoTime();
    }

    @Override
    public void show(){}


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
