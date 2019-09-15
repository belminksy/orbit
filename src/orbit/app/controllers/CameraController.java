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

package orbit.app.controllers;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import orbit.app.conf.Conf;
import orbit.app.conf.DynaConf;
import orbit.app.conf.KeyConf;
import orbit.engine.controllers.GamepadController;
import orbit.engine.controllers.MovableAndRotableController;
import orbit.engine.group.Movable;
import orbit.engine.group.Rotable;
import orbit.utils.in.Gamepad;
import orbit.utils.in.Keyboard;
import orbit.utils.lang.Interval;
import orbit.utils.lang.Size;
import orbit.utils.math.Vector3f;
import orbit.utils.out.Console;

import org.lwjgl.input.Mouse;

public class CameraController implements MovableAndRotableController, GamepadController {

	protected Vector3f movement = new Vector3f();
	protected Controller[] controllers;


	public void setControllers(Controller[] controllers){
		this.controllers = controllers;

		for (int i = 0; i < controllers.length; i++){
			Console.info("Found " + controllers[i].getType().toString() + " : " + controllers[i].getName(), this);
		}

	}


	public void active(Movable movable){ }

	public void active(Rotable rotable){

		if (!Mouse.isGrabbed()) return;

		rotable.getOrientation().addX(-Mouse.getDY());
		rotable.getOrientation().addY(Mouse.getDX());

		if (rotable.getOrientation().getX() > 90){
			rotable.getOrientation().setX(90);
		}
		if (rotable.getOrientation().getX() < -90){
			rotable.getOrientation().setX(-90);
		}

	}

	public void active(Movable movable, Rotable rotable){

		/* ************ */
		/* MOVE FORWARD */
		/* ************ */
		if (Keyboard.isDown(KeyConf.MOVE_UP)){
			movement.add(movable.getForward().mul(new Vector3f(-movable.getSpeed(), -movable.getSpeed(), -movable.getSpeed())));
		}
		if (Keyboard.isDown(KeyConf.MOVE_DOWN)){
			movement.add(movable.getForward().mul(new Vector3f(movable.getSpeed(), movable.getSpeed(), movable.getSpeed())));
		}
		if (Keyboard.isDown(KeyConf.MOVE_LEFT)){
			movement.add(movable.getRight().mul(new Vector3f(-movable.getSpeed(), -movable.getSpeed(), -movable.getSpeed())));
		}
		if (Keyboard.isDown(KeyConf.MOVE_RIGHT)){
			movement.add(movable.getRight().mul(new Vector3f(movable.getSpeed(), movable.getSpeed(), movable.getSpeed())));
		}

		if (Keyboard.isDown(KeyConf.MOVE_JUMP)){
			movement.addY(movable.getSpeed());
		}
		if (Keyboard.isDown(KeyConf.MOVE_SHIFT)){
			movement.addY(-movable.getSpeed());
		}


		/* ********** */
		/* MOVE PLANE */
		/* ********** */
		float z = Math.abs(rotable.getOrientation().getY() % 360 );
		float x = Math.abs((rotable.getOrientation().getY() + 270 ) % 360 );

		if (Keyboard.isDown(KeyConf.MOVE_PLANE_UP)){
			movement.add(new Vector3f(
				movable.getSpeed() * -getOrientation(x),
				0,
				movable.getSpeed() * getOrientation(z)
			));
		}
		if (Keyboard.isDown(KeyConf.MOVE_PLANE_DOWN)){
			movement.add(new Vector3f(
				movable.getSpeed() * getOrientation(x),
				0,
				movable.getSpeed() * -getOrientation(z)
			));
		}
		if (Keyboard.isDown(KeyConf.MOVE_PLANE_LEFT)){
			movement.add(new Vector3f(
				movable.getSpeed() * getOrientation(z),
				0,
				movable.getSpeed() * getOrientation(x)
			));
		}
		if (Keyboard.isDown(KeyConf.MOVE_PLANE_RIGHT)){
			movement.add(new Vector3f(
				movable.getSpeed() * -getOrientation(z),
				0,
				movable.getSpeed() * -getOrientation(x)
			));
		}


		/* ******* */
		/* GAMEPAD */
		/* ******* */
		for (int i = 0; i < controllers.length; i++){
			controllers[i].poll();

			Component[] components = controllers[i].getComponents();

			for (int j = 0; j < components.length; j++){

				if (Gamepad.isActive(components[j], KeyConf.GAMEPAD_MOVE_Y)){
					movement.add(movable.getForward().mul(new Vector3f(
						movable.getSpeed() * components[j].getPollData(),
						movable.getSpeed() * components[j].getPollData(),
						movable.getSpeed() * components[j].getPollData()
					)));
				}
				if (Gamepad.isActive(components[j], KeyConf.GAMEPAD_MOVE_X)){
					movement.add(movable.getRight().mul(new Vector3f(
						movable.getSpeed() * components[j].getPollData(),
						movable.getSpeed() * components[j].getPollData(),
						movable.getSpeed() * components[j].getPollData()
					)));
				}
				if (Gamepad.isActive(components[j], KeyConf.GAMEPAD_ROTATION_V)){
					rotable.getOrientation().addX(components[j].getPollData());
				}
				if (Gamepad.isActive(components[j], KeyConf.GAMEPAD_ROTATION_H)){
					rotable.getOrientation().addY(components[j].getPollData());
				}

				if (Gamepad.isActive(components[j], KeyConf.GAMEPAD_JUMP) && !components[j].isAnalog()){
					if (components[j].getPollData() == 1f) movement.addY(movable.getSpeed());
				}
				if (Gamepad.isActive(components[j], KeyConf.GAMEPAD_SHIFT) && !components[j].isAnalog()){
					if (components[j].getPollData() == 1f) movement.addY(-movable.getSpeed());
				}

				if (Gamepad.isActive(components[j], KeyConf.GAMEPAD_VIEW_NEXT) && !components[j].isAnalog()){
					if (components[j].getPollData() == 1f && Interval.isValid(Conf.INTERVAL_SCENE_CHANGE)) setView(++DynaConf.VIEW_ID, movable, rotable);
				}
				if (Gamepad.isActive(components[j], KeyConf.GAMEPAD_VIEW_PREV) && !components[j].isAnalog()){
					if (components[j].getPollData() == 1f && Interval.isValid(Conf.INTERVAL_SCENE_CHANGE)) setView(--DynaConf.VIEW_ID, movable, rotable);
				}

			}
		}


		/* ************ */
		/* USE MOVEMENT */
		/* ************ */
		movable.getPosition().addX(applyLimit(movable.getSpeed(), movement.getX()));
		movable.getPosition().addY(applyLimit(movable.getSpeed(), movement.getY()));
		movable.getPosition().addZ(applyLimit(movable.getSpeed(), movement.getZ()));

		movement.clear();

		movable.getPosition().invisbleSphere(
			Conf.OBJECT_PLANET_EARTH_ORIGIN,
			Size.resizef(Conf.OBJECT_PLANET_EARTH_RADIUS) + 0.2f
		);
		movable.getPosition().invisbleSphere(
			Conf.OBJECT_PLANET_MOON_ORIGIN,
			Size.resizef(Conf.OBJECT_PLANET_MOON_RADIUS) + 0.2f
		);


		/* **** */
		/* VIEW */
		/* **** */
		if (Keyboard.isDown(KeyConf.CAMERA_VIEW_1)){ setView(0, movable, rotable); }
		if (Keyboard.isDown(KeyConf.CAMERA_VIEW_2)){ setView(1, movable, rotable); }
		if (Keyboard.isDown(KeyConf.CAMERA_VIEW_3)){ setView(2, movable, rotable); }
		if (Keyboard.isDown(KeyConf.CAMERA_VIEW_4)){ setView(3, movable, rotable); }
		if (Keyboard.isDown(KeyConf.CAMERA_VIEW_5)){ setView(4, movable, rotable); }
		if (Keyboard.isDown(KeyConf.CAMERA_VIEW_6)){ setView(5, movable, rotable); }
		if (Keyboard.isDown(KeyConf.CAMERA_VIEW_7)){ setView(6, movable, rotable); }
		if (Keyboard.isDown(KeyConf.CAMERA_VIEW_8)){ setView(7, movable, rotable); }
		if (Keyboard.isDown(KeyConf.CAMERA_VIEW_9)){ setView(8, movable, rotable); }

	}


	protected void setView(int view, Movable movable, Rotable rotable){
		view = view % (Conf.CAMERA_VIEW.length / 2);
		if (view < 0){
			setView((Conf.CAMERA_VIEW.length / 2) + view, movable, rotable);
			return;
		}

		movable.getPosition().set(Conf.CAMERA_VIEW[view * 2]);
		rotable.getOrientation().set(Conf.CAMERA_VIEW[view * 2 + 1]);

		DynaConf.VIEW_ID = view;
	}


	protected float getOrientation(float ori){
		return ((ori <= 180 ? ori : 180-(ori-180) ) - 90) / 90;
	}
	protected float applyLimit(float limit, float value){
		return (value > limit ? limit : value < -limit ? -limit : value);
	}

}
