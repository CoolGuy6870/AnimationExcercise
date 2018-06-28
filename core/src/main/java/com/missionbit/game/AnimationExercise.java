//package com.missionbit.game;
//
//import com.badlogic.gdx.ApplicationAdapter;
//import com.badlogic.gdx.Gdx;
//
//import com.badlogic.gdx.Graphics;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Texture;
//
//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.math.Vector3;
//
//import java.util.Random;
//
//public class AnimationExercise extends ApplicationAdapter {
//    private OrthographicCamera camera;
//    private Random randomSource;
//    private Sprite myImage;
//    private SpriteBatch myBatch;
//    private Vector2 velocity;
//
//
//    @Override
//    public void create() {
//        randomSource = new Random();
//
//        // Set up camera for 2d view of 800x480 pixels
//        camera = new OrthographicCamera();
//        camera.setToOrtho(false, 800, 480);
//
//        // Create a sprite batch for rendering our image
//        myBatch = new SpriteBatch();
//
//        //TODO: Load our image
//        myImage = new Sprite( new Texture(Gdx.files.internal("images/Ainsley.jpg")));
//        myImage.setX(200);
//        myImage.setY(200);
//
//        // Create a random X and Y velocity
//        velocity = new Vector2(-randomSource.nextFloat() * 300, -randomSource.nextFloat() * 300);
//    }
//
//    @Override
//    public void render() {
//
//        // Clear the screen
//        Gdx.gl.glClearColor(0, 0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        //Set up our camera
//        camera.update();
//        myBatch.setProjectionMatrix(camera.combined);
//
//        float xPos = myImage.getX() + velocity.x * Gdx.graphics.getDeltaTime();
//        float yPos = myImage.getY() + velocity.y * Gdx.graphics.getDeltaTime();
////
//        myImage.setX(xPos);
//        myImage.setY(yPos);
//
//        Gdx.graphics.getWidth();
//        Gdx.graphics.getHeight();
//
//        if(myImage.getX() < 0){
//            myImage.setX(0);
//            velocity.x *= -1;
//        }
//
//        if(myImage.getY() < 0){
//            myImage.setY(0);
//            velocity.y *= -1;
//        }
//
//        if(myImage.getX() + myImage.getWidth() > Gdx.graphics.getWidth()){
//            myImage.setX(Gdx.graphics.getWidth()-myImage.getWidth());
//            velocity.x *= -1;
//        }
//
//        if(myImage.getY() + myImage.getHeight() > Gdx.graphics.getHeight()){
//            myImage.setY(Gdx.graphics.getHeight()-myImage.getHeight());
//            velocity.y *= -1;
//        }
//
//        if(Gdx.input.isTouched()){
//            Vector3 touchPos = new Vector3();
//            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//
//            camera.unproject(touchPos);
//            System.out.println(touchPos.x + " " + touchPos.y);
//            boolean flag = myImage.getBoundingRectangle().contains(touchPos.x, touchPos.y);
//            System.out.println(flag);
//
//
//            if(flag) {
//            myImage.setColor(0, 1, 0, 1 );
//
//            }
//            else{
//                myImage.setColor(1, 0, 0, 1);
//            }
//        }
//
//        //TODO: Draw our image!
//        myBatch.begin();
//        myImage.draw(myBatch);
//        myBatch.end();
//    }
//
//    @Override
//    public void dispose() {
//        myBatch.dispose();
//    }
//}
package com.missionbit.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;


public class AnimationExercise extends ApplicationAdapter {
    private OrthographicCamera camera;
    private SpriteBatch myBatch;
    //FlyingCreature creature;
    private ArrayList<FlyingCreature> creatures;

    @Override
    public void create() {


        // Set up camera for 2d view of 800x480 pixels
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // Create a sprite batch for rendering our image
        myBatch = new SpriteBatch();

        // Create a new creature
        //creature = new FlyingCreature(myBatch);
        creatures = new ArrayList<FlyingCreature>();

        for(int i = 0; i < 10; i++){
            FlyingCreature f = new FlyingCreature(myBatch);
            creatures.add(f);
        }
    }

    @Override
    public void render() {

        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Set up our camera
        camera.update();
        myBatch.setProjectionMatrix(camera.combined);

        //creature.update();

        myBatch.begin();
        for(FlyingCreature f : creatures){
            f.update();
            f.draw();
        }
        myBatch.end();

        if(Gdx.input.isTouched()){
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);

            camera.unproject(touchPos);

            for(FlyingCreature f : creatures){
                f.handleClick(touchPos);
            }
        }

    }


    @Override
    public void dispose() {
        myBatch.dispose();
    }
}