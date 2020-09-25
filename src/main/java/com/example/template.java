package com.example;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class template {

    final static int EXTERNAL_THRESHOLD = 713045;
    final static int INTERNAL_THRESHOLD = 3697245;

    private static Mat makeMask(Mat src,int oneOrZero){
        Size sz = new Size(src.width(),src.height());
        Mat mask;
        if(oneOrZero == 0)
            mask = Mat.ones(sz, CvType.CV_8UC1);
        else
            mask = Mat.zeros(sz, CvType.CV_8UC1);
        for(int y=src.height()/6; y<src.height()*5/6; y++){
            for(int x=src.height()/9; x<src.width()*8/9; x++){
                mask.put(y, x, oneOrZero);
            }
        }
        return mask;
    }

    public static Mat dilate(Mat src){
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3,3));
        //膨胀
        Mat dilateImage = src.clone();
        Imgproc.dilate(src, dilateImage, kernel, new Point(-1, -1), 1);
        return dilateImage;
    }

    public static Mat close(Mat src) {
        Mat image = new Mat(src.size(), src.type());
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3), new Point(-1, -1));
        Imgproc.morphologyEx(src, image, Imgproc.MORPH_CLOSE, kernel);
        return image;
    }

    public static void main(String[] args) {
//        System.load(Core.NATIVE_LIBRARY_NAME);
        System.load("D:\\openCV\\opencv_java340\\opencv_java340-x64.dll");
        int width = 1767;
        int height = 1869;

//---------------------------------------resize-------------------------
        Mat src = Imgcodecs.imread("D:\\APC-EInvoice\\splitRes\\OKL.20200820110602.65351_004\\OKL.20200820110602.65351_004_5.png", 0);
        int cols = src.cols();
        int rows = src.rows();
        System.out.println(rows);
        Mat src_resize = new Mat();
        src_resize.create(src.size(), CvType.CV_8UC1);
        Imgproc.resize(src, src_resize, new Size(cols,rows), 0, 0, Imgproc.INTER_AREA);

        Mat wrong = Imgcodecs.imread("D:\\APC-EInvoice\\showcaseImg\\testInvoice\\OKL.like.png", 0);
        Mat wrong_resize = new Mat();
        wrong_resize.create(src.size(), CvType.CV_8UC1);
        Imgproc.resize(wrong, wrong_resize, new Size(cols,rows), 0, 0, Imgproc.INTER_AREA);


        Mat right = Imgcodecs.imread("D:\\APC-EInvoice\\showcaseImg\\testInvoice\\OTL.right.png", 0);
        Mat right_resize = new Mat();
        right_resize.create(src.size(), CvType.CV_8UC1);
        Imgproc.resize(right, right_resize, new Size(cols,rows), 0, 0, Imgproc.INTER_AREA);

//---------------------------------------Binarization-------------------------
        Mat bin_src = new Mat();
        Imgproc.threshold(src_resize, bin_src, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
//        Imgcodecs.imwrite("D:\\APC-EInvoice\\showcaseImg\\testResult\\bin_src.jpg", bin_src);

        Mat bin_wrong = new Mat();
        Imgproc.threshold(wrong_resize, bin_wrong, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
//        Imgcodecs.imwrite("D:\\APC-EInvoice\\showcaseImg\\testResult\\bin_test.jpg", bin_wrong); //显示图片

        Mat bin_right = new Mat();
        Imgproc.threshold(right_resize, bin_right, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
//        Imgcodecs.imwrite("D:\\APC-EInvoice\\showcaseImg\\testResult\\bin_right.jpg", bin_right); //显示图片


//---------------------------------------MiddleAreaMask-------------------------
//        Mat middle_mask = makeMask(bin_right,1);
//        Mat middle_right = new Mat();
//        bin_right.copyTo(middle_right,middle_mask);
//        Imgcodecs.imwrite("D:\\APC-EInvoice\\showcaseImg\\testResult\\middle_right.jpg", middle_right); //显示图片
//
//        Mat middle_mask2 = makeMask(bin_wrong,1);
//        Mat middle_wrong = new Mat();
//        bin_wrong.copyTo(middle_wrong,middle_mask2);
//        Imgcodecs.imwrite("D:\\APC-EInvoice\\showcaseImg\\testResult\\middle_test.jpg", middle_wrong); //显示图片


//---------------------------------------dilate-----------------------
//        Mat dilate_right = dilate(middle_right);
//        Mat dilate_wrong = dilate(middle_wrong);
//        Imgcodecs.imwrite("D:\\APC-EInvoice\\mask\\dilate_right.jpg", dilate_right);
//        Imgcodecs.imwrite("D:\\APC-EInvoice\\mask\\dilate_wrong.jpg", dilate_wrong);

//---------------------------------------gradient-y-----------------------
//        Mat scharr_y_wrong = new Mat();
//        Imgproc.Scharr(middle_wrong,scharr_y_wrong, CvType.CV_32F,0,1);//y方向
//        Core.convertScaleAbs(scharr_y_wrong,scharr_y_wrong);
//
//        Mat scharr_y_right = new Mat();
//        Imgproc.Scharr(middle_right,scharr_y_right, CvType.CV_32F,0,1);//y方向
//        Core.convertScaleAbs(scharr_y_right,scharr_y_right);
//
//        Imgcodecs.imwrite("D:\\APC-EInvoice\\showcaseImg\\testResult\\scharr_y_right.jpg", scharr_y_right);
//        Imgcodecs.imwrite("D:\\APC-EInvoice\\showcaseImg\\testResult\\scharr_y_wrong.jpg", scharr_y_wrong);

//---------------------------------------gradient-x-----------------------
//        Mat scharr_x_wrong = new Mat();
//        Imgproc.Scharr(middle_wrong,scharr_x_wrong, CvType.CV_32F,1,0);//x方向
//        Core.convertScaleAbs(scharr_x_wrong,scharr_x_wrong);
//
//        Mat scharr_x_right = new Mat();
//        Imgproc.Scharr(middle_right,scharr_x_right, CvType.CV_32F,1,0);//x方向
//        Core.convertScaleAbs(scharr_x_right,scharr_x_right);
//
//        Imgcodecs.imwrite("D:\\APC-EInvoice\\showcaseImg\\testResult\\scharr_x_right.jpg", scharr_x_right);
//        Imgcodecs.imwrite("D:\\APC-EInvoice\\showcaseImg\\testResult\\scharr_x_wrong.jpg", scharr_x_wrong);

//---------------------------------------subtract-------------------------
        Mat wrong_image = new Mat();
        wrong_image.create(right_resize.size(), CvType.CV_8UC1);
        Core.subtract(bin_src, bin_wrong, wrong_image);
        Imgcodecs.imwrite("D:\\APC-EInvoice\\showcaseImg\\testResult\\wrong_image.jpg", wrong_image); //显示图片

        Mat right_image = new Mat();
        right_image.create(right_resize.size(), CvType.CV_8UC1);
        Core.subtract(bin_src, bin_right, right_image);
        Imgcodecs.imwrite("D:\\APC-EInvoice\\showcaseImg\\testResult\\right_image.jpg", right_image); //显示图片


//---------------------------------------mask-------------------------
        Mat mask = makeMask(right_image,0);
        Mat mask_right = new Mat();
        right_image.copyTo(mask_right,mask);
        Imgcodecs.imwrite("D:\\APC-EInvoice\\showcaseImg\\testResult\\mask_right.jpg", mask_right); //显示图片

        Mat mask2 = makeMask(wrong_image,0);
        Mat mask_wrong = new Mat();
        wrong_image.copyTo(mask_wrong,mask2);
        Imgcodecs.imwrite("D:\\APC-EInvoice\\showcaseImg\\testResult\\mask_wrong.jpg", mask_wrong); //显示图片

//---------------------------------------close-----------------------
        Mat close_right = close(mask_right);
        Mat close_wrong = close(mask_wrong);
        Imgcodecs.imwrite("D:\\APC-EInvoice\\showcaseImg\\testResult\\close_right.jpg", close_right);
        Imgcodecs.imwrite("D:\\APC-EInvoice\\showcaseImg\\testResult\\close_wrong.jpg", close_wrong);

//---------------------------------------sum-------------------------
        double right_res_x = Core.sumElems(right_image).val[0];
        double wrong_res_x = Core.sumElems(wrong_image).val[0];
//        double right_res_y = Core.sumElems(scharr_y_right).val[0];
//        double wrong_res_y = Core.sumElems(scharr_y_wrong).val[0];
        System.out.println("wrongSumX: "+wrong_res_x);
        System.out.println("rightSumX: "+right_res_x);
//        System.out.println("wrongSumY: "+wrong_res_y);
//        System.out.println("rightSumY: "+right_res_y);

//---------------------------------------scope-------------------------

//        double threshold = wrong_res - right_res;
//        System.out.println("差值： "+threshold);
//        if(wrong_res <= right_res){
//            System.out.println("该图为条形码");
//        }
//        else{
//            System.out.println("该图不是条码");
//        }


    }


}
