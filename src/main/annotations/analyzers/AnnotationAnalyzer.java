package main.annotations.analyzers;

/**
 * Created on 27.11.2016.
 */
public interface AnnotationAnalyzer {
	void analyze(Class<?> cl,Object instance);
}
