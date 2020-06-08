package com.example.demo.xudayang_func.video_func;

import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.util.Enumeration;  
import java.util.zip.ZipEntry;  
import java.util.zip.ZipException;  
import java.util.zip.ZipFile;  
import java.util.zip.ZipOutputStream;  
  
  
/** 
 *  
 * 对比较大的文件进行压缩与解压
 * 
 * 
 * 
 */  
public final class ZipUtil  
{  
    public static void main(String[] args) {
        zip(new String[] {"d:\\myeclipse\\test\\test.jpg",
        "d:\\myeclipse\\test\\test1.jpg"}, "d:\\myeclipse\\test\\test.zip");
        //unzip("d:\\myeclipse\\test\\test.zip", "d:\\myeclipse\\test");
	}
  
    /** 
     * 缓冲大小 
     */  
    private static int BUFFERSIZE = 2 << 10;  
      
    /** 
     * 压缩 
     * @param paths 
     * @param fileName 
     */  
    public static void zip(String[] paths, String fileName)  
    {  
          
        ZipOutputStream zos = null;  
        try  
        {  
            zos = new ZipOutputStream(new FileOutputStream(fileName));  
            for(String filePath : paths)  
            {  
                //递归压缩文件  
                File file = new File(filePath);  
                String relativePath = file.getName();  
                if(file.isDirectory())  
                {  
                    relativePath += File.separator;  
                }  
                zipFile(file, relativePath, zos);  
            }  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
        finally  
        {  
            try  
            {  
                if(zos != null)  
                {  
                    zos.close();  
                }  
            }  
            catch (IOException e)  
            {  
                e.printStackTrace();  
            }  
        }  
    }  
      
    public static void zipFile(File file, String relativePath, ZipOutputStream zos)  
    {  
        InputStream is = null;  
        try  
        {  
            if(!file.isDirectory())  
            {  
                ZipEntry zp = new ZipEntry(relativePath);  
                zos.putNextEntry(zp);  
                is = new FileInputStream(file);  
                byte[] buffer = new byte[BUFFERSIZE];  
                int length = 0;  
                while ((length = is.read(buffer)) >= 0)  
                {  
                    zos.write(buffer, 0, length);  
                }  
                zos.flush();  
                zos.closeEntry();  
            }  
            else  
            {  
                    String tempPath = null;  
                   for(File f: file.listFiles())  
                    {  
                          tempPath = relativePath + f.getName();  
                          if(f.isDirectory())  
                          {  
                             tempPath += File.separator;  
                          }  
                          zipFile(f, tempPath, zos);  
                    }  
            }  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
        finally  
        {  
            try  
            {  
                if(is != null)  
                {  
                    is.close();  
                }  
            }  
            catch (IOException e)  
            {  
                e.printStackTrace();  
            }  
        }  
    }  
      
    /** 
     * 解压缩 
     * @param fileName 
     * @param path 
     */  
    public static void unzip(String fileName, String path)  
    {  
        FileOutputStream fos = null;  
        InputStream is = null;  
        try  
        {  
            ZipFile zf = new ZipFile(new File(fileName));  
            Enumeration en = zf.entries();  
            while (en.hasMoreElements())  
            {  
                ZipEntry zn = (ZipEntry) en.nextElement();  
                if (!zn.isDirectory())  
                {  
                    is = zf.getInputStream(zn);  
                    File f = new File(path + zn.getName());  
                    File file = f.getParentFile();  
                    file.mkdirs();  
                    fos = new FileOutputStream(path + zn.getName());  
                    int len = 0;  
                    byte bufer[] = new byte[BUFFERSIZE];  
                    while (-1 != (len = is.read(bufer)))  
                    {  
                        fos.write(bufer, 0, len);  
                    }  
                    fos.close();  
                }  
            }  
        }  
        catch (ZipException e)  
        {  
            e.printStackTrace();  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
        finally  
        {  
            try  
            {  
                if(null != is)  
                {  
                    is.close();  
                }  
                if(null != fos)  
                {  
                    fos.close();  
                }  
            }  
            catch (IOException e)  
            {  
                e.printStackTrace();  
            }  
        }  
    }  
}