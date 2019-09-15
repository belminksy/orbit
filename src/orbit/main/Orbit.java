/*
 * MIT License
 *
 * Copyright (c) 2019 Adrien Belminksy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package orbit.main;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;
import orbit.app.conf.Conf;
import orbit.app.conf.DynaConf;
import orbit.app.conf.KeyConf;
import orbit.app.controllers.CameraController;
import orbit.app.scene.MasterScene;
import orbit.app.scene.Scene1;
import orbit.app.scene.Scene2;
import orbit.app.scene.Scene3;
import orbit.engine.render.manager.SceneManager;
import orbit.engine.render.object.Camera;
import orbit.utils.in.Gamepad;
import orbit.utils.in.Keyboard;
import orbit.utils.out.Console;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Orbit {

	protected boolean running = false;
	protected boolean playing = false;

	protected Camera camera = new Camera(Conf.CAMERA_VIEW[0].copy(), Conf.CAMERA_VIEW[1].copy());
	protected Controller[] controllers;

	protected SceneManager sceneManager = new SceneManager();

	public Orbit(){ }


	public void init(){

		CameraController controller = new CameraController();

		controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

		camera.setPerspectiveProjection(Conf.ENGINE_CAMERA_ZNEAR, Conf.ENGINE_CAMERA_ZFAR, Conf.ENGINE_CAMERA_FOV);
		camera.setMovableAndRotableController(controller);

		sceneManager.beginOnlyMaster(true);

		sceneManager.setMaster(new MasterScene());
		sceneManager.add(new Scene1(), new Scene2(), new Scene3());

		controller.setControllers(controllers);

	}

	public void initgl(){

		try {

			Display.setDisplayMode(new DisplayMode(Conf.WINDOW_WIDTH, Conf.WINDOW_HEIGHT));
			Display.setResizable(Conf.WINDOW_RESIZABLE);
			Display.setTitle(Conf.WINDOW_TITLE);

			Display.create();

			GL11.glClearColor(0, 0, 0, 0);

			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glShadeModel(GL11.GL_SMOOTH);
			GL11.glDepthFunc(GL11.GL_LEQUAL);
			GL11.glClearDepth(1.0f);
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();

		} catch (Exception e){
			Console.err(e, this);
		}

	}

	public void start(){
		Console.info("Start", this);
		running = true;
		playing = true;
	}

	public void pause(){
		Console.info("Pause", this);
		playing = false;
	}

	public void stop(){
		running = false;
	}

	public void exit(){
		Console.info("Good bye", this);

		Display.destroy();
		System.exit(0);
	}


	public void togglePlay(){
		if (playing){
			pause();
		} else {
			start();
		}
	}
	public void toggleRotation(){
		if (DynaConf.ROTATE_ACTIVATED){
			Console.info("Rotation disabled", this);
		} else {
			Console.info("Rotation activated", this);
		}

		DynaConf.ROTATE_ACTIVATED = !DynaConf.ROTATE_ACTIVATED;
	}


	public void loop(){

		long lastTick  = System.nanoTime();
		long lastFrame = System.nanoTime();

		double tickTime  = 1000000000.0 / Conf.ENGINE_MAX_TPS;
		double frameTime = 1000000000.0 / Conf.ENGINE_MAX_FPS;

		int fps = 0;

		long timer = System.currentTimeMillis();

		while (running){
			if (Display.isCloseRequested()){
				stop();
			}

			if (System.nanoTime() - lastTick > tickTime){
				update();

				lastTick += tickTime;

			} else if (System.nanoTime() - lastFrame > frameTime){
				render();

				fps++;
				lastFrame += frameTime;

			} else {
				try {
					Thread.sleep(1);
				} catch (Exception e){
					Console.err(e, this);
				}
			}

			if (System.currentTimeMillis() - timer > 1000){

				Display.setTitle(Conf.WINDOW_TITLE + " " + fps + "fps");

				fps = 0;
				timer += 1000;

			}
		}

		exit();

	}


	public void update(){
		if (Keyboard.isDown(KeyConf.ESCAPE)){
			Mouse.setGrabbed(false);
		}
		if (Mouse.isButtonDown(0)){
			Mouse.setGrabbed(true);
		}


		while (Keyboard.next()){

			if (Keyboard.isPressed(KeyConf.PLAY_PAUSE)){
				togglePlay();
			}

			if (Keyboard.isPressed(KeyConf.SCENE_NEXT)){
				sceneManager.next();
			}
			if (Keyboard.isPressed(KeyConf.SCENE_PREV)){
				sceneManager.prev();
			}

			if (Keyboard.isPressed(KeyConf.SCENE_ACTION_5)){
				sceneManager.clear();
			}

			if (Keyboard.isPressed(KeyConf.SCENE_ACTION_7)){
				Console.info("pos : " + camera.getPosition().getX() + ", " + camera.getPosition().getY() + ", " + camera.getPosition().getZ(), this);
				Console.info("ori: " + camera.getOrientation().getX() + ", " + camera.getOrientation().getY() + ", " + camera.getOrientation().getZ(), this);
			}

			if (Keyboard.isPressed(KeyConf.SCENE_ACTION_8)){
				toggleRotation();
			}

			if (Keyboard.isPressed(KeyConf.SCENE_ACTION_9)){
				DynaConf.SHOW_SATELLITE_ORBIT = !DynaConf.SHOW_SATELLITE_ORBIT;
			}

		}


		for (int i = 0; i < controllers.length; i++){
			controllers[i].poll();

			EventQueue queue = controllers[i].getEventQueue();
			Event event = new Event();

			while (queue.getNextEvent(event)){

				if (Gamepad.isActive(event.getComponent(), KeyConf.GAMEPAD_SCENE_NEXT) && !event.getComponent().isAnalog()){
					if (event.getComponent().getPollData() == 1f) sceneManager.next();
				}
				if (Gamepad.isActive(event.getComponent(), KeyConf.GAMEPAD_SCENE_PREV) && !event.getComponent().isAnalog()){
					if (event.getComponent().getPollData() == 1f) sceneManager.prev();
				}

				if (Gamepad.isActive(event.getComponent(), KeyConf.GAMEPAD_PLAY_PAUSE) && !event.getComponent().isAnalog()){
					if (event.getComponent().getPollData() == 1f) togglePlay();
				}
				if (Gamepad.isActive(event.getComponent(), KeyConf.GAMEPAD_ROTATE) && !event.getComponent().isAnalog()){
					if (event.getComponent().getPollData() == 1f) toggleRotation();
				}

			}
		}


		if (playing) sceneManager.update();

		camera.activeControllers();

	}

	public void render(){
		if (Display.wasResized()){
			GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		}

		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		camera.applyPerspectiveProjection();
		camera.update();


		if (DynaConf.ROTATE_ACTIVATED){
			DynaConf.ROTATE_VALUE += Conf.ROTATE_INCR;
			DynaConf.ROTATE_VALUE = DynaConf.ROTATE_VALUE % 360;

		} else if (DynaConf.ROTATE_VALUE != Conf.ROTATE_DEFAULT && !DynaConf.ROTATE_RESET){

			DynaConf.ROTATE_RESET_BEGIN = System.currentTimeMillis();
			DynaConf.ROTATE_RESET_SAVE_VALUE = DynaConf.ROTATE_VALUE;

			DynaConf.ROTATE_RESET = true;
		}

		if (DynaConf.ROTATE_RESET){
			if (System.currentTimeMillis() - DynaConf.ROTATE_RESET_BEGIN >= Conf.ROTATE_RESET_TIME){
				DynaConf.ROTATE_VALUE = Conf.ROTATE_DEFAULT;
				DynaConf.ROTATE_RESET_SAVE_VALUE = 0;

				DynaConf.ROTATE_RESET = false;

			} else {

				DynaConf.ROTATE_RESET_RATIO = (float) (System.currentTimeMillis() - DynaConf.ROTATE_RESET_BEGIN) / Conf.ROTATE_RESET_TIME;

				DynaConf.ROTATE_VALUE = DynaConf.ROTATE_RESET_SAVE_VALUE - (((DynaConf.ROTATE_RESET_SAVE_VALUE % 360 ) - Conf.ROTATE_DEFAULT) * DynaConf.ROTATE_RESET_RATIO);

			}
		}


		GL11.glRotatef(DynaConf.ROTATE_VALUE, 0, 1, 0);


		sceneManager.render();

		Display.update();
	}

}
