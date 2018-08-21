/**
 * @date 19/07/2018.
 * @author Qiannan Lu
 */
public class Main {
	public static com.sun.tools.javac.main.Main.Result compile(String[] args) {
		com.sun.tools.javac.main.Main compiler = new com.sun.tools.javac.main.Main("javac");
		return compiler.compile(args);
	}
}
