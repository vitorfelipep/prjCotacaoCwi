package com.algaworks.wine.storage;

import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Profile("storage-local")
@Component
public class FotoStorageLocal implements FotoStorage, FotoReader{
	
	private Path local;
	
	public FotoStorageLocal() {
		this.local = getDefault().getPath(System.getenv("HOME"), ".winefotos");
		
		try {
			
		}catch (Exception e) {
			throw new RuntimeException("Erro criando uma pasta para salvar foto ", e);
		}
	}
	
	@Override
	public String salvar(MultipartFile foto) {
		String nomeFoto = foto.getOriginalFilename();
		try {
			foto.transferTo(new File(this.local.toAbsolutePath().toString() + getDefault().getSeparator() + nomeFoto));
		}catch (Exception e) {
			throw new RuntimeException("Erro salvando a foto ", e);
		}
		return nomeFoto;
	}

	@Override
	public String getUrl(String nomeFoto) {
		return "http://localhost:8080/fotos/"+ nomeFoto;
	}

	@Override
	public byte[] recuperar(String nomeFoto) {
		try {
			return Files.readAllBytes(this.local.resolve(nomeFoto));
		}catch (Exception e) {
			throw new RuntimeException("Erro ao recuperar a foto " + e); 
		}
	}

}
