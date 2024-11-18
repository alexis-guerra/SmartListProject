package com.example.smartlist.Activity;

import static org.junit.Assert.*;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.smartlist.Activity.DetailActivity;
import com.example.smartlist.Domain.Stores;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    // Variables de contexto y objeto de prueba
    private Context contexto;
    private Stores tiendaDePrueba;

    @Before
    public void setUp() {
        // Inicializar el contexto de prueba y el objeto tienda con valores de prueba
        contexto = InstrumentationRegistry.getInstrumentation().getTargetContext();
        tiendaDePrueba = new Stores("Tienda de Prueba", "Descripción de Prueba", "https://example.com/image.jpg", 4.5, 100.0);
    }

    @Test
    public void probarLanzamientoActividadConIntent() {
        // Crear un intent para lanzar DetailActivity con el objeto tiendaDePrueba
        Intent intent = new Intent(contexto, DetailActivity.class);
        intent.putExtra("object", tiendaDePrueba);

        // Lanzar la actividad y verificar que se inicia correctamente
        try (ActivityScenario<DetailActivity> escenario = ActivityScenario.launch(intent)) {
            escenario.onActivity(actividad -> {
                assertNotNull(actividad); // Asegurarse de que la actividad no sea nula
                assertEquals("Tienda de Prueba", actividad.binding.titleTxt.getText().toString()); // Verificar que el título mostrado coincide con el título de tiendaDePrueba
            });
        }
    }

    @Test
    public void probarBotonIncrementarCantidad() {
        // Crear un intent para lanzar DetailActivity con el objeto tiendaDePrueba
        Intent intent = new Intent(contexto, DetailActivity.class);
        intent.putExtra("object", tiendaDePrueba);

        // Lanzar la actividad y simular un clic en el botón "plus" para incrementar la cantidad
        try (ActivityScenario<DetailActivity> escenario = ActivityScenario.launch(intent)) {
            escenario.onActivity(actividad -> {
                int cantidadInicial = Integer.parseInt(actividad.binding.numTxt.getText().toString()); // Obtener la cantidad inicial
                actividad.binding.plusBtn.performClick(); // Simular clic en el botón "plus"
                int cantidadActualizada = Integer.parseInt(actividad.binding.numTxt.getText().toString()); // Obtener la cantidad actualizada
                assertEquals(cantidadInicial + 1, cantidadActualizada); // Verificar que la cantidad se haya incrementado en 1
            });
        }
    }

    @Test
    public void probarBotonDisminuirCantidad() {
        // Crear un intent para lanzar DetailActivity con el objeto tiendaDePrueba
        Intent intent = new Intent(contexto, DetailActivity.class);
        intent.putExtra("object", tiendaDePrueba);

        // Lanzar la actividad y simular un clic en el botón "minus" para disminuir la cantidad
        try (ActivityScenario<DetailActivity> escenario = ActivityScenario.launch(intent)) {
            escenario.onActivity(actividad -> {
                actividad.binding.plusBtn.performClick(); // Aumentar la cantidad para asegurarse de que sea mayor a 1
                int cantidadInicial = Integer.parseInt(actividad.binding.numTxt.getText().toString()); // Obtener la cantidad inicial
                actividad.binding.minusBtn.performClick(); // Simular clic en el botón "minus"
                int cantidadActualizada = Integer.parseInt(actividad.binding.numTxt.getText().toString()); // Obtener la cantidad actualizada
                assertEquals(cantidadInicial - 1, cantidadActualizada); // Verificar que la cantidad se haya disminuido en 1
            });
        }
    }

    @Test
    public void probarBotonDisminuirNoBajaDeUno() {
        // Crear un intent para lanzar DetailActivity con el objeto tiendaDePrueba
        Intent intent = new Intent(contexto, DetailActivity.class);
        intent.putExtra("object", tiendaDePrueba);

        // Lanzar la actividad y simular un clic en el botón "minus" para intentar reducir la cantidad por debajo de 1
        try (ActivityScenario<DetailActivity> escenario = ActivityScenario.launch(intent)) {
            escenario.onActivity(actividad -> {
                actividad.binding.minusBtn.performClick(); // Intentar reducir la cantidad por debajo de 1
                int cantidadActual = Integer.parseInt(actividad.binding.numTxt.getText().toString()); // Obtener la cantidad actual
                assertEquals(1, cantidadActual); // Verificar que la cantidad no baje de 1
            });
        }
    }

    @Test
    public void probarAgregarAlCarritoActualizaCantidad() {
        // Crear un intent para lanzar DetailActivity con el objeto tiendaDePrueba
        Intent intent = new Intent(contexto, DetailActivity.class);
        intent.putExtra("object", tiendaDePrueba);

        // Lanzar la actividad y simular agregar el artículo al carrito
        try (ActivityScenario<DetailActivity> escenario = ActivityScenario.launch(intent)) {
            escenario.onActivity(actividad -> {
                actividad.binding.plusBtn.performClick(); // Incrementar la cantidad a 2
                actividad.binding.addBtn.performClick(); // Simular clic en el botón "add to cart"
                assertEquals(tiendaDePrueba.getNumberInCart(), 2); // Verificar que la cantidad en el carrito sea 2
            });
        }
    }
}
