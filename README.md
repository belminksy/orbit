Orbit
===

Orbit is an open source program which allows a simplistic representation of the Earth and its satellites.

This project was originally released in March 2018 as the result of the supervised personal work exam (Travaux personnels encadrés) of the Baccalauréat Français (equivalent to high school diploma). I received the grade of 14/20.

/!\ This project is not longer maintained!

![Orbit distant animation](data/animation-distant.gif?raw=true)


Building
---

You'll need the following dependencies:

- Java 8
- meson >= 0.49
- python3

Run `meson build` to configure the build environment. Change to the build directory and run `ninja install` to build.

```bash
meson build
cd build
ninja install
```

To execute, run the following command in the project directory by remplacing _\<your system\>_ with `linux`, `macos` or `windows`:

```bash
java -Djava.library.path=./lib/bin/<your system> -jar ./build/src/Orbit.jar
```


Controls
---

### Movement

| **Key** | **Function** |
|--|--|
| <kbd>W</kbd>, <kbd>Z</kbd> | Go forward |
| <kbd>S</kbf> | Go backward |
| <kbd>A</kbd>, <kbd>Q</kbd>, _LEFT_ | Strafe left |
| <kbd>D</kbd>, _RIGHT_ | Strafe right |
| _UP_ | Go forward on the horizontal plane |
| _DOWN_ | Go backward on the horizontal plane |
| _SPACEBAR_ | Go up |
| _SHIFT_ | Go down |

> Note 1 : Qwerty and Azerty keyboards are supported simultaneously.

> Note 2 : Joysticks are also supported but their keys code are too different to be shown here.


### Scene

| **Key** | **Function** |
|--|--|
| <kbd>M</kbd>, <kbd>+</kbd> | Next scene |
| <kbd>L</kbd>, <kbd>-</kbd> | Previous scene |


### Action

| **Key** | **Function** |
|--|--|
| _NUMPAD 1_ | Reset scene 3 |
| _NUMPAD 2_ | Stop before the collision of the scene 3 |
| _NUMPAD 5_ | Clear current scene |
| _NUMPAD 7_ | Print current position and orientation |
| _NUMPAD 8_ | Active Earth rotation |
| _NUMPAD 9_ | Show the satellite orbit |


### Camera View

| **Key** | **Function** |
|--|--|
| <kbd>1</kbd> | View 1 |
| <kbd>2</kbd> | View 2 |
| <kbd>3</kbd> | View 3 |
| <kbd>4</kbd> | View 4 |
| <kbd>5</kbd> | View 5 |
| <kbd>6</kbd> | View 6 |
| <kbd>7</kbd> | View 7 |
| <kbd>8</kbd> | View 8 |
| <kbd>9</kbd> | View 9 |


### Miscellaneous

| **Key** | **Function** |
|--|--|
| <kbd>P</kbd>, _NUMPAD 0_ | Play / Pause |
| <kbd>ESC</kbd> | Stop grabbing |


Screenshots
===

![Orbit near animation](data/animation-near.gif?raw=true)
> Near animation of the Earth.

![Orbit collision animation](data/animation-collision.gif?raw=true)
> Iridium-33 and Kosmos-2251 collision animation.


Credits
===

**Earth Texture**

> October, Blue Marble Next Generation

> Reto Stöckli, NASA Earth Observatory

> https://visibleearth.nasa.gov/view.php?id=73826

**Moon and Sun Textures**

> https://www.solarsystemscope.com/textures/
