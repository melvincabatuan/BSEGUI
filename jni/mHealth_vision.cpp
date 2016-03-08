//
// Created by cobalt on 1/4/16.
// mHealth JNI
//

#include <jni.h>
#include <opencv2/core.hpp>

/* Mixers (Filter)*/
#include "RecolorRC.h"


/* Contours */
#include "Contours.h"

using namespace mhealth;


#ifdef __cplusplus
extern "C" {
#endif


/****************************** RecolorRC ******************************/

JNIEXPORT jlong JNICALL Java_ph_edu_dlsu_fx_vision_RecolorRC_nativeCreateObject(JNIEnv *env, jclass type) {

    RecolorRCFilter *self = new RecolorRCFilter();
    return (jlong) self;

}


JNIEXPORT void JNICALL Java_ph_edu_dlsu_fx_vision_RecolorRC_nativeDestroyObject(JNIEnv *env, jclass type,
                                                              jlong thiz) {

    if (thiz != 0) {
        RecolorRCFilter *self = (RecolorRCFilter *) thiz;
        delete self;
    }

}

JNIEXPORT void JNICALL Java_ph_edu_dlsu_fx_vision_RecolorRC_apply(JNIEnv *env, jclass type, jlong thiz,
                                                     jlong srcAddr, jlong dstAddr) {

    if (thiz != 0) {
        RecolorRCFilter *self = (RecolorRCFilter *) thiz;
        cv::Mat &src = *(cv::Mat *) srcAddr;
        cv::Mat &dst = *(cv::Mat *) dstAddr;
        self->apply(src, dst);
    }

}


/******************************** Contours *******************************************/

JNIEXPORT jlong JNICALL Java_ph_edu_dlsu_fx_vision_Contours_nativeCreateObject
  (JNIEnv *env, jclass type){
      Contours *self = new Contours();
      return (jlong) self;
  }

JNIEXPORT void JNICALL Java_ph_edu_dlsu_fx_vision_Contours_nativeDestroyObject
  (JNIEnv *env, jclass type, jlong thiz){
        if (thiz != 0) {
            Contours *self = (Contours *) thiz;
            delete self;
        }
  }


JNIEXPORT void JNICALL Java_ph_edu_dlsu_fx_vision_Contours_apply
  (JNIEnv *env, jclass type, jlong thiz, jlong srcAddr, jlong dstAddr){
      if (thiz != 0) {
          Contours *self = (Contours *) thiz;
          cv::Mat &src = *(cv::Mat *) srcAddr;
          cv::Mat &dst = *(cv::Mat *) dstAddr;
          self->apply(src, dst);
      }
  }



#ifdef __cplusplus
}
#endif
