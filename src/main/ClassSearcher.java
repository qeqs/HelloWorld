package main;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadim on 28.11.2016.
 */
public class ClassSearcher {

    private static Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(className);
        }
    }
    public static List<Class<?>> processDirectory(File directory, String pkgname) {

        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();

        String[] files = directory.list();
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i];
            String className = null;

            if (fileName.endsWith(".java")) {
                className = pkgname.substring(pkgname.indexOf(".",1)+1) + '.' + fileName.substring(0, fileName.length() - 5);
            }

            if (className != null) {
                classes.add(loadClass(className));
            }

            File subdir = new File(directory, fileName);
            if (subdir.isDirectory()) {
                classes.addAll(processDirectory(subdir, pkgname + '.' + fileName));
            }
        }
        return classes;
    }


    public static List<Class<?>> getClassesForPackage(Package pkg) {
        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();

        String pkgname = pkg.getName();
        String relPath = pkgname.replace('.', '/');

        URL resource = ClassLoader.getSystemClassLoader().getResource(relPath);

        if (resource == null) {
            throw new RuntimeException(relPath);
        }

        classes.addAll(processDirectory(new File(resource.getPath()), pkgname));


        return classes;
    }
    public static List<Class<?>> getClassesForPackageAndAnnotation(File directory, String pkgname,Class<? extends Annotation> annotation){
        List<Class<?>> classes = processDirectory(directory, pkgname);
        for(int i = 0; i<classes.size();i++)
            if(!classes.get(i).isAnnotationPresent(annotation))
                classes.remove(i);
        return classes;
    }
}
