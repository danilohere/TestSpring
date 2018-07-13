package com.test.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Localizacao {

	private String cidade;
	
	@Column(name = "temp_min")
	private String tempMin;
	
	@Column(name = "temp_max")
	private String tempMax;
	
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getTempMin() {
		return tempMin;
	}
	public void setTempMin(String tempMin) {
		this.tempMin = tempMin;
	}
	public String getTempMax() {
		return tempMax;
	}
	public void setTempMax(String tempMax) {
		this.tempMax = tempMax;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((tempMax == null) ? 0 : tempMax.hashCode());
		result = prime * result + ((tempMin == null) ? 0 : tempMin.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Localizacao other = (Localizacao) obj;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (tempMax == null) {
			if (other.tempMax != null)
				return false;
		} else if (!tempMax.equals(other.tempMax))
			return false;
		if (tempMin == null) {
			if (other.tempMin != null)
				return false;
		} else if (!tempMin.equals(other.tempMin))
			return false;
		return true;
	}
	
	
}
