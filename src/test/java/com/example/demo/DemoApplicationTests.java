package com.example.demo;

import java.io.File;

import com.example.demo.tools.FastDFSClient;
import com.example.demo.tools.Encrypt;
import com.example.demo.tools.Sha1withRSAUtil;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

	@Test
	public void Upload() {
		String fileUrl = this.getClass().getResource("/static/test.jpg").getPath();//测试照片文件是否存储成功
		File file = new File(fileUrl);
		String str = FastDFSClient.uploadFile(file);
		FastDFSClient.getResAccessUrl(str);
	}
 
	@Test
	public void Delete() {
		FastDFSClient.deleteFile("group1/M00/00/00/wKiOgV58LPKAAwXpABu6FUtZEHg324.jpg");//测试删除文件服务上的照片文件
	}

	@Test
	public void Md5(){
		String text = "123456";
		String md5text = Encrypt.stringMD5(text);
		System.out.println("Md5="+md5text);

	}

	@Test

	public void Sha1withRSA(){
		String data = "0123456789";
		System.out.println(data);

		String singData = Sha1withRSAUtil.sign(data);
		System.out.println(singData);

		try{
			boolean flag = Sha1withRSAUtil.verify(singData, data);
			System.out.println(flag);

			String eData = Sha1withRSAUtil.encrypt(data);
			System.out.println(eData);
			String dData = Sha1withRSAUtil.decode(eData);
			System.out.println(dData);
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
