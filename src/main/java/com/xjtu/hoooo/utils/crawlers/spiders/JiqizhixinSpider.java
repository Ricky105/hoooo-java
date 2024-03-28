package com.xjtu.hoooo.utils.crawlers.spiders;

import com.xjtu.hoooo.dao.News;
import com.xjtu.hoooo.utils.crawlers.Spider;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JiqizhixinSpider implements Spider {

    private static final Logger logger = LoggerFactory.getLogger(WechatSpider.class);

    @Override
    public List<News> crawl() {
        String url = "https://jiqizhixin.com/";
        WebDriver driver = new ChromeDriver();
        driver.get(url);

        logger.info("Scraping {}", url);
        boolean upToDate = false;

//        LocalDate setDate = LocalDate.now();
        LocalDate setDate = LocalDate.of(2024, 2, 1);

        int curIndex = 0, trial = 0;
        List<News> news = new ArrayList<>();

        while(!upToDate) {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        }
//        TODO
        return null;
    }
}
