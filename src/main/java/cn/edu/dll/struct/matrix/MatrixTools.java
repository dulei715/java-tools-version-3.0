package cn.edu.dll.struct.matrix;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixTools {

    /**
     * 转置并返回新的矩阵
     * @param data
     * @return
     * @param <T>
     */
    public static  <T> List<List<T>> getTransposition(List<List<T>> data) {
        int rowSize = data.size(), colSize = data.get(0).size();
        List<T> tempList;
        List[] listArray = new List[colSize];
        for (int i = 0; i < colSize; i++) {
            listArray[i] = new ArrayList();
        }
        for (List<T> dataList : data) {
            for (int i = 0; i < dataList.size(); i++) {
                listArray[i].add(dataList.get(i));
            }
        }
        return Arrays.asList(listArray);
    }


    public static void main(String[] args) {
        double[][] dataA = {{1,2}, {3,4}};
        double[][] dataB = {{5,6}, {7,8}};
//        RealMatrix matrixA = new Array2DRowRealMatrix(dataA);
//        RealMatrix matrixB = new Array2DRowRealMatrix(dataB);
//        RealMatrix sum = matrixA.add(matrixB);
//        RealMatrix product = matrixA.multiply(matrixB);
//        System.out.println(sum);
//        System.out.println(product);
        SimpleMatrix matrixA = new SimpleMatrix(dataA);
        SimpleMatrix matrixB = new SimpleMatrix(dataB);
        SimpleMatrix matrixSum = matrixA.plus(matrixB);
        SimpleMatrix matrixProduct = matrixA.mult(matrixB);
        System.out.println(matrixSum);
        System.out.println(matrixProduct);
    }
}