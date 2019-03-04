package com.jeesite.common.l.l;	
	
import java.util.HashMap;	
import java.util.Map;	
	
public final class M {	
   public static final int h = 3;	
   public static final int F = 1;	
   public static final int J = 201;	
   public static final int m = 7;	
   public static final int b = 2;	
   public static final int j = 301;	
   public static final int C = 0;	
   public static final int i = 5;	
   public static final int L = 102;	
   public static final int D = 203;	
   public static final int g = 6;	
   public static final int I = 302;	
   public static final int E = 4;	
   public static Map B = new HashMap() {	
      {	
         a.put(0, "SUCCESS");	
         a.put(101, "无效的Action");	
         a.put(102, "配置文件初始化失败");	
         a.put(203, "抓取远程图片失败");	
         a.put(201, "被阻止的远程主机");	
         a.put(202, "远程连接出错");	
         a.put(1, "文件大小超出限制");	
         a.put(2, "权限不足");	
         a.put(3, "创建文件失败");	
         a.put(4, "IO错误");	
         a.put(5, "上传表单不是multipar/form-data类型");	
         a.put(6, "解析上传表单错误");	
         a.put(7, "未找到上传数据");	
         a.put(8, "不允许的文件类型");	
         a.put(301, "指定路径不是目录");	
         a.put(302, "指定路径并不存在");	
         a.put(401, "Callback参数名不合法");	
      }	
   };	
   public static final int G = 202;	
   public static final int d = 8;	
   public static final int c = 401;	
   public static final int ALLATORIxDEMO = 101;	
	
   public static String ALLATORIxDEMO(int key) {	
      return (String)B.get(key);	
   }	
}	
