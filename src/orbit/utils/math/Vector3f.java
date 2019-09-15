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

package orbit.utils.math;

public class Vector3f {

	protected float x;
	protected float y;
	protected float z;

	public Vector3f(){
		this(0, 0, 0);
	}
	public Vector3f(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}


	public void setX(float x){
		this.x = x;
	}
	public void setY(float y){
		this.y = y;
	}
	public void setZ(float z){
		this.z = z;
	}


	public void addX(float x){
		this.x += x;
	}
	public void addY(float y){
		this.y += y;
	}
	public void addZ(float z){
		this.z += z;
	}


	public Vector3f subX(float x){
		this.x -= x;
		return this;
	}
	public Vector3f subY(float y){
		this.y -= y;
		return this;
	}
	public Vector3f subZ(float z){
		this.z -= z;
		return this;
	}


	public void mulX(float x){
		this.x *= x;
	}
	public void mulY(float y){
		this.y *= y;
	}
	public void mulZ(float z){
		this.z *= z;
	}


	public Vector3f set(Vector3f vector){
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
		return this;
	}
	public Vector3f add(Vector3f vector){
		this.x += vector.x;
		this.y += vector.y;
		this.z += vector.z;
		return this;
	}
	public Vector3f sub(Vector3f vector){
		this.x += vector.x;
		this.y += vector.y;
		this.z += vector.z;
		return this;
	}
	public Vector3f mul(Vector3f vector){
		this.x *= vector.x;
		this.y *= vector.y;
		this.z *= vector.z;
		return this;
	}


	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public float getZ(){
		return z;
	}


	public Vector3f setLength(float length){
		float ratio = (float) (length / getLength());

		this.x *= ratio;
		this.y *= ratio;
		this.z *= ratio;

		return this;
	}
	public float getLength(){
		return (float) Math.sqrt( Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2) );
	}


	public boolean invisbleSphere(Vector3f origin, float radius){
		if (copy().from(origin).getLength() < radius){
			set(origin.copy().add( copy().from(origin).setLength(radius) ));
			return true;
		}

		return false;
	}


	public Vector3f from(Vector3f origin){
		this.x -= origin.x;
		this.y -= origin.y;
		this.z -= origin.z;

		return this;
	}
	public Vector3f inverse(){
		this.x = -x;
		this.y = -y;
		this.z = -z;

		return this;
	}
	public Vector3f copy(){
		return new Vector3f(x, y, z);
	}
	public void clear(){
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

}
