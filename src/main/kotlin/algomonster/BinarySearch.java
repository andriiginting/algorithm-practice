package algomonster;

import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {

    }

    public static int binarySearch(List<Integer> arr, int target) {
        int first = 0;
        int last = arr.size() - 1;

        while(first <= last) {
            int mid = (first + last) / 2;

            if(arr.get(mid) == target) {
                return mid;
            } else if(arr.get(mid) < target) {
                first = mid + 1;
            } else {
                last = mid - 1;
            }
        }

        return -1;
    }

    public static int findBoundary(boolean[] arr) {
        int first = 0;
        int last = arr.length - 1;
        int result = -1;
        while(first <= last) {
            int mid = first + (last-first) / 2;

            if(arr[mid]) {
                result = mid;
                first = mid + 1;
            } else {
                last = mid - 1;
            }
        }

        return result;
    }

    public static int firstNotSmaller(List<Integer> arr, int target) {
        int first = 0;
        int last = arr.size() - 1;
        int idx = -1;

        while(first <= last) {
            int mid = first + (last - first) / 2;

            if(arr.get(mid) >= target) {
                idx = mid;
                last = mid - 1;
            } else {
                first = mid + 1;
            }
        }

        return idx;
    }

    public static int findFirstOccurrence(List<Integer> arr, int target) {
        int first = 0;
        int last = arr.size() - 1;
        int firstIdx = -1;

        while(first <= last) {
            int mid = first + (last-first)/2;

            if (arr.get(mid) == target) {
                firstIdx = mid;
                last = mid - 1;
            } else if (arr.get(mid) < target) {
                first = mid + 1;
            } else  {
                last = mid - 1;
            }
        }

        return firstIdx;
    }
}
