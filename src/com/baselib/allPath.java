package com.baselib;

import java.util.Map;

public class allPath {
	
	public void getEnvPath(){
		//System.getenv()方法是获取指定的环境变量的值
		for(Map.Entry entry:System.getenv().entrySet()){ //1.8版本有问题
			System.out.println(entry.getKey()+"-->"+entry.getValue());
		}
	}
	
	public void getPath(){
		//for(Map.Entry entry:System.getenv().entrySet)
		//java运行时环境版本
		System.out.println(System.getProperty("java.version"));
		//java运行时环境供应商
		System.out.println(System.getProperty("java.vendor"));
		//java供应商url
		System.out.println(System.getProperty("java.vendor.url"));
		//java安装目录
		System.out.println(System.getProperty("java.home"));
		//java虚拟机规范版本
		System.out.println(System.getProperty("java.vm.specification.version"));
		//java虚拟机规范供应商
		System.out.println(System.getProperty("java.vm.specification.vendor"));
		//java虚拟机规范名称
		System.out.println(System.getProperty("java.vm.specification.name"));
		//java虚拟机实现版本
		System.out.println(System.getProperty("java.vm.version"));
		//java虚拟机实现供应商
		System.out.println(System.getProperty("java.vm.vendor"));
		//java虚拟机实现名称
		System.out.println(System.getProperty("java.vm.name"));
		//java运行时环境规范供应商
		System.out.println(System.getProperty("java.specification.vendor"));
		//java运行时环境规范版本
		System.out.println(System.getProperty("java.specification.version"));
		//java运行时环境规范名称
		System.out.println(System.getProperty("java.specification.name"));
		//java类格式版本号
		System.out.println(System.getProperty("java.class.version"));
		//java类路径
		System.out.println(System.getProperty("java.class.path"));
		//加载库时搜索的路径列表
		System.out.println(System.getProperty("java.library.path"));
		//默认的临时文件路径
		System.out.println(System.getProperty("java.io.tmpdir"));
		//要使用的JIT编译器的名称
		System.out.println(System.getProperty("java.compiler"));
		//一个或多个扩展目录的路径
		System.out.println(System.getProperty("java.ext.dirs"));
		//操作系统的名称
		System.out.println(System.getProperty("os.name"));
		//操作系统的架构
		System.out.println(System.getProperty("os.arch"));
		//操作系统的版本
		System.out.println(System.getProperty("os.version"));
		//文件分隔符（在 UNIX 系统中是“/” ）
		System.out.println(System.getProperty("file.separator"));
		//路径分隔符（在 UNIX 系统中是“:” ）
		System.out.println(System.getProperty("path.separator"));
		//行分隔符（在 UNIX 系统中是“/n” ）
		System.out.println(System.getProperty("line.separator"));
		//用户的账户名称
		System.out.println(System.getProperty("user.name"));
		//用户的主目录
		System.out.println(System.getProperty("user.home"));
		//用户的当前工作目录
		System.out.println(System.getProperty("user.dir"));
		
	}
}
