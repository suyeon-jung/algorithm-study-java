package search_algorithm.binary_search;

public class Main {

    public static void main(String[] args) {

        int target = 28;

        int[] arr = {17, 28, 43, 67, 88, 92, 100, 201};

        int start = 0;
        int end = arr.length - 1;

        int mid = (start + end) / 2;

        while (start <= end) {
            mid = (start + end) / 2;

            if (arr[mid] == target) {
                System.out.println(arr[mid]);
                return;
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

    }

}
