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

package orbit.app.conf;

import orbit.utils.lang.Size;
import orbit.utils.math.Vector3f;

public class Conf {

	public static final String APP_NAME = "Orbit";
	public static final String APP_VERSION = "1.0";

	public static final String[] APP_SPECIFICATIONS = new String[]{
		"lwjgl2",
		"jinput2.0.7"
	};

	public static final String WINDOW_TITLE = APP_NAME + " " + APP_VERSION;
	public static final int WINDOW_WIDTH = 720;
	public static final int WINDOW_HEIGHT = 480;
	public static final boolean WINDOW_RESIZABLE = true;

	public static final double ENGINE_MAX_TPS = 60;
	public static final double ENGINE_MAX_FPS = 60;

	public static final float ENGINE_CAMERA_ZNEAR = 0.1f;
	public static final float ENGINE_CAMERA_ZFAR = 5000f;
	public static final float ENGINE_CAMERA_FOV = 70f;

	public static final int INTERVAL_SCENE_CHANGE = 0;
	public static final int INTERVAL_SCENE_ACTION = 1; 

	public static final double[] INTERVALS = new double[]{
		300, // scene change
		300  // scene action
	};

	public static final int ORBIT_LOW_PERCENT_SATELLITE = 97;
	public static final float ORBIT_GEOSTATIONARY_ALTITUDE = 35786f;

	public static final float OBJECT_PLANET_EARTH_RADIUS = 6371f;
	public static final float OBJECT_PLANET_MOON_RADIUS = 1737f;
	public static final float OBJECT_PLANET_SUN_RADIUS = 4000;

	public static final Vector3f OBJECT_PLANET_EARTH_ORIGIN = new Vector3f(0, 0, 0);
	public static final Vector3f OBJECT_PLANET_MOON_ORIGIN = new Vector3f(Size.resizef(384400), 0, 0);
	public static final Vector3f OBJECT_PLANET_SUN_ORIGIN = new Vector3f(Size.resizef(-384400), 0, 0);

	public static final float ROTATE_INCR = 0.1f;
	public static final float ROTATE_DEFAULT = 0;
	public static final long  ROTATE_RESET_TIME = 1000;

	public static final Vector3f[] CAMERA_VIEW = new Vector3f[]{
		new Vector3f(0, 0, 100), new Vector3f(0, 0, 0),
		new Vector3f(0, 15, 270), new Vector3f(0, 0, 0),
		new Vector3f(0, 42, 42), new Vector3f(45, 0, 0),
		new Vector3f(-70, 0, 30), new Vector3f(0, 90, 0),
		new Vector3f(-8, 25, 30), new Vector3f(13, 63, 0),
		new Vector3f(1935, 0, 14), new Vector3f(0, 295, 0),
		new Vector3f(32, 60, 8), new Vector3f(60, -74, 0)
	};

}
