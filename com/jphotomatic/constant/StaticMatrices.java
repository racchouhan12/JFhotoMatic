package com.jphotomatic.constant;

public interface StaticMatrices
{
  // red band Matrix
  float RED_BAND_MATRIX[][] = { { 1.0f, 0.0f, 0.0f },
      { 0.0f, 0.0f, 0.0f }, { 0.0f, 0.0f, 0.0f } };
  // green band Matrix
   float GREEN_BAND_MATRIX[][] = { { 0.0f, 0.0f, 0.0f },
      { 0.0f, 1.0f, 0.0f }, { 0.0f, 0.0f, 0.0f } };
  // blue band Matrix
   float BLUE_BAND_MATRIX[][] = { { 0.0f, 0.0f, 0.0f },
      { 0.0f, 0.0f, 0.0f }, { 0.0f, 0.0f, 1.0f } };
  // Matrix that inverts all the bands
  // the nagative of the image.
  float INVERSE_BAND_MATRIX[][] = { { -1.0f, 0.0f, 0.0f },
      { 0.0f, -1.0f, 0.0f }, { 0.0f, 0.0f, -1.0f } };
  // yellow
   float AVERAGE_BAND_MATRIX[][] = { { 1.0f, 0.0f, 0.0f },
      { 0.0f, 1.0f, 0.0f }, { 0.0f, 0.0f, 0.0f } };
  //matrix for sharpening
   float  SHARPEN_MATRIX[]={ 0.0f, -1.0f, 0.0f,-1.0f, 5.0f, -1.0f,0.0f, -1.0f, 0.0f};
 //matrix for blurring
  static final float  BLUR_MATRIX[]={ 0.125f,0.125f, 0.125f,0.125f,0.125f,0.125f,0.125f, 0.111f,0.111f};
 //matrix for edge detect
  float  EDGE_MATRIX[]={ 0.0f, -1.0f, 0.0f,-1.0f, -20.f, -1.0f,0.0f, -1.0f, 0.0f};


}