package com.learn.di.service;

import com.learn.di.service.SpellChecker;

public class SpellCheckerImpl implements SpellChecker {
   @Override
   public void checkSpelling() {
      System.out.println("Inside checkSpelling." );
   } 
}