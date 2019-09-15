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

import org.lwjgl.input.Keyboard;

public class KeyConf {

	public static final int[] MOVE_UP    = new int[]{ Keyboard.KEY_W, Keyboard.KEY_Z };
	public static final int[] MOVE_DOWN  = new int[]{ Keyboard.KEY_S };
	public static final int[] MOVE_LEFT  = new int[]{ Keyboard.KEY_A, Keyboard.KEY_Q };
	public static final int[] MOVE_RIGHT = new int[]{ Keyboard.KEY_D };

	public static final int[] MOVE_PLANE_UP    = new int[]{ Keyboard.KEY_UP };
	public static final int[] MOVE_PLANE_DOWN  = new int[]{ Keyboard.KEY_DOWN };
	public static final int[] MOVE_PLANE_LEFT  = new int[]{ Keyboard.KEY_LEFT };
	public static final int[] MOVE_PLANE_RIGHT = new int[]{ Keyboard.KEY_RIGHT };

	public static final int[] MOVE_JUMP  = new int[]{ Keyboard.KEY_SPACE };
	public static final int[] MOVE_SHIFT = new int[]{ Keyboard.KEY_LCONTROL };

	/*public static final String[] GAMEPAD_MOVE_Y   = new String[]{ "y" };
	public static final String[] GAMEPAD_MOVE_X     = new String[]{ "x" };
	public static final String[] GAMEPAD_ROTATION_Y = new String[]{ "rz" };
	public static final String[] GAMEPAD_ROTATION_X = new String[]{ "slider" };
	public static final String[] GAMEPAD_JUMP       = new String[]{ "A" };
	public static final String[] GAMEPAD_SHIFT      = new String[]{ "B" };
	public static final String[] GAMEPAD_SCENE_NEXT = new String[]{ "Right Thumb" };
	public static final String[] GAMEPAD_SCENE_PREV = new String[]{ "Left Thumb" };
	public static final String[] GAMEPAD_VIEW_NEXT  = new String[]{ "Right Thumb 2" };
	public static final String[] GAMEPAD_VIEW_PREV  = new String[]{ "Left Thumb 2" };*/

	public static final String[] GAMEPAD_MOVE_X     = new String[]{ "x" };
	public static final String[] GAMEPAD_MOVE_Y     = new String[]{ "y" };
	public static final String[] GAMEPAD_ROTATION_V = new String[]{ "rz" };
	public static final String[] GAMEPAD_ROTATION_H	= new String[]{ "rx" };
	public static final String[] GAMEPAD_JUMP       = new String[]{ "Pinkie" };
	public static final String[] GAMEPAD_SHIFT      = new String[]{ "Top 2" };
	public static final String[] GAMEPAD_SCENE_NEXT = new String[]{ "Thumb" };
	public static final String[] GAMEPAD_SCENE_PREV = new String[]{ "Thumb 2" };
	public static final String[] GAMEPAD_VIEW_NEXT  = new String[]{ "Trigger" };
	public static final String[] GAMEPAD_VIEW_PREV  = new String[]{ "Top" };
	public static final String[] GAMEPAD_PLAY_PAUSE = new String[]{ "Base 2" };
	public static final String[] GAMEPAD_ROTATE     = new String[]{ "Base" };

	public static final int[] SCENE_NEXT = new int[]{ Keyboard.KEY_ADD, Keyboard.KEY_M };
	public static final int[] SCENE_PREV = new int[]{ Keyboard.KEY_SUBTRACT, Keyboard.KEY_L };

	public static final int[] SCENE_ACTION_1 = new int[]{ Keyboard.KEY_NUMPAD1 };
	public static final int[] SCENE_ACTION_2 = new int[]{ Keyboard.KEY_NUMPAD2 };
	public static final int[] SCENE_ACTION_3 = new int[]{ Keyboard.KEY_NUMPAD3 };
	public static final int[] SCENE_ACTION_4 = new int[]{ Keyboard.KEY_NUMPAD4 };
	public static final int[] SCENE_ACTION_5 = new int[]{ Keyboard.KEY_NUMPAD5 };
	public static final int[] SCENE_ACTION_6 = new int[]{ Keyboard.KEY_NUMPAD6 };
	public static final int[] SCENE_ACTION_7 = new int[]{ Keyboard.KEY_NUMPAD7 };
	public static final int[] SCENE_ACTION_8 = new int[]{ Keyboard.KEY_NUMPAD8 };
	public static final int[] SCENE_ACTION_9 = new int[]{ Keyboard.KEY_NUMPAD9 };

	public static final int[] CAMERA_VIEW_1 = new int[]{ Keyboard.KEY_1 };
	public static final int[] CAMERA_VIEW_2 = new int[]{ Keyboard.KEY_2 };
	public static final int[] CAMERA_VIEW_3 = new int[]{ Keyboard.KEY_3 };
	public static final int[] CAMERA_VIEW_4 = new int[]{ Keyboard.KEY_4 };
	public static final int[] CAMERA_VIEW_5 = new int[]{ Keyboard.KEY_5 };
	public static final int[] CAMERA_VIEW_6 = new int[]{ Keyboard.KEY_6 };
	public static final int[] CAMERA_VIEW_7 = new int[]{ Keyboard.KEY_7 };
	public static final int[] CAMERA_VIEW_8 = new int[]{ Keyboard.KEY_8 };
	public static final int[] CAMERA_VIEW_9 = new int[]{ Keyboard.KEY_9 };

	public static final int[] PLAY_PAUSE = new int[]{ Keyboard.KEY_NUMPAD0, Keyboard.KEY_P };

	public static final int[] ESCAPE = new int[]{ Keyboard.KEY_ESCAPE };

}
