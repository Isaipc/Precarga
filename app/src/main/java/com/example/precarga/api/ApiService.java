package com.example.precarga.api;

import com.example.precarga.data.models.LoginRequest;
import com.example.precarga.data.models.LoginResponse;
import com.example.precarga.data.models.Materia;
import com.example.precarga.data.models.MensajeResponse;
import com.example.precarga.data.models.ReticulaResponse;
import com.example.precarga.data.models.UsuarioResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    String URL = "https://auth-precarga.herokuapp.com/api/";

    @Headers({"Content-Type: application/json;charset=UTF-8", "X-Requested-With: XMLHttpRequest"})
//    @FormUrlEncoded
    @POST("auth/login")
    Call<LoginResponse> login(
            @Body LoginRequest user
    );

    @Headers({"Content-Type: application/json;charset=UTF-8", "X-Requested-With: XMLHttpRequest"})
    @GET("auth/user")
    Call<UsuarioResponse> user(
            @Header("Authorization") String auth
    );

    @Headers({"Content-Type: application/json;charset=UTF-8", "X-Requested-With: XMLHttpRequest"})
    @POST("precarga/guardar")
    Call<MensajeResponse> guardarPrecarga(
            @Header("Authorization") String auth,
            @Field("materias") List<Materia> materias
    );

    @Headers({"Content-Type: application/json;charset=UTF-8", "X-Requested-With: XMLHttpRequest"})
    @FormUrlEncoded
    @GET("precarga/solicitar")
    Call<ReticulaResponse> solicitarPrecarga(
            @Header("Authorization") String auth
    );

    @Headers({"Content-Type: application/json;charset=UTF-8", "X-Requested-With: XMLHttpRequest"})
    @GET("obtenerMaterias")
    Call<ReticulaResponse> obtenerReticula(
            @Query("periodo") int periodo
    );


}
