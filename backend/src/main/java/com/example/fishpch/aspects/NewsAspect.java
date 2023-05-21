package org.example.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.example.model.News;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class NewsAspect {
    private float count = 0;

    private float lastMinCount = 0;
    private int newsContentTotalLength = 0;

    public float getLastMinCount() {
        return lastMinCount;
    }

    public float getNewsAverageLength() {
        if (this.count == 0) {
            return 0;
        }
        return this.newsContentTotalLength / this.count;
    }

    private Map<News, Long> newsCache = new LinkedHashMap<News, Long>() {
        @Override
        protected boolean removeEldestEntry(Map.Entry<News, Long> eldest) {
            return System.currentTimeMillis() - eldest.getValue() > 900000;
        }
    };

    @AfterReturning(pointcut = "execution(* org.example.service.NewsService.addNews(..)) && args(news)")
    public void countNewsWithinLast15Min(News news) {
        newsCache.put(news, System.currentTimeMillis());
        long count = newsCache.values().stream().filter(timestamp -> System.currentTimeMillis() - timestamp <= 900000).count();
        lastMinCount = count;
        System.out.println("Number of news in the last 15 minutes: " + count);
    }

    @Pointcut("execution(* org.example.service.NewsServiceImpl.addNews(..)) && args(news)")
    public void saveNewsPointcut(News news) {}

    @Before("saveNewsPointcut(news)")
    public void calculateAverageContentLength(News news) {
        int newsContentLength = news.getNewsContent().length();
        System.out.println("This string length: " + newsContentLength);

        count++;
        newsContentTotalLength += newsContentLength;
        System.out.println("Average news content length: " +
                (double) newsContentTotalLength /count);
    }
}

