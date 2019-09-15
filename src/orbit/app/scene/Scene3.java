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
import orbit.app.conf.KeyConf;
import orbit.app.object.Satellite;
import orbit.engine.render.group.Scene;
import orbit.main.Main;
import orbit.utils.in.Keyboard;
import orbit.utils.lang.Color;
import orbit.utils.lang.Interval;
import orbit.utils.lang.Rand;
import orbit.utils.lang.Size;
import orbit.utils.math.Vector3f;

public class Scene3 extends Scene {

	protected Satellite iridium33;
	protected Satellite kosmos2251;

	protected Satellite[] debris = new Satellite[500];

	protected Vector3f top = new Vector3f(10.403761f, 35.488384f, -1.1559734f);

	protected float iridiumAngle = 90f; // bleu
	protected float kosmosAngle  = 0f; // jaune
	protected float randomAngle = 10;

	protected float pointPrecision = 0.3f;

	protected boolean collision = false;
	protected boolean stopbefore = false;

	public void update(){

		if (stopbefore && iridium33.getProgress() >= 25 - (4 * iridium33.getSpeed())){
			Main.orbit.pause();
			stopbefore = false;
		}
		if (iridium33.getProgress() >= 25){
			iridium33.hide();
			kosmos2251.hide();

			for (int i = 0; i < debris.length; i++){
				debris[i].show();
			}

			collision = true;
		}


		if (!collision){
			iridium33.update();
			kosmos2251.update();
		} else {
			for (int i = 0; i < debris.length; i++){
				debris[i].update();
			}
		}


		if (Keyboard.isDown(KeyConf.SCENE_ACTION_1) && Interval.isValid(Conf.INTERVAL_SCENE_ACTION)){
			iridium33.show();
			iridium33.setProgress(0);

			kosmos2251.show();
			kosmos2251.setProgress(0);

			for (int i = 0; i < debris.length; i++){
				debris[i].hide();
				debris[i].setProgress(25);
			}

			stopbefore = false;
			collision = false;
		}
		if (Keyboard.isDown(KeyConf.SCENE_ACTION_2) && Interval.isValid(Conf.INTERVAL_SCENE_ACTION)){
			if (!collision) stopbefore = !stopbefore;
		}
	}

	public void render(){

		iridium33.draw();
		kosmos2251.draw();

		for (int i = 0; i < debris.length; i++){
			debris[i].draw();
		}

	}


	public void load(){

		iridium33  = new Satellite();
		kosmos2251 = new Satellite();

		iridium33.setColor(new Color(0, 1, 1));
		iridium33.setSpeed(0.04f);
		iridium33.setRadius(Size.resizef(Conf.OBJECT_PLANET_EARTH_RADIUS + 1000));
		iridium33.setProgress(0);
		iridium33.setOrbit(top, new Vector3f(0, 0, 0));
		iridium33.setAngle(iridiumAngle);
		iridium33.changePoints(1, 0, 3, 2);

		kosmos2251.setColor(new Color(1, 1, 0));
		kosmos2251.setSpeed(0.04f);
		kosmos2251.setRadius(Size.resizef(Conf.OBJECT_PLANET_EARTH_RADIUS + 1000));
		kosmos2251.setProgress(0);
		kosmos2251.setOrbit(top, new Vector3f(0, 0, 0));
		kosmos2251.setAngle(kosmosAngle);
		kosmos2251.changePoints(3, 0, 1, 2);

		for (int i = 0; i < debris.length; i++){

			debris[i] = new Satellite();

			debris[i].setSpeed(Rand.randomf(0.02f, 0.07f));
			debris[i].setSize(0.13f);
			debris[i].setQuality(3);
			debris[i].setRadius(Size.resizef(Conf.OBJECT_PLANET_EARTH_RADIUS + 1000));
			debris[i].setProgress(25);
			debris[i].setTurn(new Vector3f(Rand.randomf(0, 5), Rand.randomf(0, 5), Rand.randomf(0, 5)));
			debris[i].setOrbit(
				new Vector3f(top.getX() + Rand.randomf(-pointPrecision, pointPrecision), top.getY() + Rand.randomf(-pointPrecision, pointPrecision), top.getZ()),
				new Vector3f(0, 0, 0)
			);

			if (i < debris.length / 2){

				debris[i].setColor(new Color(0, 1, 1));
				debris[i].setAngle(iridiumAngle + Rand.randomf(-randomAngle, randomAngle));
				debris[i].changePoints(1, 0, 3, 2);

			} else {

				debris[i].setColor(new Color(1, 1, 0));
				debris[i].setAngle(kosmosAngle + Rand.randomf(-randomAngle, randomAngle));
				debris[i].changePoints(3, 0, 1, 2);

			}

			debris[i].hide();

		}

		collision = false;
		stopbefore = false;

	}

	public void unload(){

		iridium33 = null;
		kosmos2251 = null;

		debris = new Satellite[debris.length];

	}

}
