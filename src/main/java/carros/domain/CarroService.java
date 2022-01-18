package carros.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired // indica o ponto onde a injeção automática deve começar..
    private CarroRepository rep;

    public Iterable<Carro> getCarros() {
        return rep.findAll();
    }

    // Significar que vou buscar no banco de Dados
    public Optional<Carro> getCarrosById(Long id) {
        return rep.findById(id);
    }

    public Iterable<Carro> getCarrosBytipo(String tipo) {
        return rep.findByTipo(tipo);

    }// Atualizar o carro

    public Carro save(Carro carro) {
        return rep.save(carro);

    }

    public Carro insert(Carro carro) {

        return rep.save(carro);
    }


    // Se existe no banco de dados.
    // Se existe... Delete.
    public void delete(Long id) {
        Optional<Carro> carro = getCarrosById(id);
        if (carro.isPresent()) {
            rep.deleteById(id);
        }
    }
    public Carro update(Carro carro, Long id) {
        Assert.isNull(id, "Não foi possível atulizar o registro");

        // Buscar o carro no banco de dados
        Optional<Carro> optional = getCarrosById(id);
        // Verificando se ele existe
        if (optional.isPresent()) {
            Carro db = optional.get();
            //Copiar as propriedades
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.println("Carro id " + db.getId());

            //Atulizar o carro
            rep.save(db);

            return db;
        } else  {
            throw new RuntimeException( "Não foi possivel atulizar o registro");
        }
    }
}