package basic.complier;

import javax.tools.*;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * @author Qiannan Lu
 * @date 19/07/2018.
 */
public class Bootstrap {
    private static final String FILE_TO_COMPLIER = "C:\\workspace\\coding-lab\\java\\coding-common\\src\\main\\resources\\Cifa.java";
    private static JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.usingDiagnosticListener();
        bootstrap.usingDiagnosticCollector();
        try {
            bootstrap.showAccessibleOfField();
            bootstrap.complierFromStream();
        } catch (URISyntaxException | IllegalAccessException | InstantiationException | ClassNotFoundException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void showAccessibleOfField() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        int result = compiler.run(null, null, null, FILE_TO_COMPLIER);
        System.out.println("showAccessibleOfField() compiler result " + result);
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class cifa = classLoader.loadClass("Cifa");
        Object o = cifa.newInstance();

        for (Field field : cifa.getDeclaredFields()) {

            // Refer to https://stackoverflow.com/questions/32024118/java-reflection-isaccessible-method
            // https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/AccessibleObject.html
            System.out.println(field.isAccessible());

            if (Modifier.isPublic(field.getModifiers())) {
                System.out.println("Cifa's field " + field.getName() + "->" + field.get(o));
            }
        }

    }

    private void usingDiagnosticListener() {

        // DiagnosticListener用于获取Diagnostic信息，Diagnostic信息包括：错误，警告和说明性信息
        CustomizeDiagnosticListener diagnosticListener = new CustomizeDiagnosticListener();
        // JavaFileManager:用于管理与工具有关的所有文件，这里的文件可以是内存数据，也可以是Socket数据，而不仅仅是磁盘文件。
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnosticListener, Locale.ENGLISH, Charset.forName("UTF-8"));
        // JavaFileObjects: 是java源码文件(.java)和class文件(.class)的抽象
        Iterable<? extends JavaFileObject> javaFileObjects = fileManager.getJavaFileObjects(FILE_TO_COMPLIER);
        // 编译任务
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnosticListener, null, null, javaFileObjects);
        Boolean result = task.call();
        System.out.println("usingDiagnosticListener(): " + result);
    }

    private void usingDiagnosticCollector() {
//        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        DiagnosticCollector diagnosticCollector = new DiagnosticCollector();
        // JavaFileManager:用于管理与工具有关的所有文件，这里的文件可以是内存数据，也可以是Socket数据，而不仅仅是磁盘文件。
        StandardJavaFileManager standardFileManager = compiler.getStandardFileManager(diagnosticCollector, Locale.ENGLISH, Charset.forName("utf-8"));
        // JavaFileObjects: 是java源码文件(.java)和class文件(.class)的抽象
        Iterable<? extends JavaFileObject> fileObjects = standardFileManager.getJavaFileObjects(FILE_TO_COMPLIER);

        // 编译任务
        JavaCompiler.CompilationTask task = compiler.getTask(null, standardFileManager, diagnosticCollector, null, null, fileObjects);
        Boolean result = task.call();
        System.out.println("usingDiagnosticCollector(): " + result);
        List list = diagnosticCollector.getDiagnostics();
        for (Object object : list) {
            Diagnostic d = (Diagnostic) object;
            System.out.println(d.getMessage(Locale.ENGLISH));
        }
    }

    private void complierFromStream() throws URISyntaxException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        // 获取JavaCompiler
//        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        // DiagnosticListener用于获取Diagnostic信息，Diagnostic信息包括：错误，警告和说明性信息
        // MyDiagnosticListener diagnosticListener = new MyDiagnosticListener();
        DiagnosticCollector diagnosticCollector = new DiagnosticCollector();

        // JavaFileManager:用于管理与工具有关的所有文件，这里的文件可以是内存数据，也可以是Socket数据，而不仅仅是磁盘文件。
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnosticCollector, Locale.ENGLISH, Charset.forName("utf-8"));
        /**
         * 组装java源码并返回JavaFileObject
         */
        String javaFileContent = "public class TestClass { "
                + "    public void testMethod() { "
                + "        System.out.println(\"test\");"
                + "    }"
                + "}";
        JavaObjectFromString javaSourceObject = new JavaObjectFromString("TestClass", javaFileContent);

        // JavaFileObjects: 是java源码文件(.java)和class文件(.class)的抽象
        Iterable<? extends JavaFileObject> javaFileObjects = Collections.singletonList(javaSourceObject);


        String flag = "-d";
        String outDir = "";
        try {
            File classPath = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
            outDir = classPath.getAbsolutePath() + File.separator;
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
        Iterable<String> options = Arrays.asList(flag, outDir);
        // 编译任务
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnosticCollector, options, null, javaFileObjects);
        Boolean result = task.call();

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class test = classLoader.loadClass("TestClass");

        Object instance = test.newInstance();
        for (Method method : test.getMethods()) {
            if (method.getName().equals("testMethod")) {
                System.out.print("The testMethod of TestClass invoke result: ");
                method.invoke(instance);
            }
        }

        /**
         * 输出结果
         */
        System.out.println(result);
        List list = diagnosticCollector.getDiagnostics();
        for (Object object : list) {
            Diagnostic d = (Diagnostic) object;
            System.out.println(d.getMessage(Locale.ENGLISH));
        }
    }
//	public static com.sun.tools.javac.main.Main.Result compile(String[] args) {
//		com.sun.tools.javac.main.Main compiler = new com.sun.tools.javac.main.Main("javac");
//		return compiler.compile(args);
//	}

    static class JavaObjectFromString extends SimpleJavaFileObject {
        // 存放java源码内容
        private String contents;

        public JavaObjectFromString(String className, String contents) throws URISyntaxException {
            super(URI.create("string:///" + className.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
//            super(new URI(className), Kind.SOURCE);
            this.contents = contents;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return contents;
        }
    }

    static class CustomizeDiagnosticListener implements DiagnosticListener {
        @Override
        public void report(Diagnostic diagnostic) {
            System.out.println("Code->" + diagnostic.getCode());
            System.out.println("Column Number->" + diagnostic.getColumnNumber());
            System.out.println("End Position->" + diagnostic.getEndPosition());
            System.out.println("Kind->" + diagnostic.getKind());
            System.out.println("Line Number->" + diagnostic.getLineNumber());
            System.out.println("Message->" + diagnostic.getMessage(Locale.ENGLISH));
            System.out.println("Position->" + diagnostic.getPosition());
            System.out.println("Source" + diagnostic.getSource());
            System.out.println("Start Position->" + diagnostic.getStartPosition());
            System.out.println("\n");
        }
    }
}
