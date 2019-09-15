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

package orbit.utils.out;

import java.io.PrintStream;

public class Console {

	public static void print(PrintStream stream, String message){
		stream.println(message);
	}
	public static void print(PrintStream stream, String tag, String message, Class<? extends Object> cl){
		Console.print(stream, "[" + tag.toUpperCase() + "] " + cl.getCanonicalName() + " : " + message);
	}


	public static void info(String message, Class<? extends Object> cl){
		Console.print(System.out, "info", message, cl);
	}
	public static void info(String message, Object obj){
		Console.info(message, obj.getClass());
	}


	public static void err(String message, Class<? extends Object> cl){
		Console.print(System.err, "err", message, cl);
	}
	public static void err(String message, Object obj){
		Console.err(message, obj.getClass());
	}
	public static void err(Exception e, Class<? extends Object> cl){
		Console.err(e.getMessage(), cl);
	}
	public static void err(Exception e, Object obj){
		Console.err(e.getMessage(), obj);
	}

}
