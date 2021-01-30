개인 프로젝트
==============
>글 메모와 사진/손필기 메모가 가능한 메모장

개발환경
-----------------
* Android Java Glide MVVM(Model-View-ViewModel) RoomDB RxJava lifecycle Camera SharedPreferences ColorPicker

결과물
-----------------
### 전체적인 구성
- 처음 앱 실행시 나타나는 카메라 권한 허용 선택창
<div>
  <img width="200" src="https://user-images.githubusercontent.com/43267195/105188671-7d847280-5b77-11eb-9b31-35b24fa27e08.PNG"> 
</div>

- 글메모 탭
<div>
  <img width="200" src="https://user-images.githubusercontent.com/43267195/83618657-b9a59b80-a5c5-11ea-8891-d44d63e641e1.jpg"> 
</div>

- 사진메모 탭
<div>
  <img width="200" src="https://user-images.githubusercontent.com/43267195/106307688-fd5ebb00-62a2-11eb-98e4-671195ba9a2d.PNG">          
</div>

- 글메모 생성 버튼 및 사진 메모 생성 버튼
<div>
  <img width="200" src="https://user-images.githubusercontent.com/43267195/105188505-52018800-5b77-11eb-93e4-d6f925bf3b96.gif">          
</div>

### 글메모
- 글메모 생성/특정 메모 상세보기/롱클릭 삭제/수정1. 글 수정/수정2. 제목, 내용 에디트텍스트가 빈칸이면 메모 자동 삭제
<div>
  <img width="200" src="https://user-images.githubusercontent.com/43267195/105189494-5da17e80-5b78-11eb-88b0-fadcc840b9fb.gif">        
  <img width="200" src="https://user-images.githubusercontent.com/43267195/105190016-ed472d00-5b78-11eb-84b6-4bb3dce27b55.gif">      
  <img width="200" src="https://user-images.githubusercontent.com/43267195/105189744-9f322980-5b78-11eb-8f48-a1a55de3d0c8.gif">      
  <img width="200" src="https://user-images.githubusercontent.com/43267195/105190512-6a72a200-5b79-11eb-8666-f124cf20f7f9.gif"> 
  <img width="200" src="https://user-images.githubusercontent.com/43267195/105190644-9130d880-5b79-11eb-8ff6-3b020f9bda40.gif"> 
</div>

### 사진메모
- 사진메모 버튼 누르면 켜지는 카메라
<div>
  <img width="200" src="https://user-images.githubusercontent.com/43267195/105190927-dead4580-5b79-11eb-9c51-5aaf79b7616b.gif">          
</div>

- 사진찍으면 사진메모 생성 액티비티로 진입 및 이미지뷰에 찍은 이미지 삽입
<div>
  <img width="200" src="https://user-images.githubusercontent.com/43267195/105191282-3ba8fb80-5b7a-11eb-9b29-918de10a298f.gif">          
</div>

- 백프레스버튼 누르면 나오는 대화상자. "No"는 사진메모 생성 액티비티 나가기, "Yes"는 사진메모 저장
<div>
  <img width="200" src="https://user-images.githubusercontent.com/43267195/105192008-0a7cfb00-5b7b-11eb-9657-f8cb7f5a9897.gif">    
  <img width="200" src="https://user-images.githubusercontent.com/43267195/106306153-fdf65200-62a0-11eb-982b-c33d5622563a.gif"> 
</div>

- 찍은 사진 위에 펜메모 남기기(1. 펜색상 선택하고 그리기, 2. 다른 펜색상 선택 및 그리기<색 구분되어 그려짐>, 3. 지우개 기능, 4. 저장)
<div>
  <img width="200" src="https://user-images.githubusercontent.com/43267195/106306770-d784e680-62a1-11eb-813c-ab40a88218d8.gif">       
  <img width="200" src="https://user-images.githubusercontent.com/43267195/106307058-36e2f680-62a2-11eb-8ea3-7979a5c85fcf.gif">    
  <img width="200" src="https://user-images.githubusercontent.com/43267195/106307272-7f021900-62a2-11eb-8766-79dc175512ca.gif">  
  <img width="200" src="https://user-images.githubusercontent.com/43267195/106307387-a48f2280-62a2-11eb-9ab8-9e3966948093.gif">  
</div>

- 롱클릭 후 사진 메모 삭제. "예"는 사진메모 삭제, "아니오"는 그냥 return
<div>
  <img width="200" src="https://user-images.githubusercontent.com/43267195/106307925-52023600-62a3-11eb-8269-4ec18270c7d7.gif">    
  <img width="200" src="https://user-images.githubusercontent.com/43267195/106308222-be7d3500-62a3-11eb-9a34-759133716590.gif">  
</div>

- 특정 사진 메모 상세보기
<div>
  <img width="200" src="https://user-images.githubusercontent.com/43267195/106309034-e7ea9080-62a4-11eb-981c-b6837ad92e08.gif">    
</div>


### 환경설정
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
    
    <application
        //백업설정 취소
        android:allowBackup="false"
        android:fullBackupOnly="false"
        tools:replace="android:allowBackup"
        //db 설정
        android:requestLegacyExternalStorage="true"
        ...>
    
    //상단 탭 폰트 설정
    <meta-data
          android:name="preloaded_fonts"
          android:resource="@array/preloaded_fonts" />
```
