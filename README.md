# collageview
[![](https://jitpack.io/v/lopei/collageview.svg)](https://jitpack.io/#lopei/collageview)

Sample here:
[Sample App](https://github.com/lopei/collageview/blob/master/app/src/main/java/com/lopei/myapplication/MainActivity.java)

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
  compile 'com.github.lopei:collageview:0.0.6'
}
```

Maven:

```
<dependency>
  <groupId>com.github.lopei</groupId>
  <artifactId>collageview</artifactId>
  <version>0.0.4</version>
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
    .useSquarePhotos(true) // makes all photos square
    .useCards(true) // adds cardview backgrounds to all photos
    .placeHolder(R.drawable.placeholder_photo) //adds placeholder resource
    .loadPhotos(urls); // here you can use Array/List of photo urls or array of resource ids
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

<img src="https://pp.vk.me/c626823/v626823371/29429/C73w7R3oZrA.jpg" width="400"/>
