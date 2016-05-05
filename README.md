# GanWuMei
> 广告语：自从有了Gan物妹，每天都有新体...

一个分享干货的APP，运用了现在流行的Dagger2+Retrofit2+RxJava所制作的一个关于干货分享的开源项目，API接口由gank.io提供。

## 2016-5-05

>项目进入1.0阶段

本项目主要由3部分组成：
- 干货主界面，每日妹子预览list；
- Android、iOS 20条干货随机浏览；
- 每日干货详情区；


预览如下：

![](http://7xs99u.com1.z0.glb.clouddn.com/image/gif/GanWu20160505.gif)


[更新日志](https://github.com/Dimon94/GanWuMei/blob/master/UPDATE_LOG.md)

## TODO

- 收藏夹功能
- 分享功能
- GitHub用户登录功能
- 缓存功能

> 如果您对Gan物妹有什么自己的想法和意见欢迎`Fork`和`Issues` ♪(＾∀＾●)ﾉ ，谢谢支持！

## About

### 关于作者我：

呆毛我为大四即将毕业小朋友一枚，大四上学期开始的Android开发，至今约半年。欢迎前来勾搭哟~(✿◡‿◡)~

### 关于Gan物妹：

此乃吾`First`练手项目，可能代码充满着臭味道，但还是充满了对技术的追求。因为吾等晚辈才学疏浅，总会有些地方错漏，衷心希望如果您有好的建议或优化方案，欢迎`Fork`和`Issues` ♪(＾∀＾●)ﾉ ~

### 关于开源库：

感谢开源世界，抱着不重复造轮子的心态，默默地依赖了一大堆开源库(｡･∀･)ﾉﾞ，运用的都是一些先进比较流行、成熟度比较高的库，如下：

```
    compile 'com.github.chrisbanes:PhotoView:1.2.6'

    compile 'com.jakewharton:butterknife:7.0.1'// 标注
    compile 'com.jakewharton:disklrucache:2.0.2'//缓存

    apt 'com.google.dagger:dagger-compiler:2.0.2' //指定注解处理器
    compile 'com.google.dagger:dagger:2.0.2' // dagger2公用api
    compile 'com.google.dagger:dagger-compiler:2.0.2' // dagger2

    compile 'io.reactivex:rxandroid:1.1.0' // RxAndroid
    compile 'io.reactivex:rxjava:1.1.0' // 推荐同时加载RxJava

    compile 'com.squareup.retrofit2:retrofit:2.0.0-beta4' // Retrofit网络处理
    compile 'com.squareup.retrofit2:adapter-rxjava:2.0.0-beta4' // Retrofit的rx解析库
    compile 'com.squareup.retrofit2:converter-gson:2.0.0-beta4' // Retrofit的gson库

    compile 'com.google.code.gson:gson:2.6.2'

    compile 'com.github.bumptech.glide:glide:3.7.0'

    compile 'com.squareup.okhttp3:okhttp:3.1.2'
    compile 'com.squareup.okhttp3:logging-interceptor:3.1.2'

    compile 'com.umeng.analytics:analytics:latest.integration'

    compile 'com.github.florent37:materialviewpager:1.1.3'
    compile 'com.github.ksoichiro:android-observablescrollview'

    provided 'javax.annotation:jsr250-api:1.0' // Java标注
    provided 'org.glassfish:javax.annotation:10.0-b28'//添加android缺失的部分javax注解

    compile 'com.github.zhaokaiqiang.klog:library:1.3.0'//日志输出

    compile 'org.greenrobot:eventbus:3.0.0'
```

# 最后感谢

- 感谢[扔物线大大](https://github.com/rengwuxian)的[给 Android 开发者的 RxJava 详解](http://gank.io/post/560e15be2dca930e00da1083)

- 感谢[drakeet](https://drakeet.me/)的[Meizhi](https://github.com/drakeet/Meizhi)

- 感谢[开源世界](https://github.com/)

- 感谢[党](https://zh.wikipedia.org/zh/%E4%B8%AD%E5%9B%BD%E5%85%B1%E4%BA%A7%E5%85%9A)（纳尼？）

- 感谢[我的学校](https://dimon94.github.io/)（摁？）

- 感谢[我的母亲]()（这是真心的）