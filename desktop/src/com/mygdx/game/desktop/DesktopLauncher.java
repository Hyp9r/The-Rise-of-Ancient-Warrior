package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.TileMap.MyEditor;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {


	public static void main (String[] arg) {
		/*LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Game";
		config.width = 1080;
		config.height = 768;
		new LwjglApplication(new MyGdxGame(), config);*/
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Editor";
		config.width = 1440;
		config.height = 850;
		new LwjglApplication(new MyEditor(), config);
	}
}
