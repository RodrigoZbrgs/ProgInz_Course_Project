package eu.virac.service;

public interface ITranslationService {
	
	public abstract String translateText(String text, String targetLang) throws Exception;
}
