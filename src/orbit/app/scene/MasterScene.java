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
import orbit.app.conf.DynaConf;
import orbit.app.conf.ResConf;
import orbit.app.object.Planet;
import orbit.engine.render.group.Scene;
import orbit.utils.lang.Size;
import orbit.utils.math.Vector3f;
import orbit.utils.ui.LoadingDialog;

public class MasterScene extends Scene {

	protected Planet earth;
	protected Planet moon;
	protected Planet sun;

	public void update(){

	}

	public void render(){

		if (!DynaConf.ROTATE_ACTIVATED && !DynaConf.ROTATE_RESET){
			moon.draw();
			sun.draw();
		}

		earth.draw();

	}


	public void load(){

		LoadingDialog dialog = new LoadingDialog().setTitle("Load Master Scene").setIndeterminate(true).create();

		earth = new Planet();

		earth.setPosition(Conf.OBJECT_PLANET_EARTH_ORIGIN);
		earth.setOrientation(new Vector3f(90, 0, 180));
		earth.setRadius(Size.resizef(Conf.OBJECT_PLANET_EARTH_RADIUS));
		earth.setQuality(30);

		dialog.setStatus("Loading " + ResConf.TEXTURE_PLANET_EARTH);
		earth.setTexturePath(ResConf.TEXTURE_PLANET_EARTH_TYPE, ResConf.TEXTURE_PLANET_EARTH);


		moon = new Planet();

		moon.setPosition(Conf.OBJECT_PLANET_MOON_ORIGIN);
		moon.setOrientation(new Vector3f(90, 0, 0));
		moon.setRadius(Size.resizef(Conf.OBJECT_PLANET_MOON_RADIUS));
		moon.setQuality(20);

		dialog.setStatus("Loading " + ResConf.TEXTURE_PLANET_MOON);
		moon.setTexturePath(ResConf.TEXTURE_PLANET_MOON_TYPE, ResConf.TEXTURE_PLANET_MOON);


		sun = new Planet();

		sun.setPosition(Conf.OBJECT_PLANET_SUN_ORIGIN);
		sun.setRadius(Size.resizef(Conf.OBJECT_PLANET_SUN_RADIUS));
		sun.setQuality(20);

		dialog.setStatus("Loading " + ResConf.TEXTURE_PLANET_SUN);
		sun.setTexturePath(ResConf.TEXTURE_PLANET_SUN_TYPE, ResConf.TEXTURE_PLANET_SUN);


		dialog.destroy();

	}

	public void unload(){

		earth = null;
		moon = null;
		sun = null;

	}

}
