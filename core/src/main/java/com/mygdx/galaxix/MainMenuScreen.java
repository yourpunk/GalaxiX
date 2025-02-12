package com.mygdx.galaxix;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen implements Screen {
    private final Main game;
    private Stage stage;
    private Skin skin;
    private Music backgroundMusic;
    private Texture background;
    private Texture titleTexture;
    private SpriteBatch batch;

    public MainMenuScreen(final Main game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();
        background = new Texture("background.png");
        titleTexture = new Texture("title.png");

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("mainMenu.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f);
        backgroundMusic.play();

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);


        TextButton newGameButton = new TextButton("New Game", skin);
        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
              //  game.setScreen(new GameScreen(game));
            }
        });


        TextButton loadGameButton = new TextButton("Load Game", skin);
        loadGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Load Game button pressed!");
            }
        });


        TextButton exitButton = new TextButton("Exit", skin);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });


        table.add(newGameButton).fillX().uniformX().pad(10);
        table.row();
        table.add(loadGameButton).fillX().uniformX().pad(10);
        table.row();
        table.add(exitButton).fillX().uniformX().pad(10);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        float titleHeight = Gdx.graphics.getHeight() / 4;
        float titleWidth = Gdx.graphics.getWidth() / 2.5f;
        float titleX = (Gdx.graphics.getWidth() - titleWidth) / 2;
        float titleY = (Gdx.graphics.getHeight() - titleHeight) / 1.2f;
        batch.draw(titleTexture, titleX, titleY, titleWidth, titleHeight);

        batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        background.dispose();
        backgroundMusic.dispose();
        batch.dispose();
    }
}
