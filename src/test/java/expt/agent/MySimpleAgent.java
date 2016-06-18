package expt.agent;

import java.lang.instrument.Instrumentation;
public class MySimpleAgent {
public static void premain(String agentArgs,Instrumentation inst) {
inst.addTransformer(new MySimpleTransformer());
}
}