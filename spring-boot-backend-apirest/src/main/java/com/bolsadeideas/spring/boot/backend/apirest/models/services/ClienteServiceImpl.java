package com.bolsadeideas.spring.boot.backend.apirest.models.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.spring.boot.backend.apirest.models.dao.IClienteDao;
import com.bolsadeideas.spring.boot.backend.apirest.models.dao.IFacturaDao;
import com.bolsadeideas.spring.boot.backend.apirest.models.entity.Cliente;
import com.bolsadeideas.spring.boot.backend.apirest.models.entity.Factura;
import com.bolsadeideas.spring.boot.backend.apirest.models.entity.Producto;
import com.bolsadeideas.spring.boot.backend.apirest.models.entity.Region;

@Service
public class ClienteServiceImpl implements IClienteService {

	private final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private IFacturaDao facturaDao;


	@Autowired
	private IProductoDao productoDao;
	
	@Override
	@Transactional(readOnly = true) // si se desea, se peude omitir esta linea, por que CrudRepository ya contiene // transactional
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	public Page<Cliente> findAll(Pageable pageable) {

		return clienteDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Region> findAllRegiones() {
		log.info("REGIONES: " + clienteDao.findAllRegiones());
		return clienteDao.findAllRegiones();
	}

	@Override
	public Factura findFacturaById(Long id) {
		// TODO Auto-generated method stub
		return facturaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Factura saveFactura(Factura factura) {
		// TODO Auto-generated method stub
		return facturaDao.save(factura);
	}

	@Override
	@Transactional
	public void deleteFacturaById(Long id) {
		// TODO Auto-generated method stub
		facturaDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findProductoByNombre(String term) {
		return productoDao.findByNombre(term);
	}

}
