# ZXingCamera
Camera for Android

* 1.利用zxing的camera实现拍照

只需要引用库，查看demo中的CameraActivity

```java
compile 'io.github.dltech21:zcamera:1.0.3'
```


* 2.实时身份证ocr拍照，ocr准确率不太高，只是用于拍照识别，后续会做拍照后采用face++做一次校验

只需要引用库idcard_ocr，不再需要zcamera，查看demo，
```java
compile 'io.github.dltech21:idcard_ocr:1.1.1' 
```

支持armv7的包
```java
compile 'io.github.dltech21:idcard_ocr:1.1.3' 
```
