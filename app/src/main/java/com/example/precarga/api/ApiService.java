package com.example.precarga.api;

import com.example.precarga.data.models.Alumno;
import com.example.precarga.data.models.LoginRequest;
import com.example.precarga.data.models.LoginResponse;
import com.example.precarga.data.models.Mensaje;
import com.example.precarga.data.models.Precarga;
import com.example.precarga.data.models.Reticula;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    /* Dirección donde se encuentra alojado el servicio web */
    String URL = "https://auth-precarga.herokuapp.com/api/";

    /* Crea una sesión de usuario */
    @Headers({"Content-Type: application/json;charset=UTF-8", "X-Requested-With: XMLHttpRequest"})
    @POST("auth/login")
    Call<LoginResponse> login(
            @Body LoginRequest user
    );

    /* Cierra una sesión de usuario */
    @Headers({"Content-Type: application/json;charset=UTF-8", "X-Requested-With: XMLHttpRequest"})
    @GET("auth/logout")
    Call<Mensaje> logout(
            @Header("Authorization") String token
    );

    /* Solicita los datos del alumno */
    @Headers({"Content-Type: application/json;charset=UTF-8", "X-Requested-With: XMLHttpRequest"})
    @GET("auth/user")
    Call<Alumno> obtenerDatosAlumno(
            @Header("Authorization") String token
    );

    /* Guardar la precarga con las materias seleccionadas */
    @Headers({"Content-Type: application/json;charset=UTF-8", "X-Requested-With: XMLHttpRequest"})
    @POST("precarga/guardar")
    Call<Mensaje> guardarPrecarga(
            @Header("Authorization") String token,
            @Body Precarga precarga
    );

    /* Solicita las materias precargadas del alumno */
    @Headers({"Content-Type: application/json;charset=UTF-8", "X-Requested-With: XMLHttpRequest"})
    @GET("precarga/obtenerPrecarga")
    Call<Precarga> obtenerPrecarga(
            @Header("Authorization") String token
    );

    /* Solicita las materias disponibles para precarga (de la reticula) */
    @Headers({"Content-Type: application/json;charset=UTF-8", "X-Requested-With: XMLHttpRequest"})
    @GET("precarga/obtenerMaterias")
    Call<Reticula> obtenerMaterias(
            @Header("Authorization") String token
    );
}
