package com.nengjun.avatar.search;

import org.apache.commons.lang3.StringUtils;
import org.nlpcn.commons.lang.index.MemoryIndex;
import org.nlpcn.commons.lang.pinyin.Pinyin;

import java.util.*;

/**
 * 标签搜索器
 * Todo: query 分词, 输出打分
 * Created by Henry on 2017/8/9.
 */
public class TagSearcher {
    private MemoryIndex<String> tags;
    private Map<String, Map<String, BitSet>> indexMap;

    public TagSearcher() {
        tags = new MemoryIndex<>();
        indexMap = new HashMap<>();
    }

    public List<String> suggest(String tag) {
        tag = tag.toLowerCase();
        return tags.smartSuggest(tag);
    }

    public Map<String, Set<Integer>> search(String tag) {
        Map<String, Set<Integer>> rs = new HashMap<>();
        if (StringUtils.isBlank(tag)) {
            return rs;
        }

        List<String> tagList = suggest(tag);
        if (tagList == null || tagList.isEmpty()) {
            return rs;
        }

        for (String t : tagList) {
            search(t, rs);
        }
        return rs;
    }

    private void search(String tag, Map<String, Set<Integer>> rs) {
        Map<String, BitSet> bitSetMap = indexMap.get(tag);
        if (bitSetMap == null) {
            return;
        }

        for (Map.Entry<String, BitSet> entry : bitSetMap.entrySet()) {
            String category = entry.getKey();
            BitSet bitSet = entry.getValue();
            Set<Integer> ids = rs.get(category);
            if (ids == null) {
                ids = new HashSet<>();
                rs.put(category, ids);
            }
            for (int id = 0; id < bitSet.size(); id++) {
                if (bitSet.get(id)) {
                    ids.add(id);
                }
            }
        }
    }

    public int index(String tag, String category, int id) {
        tag = tag.toLowerCase();
        category = category.toLowerCase();

        tags.addItem(tag, tag, StringUtils.join(Pinyin.pinyin(tag), ""), StringUtils.join(Pinyin.firstChar(tag), ""));

        Map<String, BitSet> bitSetMap = indexMap.get(tag);
        if (bitSetMap == null) {
            bitSetMap = new HashMap<>();
            indexMap.put(tag, bitSetMap);
        }

        BitSet bitSet = bitSetMap.get(category);
        if (bitSet == null) {
            bitSet = new BitSet();
            bitSetMap.put(category, bitSet);
        }

        bitSet.set(id);
        return 1;
    }
}
