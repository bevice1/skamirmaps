# SKAMIR Maps - Compose Multiplatform wrapper to work with maps
**Library SKAMIR Maps** builds on top of MapLibre Native libraries. One for iOS and one for Android. 
Using kotlin interop it was possible to link toward the native libraries without modifying their source code.  
The use of external dependencies is being kept at minimum.

# Requirements
- Apple computer to run this the sample app on your Apple device.
- Knowledge of [Compose](https://developer.android.com/compose) is necessary
- It helps if you have basic understanding of how MapLibre works and that you have to use a tile server to show tiles on the map.

# Features
Many features have been developed. For some of them I have created an article on my blog.
- **Features**:
    - Circle
    - Symbol(icon + text)
    - Line
    - Fill
- style switching
- Camera control
- Controlling map settings
- Features are ordered based on order of composable calls. No z-indices are necessary anymore!

# License
This repository is licensed under MPLv2. Read [LICENSE](https://github.com/skamirmaps/skamirmaps/blob/main/LICENSE.txt) file for more info.
