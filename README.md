# collageview

[![](https://jitpack.io/v/lopei/collageview.svg)](https://jitpack.io/#lopei/collageview)

CollageView is a library for creating simple photo collages in your applications. Would be great, for example, in profile page or feed page.


## What's new in version 0.2.0?
- Updated project libraries, updated build.gradle, removed sample images

## How to use?
Step 1. Add the JitPack repository to your build file

Gradle:

```
allprojects {
  repositories {
    maven { url "https://jitpack.io" }
  }
}
```

Maven:

```
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

```
Step 2. Add the dependency

Gradle:

```
dependencies {
  compile 'com.github.lopei:collageview:0.1.3'
}
```

Maven:

```
<dependency>
  <groupId>com.github.lopei</groupId>
  <artifactId>collageview</artifactId>
  <version>0.1.3</version>
</dependency>
  
```


Step 3. Add Collage view to your layout resource file:
```
<com.lopei.collageview.CollageView
  android:id="@+id/collageView"
  android:layout_width="match_parent"
  android:layout_height="match_parent" />
```

Step 4. Setup needed parameters and load photos:
```
CollageView collageView = (CollageView) findViewById(R.id.collageView);

  collageView
    .photoMargin(1)
    .photoPadding(3)
    .backgroundColor(Color.RED)
    .photoFrameColor(Color.BLUE)
    .useFirstAsHeader(true) // makes first photo fit device widtdh and use full line
    .defaultPhotosForLine(5) // sets default photos number for line of photos (can be changed by program at runtime)
    .iconSelector(this, getResources().getDimensionPixelSize(R.dimen.icon_size)) (or use 0 as size to wrap content)
    .useCards(true) // adds cardview backgrounds to all photos
    .maxWidth(100) // will resize images if their side is bigger than 100
    .placeHolder(R.drawable.placeholder_photo) //adds placeholder resource
    .headerForm(CollageView.ImageForm.IMAGE_FORM_SQUARE) // sets form of image for header (if useFirstAsHeader == true)
    .photosForm(CollageView.ImageForm.IMAGE_FORM_HALF_HEIGHT) //sets form of image for other photos
    .loadPhotos(urls); // here you can use Array/List of photo urls or array of resource ids
```

Step 5. Setup On Photo Click Listener (if needed)
```
collageView.setOnPhotoClickListener(new CollageView.OnPhotoClickListener() {
  @Override
  public void onPhotoClick(int position) {
     // do random stuff here
   }
});
```

Step 6. Setup icons for your images (if needed, to be used with .iconSelector(IconSelector))
```
collageView.iconSelector(new CollageView.IconSelector() {
                    @Override
                    public int getIconResId(int pos) {
                        if (pos == 0 || pos  == 4) {
                            return R.mipmap.ic_launcher;
                        }
                        return 0;
                    }
                }
, getResources().getDimensionPixelSize(R.dimen.icon_size));
```

## Contrubution

Contributions are always welcome

## Licence

Copyright 2016 lopei

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

## Screenshots
<img src="https://pp.vk.me/c626823/v626823371/2947c/ZO5N0vDiaT4.jpg" width="400"/>


<img src="https://pp.vk.me/c626823/v626823371/29407/fyIqt_C1uWQ.jpg" width="400"/>


<img src="https://pp.vk.me/c636817/v636817371/4548f/UQ9aVtDpgtU.jpg" width="400"/>


<img src="https://pp.vk.me/c636817/v636817371/45499/g-4JTsHli5A.jpg" width="400"/>


<img src="https://pp.vk.me/c636818/v636818371/40af0/_sxbhRCQifE.jpg" width="400"/>


<img src="https://pp.vk.me/c636818/v636818371/40afa/QcjVfRKo0_A.jpg" width="400"/>
