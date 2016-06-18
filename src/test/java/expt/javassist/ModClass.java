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

//		pool.insertClassPath("D:\\_dev_tools\\.m2\\repository\\antlr\\antlr\\2.7.7\\antlr-2.7.7.jar");
        pool.insertClassPath("D:\\niuyinghao\\桌面");
        pool.insertClassPath("C:\\Users\\niuyinghao\\.m2\\repository\\commons-net\\commons-net\\1.4.1\\commons-net-1.4.1.jar");

		//取得需要反编译修改的文件，注意是完整路径

		CtClass cc1 = pool.get("com.dhgate.alm.action.AustremeCsvRecordAction");
        pool.importPackage("org.apache.commons.net.ftp.FTPClient");
        pool.importPackage("org.apache.commons.net.ftp.FTPReply");
        pool.importPackage("org.apache.commons.net.ftp.FTP");
        pool.importPackage("java.io.IOException");


		try {

			//取得需要修改的方法

			CtMethod method = cc1.getDeclaredMethod("uploadFile",new CtClass[]{pool.get("com.dhgate.alm.action.AustremeCsvRecordAction$FtpParam")});


			//插入修改项，我们让他直接返回(注意：根据方法的具体返回值返回，因为这个方法返回值是void，所以直接return；)


//			@mark not work before
//			method.insertBefore("{if(true) return ;}");
//            @mark work after
//			method.insertAfter("{if(true) return ;}");
            method           .setBody("{        boolean result = false;\n" +
                    "        FTPClient ftp = new FTPClient();\n" +
                    "        try {\n" +
                    "            int reply;\n" +
                    "            ftp.connect($1.getUrl(), $1.getPort());//连接FTP服务器\n" +
                    "\n" +
                    "            ftp.enterLocalPassiveMode();\n" +
                    "            ftp.setFileTransferMode(FTP.BINARY_FILE_TYPE);\n" +
                    "            ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);\n" +
                    "            ftp.setBufferSize(1*1024*1024);\n" +
                    "            ftp.setDataTimeout(60*1000);\n" +
                    "            ftp.setDefaultTimeout(60*1000);\n" +
                    "\n" +
                    "            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器\n" +
                    "            ftp.login($1.getUsername(), $1.getPassword());//登录\n" +
                    "            reply = ftp.getReplyCode();\n" +
                    "            if (!FTPReply.isPositiveCompletion(reply)) {\n" +
                    "                ftp.disconnect();\n" +
                    "                return result;\n" +
                    "            }\n" +
                    "            ftp.changeWorkingDirectory($1.getPath());\n" +
                    "\n" +
                    "            ftp.storeFile($1.getFilename(), $1.getInput());\n" +
                    "\n" +
                    "            $1.getInput().close();\n" +
                    "            ftp.logout();\n" +
                    "            result = true;\n" +
                    "        } catch (IOException e) {\n" +
                    "            System.out.println(LOG_PREFIX +\n" +
                    "                    \" exception : uploadFile\");\n" +
                    "            e.printStackTrace();\n" +
                    "            Throwable cause = e.getCause();\n" +
                    "            if (cause != null) {\n" +
                    "                cause.printStackTrace();\n" +
                    "            }else{" +
                    "            System.out.println(\" no cause badly\" );" +
                    "     }\n" +
                    "        } finally {\n" +
                    "            if (ftp.isConnected()) {\n" +
                    "                try {\n" +
                    "                    ftp.disconnect();\n" +
                    "                } catch (IOException ioe) {\n" +
                    "                }\n" +
                    "            }\n" +
                    "        }\n" +
                    "        return result;\n }");


			//写入保存

			cc1.writeFile();

		} catch (NotFoundException e) {

			e.printStackTrace();

		}

	}
}
