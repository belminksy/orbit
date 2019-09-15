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

package orbit.app.scene;

import orbit.app.conf.Conf;
import orbit.app.object.Satellite;
import orbit.engine.render.group.Scene;
import orbit.utils.lang.Color;
import orbit.utils.lang.Rand;
import orbit.utils.lang.Size;
import orbit.utils.math.Vector3f;

public class Scene2 extends Scene {

	protected Satellite[] iridium = new Satellite[72];

	protected int[] horizontals = new int[]{
		0, 30, 60, 90, 120, 150
	};

	public void update(){

		for (int i = 0; i < iridium.length; i++){
			iridium[i].update();
		}

	}

	public void render(){

		for (int i = 0; i < iridium.length; i++){
			iridium[i].draw();
		}

	}


	public void load(){

		for (int i = 0; i < iridium.length; i++){

			iridium[i] = new Satellite();

			iridium[i].setColor(new Color(0, 1, 1));
			iridium[i].setSpeed(0.025f);
			iridium[i].setSize(0.2f);
			iridium[i].setRadius(Rand.randomf(Size.resizef(Conf.OBJECT_PLANET_EARTH_RADIUS + 100), Size.resizef(Conf.OBJECT_PLANET_EARTH_RADIUS + 2000)));
			iridium[i].setProgress((float) ((100 / (iridium.length / horizontals.length)) * Math.floor(i / horizontals.length)));
			iridium[i].setOrbit(new Vector3f(0, 40, 0), new Vector3f());
			iridium[i].setAngle(horizontals[i % horizontals.length] + Rand.randomf(-2, 2));
			iridium[i].notifyOrbit();

			if ((i % horizontals.length) % 2 == 1) iridium[i].changePoints(0, 3, 2, 1);

		}

	}

	public void unload(){

		iridium = new Satellite[iridium.length];

	}

}
