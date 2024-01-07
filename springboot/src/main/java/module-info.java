module springboot {
    requires core;
    requires file;

    requires com.google.gson;

    requires spring.web;
    requires spring.beans;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;

    opens springboot to spring.beans, spring.context, spring.web;

}
