package com.test.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.entity.Cliente;
import com.test.entity.Localizacao;
import com.test.repository.ClienteRepository;
import com.test.service.ClienteService;

@RestController 
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<?> listar() {
		List<Cliente> clientes = clienteRepository.findAll();
		
		return !clientes.isEmpty() ? ResponseEntity.ok(clientes) : ResponseEntity.notFound().build(); 
	}
	
	@PostMapping("/create")
	public void criar(@RequestBody Cliente c, HttpServletRequest req) throws JSONException {
		LocalizacaoAPI api = new LocalizacaoAPI();
		Localizacao localizacao = new Localizacao();
		String nomeCidade = api.retornarCidade();
		String latlong = api.retornarLat() + "," + api.retornarLon(); 
		localizacao.setCidade(nomeCidade);
		System.out.println(latlong);
		localizacao.setTempMax(api.retornarTempMax(latlong));
		localizacao.setTempMin(api.retornarTempMin(latlong));
		c.setLocalizacao(localizacao);
		clienteRepository.save(c);
	}
	
	@PostMapping("/update/{id}")
	public void alterar(@RequestBody Cliente c, @PathVariable Long id ) throws JSONException {
		clienteService.atualizar(id, c);
	}
	
	@GetMapping("/{id}")
	public Optional<Cliente> buscarPorId(@PathVariable Long id) {
		
		return clienteService.buscarPorId(id);
	}
	
	@GetMapping("/delete/{id}")
	public void remover (@PathVariable Long id) {
		clienteRepository.deleteById(id);
	}
	
}
