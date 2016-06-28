/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_smarteye_mpu_MPUCoreSDK */

#ifndef _Included_com_smarteye_mpu_MPUCoreSDK
#define _Included_com_smarteye_mpu_MPUCoreSDK
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_smarteye_mpu_MPUCoreSDK
 * Method:    FetchAudioPlayBuffer
 * Signature: (I)[B
 */
JNIEXPORT jbyteArray JNICALL Java_com_smarteye_mpu_MPUCoreSDK_FetchAudioPlayBuffer
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_smarteye_mpu_MPUCoreSDK
 * Method:    Finish
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_smarteye_mpu_MPUCoreSDK_Finish
  (JNIEnv *, jclass);

/*
 * Class:     com_smarteye_mpu_MPUCoreSDK
 * Method:    GetSDKOptionInt
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_smarteye_mpu_MPUCoreSDK_GetSDKOptionInt
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_smarteye_mpu_MPUCoreSDK
 * Method:    Initialize
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_smarteye_mpu_MPUCoreSDK_Initialize
  (JNIEnv *, jclass);

/*
 * Class:     com_smarteye_mpu_MPUCoreSDK
 * Method:    InputAudioData
 * Signature: ([BIJ)I
 */
JNIEXPORT jint JNICALL Java_com_smarteye_mpu_MPUCoreSDK_InputAudioData
  (JNIEnv *, jclass, jbyteArray, jint, jlong);

/*
 *
 *
 *
 */
JNIEXPORT jint JNICALL Java_com_smarteye_mpu_MPUCoreSDK_PostMessageToNative
  (JNIEnv *, jclass, jobject);

/*
 * Class:     com_smarteye_mpu_MPUCoreSDK
 * Method:    InputVideoData
 * Signature: ([BIJ)I
 */
JNIEXPORT jint JNICALL Java_com_smarteye_mpu_MPUCoreSDK_InputVideoData
  (JNIEnv *, jclass, jbyteArray, jint, jlong);

/*
 * Class:     com_smarteye_mpu_MPUCoreSDK
 * Method:    InputGPSData
 * Signature: (Lcom/smarteye/mpu/bean/GPSData;)I
 */
JNIEXPORT jint JNICALL Java_com_smarteye_mpu_MPUCoreSDK_InputGPSData
  (JNIEnv *, jclass, jobject);

/*
 * Class:     com_smarteye_mpu_MPUCoreSDK
 * Method:    Register
 * Signature: (Lcom/smarteye/mpu/bean/RegisterInfo;)I
 */
JNIEXPORT jint JNICALL Java_com_smarteye_mpu_MPUCoreSDK_Register
  (JNIEnv *, jclass, jobject);

/*
 * Class:     com_smarteye_mpu_MPUCoreSDK
 * Method:    ReStart
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_smarteye_mpu_MPUCoreSDK_ReStart
  (JNIEnv *, jclass);

/*
 * Class:     com_smarteye_mpu_MPUCoreSDK
 * Method:    SetInputAudioFormat
 * Signature: (IIII)I
 */
JNIEXPORT jint JNICALL Java_com_smarteye_mpu_MPUCoreSDK_SetInputAudioFormat
  (JNIEnv *, jclass, jint, jint, jint, jint);

/*
 * Class:     com_smarteye_mpu_MPUCoreSDK
 * Method:    SetInputVideoFormat
 * Signature: (IIIII)I
 */
JNIEXPORT jint JNICALL Java_com_smarteye_mpu_MPUCoreSDK_SetInputVideoFormat
  (JNIEnv *, jclass, jint, jint, jint, jint, jint);

/*
 * Class:     com_smarteye_mpu_MPUCoreSDK
 * Method:    SetSDKOptionInt
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_smarteye_mpu_MPUCoreSDK_SetSDKOptionInt
  (JNIEnv *, jclass, jint, jint);

/*
 * Class:     com_smarteye_mpu_MPUCoreSDK
 * Method:    Storage
 * Signature: (Lcom/smarteye/mpu/bean/StorageInfo;)I
 */
JNIEXPORT jint JNICALL Java_com_smarteye_mpu_MPUCoreSDK_Storage
  (JNIEnv *, jclass, jobject);

/*
 * Class:     com_smarteye_mpu_MPUCoreSDK
 * Method:    GetSDKOptionString
 * Signature: (I)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_smarteye_mpu_MPUCoreSDK_GetSDKOptionString
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_smarteye_mpu_MPUCoreSDK
 * Method:    RegisterNotify
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_smarteye_mpu_MPUCoreSDK_RegisterNotify
  (JNIEnv *, jobject);

/*
 * Class:     com_smarteye_mpu_MPUCoreSDK
 * Method:    SetSDKOptionString
 * Signature: (ILjava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_com_smarteye_mpu_MPUCoreSDK_SetSDKOptionString
  (JNIEnv *, jclass, jint, jstring);

/*
 * Class:     com_smarteye_mpu_MPUCoreSDK
 * Method:    GetFrameCount
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_smarteye_mpu_MPUCoreSDK_GetFrameCount
  (JNIEnv *jobject);

/*
 * Class:     com_smarteye_mpu_MPUCoreSDK
 * Method:    GetUploadCount
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_smarteye_mpu_MPUCoreSDK_GetUploadCount
  (JNIEnv *jobject);

#ifdef __cplusplus
}
#endif
#endif
