package com.cwi.cotacao.storage;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataCotacaoMoeda {
	
	@Autowired
	private GetData getData;
	 

	public void getCotacaoMoeda() {
		try {
			getData.getCsvData("C:\\csv\\20160823.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public GetData getGetData() {
		return getData;
	}

}
