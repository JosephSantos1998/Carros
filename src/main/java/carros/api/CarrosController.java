package carros.api;

import carros.domain.Carro;
import carros.domain.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController  // è a combinação das anotaçoes do @Controller @ResponseBody para facilitar o desensolvimento do serviço
@RequestMapping("/api/v1/carros")  //ResquestMapping anotação para Implementar a URL..
public class CarrosController {

    @Autowired // injeção de depedência onde posso iultilizar como métodos, atributos e construtores
    private CarroService service;

    // A id do carro   obs: url do google
    @GetMapping() //é uma anotação dos métodos , Leitura adicional , anotação para mapear solicitação do HTTP
    public Iterable <Carro> get( ){
        return service.getCarros();
    }
    @GetMapping("/{id}")
    public Optional<Carro> get(@PathVariable("id") Long id){
        return service.getCarrosById(id);

    } // Tem que ter  o tipo de método ou seja o tipo de carro que quer que aparecer      obs: url do google
    @GetMapping("tipo/{tipo}")
    public Iterable<Carro> getCarrosByTipo(@PathVariable("tipo") String tipo) {
        return service.getCarrosBytipo(tipo);
    }

    // Quando quer salvar o novo carro        obs:Postman
    @PostMapping  //é uma anotação dos métodos , Leitura adicional , anotação para mapear solicitação do HTTP
    public  String post (@RequestBody Carro carro) {
        Carro  c = service.insert (carro);

        return " Carro salvo com sucesso: " + c.getId();
    }
   @PutMapping("/{id}")
    public String put (@PathVariable("id") Long id, @RequestBody Carro carro){
        Carro c = service.update(carro, id);

       return "Carro atulizado com sucesso: "  + c.getId();
    }

   // Quando quer deletar o carro       obs:Postman
    @DeleteMapping ("/{id}")
     public  String delete (@PathVariable("id") Long id){

        service.delete(id);
        return "Carros deletado com sucesso";
    }

}
