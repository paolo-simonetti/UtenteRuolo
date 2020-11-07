package it.gestionearticoli.model;

import it.gestionearticoli.service.MyServiceFactory;

public class Articolo implements Comparable<Articolo>{

	private Long id;
	private String codice;
	private String descrizione;
	private Integer prezzo;
	private Long categoriaFK;
	
	public Long getCategoriaFK() {
		return categoriaFK;
	}

	public void setCategoriaFK(Long categoriaFK) {
		if (this.categoriaFK!=null) {
			try {
				Categoria categoriaMadre=MyServiceFactory.getCategoriaServiceInstance().trovaTramiteId(this.categoriaFK); 
				if (categoriaMadre!=null) {
					categoriaMadre.eliminaArticoloDaElenco(this); // se l'articolo è già stato catalogato, lo rimuovo dalla categoria attuale		
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		boolean categoriaFKesiste=false;
		Categoria categoriaSuccessiva=null;
		try {
			categoriaSuccessiva=MyServiceFactory.getCategoriaServiceInstance().trovaTramiteId(categoriaFK);
			categoriaFKesiste=(categoriaSuccessiva!=null);
		} catch(Exception e) {
			e.printStackTrace();
		}
		if (categoriaFKesiste) {
			this.categoriaFK = categoriaFK; 			
			categoriaSuccessiva.incrementaElencoArticoli(this); // inserisco l'articolo nella nuova categoria			
		} else {
			System.out.println("Non esiste alcuna categoria con quell'id; è stata lasciata la categorizzazione attuale");
		}
	}

	public Articolo() {}
	
	public Articolo(String codice, String descrizione, Integer prezzo, Long categoriaFK) {
		this.codice = codice;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		Categoria categoriaDiAppartenenza=null;
		try {
			categoriaDiAppartenenza=MyServiceFactory.getCategoriaServiceInstance().trovaTramiteId(categoriaFK);			
		} catch(Exception e) {
			e.printStackTrace();
		}
		if (categoriaDiAppartenenza==null) {
			System.err.println("La FK di Categoria impostata nel costruttore non ha corrispondenze tra gli id della tabella Categoria!");
			this.categoriaFK=null;
		} else {
			this.categoriaFK=categoriaFK;
			categoriaDiAppartenenza.incrementaElencoArticoli(this);
		}
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
