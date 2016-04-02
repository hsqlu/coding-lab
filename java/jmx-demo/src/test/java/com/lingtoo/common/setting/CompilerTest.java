package com.lingtoo.common.setting;

import javax.tools.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created: 2016/1/18.
 * Author: Qiannan Lu
 */
public class CompilerTest {
    public void test() throws URISyntaxException {
        String source = "";
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        StringSourceJavaObject sourceObject = new CompilerTest.StringSourceJavaObject("Main", source);
//        Iterator<? extends JavaFileObject> fileObjects = Arrays.asList(sourceObject);
//        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, fileObjects);
//        boolean result = task.call();
//        if (result)
//            System.out.println("编译成功。");
    }
    static class StringSourceJavaObject extends SimpleJavaFileObject {
        private String content = null;

        /**
         * Construct a SimpleJavaFileObject of the given kind and with the
         * given URI.
         *
         * @param name  the URI for this file object
         * @param content the kind of this file object
         */
        public StringSourceJavaObject(String name, String content) throws URISyntaxException {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.content = content;
        }

        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return content;
        }
    }
}
