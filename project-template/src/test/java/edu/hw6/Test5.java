package edu.hw6;

import edu.hw6.Task5.HackerNews;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test5 {

    @Test
    void AtlasToby(){
        HackerNews hackerNews = new HackerNews();
        long[] topStories = hackerNews.hackerNewsTopStories();
        assertEquals(topStories.length, 418);
        String newsTitle = hackerNews.news(37570037);
        assertEquals(newsTitle, "JDK 21 Release Notes");

    }
}
