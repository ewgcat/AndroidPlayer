# AndroidPlayer

## SDK 概述

AndroidPlayer 是一个适用于 Android 平台的影音播发器 SDK ，基于 [ijkplayer](https://github.com/Bilibili/ijkplayer) ( based on [ffplay](http://ffmpeg.org/) )，可高速定制化和二次开发，为 Android 开发者提供简单，快捷的接口。

## 播放器功能特性

	
* 支持在线视频协议：`HLS`, `RTMP`, `HTTP-FLV` 等

* 支持本地视频播放 （MP4、M4A、flv 等）

* 支持设置窗口大小和全屏设置

* 支持缓冲大小设置

* 提供 UpVideoView 控件

* 支持 ARM, ARMv7a, ARM64v8a, X86 主流芯片体系架构

## sdk 使用
allprojects {
    repositories {
        mavenCentral()
        google()
        jcenter()
        maven { url "https://jitpack.io" }
        maven { url "https://maven.aliyun.com/repository/public" }
        maven { url 'https://maven.aliyun.com/repository/google' }
    }
}

 implementation 'com.github.ewgcat:AndroidPlayer:1.0.6'


## SDK 使用示例

直接使用控件 JackVideoView

```java
        //设置默认缓存区大小 (需在setVideoPath 或者 resume 前执行生效)
        jackVideoView.setBufferSize(1 * 1024 * 1024);

        //设置背景图片
        jackVideoView.setImage(R.drawable.dog);

        //设置播放地址
       jackVideoView.setVideoPath(path);

        //开始播放
        jackVideoView.start();
        
        //暂停播放
        jackVideoView.pause();
        
        // 重新开始播放器
        jackVideoView.resume();
```


## SDK 最低要求

Android 5.0 (API 21) 及其以上

## 版本历史

* 支持rtmp, hls, http-flv h265

* 增加统计信息的获取和上传

* 支持调整宽高比

* 增加缓冲区控制

* 增加全屏播放和断线重连

* 增加pid,cid等统计参数

* 支持单音频播放，speex格式解码

* 播放器秒开优化

* 播放器累积延时优化

* 播放器内存消耗优化

* 播放器添加动态追帧

* 增加获取音频数据接口

## 说明
SDK 目前基于 [ijkplayer](https://github.com/Bilibili/ijkplayer) , 感谢 [ijkplayer](https://github.com/Bilibili/ijkplayer)。


