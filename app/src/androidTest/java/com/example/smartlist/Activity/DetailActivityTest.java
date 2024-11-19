package com.example.smartlist.Activity;

import static org.junit.Assert.*;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.smartlist.Domain.Stores;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    private Context context;
    private Stores testStore;

    @Before
    public void setUp() {
        // Inicializa el contexto y el objeto a ser utilizado durante las pruebas
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        testStore = new Stores("Test Store", "Test Description", "https://example.com/image.jpg", 4.5, 100.0);
    }

    @Test
    public void testActivityLaunchWithIntent() {
        // Crea un intent que contiene el objeto necesario para lanzar la actividad
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("object", testStore);

        // Lanza la actividad y verifica que los componentes se inicializan correctamente
        try (ActivityScenario<DetailActivity> scenario = ActivityScenario.launch(intent)) {
            scenario.onActivity(activity -> {
                assertNotNull(activity);
                assertEquals("Test Store", activity.binding.titleTxt.getText().toString());
            });
        }
    }

    @Test
    public void testPlusButtonIncreasesQuantity() {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("object", testStore);

        try (ActivityScenario<DetailActivity> scenario = ActivityScenario.launch(intent)) {
            scenario.onActivity(activity -> {
                int initialNum = Integer.parseInt(activity.binding.numTxt.getText().toString());
                activity.binding.plusBtn.performClick();
                int updatedNum = Integer.parseInt(activity.binding.numTxt.getText().toString());
                assertEquals(initialNum + 1, updatedNum);
            });
        }
    }

    @Test
    public void testMinusButtonDecreasesQuantity() {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("object", testStore);

        try (ActivityScenario<DetailActivity> scenario = ActivityScenario.launch(intent)) {
            scenario.onActivity(activity -> {
                activity.binding.plusBtn.performClick(); // Aumentar la cantidad para garantizar que > 1
                int initialNum = Integer.parseInt(activity.binding.numTxt.getText().toString());
                activity.binding.minusBtn.performClick();
                int updatedNum = Integer.parseInt(activity.binding.numTxt.getText().toString());
                assertEquals(initialNum - 1, updatedNum);
            });
        }
    }

    @Test
    public void testMinusButtonCannotReduceBelowOne() {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("object", testStore);

        try (ActivityScenario<DetailActivity> scenario = ActivityScenario.launch(intent)) {
            scenario.onActivity(activity -> {
                activity.binding.minusBtn.performClick(); // Intentar reducir por debajo de 1
                int currentNum = Integer.parseInt(activity.binding.numTxt.getText().toString());
                assertEquals(1, currentNum); // No debería ser menor que 1
            });
        }
    }

    @Test
    public void testAddToCartUpdatesCart() {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("object", testStore);

        try (ActivityScenario<DetailActivity> scenario = ActivityScenario.launch(intent)) {
            scenario.onActivity(activity -> {
                activity.binding.plusBtn.performClick(); // Incrementar la cantidad
                activity.binding.addBtn.performClick();
                assertEquals(testStore.getNumberInCart(), 2); // Debería reflejar la cantidad actualizada
            });
        }
    }
}
