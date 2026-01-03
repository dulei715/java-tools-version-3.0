package cn.edu.dll.differential_privacy.cdp.basic_struct.tools;

import cn.edu.dll.differential_privacy.cdp.basic_struct.DistanceAble;

import java.util.TreeSet;

public class DistanceAbleTool {
    public static <T extends DistanceAble<T>> T getNearestElement(T currentElement, TreeSet<T> elementCollection) {
        Double tempMinimalDistance = Double.MAX_VALUE, tempDistance;
        T chosenCentroid = null;
        for (T tempCentroidElement : elementCollection) {
            tempDistance = currentElement.getDistance(tempCentroidElement);
            if (tempDistance < tempMinimalDistance) {
                tempMinimalDistance = tempDistance;
                chosenCentroid = tempCentroidElement;
            }
        }
        return chosenCentroid;
    }
}
