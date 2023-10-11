package com.mygdx.game.obstacle;

public class Time {

    private float timer;
    private float duration;
    private boolean isExpired;
    public static long lastKillTime;

    public Time(float duration) {
        this.duration = duration;
        timer = 0.0f;
        isExpired = false;
    }

    public void update(float delta) {
        if (!isExpired) {
            timer += delta;

            if (timer >= duration) {
                isExpired = true;
                onExpired(); // Appel d'une méthode spécifique lorsque le timer expire
            }
        }
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void onExpired() {
        // Méthode à implémenter dans les classes dérivées pour exécuter des actions spécifiques lorsque le timer expire
    }
}
