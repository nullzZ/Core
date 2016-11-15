/*    */ package game.core.hotLoader;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.IOException;
/*    */ import org.apache.log4j.LogManager;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class ScriptClassLoader extends ClassLoader
/*    */ {
/* 13 */   private static Logger log = LogManager.getLogger(ScriptClassLoader.class);
/*    */ 
/*    */   public Class<?> loadScriptClass(String name) throws ClassNotFoundException {
/*    */     try {
/* 17 */       byte[] bs = loadByteCode(name);
/* 18 */       return super.defineClass(name, bs, 0, bs.length);
/*    */     }
/*    */     catch (Exception e) {
/* 21 */       log.error(e, e);
/*    */     }
/* 23 */     return null;
/*    */   }
/*    */ 
/*    */   private byte[] loadByteCode(String name)
/*    */     throws IOException
/*    */   {
/* 34 */     int iRead = 0;
/* 35 */     String classFileName = "bin/" + name.replace('.', '/') + ".class";
/*    */ 
/* 37 */     FileInputStream in = null;
/* 38 */     ByteArrayOutputStream buffer = null;
/*    */     try {
/* 40 */       in = new FileInputStream(classFileName);
/* 41 */       buffer = new ByteArrayOutputStream();
/* 42 */       while ((iRead = in.read()) != -1) {
/* 43 */         buffer.write(iRead);
/*    */       }
/* 45 */       return buffer.toByteArray();
/*    */     } finally {
/*    */       try {
/* 48 */         if (in != null)
/* 49 */           in.close();
/*    */       }
/*    */       catch (Exception e) {
/* 52 */         log.error(e, e);
/*    */       }
/*    */       try {
/* 55 */         if (buffer != null)
/* 56 */           buffer.close();
/*    */       }
/*    */       catch (Exception e) {
/* 59 */         log.error(e, e);
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           F:\workplace\qmr_6\Libs\libs\GameCore.jar
 * Qualified Name:     com.game.script.loader.ScriptClassLoader
 * JD-Core Version:    0.6.2
 */