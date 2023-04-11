package Java;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class Main {
    public static void main(String[] args){
        int a = ZhiShu();
        System.out.println(a);
    }

    public static int maximumWealth(int[][] accounts){

        int[] aa = new int[accounts.length];
        for (int i = 0; i < accounts.length; i++) {
            for (int j = 0; j < accounts[i].length; j++) {
                aa[i]+= accounts[i][j];
            }
        }
        int max = aa[0];
        for (int i = 1; i < aa.length; i++) {
            if (max<aa[i]){
                max = aa[i];
            }
        }


        System.out.println(max);
        return max;
    }

    public static int numJewelsInStones_Sencond(String jewels,String stones){
        int count = 0;
        Set<Character> jewel = new HashSet<>();
        for (int i = 0; i < jewels.length(); i++) {
            char j = jewels.charAt(i);
            jewel.add(j);
        }
        for (int i = 0; i < stones.length(); i++) {
            char j = stones.charAt(i);
            if (jewel.contains(j)){
                count++;
            }
        }
        return count;
    }

    public static int numJewelsInStones(String jewels,String stones){
        StringBuffer sb = new StringBuffer();
        sb.append(stones);
        StringBuffer ss = new StringBuffer();
        ss.append(jewels);
        int count = 0;


        for (int i = 0; i < ss.length(); i++) {
            String a = ss.substring(i,i+1);
            for (int j = 0; j < sb.length(); j++) {

                if (a.equals(sb.substring(j, j + 1))){
                    count++;
                }
            }
        }

        System.out.println(count);
        return count;
    }

    public static int[] RunningSum(int[] nums){
        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j <= i; j++) {
                count += nums[j];
                if (j == i){
                    temp[i] = count;
                }

            }
        }
        return temp;
    }

    public static int count(int[] nums,int k){
        int count_num = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length-1){
                break;
            }
            for (int j = 1; j < nums.length; j++) {
                if (Math.abs(nums[i]-nums[j]) == k){
                    count_num +=1;
                }
            }
        }
        return count_num;
    }

    public static int operation(String[] operation){
        int opera = 0;
        for (int i = 0; i < operation.length; i++) {
            switch (operation[i]){
                case "X++":
                case "++X":
                    opera += 1;
                    break;
                case "X--":
                case "--X":
                    opera -= 1;
                    break;
            }
        }
        return opera;
    }
    
    
    public static int ZhiShu(){
        int count = 0;
        for (int i = 1; i <= 50; i++) {
            if (panDuanZhiShu(i)){
                count +=i;
            }

        }
        return count;
    }

    public static boolean panDuanZhiShu(int n) {
        if (n < 2) return false;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
