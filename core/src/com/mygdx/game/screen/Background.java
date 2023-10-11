package com.mygdx.game.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
    private Texture texture;

    private float x;
    private float y;

    private float animationX;
    private float animationSpeed;

    private float animationTime; // Temps écoulé depuis le début de l'animation
    private float animationPeriod; // Durée de l'animation (temps nécessaire pour boucler l'image)



    private boolean displayTitle;
    private float titleDisplayTime;
    private BitmapFont font;
    int x1, y1;

    public Background(int x, int y, String path) {
        texture = new Texture(path);
        this.x = x;
        this.y = y;
        this.displayTitle = true;
        this.titleDisplayTime = 5f;
        this.font = new BitmapFont();
        font.setColor(Color.RED);
        font.getData().setScale(2f);
        this.animationX = 0;
        this.animationSpeed = 10;
        this.animationTime = 0;
        this.animationPeriod = texture.getWidth() / animationSpeed;

    }

    //creation de l'image de fond
    public void draw(SpriteBatch batch, float deltaTime) {
        //animationTime += deltaTime; // Mettez à jour le temps écoulé depuis le dernier rendu

        // Calculez la position de l'animation en fonction du temps écoulé
        animationX = (animationTime % animationPeriod) * animationSpeed;

        batch.draw(texture, x + animationX, y); // Dessinez l'image animée en utilisant la position mise à jour

        // Si vous souhaitez répéter l'animation, vous pouvez ajouter la logique suivante :
        if (x + animationX + texture.getWidth() < 0) {
            animationX = 0; // Réinitialisez la position de l'animation lorsque l'image est entièrement déplacée hors de l'écran
        }
    }



    public void dispose() {
        texture.dispose();
    }
}
