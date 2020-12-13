package com.freedom.greedy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author freedom
 * @date 2020/12/1 20:36
 * @description 1、贪婪算法（贪心算法）是指在对问题进行求解时，在每一步选择中都采取最好或者最优（即最有利）的选择，
 *                 从而希望能够导致结果是最好或者最优的算法。
 *              2、贪婪算法所得到的结果不一定是最优的结果（有时候会是最优解），但是都是相对近似最优解的结果
 */
public class Greedy {

    public static void main(String[] args) {
        Map<String, HashSet<String>> broadcasts = new HashMap<>();

        HashSet<String> k1 = Stream.of("北京", "上海", "天津")
                .collect(Collectors.toCollection(HashSet::new));
        HashSet<String> k2 = Stream.of("广州", "北京", "深圳")
                .collect(Collectors.toCollection(HashSet::new));
        HashSet<String> k3 = Stream.of("成都", "上海", "杭州")
                .collect(Collectors.toCollection(HashSet::new));
        HashSet<String> k4 = Stream.of("上海", "天津")
                .collect(Collectors.toCollection(HashSet::new));
        HashSet<String> k5 = Stream.of("杭州", "大连")
                .collect(Collectors.toCollection(HashSet::new));

        broadcasts.put("k1", k1);
        broadcasts.put("k2", k2);
        broadcasts.put("k3", k3);
        broadcasts.put("k4", k4);
        broadcasts.put("k5", k5);

        // 存放所有的地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.addAll(k1);
        allAreas.addAll(k2);
        allAreas.addAll(k3);
        allAreas.addAll(k4);
        allAreas.addAll(k5);

        // 存放选择的电台集合
        List<String> selects = new ArrayList<>();

        // 定义一个临时的集合，在遍历的过程中，存放遍历过程中的电台遍历的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();
        // 保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台的key
        String maxKey;

        while (allAreas.size() > 0) {
            // 必须把前一次的maxKey置空
            maxKey = null;
            for (Map.Entry<String, HashSet<String>> entry : broadcasts.entrySet()) {
                // 必需把临时集合清空
                tempSet.clear();
                HashSet<String> areas = entry.getValue();
                tempSet.addAll(areas);
                // tempSet和allAreas集合的交集，交集会赋值给tempSet
                tempSet.retainAll(allAreas);
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = entry.getKey();
                }
            }
            if (maxKey != null) {
                selects.add(maxKey);
                // 将maxKey指向的广播电台覆盖的地区从allAreas去除
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.printf("最后的结果是%s", selects);
    }
}
