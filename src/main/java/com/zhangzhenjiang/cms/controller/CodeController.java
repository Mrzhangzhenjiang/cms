package com.zhangzhenjiang.cms.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhangzhenjiang.cms.utils.CodeUtil;

@Controller
public class CodeController {
	@RequestMapping(path="/getcode",method=RequestMethod.GET)
	public void createCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//1.创建验证码
		Map<String, Object> map = CodeUtil.generateCodeAndPic();
		//2.1获取session会话
		HttpSession session = request.getSession();
		//将验证码存入到session中
		session.setAttribute("code", map.get("code").toString());
		//2.2设置浏览器不缓存验证码图像
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", -1);
		//设置响应的类型  以指定的方式响应给客户端
		response.setContentType("image/jpeg");
		// 流对象
		ServletOutputStream sos = response.getOutputStream();
		// 获取的图片
		BufferedImage image = (BufferedImage) map.get("codePic");
		//写入到指定的流中
		ImageIO.write(image, "jpeg", sos);
		//关闭流
		sos.flush();
		sos.close();
	}
}
