/**
 * @author louishu
 * @date 1/3/26 16:28
 * @description
 */
public class Funs {

    /**
     * 11. Container with most water
     * 01/03/2026
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int  maxCap = 0;
//        for (int i = 0; i < height.length; i++) {
//            for (int j = i+1; j < height.length; j++) {
//                int cap = ((height[i]>height[j])? height[j]: height[i]) * (j-i);
//                if (cap>maxCap){
//                    maxCap = cap;
//                }
//
//            }
//        }
        int left = 0, right = height.length-1;
        while (left < right){
            int cap = ((height[left]>height[right])? height[right]: height[left]) * (right-left);
            if (cap > maxCap) {
                maxCap = cap;
            }
            if(height[left]<height[right]){
                left++;
            }else{
                right--;
            }
        }
        return maxCap;
    }

    public static void main(String[] args) {
        int height[] = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }
}
