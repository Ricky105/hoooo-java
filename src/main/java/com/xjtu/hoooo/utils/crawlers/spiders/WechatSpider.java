package com.xjtu.hoooo.utils.crawlers.spiders;

import com.xjtu.hoooo.dao.News;
import com.xjtu.hoooo.utils.crawlers.CrawlersConfig;
import com.xjtu.hoooo.utils.crawlers.Spider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WechatSpider implements Spider {

    private static final Logger logger = LoggerFactory.getLogger(WechatSpider.class);

    @Override
    public List<News> crawl(String url) {
        WebDriver driver = new ChromeDriver();
        driver.get(url);

        String albumTitle = driver.findElement(By.cssSelector(".album__author-name")).getText();
        logger.info("Scraping {}", albumTitle);
        boolean upToDate = false;
//        LocalDate setDate = LocalDate.now();
        LocalDate setDate = LocalDate.of(2024, 2, 1);

        int curIndex = 0, trial = 0;
        List<News> news = new ArrayList<>();

        while (!upToDate) {
            WebElement newsList = driver.findElement(By.cssSelector(".album__list.js_album_list"));
            List<WebElement> newsElements = newsList.findElements(By.tagName("li"));
            for (int index = curIndex; index < newsElements.size(); index++) {
                try {
                    WebElement newsElement = newsElements.get(index);
                    String newsUrl = newsElement.getAttribute("data-link");
                    String newsTitle = newsElement.getAttribute("data-title");

                    Document doc = Jsoup.connect(newsUrl).get();
                    Pattern pattern = Pattern.compile("var createTime = '([^']+)'");
                    Matcher matcher = pattern.matcher(doc.html());

                    if (matcher.find()) {
                        String newsDateStr = matcher.group(1).split(" ")[0];
                        LocalDate newsDate = LocalDate.parse(newsDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                        if (newsDate.isBefore(setDate)) {
                            upToDate = true;
                            break;
                        } else {
                            news.add(new News(newsTitle, albumTitle, newsDate, newsUrl));
                            logger.info("{} got {} news", albumTitle, news.size());
                        }
                    }
                } catch (IOException e) {
                    logger.error("{} happen in {}", e, albumTitle);
                    trial++;
                    if (trial > CrawlersConfig.MAX_RETRIALS) {
                        e.printStackTrace();
                        upToDate = true;
                        break;
                    }
                }
            }

            if (!upToDate && curIndex != newsElements.size()) {
                curIndex = newsElements.size();
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                break;
            }

        }
        driver.quit();
        return news;
    }
}
