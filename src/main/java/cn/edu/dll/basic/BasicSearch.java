package cn.edu.dll.basic;

@SuppressWarnings("Duplicates")
public class BasicSearch {
    public static final Boolean FORMER = false;
    public static final Boolean LATTER = true;
    //todo: 测试有相邻重复值时（存在两个差为0的时候，是否返回前者）！！！！！！！！！！！
    public static int binarySearch(Comparable[] arr, Comparable element, Boolean border) {
        int len = arr.length;
        int from = 0, end = len - 1;
        int mid;
        int tempCompare;
        if (element.compareTo(arr[0]) < 0 ) {
            if (BasicSearch.LATTER.equals(border)) {
                return 0;
            }
            return -1;
        }
        if (element.compareTo(arr[arr.length-1]) > 0 ) {
            if (BasicSearch.FORMER.equals(border)) {
                return arr.length - 1;
            }
            return arr.length;
        }
        while (from <= end) {
            mid = (from + end) / 2;
//            tempCompare = arr[mid].compareTo(element);
            tempCompare = element.compareTo(arr[mid]);
            if (tempCompare == 0) {
                return mid;
            }
            if (tempCompare < 0) {
                end = mid -1 ;
            } else {
                from = mid + 1;
            }
        }
        if (border == null) {
            // 表示精确查找。-2表示没有找到
            return -2;
        }
        if (border.equals(BasicSearch.FORMER)) {
            return from - 1;
        }
        return from;
    }

    public static int binaryLatterSearchWithMinimalIndex(Comparable[] arr, Comparable element){
        int len = arr.length;
        int from = 0, end = len - 1;
        int mid;
        int tempCompare;
        if (element.compareTo(arr[0]) < 0 ) {
            return 0;
        }
        if (element.compareTo(arr[arr.length-1]) > 0 ) {
            return arr.length;
        }
        while (from <= end) {
            mid = (from + end) / 2;
//            tempCompare = arr[mid].compareTo(element);
            tempCompare = element.compareTo(arr[mid]);
            if (tempCompare == 0) {
                for (int i = mid-1; i >=0; i--) {
                    if (element.compareTo(arr[i]) == 0) {
                        mid = i;
                    } else {
                        break;
                    }
                }
                return mid;
            }
            if (tempCompare < 0) {
                end = mid -1 ;
            } else {
                from = mid + 1;
            }
        }
        return from;
    }

    public static int binaryFormerSearchWithMinimalIndex(Comparable[] arr, Comparable element) {
        int len = arr.length;
        int from = 0, end = len - 1;
        int mid;
        int tempCompare;
        if (element.compareTo(arr[0]) < 0) {
            return -1;
        }
        if (element.compareTo(arr[arr.length-1]) > 0 ) {
            return arr.length - 1;
        }
        while (from <= end) {
            mid = (from + end) / 2;
//            tempCompare = arr[mid].compareTo(element);
            tempCompare = element.compareTo(arr[mid]);
            if (tempCompare == 0) {
                for (int i = mid-1; i >=0; i--) {
                    if (element.compareTo(arr[i]) == 0) {
                        mid = i;
                    } else {
                        break;
                    }
                }
                return mid;
            }
            if (tempCompare < 0) {
                end = mid -1 ;
            } else {
                from = mid + 1;
            }
        }
        return from - 1;
    }

}
