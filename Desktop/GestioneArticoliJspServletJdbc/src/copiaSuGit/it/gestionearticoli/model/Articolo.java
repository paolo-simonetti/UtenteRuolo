package it.gestionearticoli.model;

public class Articolo implements Comparable<Articolo>{

	private Long id;
	private String codice;
	private String descrizione;
	private Integer prezzo;
	private Integer categoriaFK;
	
	public Integer getCategoriaFK() {
		return categoriaFK;
	}

	public void setCategoriaFK(Integer categoriaFK) {
		if (this.categoriaFK!=null) {
			Categoria categoriaMadre=MyServiceFactory.getCategoriaServiceInstance().trovaTramiteId(categoriaFK); /* TODO: crea una classe 
			CategoriaService e definisci un metodo trovaTramiteId in CategoriaServiceImpl*/
			categoriaMadre.eliminaArticoloDaElenco(this); // se l'articolo è già stato catalogato, lo rimuovo dalla categoria attuale
		}
		this.categoriaFK = categoriaFK; 
		Categoria categoriaMadre=MyServiceFactory.getCategoriaServiceInstance().trovaTramiteId(categoriaFK); 
		categoriaMadre.incrementaElencoArticoli(this); // inserisco l'articolo nella nuova categoria
	}

	public Articolo() {}
	
	public Articolo(String codice, String descrizione, Integer prezzo, Integer categoriaFK) {
		this.codice = codice;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.categoriaFK=categoriaFK;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	@Override
	public int compareTo(Articolo articolo) {
		return this.codice.compareTo(articolo.getCodice());
	}

}
