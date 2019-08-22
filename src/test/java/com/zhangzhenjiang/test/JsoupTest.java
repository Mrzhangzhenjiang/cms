package com.zhangzhenjiang.test;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.zhangzhenjiang.cms.utils.CmsFileUtils;

public class JsoupTest {

	int count = 0;

	@Test
	public void getArticleTest() throws IOException {
		// 获取文档对象
		Document document = Jsoup.connect("https://www.163.com/").get();
		// 获取当前页面中的所有的超链接
		Elements links = document.select("a[href]");
		for (Element link : links) {
			// 获取超连接的地址
			String href = link.attr("href");
			// 判断是不是javaScript的连接
			if (href != null && href.contains("javascript")) {
				// 如果是 则跳过
				continue;
			}
			// 判断是否是新闻
			if (href != null && href.contains("news") && href.contains(".html")) {
				String onehref = link.attr("href");
				Document oneDocument = Jsoup.connect(onehref).get();
				// 获取指定id的元素对象
				Element element = oneDocument.getElementById("epContentLeft");
				if (element != null) {
					Elements tags = element.getElementsByTag("h1");
					if (tags != null) {
						Elements h1 = tags.eq(0);
						if (tags != null) {
							count++;
							// 获取文章的标题
							String title = h1.text().replace("|", "").replace("?", "").replace(":", "").replace("\"",
									"");
							// 获取文章内容的元素对象
							Element contentElement = oneDocument.getElementById("endText");
							// 获取文章内容纯文本
							String content = contentElement.text();
							// 获取文章内容html版本
							String html = contentElement.html();
							CmsFileUtils.writeFile("D:\\getarticle\\" + title + ".txt", content);
						}
					}
				}
			}
		}
		System.out.println("抓取文章的数量" + count);
	}

	@Test
	public void getFictionTest() throws IOException {
		// 获取文档对象
		Document document = Jsoup.connect("https://www.qb5200.tw/xiaoshuo/3/3728/").get();
		Elements links = document.select("a[href]");
		for (Element link : links) {
			String href = link.attr("href");
			if (href.contains("3728") && href.contains(".html")) {
				count++;
				Document onedocument = Jsoup.connect("https://www.qb5200.tw/" + href).get();
				String title = onedocument.getElementsByClass("content").select("h1").text();
				String content = onedocument.getElementById("content").text();
				CmsFileUtils.writeFile("D:\\getarticle\\" + title + ".txt", content);
			}
			if (count == 500) {
				break;
			}
		}
		System.out.println("本次一共抓取了" + count + "篇小说");
	}

	@Test
	public void getPassword() throws IOException {
		Document doc = Jsoup.connect("https://music.163.com/#/discover/toplist?id=3778678").get();
		Elements links = doc.select("div[class=ttc]").select("span[class=txt]").select("a[href]");
		for (Element link : links) {
			String href = link.attr("href");
			System.out.println(href);
		}
	}
}
