package com.example.avancetf.controller;
import com.example.avancetf.Entities.Tecnico;
import com.example.avancetf.dtos.CountSolicitudServiciosDTO;
import com.example.avancetf.dtos.TecnicoDTO;
import com.example.avancetf.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;

    @PostMapping("/tecnico")
    public TecnicoDTO insertarTecnico(@RequestBody TecnicoDTO TecnicoDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Tecnico tecnico = modelMapper.map(TecnicoDTO, Tecnico.class);
        tecnico = tecnicoService.insertarTecnico(tecnico);
        return modelMapper.map(tecnico, TecnicoDTO.class);
    }

    @GetMapping("/tecnicos")
    public List<TecnicoDTO> listarTecnicos() {
        List<Tecnico> lista = tecnicoService.listarTecnicos();
        ModelMapper modelMapper = new ModelMapper();
        List<TecnicoDTO> listaDTO = modelMapper.map(lista, List.class);
        return listaDTO;
    }

    @PutMapping("/tecnico")
    public TecnicoDTO modificarTecnico(@RequestBody TecnicoDTO TecnicoDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Tecnico tecnico = modelMapper.map(TecnicoDTO, Tecnico.class);
        tecnico = tecnicoService.modificarTecnico(tecnico);
        return modelMapper.map(tecnico, TecnicoDTO.class);
    }
    @DeleteMapping("/tecnico")
    public void eliminarTecnico(@RequestBody TecnicoDTO TecnicoDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Tecnico tecnico = modelMapper.map(TecnicoDTO, Tecnico.class);
        tecnicoService.eliminarTecnico(tecnico.getId());
    }
    @GetMapping("/tecnico/listarSolicitudDesc")
    public List<CountSolicitudServiciosDTO>listarSolicitudServicios(){
        return tecnicoService.listarSolicitudServicios();
    }
    @GetMapping("/tecnicos/{id}")
    public ResponseEntity<TecnicoDTO> buscaTecnico(@PathVariable Long id) {
        ModelMapper modelMapper = new ModelMapper();
        Tecnico tecnico = tecnicoService.buscarPorId(id);
        TecnicoDTO tecnicoDTO = modelMapper.map(tecnico, TecnicoDTO.class);
        return ResponseEntity.ok(tecnicoDTO);
    }
}