package com.xjtu.hoooo.utils.crawlers;

import com.xjtu.hoooo.dao.News;
import com.xjtu.hoooo.utils.crawlers.spiders.WechatSpider;
import org.junit.Test;

import java.util.List;

public class CrawlersTest {

    @Test
    public void testCrawler() {
        Spider spider = new WechatSpider();
//        String url = "https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzIwMjAwMjk4Mg==&action=getalbum&album_id=3079598529188298756&scene=126#wechat_redirect";
        String url = "https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzU1MzY0MDI2NA==&action=getalbum&album_id=1562988036857053186&scene=173&subscene=93&sessionid=1705627760&enterid=1705627950&from_msgid=2247504883&from_itemidx=1&count=3&nolastread=1#wechat_redirect";
        List<News> newsInfos = spider.crawl(url);
        System.out.println(newsInfos);
    }

}
