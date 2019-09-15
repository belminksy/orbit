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

import orbit.engine.render.group.Object3D;
import orbit.utils.in.Resource;
import orbit.utils.math.Vector3f;
import orbit.utils.out.Console;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Planet extends Object3D {

	protected int list = 0;

	protected float radius = 10f;
	protected int quality  = 10;

	protected Sphere sphere;

	protected Vector3f position = new Vector3f();
	protected Vector3f orientation = new Vector3f();

	protected String[] texture_params = new String[2];
	protected Texture texture = null;

	public void update(){ }

	protected void render(){

		GL11.glPushMatrix();
			texture.bind();
			GL11.glColor3f(1, 1, 1);
			GL11.glTranslatef(position.getX(), position.getY(), position.getZ());
			GL11.glRotatef(orientation.getX(), 1, 0, 0);
			GL11.glRotatef(orientation.getY(), 0, 1, 0);
			GL11.glRotatef(orientation.getZ(), 0, 0, 1);
			GL11.glCallList(list);
		GL11.glPopMatrix();

	}


	public void setRadius(float radius){
		this.radius = radius;
	}
	public void setQuality(int quality){
		this.quality = quality;
	}

	public void setTexturePath(String type, String path){
		try {
			texture = TextureLoader.getTexture(type, Resource.getRes(path));
		} catch (Exception e){
			Console.err(e, this);
		}
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


	protected void init(){

		sphere = new Sphere();

		sphere.setDrawStyle(GLU.GLU_FILL);
		sphere.setTextureFlag(true);

		list = GL11.glGenLists(1);

		GL11.glNewList(list, GL11.GL_COMPILE);
			sphere.draw(radius, quality, quality);
		GL11.glEndList();

	}

}
