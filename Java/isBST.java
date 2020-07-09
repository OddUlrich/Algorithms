
public class isBST {
    public static void main(String[] args) {
        int[] arr = new int[]{8, 6, 5, 7, 10, 9, 11};

        int len = arr.length;
        if (len == 0) {
            // return false;
        } else if (len == 1) {
            // return true;
        }

        boolean bBST;
        bBST = isSubBST(arr, 1, len);


    }

    public static boolean isSubBST(int[] arr, int start, int end) {
    
        int base = arr[start-1];
        boolean bLeft = false, bRight = false;

        int i, j;
        // Get the left subtree array.
        for (i = start; i < end; ++i) {
            if (arr[i] > base) {
                break;
            }
        }
        bLeft = isSubBST(arr, start, i);

        // Get the right subtree array.
        for (j = i; j < end; ++j) {
            if (arr[j] < base) {
                break;
            }
        }
        if (j != end) {
            return false;
        } else {
            bRight = isSubBST(arr, i, j);
        }
        
        return bLeft && bRight;

    }

}