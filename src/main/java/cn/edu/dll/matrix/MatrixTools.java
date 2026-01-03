package cn.edu.dll.matrix;

import org.ejml.simple.SimpleMatrix;

public class MatrixTools {
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