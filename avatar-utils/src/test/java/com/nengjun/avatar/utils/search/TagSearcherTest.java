package com.nengjun.avatar.utils.search;

import org.junit.Test;

/**
 * Created by Henry on 2017/8/9.
 */
public class TagSearcherTest {
    @Test
    public void test() {
        TagSearcher bitmapIndex = new TagSearcher();
        bitmapIndex.index("中国", "文章", 1);
        bitmapIndex.index("zg", "图片", 10);
        bitmapIndex.index("zhongguo", "图片", 100);
        System.out.println(bitmapIndex.suggest("中国"));
        System.out.println(bitmapIndex.search("中国"));
    }
}
