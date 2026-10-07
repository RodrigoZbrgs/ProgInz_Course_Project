package eu.virac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translation;

import eu.virac.service.ITranslationService;

@Service
public class TranslationServiceImpl implements ITranslationService {

    @Autowired
    private Translate translate;

    @Override
    public String translateText(String text, String targetLang) throws Exception {
        if (text != null && !text.isEmpty()) {
            try {
                Translation translation = translate.translate(
                    text, 
                    Translate.TranslateOption.targetLanguage(targetLang)
                );
                return translation.getTranslatedText();
            } catch (Exception e) {
            	
                return text;
            }
        }
        return text;
    }
}