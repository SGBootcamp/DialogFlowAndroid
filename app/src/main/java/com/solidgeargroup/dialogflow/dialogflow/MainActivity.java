package com.solidgeargroup.dialogflow.dialogflow;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ai.api.AIListener;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.Result;

public class MainActivity extends AppCompatActivity implements OnRecyclerViewItemListener, AIListener {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MyAdapter mAdapter;

    private List<Film> myFilms = new ArrayList<Film>();
    private TextToSpeech mTextToSpeech;
    private AIService mAIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFilms();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(myFilms, this);
        mRecyclerView.setAdapter(mAdapter);


        final AIConfiguration config = new AIConfiguration("74da4132475c4d7fbb7f27d9492e969d",
                AIConfiguration.SupportedLanguages.Spanish,
                AIConfiguration.RecognitionEngine.System);

        mAIService = AIService.getService(this, config);
        mAIService.setListener(this);

        mTextToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        });

        findViewById(R.id.micButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAIService.startListening();
            }
        });

    }

    private void loadFilms() {
        myFilms.add(new Film(1, "Amor a medianoche", R.drawable.amoramedianoche, "Katie (Bella Thorne) es una chica de 17 años quien desde su infancia vive protegida dentro de su casa debido a una extraña enfermedad: debe vivir en la oscuridad, sin recibir las más mínima cantidad de luz solar. Sin embargo, cuando el joven Charlie (Patrick Schwarzenegger) se cruza en su camino, la vida de Katie se transforma por completo al descubrir el amor."));
        myFilms.add(new Film(2, "Campeones", R.drawable.campeones, "Javier Gutiérrez da vida al protagonista, Marco, un entrenador profesional de baloncesto que se ve, en medio de una crisis personal, entrenando a un equipo compuesto por personas con discapacidad intelectual. Lo que comienza como un castigo se acaba convirtiendo en una lección de vida."));
        myFilms.add(new Film(3, "El príncipe encantador", R.drawable.elprincipeencantador, "El Príncipe Encantador tiene enamorado a todas las princesas del mundo de los cuentos: Blancanieves, Cenicienta, Bella Durmiente... Su padre, enfadado por la forma de actuar de su hijo, le pone un ultimátum: o encuentra a su amor verdadero antes de cumplir 21 años o pierde su derecho al trono. El Príncipe Encantador, con ayuda de la heroína Lenore, una mujer que es incapaz de amar, emprende un viaje épico para encontrar el verdadero amor. "));
        myFilms.add(new Film(4, "Gorrión rojo", R.drawable.gorrionrojo, "Dominika Egorova (Jennifer Lawrence) es una joven y hermosa mujer rusa que, tras la muerte de su padre, es reclutada por los servicios secretos de su país. Con el objetivo de ayudar a su madre enferma, ingresa en la Escuela Gorrión, donde aprenderá el arte del espionaje utilizando métodos de seducción, un sistema muy eficaz para sacar información a los informadores enemigos."));
        myFilms.add(new Film(5, "Noche de juegos", R.drawable.nochedejuegos, "Max y Annie son una pareja que forman parte de un grupo de amigos que queda todas las semanas para una noche de juegos organizada por uno de ellos. En una de esas noches Brooks (Kyle Chandler), el carismático hermano de Max, organiza un misterioso juego en el que sus amigos deberán resolver un asesinato con falsos matones y falsos agentes."));
        myFilms.add(new Film(6, "Vengadores: Infinity War", R.drawable.vengadores, "Vengadores: Infinity War seguirá la lucha de los superhéroes de Marvel contra el mayor villano al que se han enfrentado nunca: Thanos. Su único objetivo será detener a este poderoso antagonista e impedir que se haga con el control de la galaxia. De nuevo veremos al grupo formado por Iron Man, Capitán América, Viuda negra, Ant-Man, Ojo de Halcón, Thor y Hulk, entre otros. En su nueva e impactante aventura, las Gemas del Infinito estarán en juego, unos querrán protegerlas y otros controlarlas, ¿quién ganará?"));
        myFilms.add(new Film(7, "Ready Player One", R.drawable.readyplayerone, " En el año 2045, el mundo está al borde del caos y del colapso. Sin embargo, la gente ha encontrado la salvación en OASIS, un enorme universo de realidad virtual creado por el brillante y excéntrico James Halliday (Mark Rylance). Cuando Halliday fallece, deja su inmensa fortuna a la primera persona que encuentre un huevo de Pascua digital que ha escondido en algún lugar de OASIS, desatando una competición que tiene enganchado al mundo entero. "));
    }

    private void showFilmDetails() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position) {
        openFilmDetails(position);
    }

    private void openFilmDetails(int id) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("film", myFilms.get(id));
        intent.putExtras(b);

        startActivity(intent);
    }

    @Override
    public void onResult(AIResponse response) {
        Result result = response.getResult();

        mTextToSpeech.speak(result.getFulfillment().getSpeech(), TextToSpeech.QUEUE_FLUSH, null, null);

    }

    @Override
    public void onError(AIError error) {

    }

    @Override
    public void onAudioLevel(float level) {

    }

    @Override
    public void onListeningStarted() {

    }

    @Override
    public void onListeningCanceled() {

    }

    @Override
    public void onListeningFinished() {

    }

}
