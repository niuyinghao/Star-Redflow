package expt.agent;

/**
 *   jar -cfm MyAgent.jar MANIFEST.MF expt\agent\MySimpleAgent.class expt\agent\MySimpleTransformer.class
 *  其中的agentmantext的内容如下：
 * Premain-Class: MySimpleAgent
 * java -javaagent:MyAgent.jar HelloWorld
 */
public class HelloWorld {
public static void main(String arg[]) {
System.out.println("The number six is 6");
}
}