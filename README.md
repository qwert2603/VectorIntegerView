# VectorIntegerView

[![](https://jitpack.io/v/qwert2603/VectorIntegerView.svg)](https://jitpack.io/#qwert2603/VectorIntegerView)

`VectorIntegerView` (viv) is custom view to display integers on Android via great animations (*minSdkVersion **21***).

Paths for vector drawables drawables are taken [here](https://github.com/alexjlockwood/adp-delightful-details).

## [showcase video](https://www.youtube.com/watch?v=fn8yI9tRgjY)

![art](https://github.com/qwert2603/VectorIntegerView/blob/master/art/device-2018-08-22-111118. png)
![art](https://github.com/qwert2603/VectorIntegerView/blob/master/art/device-2018-08-22-111149. png)

## Customizing

### `VectorIntegerView` has following XML-attributes:

* ***viv_vector_integer*** initial integer to show.
* ***viv_digit_color*** color of integer.

### Other properties may be configured by overriding resourses from library:
#### (those will be applied to all `VectorIntegerView` in app, for example see [demo-app](https://github.com/qwert2603/VectorIntegerView/tree/master/app))

* ***@dimen/viv_digit_size*** defines size of one digit.
* ***@integer/viv_animation_duration*** defines duration of animation.

## In code

Digit can be set also via code:
```
final VectorIntegerView vectorIntegerView = findViewById(R.id.vectorIntegerView);
vectorIntegerView.setInteger(
        vectorIntegerView.getInteger().add(BigInteger.ONE),
        /* animated = */ true
);
```
Also there is overloaded function that allows set integer as ```long```:
```
vectorIntegerView.setInteger(1918, false);
```

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

## ToDo

* dots between digits (ex. "19.182.614")
* try to set size/duration via xml-attribute
