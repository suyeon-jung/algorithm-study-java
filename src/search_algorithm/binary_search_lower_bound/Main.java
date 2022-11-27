package search_algorithm.binary_search_lower_bound;

// 원하는 값 K 이상이 처음 나오는 위치
public class Main {

    public static void main(String[] args) {

        int[] arr = {1, 3, 4, 5, 7, 9, 12, 13, 15, 18};
        int target = 10;

        int start = 0;
        int end = arr.length - 1;
        int mid = 0;

        while(start < end) {

            mid = (start + end) / 2;
            if(arr[mid] >= target) {
                end = mid;
            }
            else {
                start = mid + 1;
            }
        }

        System.out.println(end);


    }

}
