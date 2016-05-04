package expt.jni;

/**
 * Created by yinghao_niu on 2016/2/1 0001 for Project.
 */
public class Jni {

	public static final String JAVA_LIBRARY_PATH = "java.library.path";

	public  static  native void test();


	static {
		String property = System.getProperty(JAVA_LIBRARY_PATH);
		System.setProperty(JAVA_LIBRARY_PATH, property + ";D:\\my\\StarRedflow\\Program\\target\\classes");
		System.out.println(System.getProperty(JAVA_LIBRARY_PATH));
//		System.loadLibrary("test");
		System.load("D:\\vagrant\\pj\\star\\src\\test\\java\\expt\\jni\\test.dll");
	}

	String uid;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public static void main(String[] args) {
		Jni.test();
	}
}
