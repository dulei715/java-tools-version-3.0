package cn.edu.dll.struct.graph.utils;

import cn.edu.dll.basic.ValidationUtil;
import cn.edu.dll.struct.graph.Community;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class CommunityUtils {
    public static <T extends Number> List<Community> getInitializedCommunityList(Integer communitySize, LinkedHashSet<T> communityIDSet) {
        ValidationUtil.requireEqual(communitySize, communityIDSet.size(), "The communitySize and communityIDSetSize are not equal!");
        List<Community> resultList = new ArrayList<>(communitySize);
        Iterator<T> communityIDIterator = communityIDSet.iterator();
        for (int i = 0; i < communitySize; ++i) {
            resultList.add(new Community(communityIDIterator.next().longValue()));
        }
        return resultList;
    }
}
