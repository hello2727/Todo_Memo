개인 프로젝트
==============
>글 메모와 사진 메모가 가능한 메모장

결과물
-----------------
### 전체적인 구성
- 글메모 탭
<div>
  <img width="200" src="https://user-images.githubusercontent.com/43267195/83618657-b9a59b80-a5c5-11ea-8891-d44d63e641e1.jpg"> 
  <img width="200" src="https://user-images.githubusercontent.com/43267195/83618679-c5915d80-a5c5-11ea-819e-26e7f49b8d99.jpg">
</div>

- 사진메모 탭
<div>
  <img width="200" src="https://user-images.githubusercontent.com/43267195/83618629-ae527000-a5c5-11ea-9d7b-499fe15c024f.jpg">          
</div>


환경설정
-----------------
### build.gradle(project)
```
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven {url 'https://maven.google.com'}
        maven {url 'https://dl.bintray.com/azeesoft/maven'}
    }
```
### build.gradle(module)
```
implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
    implementation 'com.google.android.material:material:1.1.0'

    /*Room 데이터베이스*/
    def room_version = "2.2.5"
    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"
    // optional - RxJava support for Room
    implementation "androidx.room:room-rxjava2:$room_version"
    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation "androidx.room:room-guava:$room_version"
    // optional - Test helpers
    testImplementation "androidx.room:room-testing:$room_version"

    /*ViewModel과 liveData*/
    def lifecycle_version = "2.0.0"
    implementation "android.arch.lifecycle:extensions:$lifecycle_version"
    annotationProcessor "android.arch.lifecycle:compiler:$lifecycle_version"

    // Glide dependencies
    implementation 'com.github.bumptech.glide:glide:4.11.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0'

    //colorPicker
    implementation 'com.azeesoft.lib.colorpicker:colorpicker:1.0.8@aar'
```
- Manifest
```
    <!--원본 크기의 사진 저장-->
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"
        android:maxSdkVersion="18"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.CAMERA" />
    
    //백업설정 취소
    <application
        android:allowBackup="false"
        android:fullBackupOnly="false"
        tools:replace="android:allowBackup"
        ...>
    
    //상단 탭 폰트 설정
    <meta-data
          android:name="preloaded_fonts"
          android:resource="@array/preloaded_fonts" />
```
