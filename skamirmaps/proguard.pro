-dontwarn java.lang.invoke.StringConcatFactory

-keep public interface sk.amir.maps.ImageMapping {
    public *;
}
-keep public class sk.amir.maps.MapCommandHandleKt {
    public *;
}

-keep public class sk.amir.maps.MapStateKt {
    public *;
    public static *;
    public rememberMapState(...);
}

-keepclassmembers class * implements android.os.Parcelable$Creator {
    public *;
}

-keep public class sk.amir.maps.common.LatLngBounds_androidKt {
    public *;
}

-keep public interface sk.amir.maps.MapStyle {
    public *;
}

-keep public class sk.amir.maps.ComposeMapKt {
    public *** ComposeMap(...);
}

-keep public class sk.amir.maps.MapUiSettings {
    public *;
}

-keep public interface sk.amir.maps.MapState {
    public *;
}

-keep public class sk.amir.maps.common.LatLng$Companion {

}

-keep public interface sk.amir.maps.common.LatLng {
    public *;
    public static *;
}

-keep public class sk.amir.maps.common.LatLngImpl {
    public *;
    public static *;
}

-keep public class sk.amir.maps.common.LatLng_androidKt {
    public *;
}

-keep public interface sk.amir.maps.MapCommandHandle {
    public *;
}

-keep public class sk.amir.maps.MapCommandHandleKt {
    public *;
}

-keep public interface sk.amir.maps.common.LatLngBounds {
    public *;
    public static *;
}

-keep public class sk.amir.maps.common.CameraPadding {
    public *;
}
-keep public class sk.amir.maps.common.CameraPosition {
    public *;
}
-keep public class sk.amir.maps.common.LatLngBoundsKt {
    public *;
    public static *;
}
-keep public class sk.amir.maps.compose.simple.CircleKt {
    public *;
    public static *;
}
-keep public class sk.amir.maps.compose.symbol.SymbolKt {
    public *;
    public static *;
}
-keep public class sk.amir.maps.compose.fill.FillKt {
    public *;
    public static *;
}
-keep public class sk.amir.maps.compose.line.LineKt {
    public *;
    public static *;
}
-keep public class sk.amir.maps.compose.Anchor {
    public *;
}

-keepparameternames
-renamesourcefileattribute SourceFile
-keepattributes Signature,Exceptions,
                InnerClasses,PermittedSubclasses,EnclosingMethod,
                Deprecated,SourceFile,LineNumberTable,RuntimeVisibleAnnotations,RuntimeVisibleTypeAnnotations

-keep @interface androidx.compose.runtime.Composable

-keepclasseswithmembernames,includedescriptorclasses class * {
    native <methods>;
}

-printmapping proguard.map
-keepclassmembers,allowoptimization enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}