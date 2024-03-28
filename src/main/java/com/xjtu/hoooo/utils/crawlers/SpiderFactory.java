package com.xjtu.hoooo.utils.crawlers;

import com.xjtu.hoooo.utils.crawlers.spiders.WechatSpider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SpiderFactory {

    private static final Map<String, Supplier<Spider>> spiderMap = new HashMap<>();

    static {
        spiderMap.put("wechat", WechatSpider::new);
    }

    public Spider getSpider(String spiderType){
        Supplier<Spider> spider = spiderMap.get(spiderType.toLowerCase());
        return (spider != null) ? spider.get() : null;
    }

}
