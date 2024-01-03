module core {
    exports core;
    requires java.desktop;
    requires tess4j;
    opens core to com.google.gson;
}
