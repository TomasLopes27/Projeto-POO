package aux_functions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entities.Data_Base;
import entities.Transportadoras;
import entities.Utilizador;

public class DataBaseAux {
	public static boolean verify(Data_Base d, Utilizador x) {
		boolean r;
	Data_Base aux = new Data_Base();
		if(d.getUsers().stream().filter(y -> y.getMail().equals(x.getMail())).collect(Collectors.toList()).isEmpty() ||
				d.getUsers().stream().filter(y -> y.getNif() == x.getNif()).collect(Collectors.toList()).isEmpty() ||
				d.getUsers().stream().filter(y -> y.getCode() == x.getCode()).collect(Collectors.toList()).isEmpty()) r=false;
		
		else r=true;
		
		return r;
	}
	
	
    public static List<Transportadoras> removerDuplicados(List<Transportadoras> transportadoras) {
        List<Transportadoras> transportadorasUnicas = new ArrayList<>();

        for (Transportadoras transportadora : transportadoras) {
            if (!transportadorasUnicas.contains(transportadora)) {
                transportadorasUnicas.add(transportadora);
            }
        }

        return transportadorasUnicas;
    }
}
