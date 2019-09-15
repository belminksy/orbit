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

package orbit.engine.render.manager;

import java.util.ArrayList;

import orbit.engine.render.group.Scene;
import orbit.utils.out.Console;

public class SceneManager {

	protected ArrayList<Scene> list = new ArrayList<Scene>();
	protected Scene master;

	protected int current = 0;

	protected boolean onlyMaster = false;
	protected boolean ignoreScene = false;

	public SceneManager(){ }


	public void add(Scene scene){
		list.add(scene);
		if (list.size() == 1 && !onlyMaster) load(getSelected());
	}
	public void add(Scene... scenes){
		for (int i = 0; i < scenes.length; i++){
			add(scenes[i]);
		}
	}
	public void setMaster(Scene master){
		this.master = master;
		load(master, true);
	}


	public void beginOnlyMaster(boolean only){
		onlyMaster = only;
	}


	public Scene getSelected(){
		return list.get(current);
	}


	public void next(){
		if (onlyMaster){
			onlyMaster = false;
			load(getSelected());
			return;
		}

		getSelected().unload();

		current++;
		if (current == list.size()) current = 0;

		ignoreScene = false;
		load(getSelected());
	}
	public void prev(){
		if (onlyMaster){
			onlyMaster = false;
		}

		getSelected().unload();

		current--;
		if (current < 0) current = list.size() - 1;

		ignoreScene = false;
		load(getSelected());
	}


	public void clear(){
		getSelected().unload();

		current = 0;
		onlyMaster = true;
	}

	protected void load(Scene scene){
		load(scene, false);
	}
	protected void load(Scene scene, boolean ismaster){
		Console.info("Loading " + (ismaster ? "master scene" : "scene " + current) + " ...", this);

		try {
			scene.load();
			Console.info((ismaster ? "Master scene" : "Scene " + current) + " loaded", this);
		} catch (Exception e){
			Console.info("Error while loading " + (ismaster ? "master scene" : "scene " + current), this);
			Console.err(e, this);

			ignoreScene = true;

		}
	}


	public void update(){
		if (master != null) master.update();
		if (onlyMaster) return;
		if (ignoreScene) return;
		getSelected().update();
	}
	public void render(){
		if (master != null) master.render();
		if (onlyMaster) return;
		if (ignoreScene) return;
		getSelected().render();
	}

}
