/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.restaurante.advice;

import com.proyecto.restaurante.dto.ErrorDto;
import com.proyecto.restaurante.exception.CantidadNovalidaException;
import com.proyecto.restaurante.exception.IdNoEncontradoException;
import com.proyecto.restaurante.exception.NombreNoValidoException;
import com.proyecto.restaurante.exception.PedidoNuloException;
import com.proyecto.restaurante.exception.PrecioNoValidoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = CantidadNovalidaException.class)
    public ResponseEntity<ErrorDto> CantidadNovalidaExceptionHandler(CantidadNovalidaException ex) {
        ErrorDto error = ErrorDto.builder().code("p-400").message(ex.getMessage()).Exception("CantidadNovalidaException").build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDto> DataIntegrityViolationExceptionHandler(DataIntegrityViolationException ex) {
        ErrorDto error = ErrorDto.builder().code("p-450")
                .message("Un parametro de los ingresados no existe en la base de datos [favor verificar los Id ingresados]")
                .Exception("DataIntegrityViolationException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = IdNoEncontradoException.class)
    public ResponseEntity<ErrorDto> IdNoEncontradoExceptionHandler(IdNoEncontradoException ex) {
        ErrorDto error = ErrorDto.builder().code("p-410").message(ex.getMessage())
                .Exception("IdNoEncontradoException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PrecioNoValidoException.class)
    public ResponseEntity<ErrorDto> PrecioNoValidoExceptionHandler(PrecioNoValidoException ex) {
        ErrorDto error = ErrorDto.builder().code("p-430").message(ex.getMessage())
                .Exception("PrecioNoValidoException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = NombreNoValidoException.class)
    public ResponseEntity<ErrorDto> PrecioNoValidoExceptionHandler(NombreNoValidoException ex) {
        ErrorDto error = ErrorDto.builder().code("p-415").message(ex.getMessage())
                .Exception("NombreNoValidoException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity<ErrorDto> NoResourceFoundExceptionHandler(NoResourceFoundException ex) {
        ErrorDto error = ErrorDto.builder().code("p-455").message("La ruta a la que haces referencia no existe")
                .Exception("NoResourceFoundException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorDto> HttpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex) {
        ErrorDto error = ErrorDto.builder().code("p-457").message("El metodo no es compatible con la ruta")
                .Exception("NoResourceFoundException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = PedidoNuloException.class)
    public ResponseEntity<ErrorDto> PedidoNuloExceptionHandler(PedidoNuloException ex) {
        ErrorDto error = ErrorDto.builder().code("p-457").message(ex.getMessage())
                .Exception("NoResourceFoundException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorDto> MissingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        ErrorDto error = ErrorDto.builder().code("p-460").message("El parámetro de solicitud requerido 'fecha' "
                + "no está presente, verifica que la ruta tenga el siguiente formato 'dia?fecha='")
                .Exception("MissingServletRequestParameterException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
  
}

