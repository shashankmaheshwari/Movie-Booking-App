package com.moviebookingapp.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
@Builder
public class MovieCompositeKey implements Serializable {
 
	 
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Movie Name can't be empty")
	private String  movieName;
	
	@NotBlank(message = "Theatre Name can't be empty") 
	 private String theatreName;
}
