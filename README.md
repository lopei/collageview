# collageview

<a href='https://play.google.com/store/apps/details?id=com.lopei.collageviewdemo&pcampaignid=MKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' align="left" width="400" /></a>

[![](https://jitpack.io/v/lopei/collageview.svg)](https://jitpack.io/#lopei/collageview)

CollageView is a library for creating simple photo collages in your applications. Would be great, for example, in profile page or feed page.


## What's new in version 0.0.8?
- Removed parameter useSquarePhotos, now all photos are square
- Added ability to use first photo as header (fit parent width and use full line - useFirstAsHeader(true)
- Added ability to set default photos number for a line (can be changed by a program at runtime, if there are extra photos)

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
  compile 'com.github.lopei:collageview:0.0.8'
}
```

Maven:

```
<dependency>
  <groupId>com.github.lopei</groupId>
  <artifactId>collageview</artifactId>
  <version>0.0.8</version>
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
    .useCards(true) // adds cardview backgrounds to all photos
    .placeHolder(R.drawable.placeholder_photo) //adds placeholder resource
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


<img src="https://pp.vk.me/c626823/v626823371/29429/C73w7R3oZrA.jpg" width="400"/>
