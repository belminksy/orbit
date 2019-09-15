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

package orbit.engine.render.object;

import orbit.engine.controllers.MovableAndRotableController;
import orbit.engine.controllers.MovableController;
import orbit.engine.controllers.RotableController;
import orbit.engine.group.Movable;
import orbit.engine.group.Rotable;
import orbit.utils.math.Vector3f;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class Camera implements Movable, Rotable {

	protected Vector3f position;
	protected Vector3f orientation;

	protected float speed = 0.3f;

	protected float zNear = 0;
	protected float zFar = 0;
	protected float fov = 0;

	protected MovableController movableController = new MovableController(){
		public void active(Movable movable){ }
	};
	protected RotableController rotableController = new RotableController(){
		public void active(Rotable rotable){ }
	};
	protected MovableAndRotableController movableAndRotableController = new MovableAndRotableController(){
		public void active(Movable movable){ }
		public void active(Rotable rotable){ }
		public void active(Movable movable, Rotable rotable){ }
	};

	public Camera(){
		this(new Vector3f());
	}
	public Camera(Vector3f position){
		this(position, new Vector3f());
	}
	public Camera(Vector3f position, Vector3f orientation){
		this.position = position;
		this.orientation = orientation;
	}


	public void setPosition(Vector3f position){
		this.position = position;
	}
	public Vector3f getPosition(){
		return position;
	}

	public void setOrientation(Vector3f orientation){
		this.orientation = orientation;
	}
	public Vector3f getOrientation(){
		return orientation;
	}


	public Vector3f getForward(){
		Vector3f vector = new Vector3f();

		float cosY = (float) Math.cos(Math.toRadians(orientation.getY() + 90));
		float sinY = (float) Math.sin(Math.toRadians(orientation.getY() + 90));
		float cosP = (float) Math.cos(Math.toRadians(orientation.getX()));
		float sinP = (float) Math.sin(Math.toRadians(orientation.getX()));

		vector.setX(cosY * cosP);
		vector.setY(sinP);
		vector.setZ(sinY * cosP);

		return vector;
	}
	public Vector3f getRight(){
		Vector3f vector = new Vector3f();

		float cosY = (float) Math.cos(Math.toRadians(orientation.getY()));
		float sinY = (float) Math.sin(Math.toRadians(orientation.getY()));

		vector.setX(cosY);
		vector.setZ(sinY);

		return vector;
	}


	public void setSpeed(float speed){
		this.speed = speed;
	}
	public float getSpeed(){
		return speed;
	}


	public void setMovableController(MovableController controller){
		movableController = controller;
	}
	public void setRotableController(RotableController controller){
		rotableController = controller;
	}
	public void setMovableAndRotableController(MovableAndRotableController controller){
		movableAndRotableController = controller;
	}


	public void activeControllers(){
		movableController.active(this);
		rotableController.active(this);
		movableAndRotableController.active((Movable) this);
		movableAndRotableController.active((Rotable) this);
		movableAndRotableController.active((Movable) this, (Rotable) this);
	}


	public void setPerspectiveProjection(float zNear, float zFar, float fov){
		this.zNear = zNear;
		this.zFar = zFar;
		this.fov = fov;
	}
	public void applyPerspectiveProjection(){
		GL11.glEnable(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(fov, (float) Display.getWidth() / (float) Display.getHeight(), zNear, zFar);
		GL11.glEnable(GL11.GL_MODELVIEW);
	}


	public void update(){
		GL11.glPushAttrib(GL11.GL_TRANSFORM_BIT);
		GL11.glRotatef(orientation.getX(), 1, 0, 0);
		GL11.glRotatef(orientation.getY(), 0, 1, 0);
		GL11.glRotatef(orientation.getZ(), 0, 0, 1);
		GL11.glTranslatef(-position.getX(), -position.getY(), -position.getZ());
		GL11.glPopMatrix();
	}

}
