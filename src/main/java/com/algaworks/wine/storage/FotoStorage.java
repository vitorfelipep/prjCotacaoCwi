package com.algaworks.wine.storage;

/**
 * @author vitor
 */
import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {
	
	public String salvar(MultipartFile foto);
	
	public String getUrl(String nomeFoto);
}
