package model;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Jogador {
	
private int id ;
private String nome ;	
private LocalDate dataNasc ;	
private float altura ;	
private float  peso;	
private Time time;	
	
	
	


	
	
}
