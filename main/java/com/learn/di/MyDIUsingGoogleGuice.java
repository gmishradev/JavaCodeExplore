package com.learn.di;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.learn.di.binding.TextEditorModule;

public class MyDIUsingGoogleGuice {
    public static void main(String[] args) {
            Injector injector = Guice.createInjector(new TextEditorModule());
            TextEditor editor = injector.getInstance(TextEditor.class);
            editor.makeSpellCheck();
        }
}
