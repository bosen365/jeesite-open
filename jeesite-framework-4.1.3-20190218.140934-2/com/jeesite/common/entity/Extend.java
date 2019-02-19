package com.jeesite.common.entity;	
	
import com.fasterxml.jackson.annotation.JsonFormat;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import java.io.Serializable;	
import java.util.Date;	
	
@Table(	
   columns = {@Column(	
   name = "extend_s1",	
   label = "扩展字段String1"	
), @Column(	
   name = "extend_s2",	
   label = "扩展字段String2"	
), @Column(	
   name = "extend_s3",	
   label = "扩展字段String3"	
), @Column(	
   name = "extend_s4",	
   label = "扩展字段String4"	
), @Column(	
   name = "extend_s5",	
   label = "扩展字段String5"	
), @Column(	
   name = "extend_s6",	
   label = "扩展字段String6"	
), @Column(	
   name = "extend_s7",	
   label = "扩展字段String7"	
), @Column(	
   name = "extend_s8",	
   label = "扩展字段String8"	
), @Column(	
   name = "extend_i1",	
   label = "扩展字段Integer1"	
), @Column(	
   name = "extend_i2",	
   label = "扩展字段Integer2"	
), @Column(	
   name = "extend_i3",	
   label = "扩展字段Integer3"	
), @Column(	
   name = "extend_i4",	
   label = "扩展字段Integer4"	
), @Column(	
   name = "extend_f1",	
   label = "扩展字段Float1"	
), @Column(	
   name = "extend_f2",	
   label = "扩展字段Float2"	
), @Column(	
   name = "extend_f3",	
   label = "扩展字段Float3"	
), @Column(	
   name = "extend_f4",	
   label = "扩展字段Float4"	
), @Column(	
   name = "extend_d1",	
   label = "扩展字段Date1"	
), @Column(	
   name = "extend_d2",	
   label = "扩展字段Date2"	
), @Column(	
   name = "extend_d3",	
   label = "扩展字段Date3"	
), @Column(	
   name = "extend_d4",	
   label = "扩展字段Date4"	
)}	
)	
public class Extend implements Serializable {	
   private static final long serialVersionUID = 1L;	
   private String extendS8;	
   private String extendS4;	
   private String extendS5;	
   private String extendS6;	
   private String extendS2;	
   private Long extendI3;	
   private Double extendF2;	
   private Double extendF3;	
   private Double extendF1;	
   private String extendS7;	
   private Long extendI4;	
   private Long extendI2;	
   private Long extendI1;	
   private Date extendD1;	
   private String extendS3;	
   private Date extendD3;	
   private Date extendD4;	
   private Date extendD2;	
   private String extendS1;	
   private Double extendF4;	
	
   @JsonFormat(	
      pattern = "yyyy-MM-dd HH:mm:ss"	
   )	
   public Date getExtendD4() {	
      return this.extendD4;	
   }	
	
   public void setExtendS3(String extendS3) {	
      this.extendS3 = extendS3;	
   }	
	
   public void setExtendD3(Date extendD3) {	
      this.extendD3 = extendD3;	
   }	
	
   public void setExtendS5(String extendS5) {	
      this.extendS5 = extendS5;	
   }	
	
   public void setExtendS8(String extendS8) {	
      this.extendS8 = extendS8;	
   }	
	
   public void setExtendS7(String extendS7) {	
      this.extendS7 = extendS7;	
   }	
	
   public void setExtendI1(Long extendI1) {	
      this.extendI1 = extendI1;	
   }	
	
   public void setExtendS6(String extendS6) {	
      this.extendS6 = extendS6;	
   }	
	
   @JsonFormat(	
      pattern = "yyyy-MM-dd HH:mm:ss"	
   )	
   public Date getExtendD2() {	
      return this.extendD2;	
   }	
	
   public void setExtendS1(String extendS1) {	
      this.extendS1 = extendS1;	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = (3 ^ 5) << 3 ^ 1;	
      int var10001 = (2 ^ 5) << 4 ^ 3 << 1;	
      int var10002 = 5 << 4 ^ 5;	
      int var10003 = (s = (String)s).length();	
      char[] var10004 = new char[var10003];	
      boolean var10006 = true;	
      int var5 = var10003 - 1;	
      var10003 = var10002;	
      int var3;	
      var10002 = var3 = var5;	
      char[] var1 = var10004;	
      int var4 = var10003;	
      var10000 = var10002;	
	
      for(int var2 = var10001; var10000 >= 0; var10000 = var3) {	
         var10001 = var3;	
         char var6 = s.charAt(var3);	
         --var3;	
         var1[var10001] = (char)(var6 ^ var2);	
         if (var3 < 0) {	
            break;	
         }	
	
         var10002 = var3--;	
         var1[var10002] = (char)(s.charAt(var10002) ^ var4);	
      }	
	
      return new String(var1);	
   }	
	
   public void setExtendF1(Double extendF1) {	
      this.extendF1 = extendF1;	
   }	
	
   public Double getExtendF2() {	
      return this.extendF2;	
   }	
	
   public void setExtendI4(Long extendI4) {	
      this.extendI4 = extendI4;	
   }	
	
   public String getExtendS1() {	
      return this.extendS1;	
   }	
	
   public void setExtendS4(String extendS4) {	
      this.extendS4 = extendS4;	
   }	
	
   public void setExtendF3(Double extendF3) {	
      this.extendF3 = extendF3;	
   }	
	
   public String getExtendS8() {	
      return this.extendS8;	
   }	
	
   @JsonFormat(	
      pattern = "yyyy-MM-dd HH:mm:ss"	
   )	
   public Date getExtendD1() {	
      return this.extendD1;	
   }	
	
   public Long getExtendI2() {	
      return this.extendI2;	
   }	
	
   public String getExtendS3() {	
      return this.extendS3;	
   }	
	
   public Long getExtendI1() {	
      return this.extendI1;	
   }	
	
   public String getExtendS4() {	
      return this.extendS4;	
   }	
	
   public String getExtendS6() {	
      return this.extendS6;	
   }	
	
   public String getExtendS2() {	
      return this.extendS2;	
   }	
	
   public Long getExtendI3() {	
      return this.extendI3;	
   }	
	
   public Double getExtendF1() {	
      return this.extendF1;	
   }	
	
   public String getExtendS7() {	
      return this.extendS7;	
   }	
	
   public Double getExtendF3() {	
      return this.extendF3;	
   }	
	
   @JsonFormat(	
      pattern = "yyyy-MM-dd HH:mm:ss"	
   )	
   public Date getExtendD3() {	
      return this.extendD3;	
   }	
	
   public void setExtendD4(Date extendD4) {	
      this.extendD4 = extendD4;	
   }	
	
   public void setExtendD1(Date extendD1) {	
      this.extendD1 = extendD1;	
   }	
	
   public void setExtendI3(Long extendI3) {	
      this.extendI3 = extendI3;	
   }	
	
   public void setExtendS2(String extendS2) {	
      this.extendS2 = extendS2;	
   }	
	
   public void setExtendD2(Date extendD2) {	
      this.extendD2 = extendD2;	
   }	
	
   public Long getExtendI4() {	
      return this.extendI4;	
   }	
	
   public void setExtendF2(Double extendF2) {	
      this.extendF2 = extendF2;	
   }	
	
   public String getExtendS5() {	
      return this.extendS5;	
   }	
	
   public Double getExtendF4() {	
      return this.extendF4;	
   }	
	
   public void setExtendI2(Long extendI2) {	
      this.extendI2 = extendI2;	
   }	
	
   public void setExtendF4(Double extendF4) {	
      this.extendF4 = extendF4;	
   }	
}	
