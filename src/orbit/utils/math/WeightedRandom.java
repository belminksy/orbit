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

import java.util.ArrayList;

import orbit.utils.lang.Rand;

public class WeightedRandom {

	protected ArrayList<Point> points = new ArrayList<Point>();

	public WeightedRandom(){ }


	public WeightedRandom pin(float x, float y){

		if (x != 0 && x <= length()) return this;

		points.add(new Point(x, y));

		return this;
	}


	public int size(){
		return points.size();
	}
	public float length(){
		return (size() > 0) ? get(size() - 1).getX() : 0;
	}
	public float first(){
		return (size() > 0) ? get(0).getX() : 0;
	}


	public Point get(int i){
		return points.get(i);
	}


	public Point[] getPointsAround(float x){

		for (int i = 0; i < size(); i++){

			if (get(i).getX() <= x && x <= get(i + 1).getX()){
				return new Point[]{ get(i), get(i + 1) };
			}

		}

		return new Point[]{ new Point(), new Point() };
	}



	public float random(){

		float x = Rand.randomf(first(), length());
		Point[] p = getPointsAround(x);

		return p[0].getY() + ((( x - p[0].getX() ) * 100 / ( p[1].getX() - p[0].getX() )) * (( p[1].getY() - p[0].getY()) / 100 ) );
	}

}
