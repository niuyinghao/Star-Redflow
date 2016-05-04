package expt.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

/**
 * Created by yinghao_niu on 2016/4/2 for Project.
 */
public class ModClass {
	///////入口启动函数

	public static void main(String[] args) throws Exception {

		//这个是得到反编译的池

		ClassPool pool = ClassPool.getDefault();

		//取得需要反编译的jar文件，设定路径

		pool.insertClassPath("D:\\_dev_tools\\.m2\\repository\\antlr\\antlr\\2.7.7\\antlr-2.7.7.jar");

		//取得需要反编译修改的文件，注意是完整路径

		CtClass cc1 = pool.get("antlr.ActionElement");

		try {

			//取得需要修改的方法

			CtMethod method = cc1.getDeclaredMethod("generate");

			//插入修改项，我们让他直接返回(注意：根据方法的具体返回值返回，因为这个方法返回值是void，所以直接return；)


//			@mark not work before
//			method.insertBefore("{if(true) return ;}");
			method.insertAfter("{if(true) return ;}");


			//写入保存

			cc1.writeFile();

		} catch (NotFoundException e) {

			e.printStackTrace();

		}

	}
}
