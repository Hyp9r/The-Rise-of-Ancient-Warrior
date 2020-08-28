package com.mygdx.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Panel extends Component{

    private int x,y;
    private int width, height;
    private ShapeRenderer shapeRenderer;
    private AncorSide anchor;// 0,1,2,3{top, right, bottom, left}
    private ImageButton quit, minimize, maximize;
    private boolean minimized = false;
    private int orginalHeight;

    public Panel(int x, int y, int width, int height, AncorSide anchorSide){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.orginalHeight = height;
        this.anchor = anchorSide;
        init();
    }

    public Panel(int width, int height, AncorSide anchorSide){
        this.width = width;
        this.height = height;
        this.anchor = anchorSide;
        init();
    }

    public Panel(int width, int height){
        this.width = width;
        this.height = height;
        init();
    }

    private void init(){
        shapeRenderer = new ShapeRenderer();
        //minimize = new ImageButton(x+110,y+height-40, "-");
        //quit = new ImageButton(x+160, y+height-40, "x");
        //maximize = new ImageButton(x+110, y+height-40, "[]");


        minimize.addClickListener(() -> {
            this.height = 100;
            minimized = true;
            refresh();
        });

        maximize.addClickListener(() ->{
            this.height = orginalHeight;
            minimized = false;
            refresh();
        });
    }

    private void refresh(){
        minimize.setY(y+height-40);
        quit.setY(y+height-40);
        maximize.setY(y+height-40);
    }

    @Override
    public void update(){
        minimize.update();
        quit.update();
        maximize.update();
    }

    @Override
    public void draw(){

        Gdx.gl.glEnable(GL30.GL_BLEND);
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        //shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(0, 0, 0, 0.5f));
        shapeRenderer.rect(x,y,width,height);
        shapeRenderer.setColor(new Color(0.945f,0.768f,0.0588f, 1.0f));
        shapeRenderer.rect(x, y+height-50,width, 50);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL30.GL_BLEND);


        /*Draw gui*/
        if(minimized){
            maximize.draw();
        }else{
            minimize.draw();
        }
        quit.draw();

        if(!minimized){
            super.draw();
        }

    }

    public void addComponent(Component c){
        components.add(c);
    }

    public void removeComponent(Component c){
        components.remove(c);
    }

    public int getX(){
        return this.x;
    }

    public int getHeight(){
        return this.height;
    }

    public int getWidth(){
        return this.width;
    }

}
