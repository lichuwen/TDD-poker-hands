package com.example;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class splitBarcode {

    final static String TEMPLATE_PATH = "D:\\APC-EInvoice\\splitRes\\OKL.20200820110602.65351_004\\OKL.20200820110602.65351_004_5.png";
    private static final int KERNEL_WIDTH = 3;
    private static final int KERNEL_HEIGHT = 3;
    private static final int MASK_Y_PROP = 6;
    private static final int MASK_X_PROP = 10;
    private static final int COL_SIZE = 2480 / 6;
    private static final int ROW_SIZE = 3508 / 6;
    private static final int EXTERNAL_THRESHOLD = 40000;
    private static final int INTERNAL_THRESHOLD = 3697245;

    public static void main(String[] args) {

        System.load("D:\\openCV\\opencv_java340\\opencv_java340-x64.dll");
        long startTime = System.currentTimeMillis();
//        Mat sample = Imgcodecs.imread("D:\\APC-EInvoice\\showcaseImg\\testInvoice\\OHKL.normal.png", 0);
        Mat sample = Imgcodecs.imread("D:\\APC-EInvoice\\testSplit\\OKL\\OKL.014\\OKL.014_8.png", 0);
//        System.out.println("sampleMat: " + sample);



        Mat sampleResize = resizeMat(sample);
//        System.out.println("resizeMat: " + sampleResize);
        Mat sampleBinary = binarization(sampleResize);
//        System.out.println("sampleBinary: " + getMatSum(sampleBinary));

        Mat internalMask = copyMask(sampleBinary, 1);
//        System.out.println("internalMask: " + getMatSum(internalMask));

        Mat sampleGradientX = gradientX(internalMask);
//        System.out.println("sampleGradientX: " + getMatSum(sampleGradientX));

        Mat sampleGradientY = gradientY(internalMask);
//        System.out.println("sampleGradientY: " + getMatSum(sampleGradientY));

        Mat internalSub = subtractTwoMat(sampleGradientX, sampleGradientY);
        double internalSum = getMatSum(internalSub);
//        System.out.println("INTERNAL_sum: " + internalSum);

        Mat sampleDivision = new Mat(new Size(COL_SIZE, ROW_SIZE), CvType.CV_8UC1);
        Core.divide(sampleBinary, creatValueMat(255), sampleDivision);
        Mat sampleSub = subtractTwoMat(creatOneMat(), sampleDivision);
//        System.out.println("sampleSub: " + getMatSum(sampleSub));

        Mat showMat = sampleSub.mul(creatValueMat(255));
        Mat externalMask = copyMask(showMat, 0);
//        System.out.println("externalMask: " + getMatSum(externalMask));

        Mat sampleDilate = dilate(externalMask);
//        System.out.println("sampleDilate: " + getMatSum(sampleDilate));

        double externalSum = getMatSum(sampleDilate);
//        System.out.println("EXTERNAL_sum: " + externalSum);

//        Imgcodecs.imwrite("D:\\APC-EInvoice\\showcaseImg\\testResult\\sampleBinary.jpg", sampleBinary);
//        Imgcodecs.imwrite("D:\\APC-EInvoice\\showcaseImg\\testResult\\sampleResize.jpg", sampleResize);

        Imgcodecs.imwrite("D:\\APC-EInvoice\\showcaseImg\\testResult\\internalMask.jpg", internalMask);
//        Imgcodecs.imwrite("D:\\APC-EInvoice\\showcaseImg\\testResult\\sampleGradientX.jpg", sampleGradientX);
//        Imgcodecs.imwrite("D:\\APC-EInvoice\\showcaseImg\\testResult\\sampleGradientY.jpg", sampleGradientY);
//        Imgcodecs.imwrite("D:\\APC-EInvoice\\showcaseImg\\testResult\\internalSub.jpg", internalSub);

        Imgcodecs.imwrite("D:\\APC-EInvoice\\showcaseImg\\testResult\\showMat.jpg", showMat);
        Imgcodecs.imwrite("D:\\APC-EInvoice\\showcaseImg\\testResult\\externalMask.jpg", externalMask);
//        Imgcodecs.imwrite("D:\\APC-EInvoice\\showcaseImg\\testResult\\sampleDilate.jpg", sampleDilate);
//        Imgcodecs.imwrite("D:\\APC-EInvoice\\showcaseImg\\testResult\\sampleClose.jpg", sampleDilate);

        System.out.println("EXTERNAL_sum: " + externalSum);
        System.out.println("INTERNAL_sum: " + internalSum);
        if(isExternalBarCode(externalSum) && isInternalBarCode(internalSum)){
            System.out.println(" 图为条形码---------------");
        }
        else {
            System.out.println(" 不是条形码--------------------");
        }

        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
    }

    public static Mat close(Mat src) {
        Mat image = new Mat(src.size(), src.type());
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(KERNEL_WIDTH, KERNEL_HEIGHT), new Point(-1, -1));
        Imgproc.morphologyEx(src, image, Imgproc.MORPH_CLOSE, kernel);
        return image;
    }

    public static Mat resizeMat(Mat srcMat) {
        Mat srcResize = new Mat();
        Imgproc.resize(srcMat, srcResize, new Size(COL_SIZE, ROW_SIZE), 0, 0, Imgproc.INTER_AREA);
        return srcResize;
    }

    public static Mat subtractTwoMat(Mat templateCode, Mat testCode) {
        Mat mat = new Mat();
        mat.create(templateCode.size(), CvType.CV_8UC1);
        Core.subtract(templateCode, testCode, mat);
        return mat;
    }

    public static double getMatSum(Mat mat) {
        return Core.sumElems(mat).val[0];
    }

    public static Mat creatValueMat(int value) {
        return new Mat(new Size(COL_SIZE, ROW_SIZE), CvType.CV_8UC1, new Scalar(value));
    }

    public static Mat creatOneMat() {
        return Mat.ones(new Size(COL_SIZE, ROW_SIZE), CvType.CV_8UC1);
    }

    public static Mat creatZeroMat() {
        return Mat.zeros(new Size(COL_SIZE, ROW_SIZE), CvType.CV_8UC1);
    }

    public static Mat creatMask(Mat src, int oneOrZero) {
        Mat mask = oneOrZero > 0 ? creatZeroMat() : creatOneMat();
        for (int y = src.height() / MASK_Y_PROP; y < src.height() * 5 / MASK_Y_PROP; y++) {
            for (int x = src.height() / 12; x < src.width() * 9 / 10; x++) {
                mask.put(y, x, oneOrZero);
            }
        }

        return mask;
    }

    public static Mat copyMask(Mat targetMat, int oneOrZero) {
        Mat mask = creatMask(targetMat, oneOrZero);
        Mat destinationMat = new Mat();
        targetMat.copyTo(destinationMat, mask);
        return destinationMat;
    }

    public static Mat binarization(Mat srcMat) {
        Mat binaryMat = new Mat();
        Imgproc.threshold(srcMat, binaryMat, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
        return binaryMat;
    }

    public static Mat gradientX(Mat internalMask) {
        Mat scharrX = new Mat();
        Imgproc.Scharr(internalMask, scharrX, CvType.CV_32F, 1, 0);
        Core.convertScaleAbs(scharrX, scharrX);
        return scharrX;
    }

    public static Mat gradientY(Mat internalMat) {
        Mat scharrY = new Mat();
        Imgproc.Scharr(internalMat, scharrY, CvType.CV_32F, 0, 1);
        Core.convertScaleAbs(scharrY, scharrY);
        return scharrY;
    }

    public static Mat dilate(Mat externalMat) {
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(KERNEL_WIDTH, KERNEL_HEIGHT));
        Mat dilateImage = externalMat.clone();
        Imgproc.dilate(externalMat, dilateImage, kernel, new Point(-1, -1), 1);
        return dilateImage;
    }

    private static Mat byteToMat(byte[] bytes) {
        return Imgcodecs.imdecode(new MatOfByte(bytes), Imgcodecs.IMREAD_GRAYSCALE);
    }

    public static Boolean isInternalBarCode(double sum) {
        return sum >= INTERNAL_THRESHOLD;
    }

    public static Boolean isExternalBarCode(double sum) {
        return sum <= EXTERNAL_THRESHOLD;
    }

}
