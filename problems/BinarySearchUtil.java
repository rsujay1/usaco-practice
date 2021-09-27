package misc;

public class BinarySearchUtil {
    public static int findSmallest(int[] array, Integer search, int lowerbound, int upperbound) {
        // Smallest value x that works  --- the element bigger than the search, but smallest(min) in the bigger values.
        int a = lowerbound, b = upperbound;
        while (a != b) {
            int mid = (a + b) / 2;
            if (works(mid,array,search)) {
                b = mid;
            } else {
                a = mid + 1;
            }
        }
        return array[a];
    }

    private static boolean works(int mid, int[] array, Integer search) {
        return search.compareTo(array[mid]) <= 0;
    }

    public static void main(String[] args) {
        int[] a = {1,3,4,7,9};
        int v = findSmallest(a,8,0,a.length-1);
        System.out.println("findSmallest index:"+v);

        int v2 = findBiggestOfSmallerValues(a,8,0,a.length-1);
        System.out.println("findBiggestOfSmallerValues index:"+v2);

        int[] a2 = {4,5,8,9,10};
        int v3 = findSmallest(a2,6,0,a.length-1);
        System.out.println("findSmallest index:"+v3);

        int[] a3= {4,5,8,9,10};
        int v4 = binarySearchExact(a3,8,0,a.length-1);
        System.out.println("binarySearchExact index:"+v4);
    }

    public static int findBiggestOfSmallerValues(int[] array, Integer search, int lowerbound, int upperbound) {
        // Largest value x that works ----- the element smaller element than the search, but biggest(max) in the smaller values.
        int a = lowerbound, b = upperbound;
        while(a != b) {
            int mid = (a+b + 1)/2;
            if (worksBiggest(mid,array,search)) {
                a = mid;
            }
            else {
                b = mid-1;
            }
        }
        return a;
    }



    private static boolean worksBiggest(int mid, int[] array, Integer search) {
        return search.compareTo(array[mid]) >= 0;
    }

    public static int binarySearchExact(
            int[] a, int key, int lw, int hg) {
        int index = Integer.MAX_VALUE;

        while (lw <= hg) {
            int mid = (lw + hg) / 2;
            if (a[mid] < key) {
                lw = mid + 1;
            } else if (a[mid] > key) {
                hg = mid - 1;
            } else if (a[mid] == key) {
                index = mid;
                break;
            }
        }
        return index;
    }
}