package org.uims.datamaintain.studydata;

/**
 * 稀疏数组
 * <p>一个数组中的大部分元素为0或者为同一个值的数据时，可以使用稀疏数组来保存改数据。记录数组一共有几行几列有多少个不同的值，
 * 把具有不同值的元素的行为压缩保存在一个小的程序中,常用的场景就是将数据压缩后保存在文件或其他介质中</p>
 * @author kyrie 2020-3-16 22:44:38
 * @since jdk 1.8
 *
 */
public class SparseArray {
    public static void main(String[] args) {
        int[][] allArray = new int[11][11];
        allArray[1][2] = 1;
        allArray[2][3] = 2;
        allArray[3][4] = 2;

        int sum = 0;
        for (int[] row : allArray) {
            for (int item: row) {
                if (item != 0){
                    sum++;
                }
            }
        }
        System.out.println("sum = " + sum);

        int[][] sparseArray = new int[sum+1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        int count = 0;
        for (int i = 0; i < allArray.length; i++) {
            for (int j = 0; j < allArray[i].length; j++) {
                if (allArray[i][j] != 0){
                    count++;

                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = allArray[i][j];
                }
            }
        }

        for (int i = 0;i<sparseArray.length;i++){
            System.out.printf("%d\t%d\t%d\t\n",sparseArray[i][0],sparseArray[i][1],sparseArray[i][2]);
        }

        int[][] allArray2 = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i=1;i<sparseArray.length;i++){
            allArray2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        System.out.println("稀疏数组还原后");
        for (int[] row : allArray) {
            for (int item: row) {
                System.out.printf("%d\t",item);
            }
            System.out.println("\n");
        }
    }
}
