package com.produtos.apirest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.produtos.apirest.model.Cliente;
import com.produtos.apirest.repository.ClienteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URI;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ClienteControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @MockBean
    private ClienteRepository repository;

    @Autowired
    private ClienteController controller;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void DeveSalvarUmNovoCliente() throws Exception{
        Cliente cliente = criaCliente();

        URI uri = new URI("/cliente");

        Mockito.when(repository.save(null)).thenReturn(cliente);

        MockHttpServletRequestBuilder requestBuilder = post(uri)
                .content(objectMapper.writeValueAsString(cliente))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().is(201));
    }

    @Test
    public void deveBuscarUmCliente() throws Exception {
        Cliente cliente = criaCliente();

        URI uri = new URI("/cliente");

        Mockito.when(repository.findById(1)).thenReturn(cliente);

        MockHttpServletRequestBuilder requestBuilder = get(uri)
                .content(objectMapper.writeValueAsString(cliente))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().is(200));
    }

    @Test
    public void deveAtualizarUmCliente() throws Exception{
        Cliente cliente = criaCliente();


        Mockito.when(repository.findById(1)).thenReturn(cliente);
        Mockito.when(repository.save(any(Cliente.class))).thenReturn(cliente);

        MockHttpServletRequestBuilder requestBuilder = put("/cliente/{id}",1)
                .content(objectMapper.writeValueAsString(cliente))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().is(201));
    }

    @Test
    public void deveDeletarUmCliente() throws Exception{
        Cliente cliente = criaCliente();

        Mockito.when(repository.findById(1)).thenReturn(cliente);

        MockHttpServletRequestBuilder requestBuilder = delete("/cliente/{id}",1)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().is(200));
    }

    private Cliente criaCliente(){
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("André Yuri");
        cliente.setEmail("andre@empresa.com");
        cliente.setNaturalidade("Gama-DF");
        cliente.setNacionalidade("Brasileira");
        cliente.setCpf("05244233181");
        cliente.setSexo("Masculinho");
        return cliente;
    }
}
