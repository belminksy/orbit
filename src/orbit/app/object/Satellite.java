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

package orbit.app.object;

import orbit.app.conf.DynaConf;
import orbit.engine.render.group.Object3D;
import orbit.utils.lang.Color;
import orbit.utils.lang.Rand;
import orbit.utils.math.Vector3f;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;

public class Satellite extends Object3D {

	protected int list = 0;

	protected float progress = 0;
	protected float speed = 0.05f;
	protected int point = 0;

	protected float radius = 35f;
	protected float size = 0.3f;
	protected int quality = 10;

	protected Color color = new Color(1, 1, 1);
	protected Vector3f position = new Vector3f();
	protected Vector3f orientation = new Vector3f();

	protected Vector3f origin = new Vector3f();
	protected Vector3f turn = new Vector3f();

	protected Vector3f[] points = new Vector3f[]{
		new Vector3f(0, 0, radius),
		new Vector3f(0, radius, 0),
		new Vector3f(0, 0, -radius),
		new Vector3f(0, -radius, 0)
	};

	public void update(){

		progress += speed;
		if (progress >= 100) progress -= 100;

		point = (progress >= 75 ? 3 : progress >= 50 ? 2 : progress >= 25 ? 1 : 0);
		int next = (point == points.length - 1 ? 0 : point + 1);

		position.setX( points[point].getX() + ((points[next].getX() - points[point].getX()) * (progress % 25 / 25)) );
		position.setY( points[point].getY() + ((points[next].getY() - points[point].getY()) * (progress % 25 / 25)) );
		position.setZ( points[point].getZ() + ((points[next].getZ() - points[point].getZ()) * (progress % 25 / 25)) );

		position.setLength(radius);
		position.add(origin);

		orientation.addX(turn.getX());
		orientation.addY(turn.getY());
		orientation.addZ(turn.getZ());
	}

	protected void render(){

		GL11.glPushMatrix();
			GL11.glColor3f(color.r, color.g, color.b);
			GL11.glTranslatef(position.getX(), position.getY(), position.getZ());
			GL11.glRotatef(orientation.getX(), 1, 0, 0);
			GL11.glRotatef(orientation.getY(), 0, 1, 0);
			GL11.glRotatef(orientation.getZ(), 0, 0, 1);
			GL11.glCallList(list);
		GL11.glPopMatrix();

		if (DynaConf.SHOW_SATELLITE_ORBIT){
			GL11.glColor3f(1.0f, 0.0f, 0.0f);
			GL11.glBegin(GL11.GL_LINE_STRIP);
			GL11.glVertex3d(points[0].getX(), points[0].getY(), points[0].getZ());
			GL11.glVertex3d(points[1].getX(), points[1].getY(), points[1].getZ());
			GL11.glVertex3d(points[2].getX(), points[2].getY(), points[2].getZ());
			GL11.glVertex3d(points[3].getX(), points[3].getY(), points[3].getZ());
			GL11.glVertex3d(points[0].getX(), points[0].getY(), points[0].getZ());
			GL11.glEnd();
		}

	}


	public void setProgress(float progress){
		this.progress = progress;
	}
	public void setSpeed(float speed){
		this.speed = speed;
	}
	public void setRadius(float radius){
		this.radius = radius;
	}
	public void setSize(float size){
		this.size = size;
	}
	public void setQuality(int quality){
		this.quality = quality;
	}
	public void setColor(Color color){
		this.color = color;
	}

	public void setOrigin(Vector3f origin){
		this.origin = origin;
	}
	public void setTurn(Vector3f vector){
		turn.set(vector);
	}

	public void setOrbit(Vector3f p1, Vector3f p2){
		points[0] = p1;
		points[1] = p2;

		points[0].setLength(radius);
		points[1].setLength(radius);

		points[2] = p1.copy().inverse();
		points[3] = p2.copy().inverse();
	}
	public void setAngle(float angle){
		angle = (float) ((angle-90) / 180 * Math.PI);

		points[1].setX((float) (points[0].getY() * Math.cos(angle)));
		points[1].setY((float) (-points[0].getX() * Math.cos(angle)));
		points[1].setZ((float) (radius * Math.sin(angle)));

		points[3] = points[1].copy().inverse();
	}


	public void randomOrbit(){

		float vertical   = Rand.randomf(0, (float) Math.PI);
		float horizontal = Rand.randomf(-180, 180);

		points[0].setX((float) (radius * Math.cos(vertical)));
		points[0].setY((float) (radius * Math.sin(vertical)));

		setAngle(horizontal);

		points[2] = points[0].copy().inverse();

	}


	public float getProgress(){
		return progress;
	}
	public float getSpeed(){
		return speed;
	}


	public void changePoints(int p0, int p1, int p2, int p3){

		Vector3f[] copy = new Vector3f[]{
			points[0].copy(),
			points[1].copy(),
			points[2].copy(),
			points[3].copy()
		};

		points[0] = copy[p0];
		points[1] = copy[p1];
		points[2] = copy[p2];
		points[3] = copy[p3];

	}


	public void notifyOrbit(){
		points[0].setLength(radius);
		points[1].setLength(radius);
		points[2].setLength(radius);
		points[3].setLength(radius);
	}


	protected void init(){

		list = GL11.glGenLists(1);

		GL11.glNewList(list, GL11.GL_COMPILE);
			new Sphere().draw(size, quality, quality);
		GL11.glEndList();

	}

}
