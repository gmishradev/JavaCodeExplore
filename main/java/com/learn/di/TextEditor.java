package com.learn.di;

import com.google.inject.Inject;
import com.learn.di.service.SpellChecker;

class TextEditor {
   private SpellChecker spellChecker;
   @Inject
   public TextEditor(SpellChecker spellChecker) {
      this.spellChecker = spellChecker;
   }
   public void makeSpellCheck(){
      spellChecker.checkSpelling();
   }
}