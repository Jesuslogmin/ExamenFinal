package com.barturen.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

import com.barturen.converter.InfraccionConverter;
import com.barturen.dto.InfraccionDto;
import com.barturen.entity.Infraccion;
import com.barturen.service.InfraccionService;
import com.barturen.service.PdfService;
import com.barturen.util.WrapperResponse;

@RestController
@RequestMapping("/v1/infracciones")
public class InfraccionController {

    @Autowired
    private InfraccionService service;

    @Autowired
    private InfraccionConverter converter;

    @Autowired
    private PdfService pdfService;

    @GetMapping
    public ResponseEntity<List<InfraccionDto>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<InfraccionDto> infracciones = converter.fromEntity(service.findAll(page));

        return new WrapperResponse(true, "success", infracciones).createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InfraccionDto> create(@RequestBody InfraccionDto infraccion) {
        Infraccion infraccionEntity = converter.fromDTO(infraccion);
        InfraccionDto registro = converter.fromEntity(service.save(infraccionEntity));
        return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InfraccionDto> update(@PathVariable("id") int id, @RequestBody InfraccionDto infraccion) {
        Infraccion infraccionEntity = converter.fromDTO(infraccion);
        infraccionEntity.setId(id); // Aseguramos que se actualiza el registro correspondiente
        InfraccionDto registro = converter.fromEntity(service.save(infraccionEntity));
        return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        service.delete(id);
        return new WrapperResponse(true, "success", null).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InfraccionDto> findById(@PathVariable("id") int id) {
        InfraccionDto registro = converter.fromEntity(service.findById(id));
        return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<InfraccionDto>> searchByDniOrPlaca(
            @RequestParam(value = "dni", required = false) String dni,
            @RequestParam(value = "placa", required = false) String placa,
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<InfraccionDto> infracciones;

        if (dni != null) {
            infracciones = converter.fromEntity(service.findByDni(dni, page));
        } else if (placa != null) {
            infracciones = converter.fromEntity(service.findByPlaca(placa, page));
        } else {
            infracciones = converter.fromEntity(service.findAll(page));
        }

        return new WrapperResponse(true, "success", infracciones).createResponse(HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<byte[]> generateReport() {
        List<InfraccionDto> infracciones = converter.fromEntity(service.findAll());

        LocalDateTime fecha = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHora = fecha.format(formato);

        Context context = new Context();
        context.setVariable("registros", infracciones);
        context.setVariable("fecha", fechaHora);

        byte[] pdfBytes = pdfService.createPdf("infraccionReport", context);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "reporte_infracciones.pdf");

        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }
}
