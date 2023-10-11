package com.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class KillInCold extends Game {
	SpriteBatch batch;
	Texture img;
	public BitmapFont font;

	private Array<Screen> screens = new Array<>();

	public void addScreen(Screen screen){
		this.screens.add(screen);
	}
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		Screen screen1 = new MainKillScreen(this);
		this.addScreen(screen1);
		this.setScreen(screen1);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		for(Screen s: screens){
			s.dispose();
		}
	}
}
