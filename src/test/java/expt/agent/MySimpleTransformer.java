package expt.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
public class MySimpleTransformer implements ClassFileTransformer {
public byte[] transform(ClassLoader classloader,
String classname,
Class redefinedclass,
ProtectionDomain protectiondomain,
byte b[]) throws IllegalClassFormatException {
if(!classname.endsWith("HelloWorld"))
return(null);
String line ="";
for(int i=0; i < b.length;i++){ 
line += Byte.toString(b[i]) +"";
if(line.length() > 60) {
System.out.println(line);
line ="";
}
if(b[i] == (byte)'6')
b[i] = (byte)'7';
}
System.out.println(line);
System.out.println("The number of bytes in HelloWorld:"+ b.length);
return(b);
}
}