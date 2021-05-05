package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class RacerIllegalGame extends ApplicationAdapter {
	SpriteBatch batch;
	Background background;
	Player player;
	Enemy enemy;
	Health hp;
boolean gameOver;
int gameOverCount;
int score;
Texture restartTexture;
Texture texturehp;
Texture texturehp1;
Texture texturehp2;
Texture texturehp3;
Texture texturehp4;
int w,h = 0;


  private Music music;
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Background();
		player = new Player();
		hp = new Health();
		music = Gdx.audio.newMusic(Gdx.files.internal("racer illegal.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		enemy = new Enemy();
		score = 0;
		gameOver = false;
		restartTexture = new Texture("retry.png");
        texturehp = new Texture("cross.png");
        texturehp1 = new Texture("cross1.png");
        texturehp2 = new Texture("cross2.png");
		texturehp3 = new Texture("up.png");
		texturehp4 = new Texture("down.png");




	}

	@Override
	public void render () {
		updateImg();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		background.render(batch);
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		OrthographicCamera guicam = new OrthographicCamera(w, h);
		guicam.position.set(w/2, h/2, 0);
		Rectangle wleftBounds = new Rectangle(200, 200, 200, 200);
		Rectangle wrightBounds = new Rectangle(-1400, 200, 200, 200);
		Vector3 touchPoint = new Vector3();
		for (int i=0; i<5; i++){
			if (!Gdx.input.isTouched(i)) continue;
			guicam.unproject(touchPoint.set(Gdx.input.getX(i)-500, Gdx.input.getY(i)-550, 0));
			if (wleftBounds.contains(touchPoint.x, touchPoint.y)) {
				player.carmovedown();
			} else if (wrightBounds.contains(touchPoint.x, touchPoint.y)) {
				player.carmoveup();
			}
		}
		batch.draw(texturehp4, wleftBounds.x+1600, wleftBounds.y, wleftBounds.width, wleftBounds.height);
		batch.draw(texturehp3, wrightBounds.x+1600, wrightBounds.y, wrightBounds.width, wrightBounds.height);

		if(!gameOver) {
		    hp.render(batch);
            player.render(batch);
            enemy.render(batch);

        } else{
			batch.draw(restartTexture,700,300);
		}
        if(gameOverCount == 1){

			batch.draw(texturehp,1475,900);
        }
        if(gameOverCount == 2){
			batch.draw(texturehp,1475,900);
			batch.draw(texturehp1,1625,900);
        }
        if(gameOverCount == 3) {
			batch.draw(texturehp,1475,900);
			batch.draw(texturehp1,1625,900);
            batch.draw(texturehp2, 1775, 900);
            gameOver = true;

        }
        batch.end();
            music.play();

	}
	public void updateImg() {
		background.updateImg();
		player.update();
		enemy.update();

		for (int i = 0; i < Enemy.ens.length; i++) {

			if ((player.position.x > Enemy.ens[i].position.x - 150 && player.position.y == Enemy.ens[i].position.y && player.position.x < Enemy.ens[i].position.x + 150) || (player.position.y == Enemy.ens[i].position.y && player.position.x > Enemy.ens[i].position.x - 150 && player.position.x < Enemy.ens[i].position.x + 150)) {

				if (!Enemy.ens[i].emptySpace.contains(player.position)) {
					gameOverCount += 1;
					player.position.y = 740;
					enemy.restart();

				}
			}
		}
		if (gameOver) {

			OrthographicCamera retrcam = new OrthographicCamera(w, h);
			retrcam.position.set(w / 2, h / 2, 0);
			Rectangle lastBanner = new Rectangle(-800, 400, 400, 400);
			Vector3 lastTouchPoint = new Vector3();
			for (int i = 0; i < 5; i++) {
				if (!Gdx.input.isTouched(i)) continue;
				retrcam.unproject(lastTouchPoint.set(Gdx.input.getX(i) - 500, Gdx.input.getY(i) - 550, 0));
				if (lastBanner.contains(lastTouchPoint.x, lastTouchPoint.y)) {
					restart();
				}

			}
		}
	}
	public void restart(){
	    enemy.restart();
	    player.restart();
	    gameOver = false;
	    gameOverCount = 0;
    }
	@Override
	public void dispose () {
		batch.dispose();
        super.dispose();
        music.dispose();
	}
}
