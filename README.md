# VectorIntegerView

[![](https://jitpack.io/v/qwert2603/VectorIntegerView.svg)](https://jitpack.io/#qwert2603/VectorIntegerView)

`VectorIntegerView` (viv) is custom view to display integers on Android via great animations `minSdkVersion 21`.

Paths for vector drawables drawables are taken [here](https://github.com/alexjlockwood/adp-delightful-details).

## [Showcase video](https://www.youtube.com/watch?v=tTQlPH3ROMw)

## [Demo APK](https://github.com/qwert2603/VectorIntegerView/blob/master/app/demo.apk)

## Article on [Habr.com](https://habr.com/post/420919/)

![art](https://github.com/qwert2603/VectorIntegerView/blob/master/art/device-2018-08-22-124411.gif)

## Customizing

### `VectorIntegerView` has following XML-attributes:

* ***viv_vector_integer*** initial integer to show (0 by default).
* ***viv_digit_color*** color of integer (black by default).

### Other properties may be configured by overriding resourses from library:
#### (those will be applied to all `VectorIntegerView` in app, for example see [demo-app](https://github.com/qwert2603/VectorIntegerView/tree/master/app))

* ***@integer/viv_animation_duration*** defines duration of animation (400ms by default).
* ***@dimen/viv_digit_size*** defines size of one digit (24dp by default).
* ***@dimen/viv_digit_translateX*** applied to all avd-digits, to center them horizontally.
* ***@dimen/viv_digit_translateY*** applied to all avd-digits, to center them vertically.
* ***@dimen/viv_digit_strokewidth*** applied to all avd-digits.
* ***@dimen/viv_digit_margin_horizontal*** applied to all digit-views (-3dp by default). This is needed to make horizontal spaces between digits smaller, because avd-digits are square.

## In code
### XML
```xml
<com.qwert2603.vector_integer_view.VectorIntegerView
    android:id="@+id/vectorIntegerView"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="16dp"
    app:viv_digit_color="#ff8000"
    app:viv_vector_integer="14" />
```

### Java / Kotlin
Digit can be set also via code as `BigInteger`:
```java
final VectorIntegerView vectorIntegerView = findViewById(R.id.vectorIntegerView);
vectorIntegerView.setInteger(
        vectorIntegerView.getInteger().add(BigInteger.ONE),
        /* animated = */ true
);
```
Also there is overloaded function that allows set integer as ```long```:
```java
vectorIntegerView.setInteger(1918L, false);
```
For example see [demo-app](https://github.com/qwert2603/VectorIntegerView/tree/master/app).

## Under the hood

[`VectorIntegerView`](https://github.com/qwert2603/VectorIntegerView/blob/master/library/src/main/java/com/qwert2603/vector_integer_view/VectorIntegerView.java) is implemented by `RecyclerView` and each digit is `ImageView` with `<animated-selector>` drawable.

Animation are controlled by custom `RecyclerView`'s item animator: [`DigitItemAnimator`](https://github.com/qwert2603/VectorIntegerView/blob/master/library/src/main/java/com/qwert2603/vector_integer_view/DigitItemAnimator.java).

When animated each digit stays on it's own position. This is done via `DiffUtil` in [`DigitAdapter`](https://github.com/qwert2603/VectorIntegerView/blob/master/library/src/main/java/com/qwert2603/vector_integer_view/DigitAdapter.java).

## Download

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

```
dependencies {
        implementation 'com.github.qwert2603:VectorIntegerView:x.y.z'
}
```
