package carros;


import carros.domain.Carro;
import carros.domain.CarroService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static junit.framework.TestCase.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class ApplicationTests {

	@Autowired
	private CarroService service;

	@Test
	public void  test1() {

		Carro carro = new Carro();
		carro.setNome("Ferrari");
		carro.setTipo("esportivos");

		Carro c = service.insert(carro);

		assertNotNull(c);

		Long id = c.getId();
		assertNotNull(id);

		// Buscar o objeto
		Optional<Carro> op = service.getCarrosById(id);
		assertTrue(op.isPresent());

   		c = op.get();
		assertEquals("Ferrari",c.getNome());
		assertEquals("esportivos", c.getTipo());

		service.delete(id);

		assertFalse(service.getCarrosById(id).isPresent());

	}


}
