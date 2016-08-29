package com.cwi.cotacao.storage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cwi.cotacao.model.Cotacao;

@Service
public class GetData {

	private String wayFileDataSource;
	private FileInputStream file;
	private BufferedReader lineFile;

	public List<Cotacao> getCsvData(String data) throws IOException {

		// Implementar donwload de arquivo csv por data.
		// Falta fazer a validação dos dias de feriado e finais de semana.
		System.out.println(data);

		String pathLocal = "C:\\csv\\";
		String nameCsvFile = data + ".csv";
		String urlDonwload = "http://www4.bcb.gov.br/Download/fechamento/20160826.csv";

		this.wayFileDataSource = gravaArquivoDeURL(urlDonwload, pathLocal, nameCsvFile);

		try {
			openCsvFile();
			return prepareListCotacao();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void openCsvFile() throws IOException {
		file = new FileInputStream(this.wayFileDataSource);
		lineFile = new BufferedReader(new InputStreamReader(file));
	}

	public List<Cotacao> prepareListCotacao() throws IOException {
		List<Cotacao> lista = getContent();
		return lista;
	}

	public List<Cotacao> getContent() throws IOException {

		String conteudo = "";
		String dados[] = {};

		List<Cotacao> listCotacao = new ArrayList<Cotacao>();

		while (lineFile.ready()) {

			Cotacao c = new Cotacao();

			conteudo = "";
			conteudo = lineFile.readLine() + "\r\n";

			dados = conteudo.split(";");

			c.setDataCotacao(dados[0].toString());
			c.setCodigoMoeda(dados[1]);
			c.setTipo(dados[2]);
			c.setMoeda(dados[3]);
			c.setTaxaCompra(converterToBigdecimal(dados[4]));
			c.setTaxaVenda(converterToBigdecimal(dados[5]));
			c.setParidadeCompra(converterToBigdecimal(dados[6]));
			c.setParidadeVenda(converterToBigdecimal(dados[7]));

			// System.out.println(c);
			listCotacao.add(c);

		}
		return listCotacao;
	}

	/*
	 * O método a baixo não é criação minha e sim uma implementação simples
	 * feita pelo usuário mcbrain do guj no seginte link:
	 * http://www.guj.com.br/t/fazer-download-de-arquivo-com-java/114989/2 Por
	 * ser uma implementação simples utilizando biblioteca nativa do Java.
	 */
	public static String gravaArquivoDeURL(String stringUrl, String pathLocal, String nameCsvFile) {
		try {
			// Encapsula a URL num objeto java.net.URL
			URL url = new URL(stringUrl);
			// Cria streams de leitura (este metodo ja faz a conexao)...
			InputStream is = url.openStream();
			// ... e de escrita.
			FileOutputStream fos = new FileOutputStream(pathLocal + nameCsvFile);
			// Le e grava byte a byte. Voce pode (e deve) usar buffers para
			// melhor performance (BufferedReader).
			int umByte = 0;
			while ((umByte = is.read()) != -1) {
				fos.write(umByte);
			}
			is.close();
			fos.close();

			return pathLocal + nameCsvFile;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private BigDecimal converterToBigdecimal(String number) {
		Double taxaCompra = Double.parseDouble((number).replaceAll(",", "."));
		BigDecimal bg = BigDecimal.valueOf(taxaCompra);
		return bg;
	}
}
