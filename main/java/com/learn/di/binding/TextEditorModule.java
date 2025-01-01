package com.learn.di.binding;

import com.google.inject.AbstractModule;
import com.learn.di.service.SpellChecker;
import com.learn.di.service.SpellCheckerImpl;


// create object and bind the
//Binding Module
public class TextEditorModule extends AbstractModule {
   @Override
   protected void configure() {
      bind(SpellChecker.class).to(SpellCheckerImpl.class);
   } 
}