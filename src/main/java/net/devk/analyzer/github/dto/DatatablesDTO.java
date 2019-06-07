package net.devk.analyzer.github.dto;

import java.util.Collection;

public class DatatablesDTO<T> {

	private final Collection<T> data;
	private final int recordsTotal;
	private final int recordsFiltered;
	private final int draw;

	public DatatablesDTO(Collection<T> data, int recordsTotal, int recordsFiltered, int draw) {
		super();
		this.data = data;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.draw = draw;
	}

	public Collection<T> getData() {
		return data;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public int getDraw() {
		return draw;
	}

}