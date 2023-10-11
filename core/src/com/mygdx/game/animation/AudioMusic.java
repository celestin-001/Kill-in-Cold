package com.mygdx.game.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class AudioMusic {
    public Music music;

    public AudioMusic(String path){
        music = Gdx.audio.newMusic(Gdx.files.internal(path));// schemas du fichier à uploadé
    }

    public void play(){
        music.play();
    }

    public void stop(){
        music.stop();
    }

    public void dispose(){
        music.dispose();
    }



}
