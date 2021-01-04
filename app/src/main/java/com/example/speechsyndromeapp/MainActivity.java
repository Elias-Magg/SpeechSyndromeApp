package com.example.speechsyndromeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *Code written by Manginas Ilias
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String EXTRA_SYNDROME_DATA = "com.example.speechsyndromeapp.EXTRA_SYNDROME_DATA";
    public static final String IP_ADDRESS = "192.168.1.10";
    public static final int PORT_NUM = 7878;



    private RecyclerView mRecyclerView;
    private MainRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayouteManager;
    private ArrayList<MainRecyclerItem> mCardList;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {

        startService(new Intent(getBaseContext(), OnClearFromRecentService.class));

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); //Disable night mode

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.dot_menu);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mCardList = new ArrayList<>();
        setData();

        buildRecyclerView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.search_bar,menu);

        MenuItem searchItem = menu.findItem(R.id.search_bar);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) searchItem.getActionView();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Survey:
                Intent surveyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.surveymonkey.com/r/FS8QQ9V"));
                startActivity(surveyIntent);
                break;
            case R.id.AboutUs:
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutIntent);
                break;
            default:
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.main_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayouteManager = new LinearLayoutManager(this);
        mAdapter = new MainRecyclerViewAdapter(mCardList);

        mRecyclerView.setLayoutManager(mLayouteManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MainRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getApplicationContext(), SyndromeActivity.class);
                MainRecyclerButton btn = (MainRecyclerButton)mCardList.get(position);
                intent.putExtra(MainActivity.EXTRA_SYNDROME_DATA,btn.getData());
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START))drawer.closeDrawer(GravityCompat.START);
        else {
            super.onBackPressed();
        }
    }

    /**
     * Method for settign the main activity recycler data
     */

    public void setData(){

        mCardList.add(new MainRecyclerLettering("Α"));

        //achondroplasia

        List<String> achondroplasia_keywords_tmp = Arrays.asList("achondroplasia", "Αχονδροπλασία ", "Q77.4", "15", "Μυϊκή αδυναμία", "υποπλασία", "ωτίτιδα ",
                "βαρηκοΐα ", "αναπνοή", "Καθυστέρηση ομιλίας ", "παλινδρόμηση");
        ArrayList<String> achondroplasia_keywords = new ArrayList<>(achondroplasia_keywords_tmp);

        ArrayList<Integer> achondroplasia_img = new ArrayList<>();
        achondroplasia_img.add(R.drawable.achondroplasia_1);
        mCardList.add(new MainRecyclerButton("Achondroplasia",achondroplasia_keywords,new SyndromeData(
                "Αχονδροπλασία (Achondroplasia)",
                R.raw.achondroplasia,"ICD-10: Q77.4\n" +
                "ORPHA: 15\n",
                "Βιβλιογραφία (Αχονδροπλασία)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/achondroplasia/\n" +
                        "2. Christopher M. Cielo e tal.,\" Craniofacial disorders associated with airway obstruction in the neonate\", Semin Fetal Neonatal Med. 2016\n" +
                        "3. John M. Graham, Glenis K. Scadding, Peter D. Bull, Eitors, Pediatric ENT, 2007.\n" +
                        "4. Takuo Kubota et al., \" Clinical Practice Guidelines for Achondroplasia*\", Clin Pediatr Endocrinol. 2020\n" +
                        "5. Richard M. Pauli, \"Achondroplasia: a comprehensive clinical review\", Orphanet J Rare Dis. 2019",
                achondroplasia_img

        )));

        //aicardi_syndrome

        List<String> aicardi_syndrome_keywords_tmp = Arrays.asList(
                "Aicardi", "Q04.0", "50", "παρεγκεφαλίδα", "Πλαγιοκεφαλία", "Ασυμμετρία προσώπου",
                "Χειλεοσχιστία", "Υπερωιοσχιστία",  "Νοητική υστέρηση", "Καθυστέρηση ομιλίας",  "Σιελόρροια ", "παλινδρόμηση"

        );
        ArrayList<String> aicardi_syndrome_keywords = new ArrayList<>(aicardi_syndrome_keywords_tmp);

        ArrayList<Integer> aicardi_syndrome_img = new ArrayList<>();
        aicardi_syndrome_img.add(R.drawable.aicardi_1);
        aicardi_syndrome_img.add(R.drawable.aicardi_2);
        mCardList.add(new MainRecyclerButton("Aicardi syndrome",aicardi_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Aicardi (Aicardi syndrome)",
                R.raw.aicardi_syndrome,"ICD-10: Q04.0\n" +
                "ORPHA: 50\n",
                "Βιβλιογραφία (Σύνδρομο Aicardi)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/aicardi-syndrome/\n" +
                        "2. Gillian Rice et al. ,\"Clinical and Molecular Phenotype of Aicardi-Goutières Syndrome\", Am J Hum Genet. 2007\n" +
                        "3. V Reid Sutton, MD and Ignatia B Van den Veyver, MD. , \"Aicardi Syndrome\", 2006\n" +
                        "4. Michael Wahl, MS, Zoe A. Strominger, AB, Mari Wakahiro, MSW, Rita J. Jeremy, PhD, Pratik Mukherjee, MD PhD, and Elliott H. Sherr, MD PhD, \" Diffusion Tensor\n" +
                        "Imaging of Aicardi Syndrome\", PMC 2011",
                aicardi_syndrome_img

        )));

        //alport_syndrome

        List<String> alport_syndrome_keywords_tmp = Arrays.asList(
                "Alport ", "Q87.8", "63", "κώφωση", "βαρηκοΐα", "Καθυστέρηση ομιλίας",
                "κατανόηση", "σύνταξη", " πραγματολογία", "Μαθησιακές δυσκολίες", "Γνωστικά προβλήματα",
                "Χειλεοσχιστία", "Υπερωιοσχιστία ", "αυτισμός"

        );
        ArrayList<String> alport_syndrome_keywords = new ArrayList<>(alport_syndrome_keywords_tmp);

        ArrayList<Integer> alport_syndrome_img = new ArrayList<>();
        alport_syndrome_img.add(R.drawable.alport_1);
        mCardList.add(new MainRecyclerButton("Alport syndrome",alport_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Alport (Alport syndrome)",
                R.raw.alport_syndrome,"ICD-10: Q87.8\n" +
                "ORPHA:63\n",
                "Βιβλιογραφία (Σύνδρομο Alport)\n" +
                        "1. John M. Graham, Glenis K. Scadding, Peter D. Bull, Eitors, Pediatric ENT, 2007.\n" +
                        "2. Ramesh Kaipa and Hannah Tether, \" Speech, language, and hearing function in twins with Alport syndrome: A seven-year retrospective case report\", J Otol. 2017",
                alport_syndrome_img

        )));

        //alstrom_syndrome

        List<String> alstrom_syndrome_keywords_tmp = Arrays.asList(
                "Alstrom ", "E34.8", "64", "βαρηκοΐα", "ωτίτιδα", "Εμβοές",  "Καθυστέρηση ομιλία ", "αυτισμός", "ρυθμός ομιλίας", "Γνωστικά ελλείμματα"

        );
        ArrayList<String> alstrom_syndrome_keywords = new ArrayList<>(alstrom_syndrome_keywords_tmp);

        ArrayList<Integer> alstrom_syndrome_img = new ArrayList<>();
        alstrom_syndrome_img.add(R.drawable.alstrom_1);
        mCardList.add(new MainRecyclerButton("Alstrom syndrome",alstrom_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Alstrom (Alstrom syndrome)",
                R.raw.alstrom_syndrome,"ICD-10: E34.8\n" +
                "ORPHA:64\n",
                "Βιβλιογραφία (Σύνδρομο Alstrom)\n" +
                        "1. John M. Graham, Glenis K. Scadding, Peter D. Bull, Eitors, Pediatric ENT, 2007.\n" +
                        "2. Hans-Erik Frölander et al.,\" Theory-of-mind in individuals with Alström syndrome is related to executive functions, and verbal ability\" , Front Psychol. 2015\n" +
                        "3. Spencer Lindsey et al., \" Auditory and Otologic Profile of Alström Syndrome: Comprehensive Single Center Data on 38 Patients\", Am J Med Genet A. 2017",
                alstrom_syndrome_img

        )));

        //angeman_syndrome

        List<String> angeman_syndrome_keywords_tmp = Arrays.asList(
                "Angeman ", "Q93.5", "72",  "Νοητική υστέρηση", "Αταξία ", "Επιληψία", "ΔΕΠΥ",
                "Μικροκεφαλία",  "παραγωγή λόγου",  "εκφραστικό λεξιλόγιο", "υποτονία", "απομύζηση ", "δυσφαγία"

        );
        ArrayList<String> angeman_syndrome_keywords = new ArrayList<>(angeman_syndrome_keywords_tmp);

        ArrayList<Integer> angeman_syndrome_img = new ArrayList<>();
        angeman_syndrome_img.add(R.drawable.angeman_1);
        angeman_syndrome_img.add(R.drawable.angeman_2);
        mCardList.add(new MainRecyclerButton("Angeman syndrome",angeman_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Angelman (Angeman syndrome)",
                R.raw.angeman_syndrome,"ICD-10: Q93.5\n" +
                "ORPHA: 72\n",
                "Βιβλιογραφία (Σύνδρομο Angelman)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/angelman-syndrome/\n" +
                        "2. Robert P. Carson et al., \" Preserved expressive language as a phenotypic determinant of Mosaic Angelman Syndrome\", Mol Genet Genomic Med. 2019\n" +
                        "3. Karine Pelc, Guy Cheron, and Bernard Dan,\" Behavior and neuropsychiatric manifestations in Angelman syndrome\", Neuropsychiatr Dis Treat. 2008",
                angeman_syndrome_img

        )));





        //apert_syndrome

        List<String> apert_syndrome_keywords_tmp = Arrays.asList(
                "Apert ", "Q87.0B", "87", "αναπνοή", "Νοητική υστέρηση", "Κρανιοσυνοστέωση",
                "Χειλεοσχιστία", "Υπερωιοσχιστία", "Δισχιδής σταφυλή", "Υδροκεφαλία", "Καθυστέρηση ομιλίας",
                "Μαθησιακές δυσκολίες", "άρθρωση", "φάρυγγας"

        );
        ArrayList<String> apert_syndrome_keywords = new ArrayList<>(apert_syndrome_keywords_tmp);

        ArrayList<Integer> apert_syndrome_img = new ArrayList<>();
        apert_syndrome_img.add(R.drawable.apert_1);
        mCardList.add(new MainRecyclerButton("Apert syndrome",apert_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Apert (Apert syndrome)",
                R.raw.apert_syndrome,"ICD-10: Q87.0B\n" +
                "ORPHA: 87\n",
                "Βιβλιογραφία (Σύνδρομο Apert)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/apert-syndrome/\n" +
                        "2. Ariane Hohoff et al., \" The spectrum of Apert syndrome: phenotype, particularities in orthodontic treatment, and characteristics of orthognathic surgery\",\n" +
                        "Head Face Med. 2007\n" +
                        "3. Andrea-Stacy López-Estudillo et al., \" Dental approach for Apert syndrome in children: a systematic review\", Med Oral Patol Oral Cir Bucal. 2017",
                apert_syndrome_img

        )));

        //aspartylglucosaminuria

        List<String> aspartylglucosaminuria_keywords_tmp = Arrays.asList(
                "E77.1", "93", "AGU", "αναπνοή", "επιληψία", "στοματοκινητικές δυσκολίες",  "λοιμώξεις  ", "απώλεια ακοής",
                "ΔΕΠΥ", "φωνή", "βραχνή ", "έκφραση ", "λεξιλόγιο", "μαθησιακές δυσκολίες",
                "Ανασυνδυασμένη" , "ανθρώπινη" , "ασπαρτυλγλυκοσαμινιδάση" , "Ανασυνδυασμένη ανθρώπινη ασπαρτυλγλυκοσαμινιδάση"


        );
        ArrayList<String> aspartylglucosaminuria_keywords = new ArrayList<>(aspartylglucosaminuria_keywords_tmp);

        ArrayList<Integer> aspartylglucosaminuria_img = new ArrayList<>();
        aspartylglucosaminuria_img.add(R.drawable.agu_1);
        aspartylglucosaminuria_img.add(R.drawable.agu_2);
        mCardList.add(new MainRecyclerButton("Aspartylglucosaminuria",aspartylglucosaminuria_keywords,new SyndromeData(
                "Ανασυνδυασμένη ανθρώπινη ασπαρτυλγλυκοσαμινιδάση\n" +
                        "(Aspartylglucosaminuria-AGU)",
                R.raw.aspartylglucosaminuria,"ICD-10: E77.1\n" +
                "ORPHA: 93\n",
                "Βιβλιογραφία (AGU)\n" +
                        "1. John M. Graham, Glenis K. Scadding, Peter D. Bull, Eitors, Pediatric ENT, 2007.\n" +
                        "2. https://www.genlab.gr/oloprosegkefalia/\n" +
                        "3. https://emvriomitriki.gr/egkymosynh/evryikes-anwmalies/kentriko-neyriko-systhma/oloprosegkefalia\n" +
                        "4. Manu S Raam et al., \"Holoprosencephaly: A Guide to Diagnosis and Clinical Management\", Indian Pediatr. 2011\n" +
                        "5. Paul Kruszka and Maximilian Muenke ,\"Syndromes Associated with Holoprosencephaly\", Am J Med Genet C Semin Med Genet. 2018",
                aspartylglucosaminuria_img

        )));


        mCardList.add(new MainRecyclerLettering("Β"));

        //beckwith_wiedemann_syndrome

        List<String> beckwith_wiedemann_syndrome_keywords_tmp = Arrays.asList(
                "Beckwith-Wiedemann ","Beckwith Wiedemann ", "Q87.3", "116", "Μακρογλωσσία", "αναπνοή", "άρθρωση", "σιελόρροια", "σίτιση","Beckwith","Wiedemann "

        );
        ArrayList<String> beckwith_wiedemann_syndrome_keywords = new ArrayList<>(beckwith_wiedemann_syndrome_keywords_tmp);

        ArrayList<Integer> beckwith_wiedemann_syndrome_img = new ArrayList<>();
        beckwith_wiedemann_syndrome_img.add(R.drawable.beckwith_1);
        beckwith_wiedemann_syndrome_img.add(R.drawable.beckwith_2);
        mCardList.add(new MainRecyclerButton("Beckwith-Wiedemann syndrome",beckwith_wiedemann_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Beckwith-Wiedemann (Beckwith-Wiedemann syndrome)",
                R.raw.beckwith_wiedemann_syndrome,"ICD-10: Q87.3\n" +
                "ORPHA: 116\n",
                "Βιβλιογραφία (Σύνδρομο Beckwith-Wiedemann)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/beckwith-wiedemann-syndrome/\n" +
                        "2. Kathleen H. Wang et al.,\" Diagnosis and Management of Beckwith-Wiedemann Syndrome\", Front Pediatr. 2019\n" +
                        "3. John M. Graham, Glenis K. Scadding, Peter D. Bull, Eitors, Pediatric ENT, 2007",
                beckwith_wiedemann_syndrome_img

        )));

        //bloch_sulzberger_syndrome

        List<String> incontinentia_pigmenti_bloch_sulzberger_syndrome_keywords_tmp = Arrays.asList(
                "Q82.3", "464", "ακράτεια χρωστικής", " Bloch-Sulzberger", "BeckwithWiedemann ", "Beckwith","Wiedemann ", "εγκεφαλικό ", "βασικά γάγγλια", "ασυμμετρία προσώπου", "υποπλασία μαλακής υπερώας", "χειλεοσχιστία",
                "υπερωιοσχιστία", "βαρηκοΐα", "μαθησιακές δυσκολίες", "γνωστικά ελλείμματα", " Bloch","Sulzberger"

        );
        ArrayList<String> incontinentia_pigmenti_bloch_sulzberger_syndrome_keywords = new ArrayList<>(incontinentia_pigmenti_bloch_sulzberger_syndrome_keywords_tmp);

        ArrayList<Integer> incontinentia_pigmenti_bloch_sulzberger_syndrome_img = new ArrayList<>();
        incontinentia_pigmenti_bloch_sulzberger_syndrome_img.add(R.drawable.blosch_1);
        mCardList.add(new MainRecyclerButton("Bloch-Sulzberger syndrome",incontinentia_pigmenti_bloch_sulzberger_syndrome_keywords,new SyndromeData(
                "Ακράτεια χρωστικής-Σύνδρομο Bloch-Sulzberger\n" +
                        "(Incontinentia pigmenti-Bloch-Sulzberger Syndrome)",
                R.raw.incontinentia_pigmenti_bloch_sulzberger_syndrome,"ICD-10: Q82.3\n" +
                "ORPHA:464\n",
                "Βιβλιογραφία (Incontinentia pigmenti\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/incontinentia-pigmenti/\n" +
                        "2. Cláudia Schermann Poziomczyk et al., \"Incontinentia pigmenti\", An Bras Dermatol., 2014.",
                incontinentia_pigmenti_bloch_sulzberger_syndrome_img

        )));



        //branchiootorenal_syndrome

        List<String> branchiootorenal_syndrome_keywords_tmp = Arrays.asList(
                "BOR", "Branchiootorenal", "Q87.8", "107", "ασυμμετρία προσώπου", "μικρωτία", "κοντή υπερώα", "υπερωιοσχιστία ",
                "μικρογναθία", "βαρηκοΐα", "κώφωση", "ωτίτιδα", "προσωπικό κρανιακό νεύρο ", "κρανιακά νεύρα", "φωνητικές χορδές"
        );
        ArrayList<String> branchiootorenal_syndrome_keywords = new ArrayList<>(branchiootorenal_syndrome_keywords_tmp);

        ArrayList<Integer> branchiootorenal_syndrome_img = new ArrayList<>();
        branchiootorenal_syndrome_img.add(R.drawable.bor_1);
        branchiootorenal_syndrome_img.add(R.drawable.bor_2);
        mCardList.add(new MainRecyclerButton("Branchiootorenal syndrome",branchiootorenal_syndrome_keywords,new SyndromeData(
                "Σύνδρομο BOR (Branchiootorenal syndrome)",
                R.raw.branchiootorenal_syndrome,"ICD-10: Q87.8\nORPHA:107\n",
                "Βιβλιογραφία (Σύνδρομο BOR)\n" +
                        "1. John M. Graham, Glenis K. Scadding, Peter D. Bull, Eitors, Pediatric ENT, 2007.\n" +
                        "2. Tâmara Andrade Lindau et al., \"Anatomical Changes and Audiological Profile in Branchio-oto-renal Syndrome: A Literature Review\", Int Arch\n" +
                        "Otorhinolaryngol., 2014",
                branchiootorenal_syndrome_img

        )));


        mCardList.add(new MainRecyclerLettering("C"));

        //carpenter_syndrome

        List<String> carpenter_syndrome_keywords_tmp = Arrays.asList(
                "Carpenter ", "Q87.0", "65759", "Κρανιοσυνοστέωση", "Νοητική υστέρηση ", "ασυμμετρία", "Βαρηκοΐα", "Καθυστέρηση ομιλίας"

        );
        ArrayList<String> carpenter_syndrome_keywords = new ArrayList<>(carpenter_syndrome_keywords_tmp);

        ArrayList<Integer> carpenter_syndrome_img = new ArrayList<>();
        carpenter_syndrome_img.add(R.drawable.carpenter_1);
        carpenter_syndrome_img.add(R.drawable.carpenter_2);
        mCardList.add(new MainRecyclerButton("Carpenter syndrome",carpenter_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Carpenter (Carpenter syndrome)",
                R.raw.carpenter_syndrome,"ICD-10: Q87.0\n" +
                "ORPHA: 65759\n",
                "Βιβλιογραφία (Σύνδρομο Carpenter)\n" +
                        "1. https://rarediseases.org/rare-diseases/carpenter-syndrome/\n" +
                        "2. https://childrensnational.org/visit/conditions-and-treatments/genetic-disorders-and-birth-defects/carpenter-syndrome",
                carpenter_syndrome_img

        )));

        //cmt_disease

        List<String> cmt_disease_keywords_tmp = Arrays.asList(
                "CMT", "Charcot Marie Tooth", "G60.0", "μυϊκή αδυναμία ", "αναπνοή", "ασάφεια", "ατροφία φωνητικών χορδών", "εισρόφηση"
                , "Charcot", "Marie", "Tooth"
        );
        ArrayList<String> cmt_disease_keywords = new ArrayList<>(cmt_disease_keywords_tmp);

        ArrayList<Integer> cmt_disease_img = new ArrayList<>();
        cmt_disease_img.add(R.drawable.cmt_1);
        mCardList.add(new MainRecyclerButton("Charcot-Marie-Tooth disease",cmt_disease_keywords,new SyndromeData(
                "Ασθένεια Charcot-Marie-Tooth (CMT Disease)",
                R.raw.cmt_disease,"ICD-10: G60.0\n",
                "Βιβλιογραφία (Ασθένεια CMT)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/charcot-marie-tooth-disease/,\n" +
                        "2. Donald McCorquodale et al.,\"Management of Charcot–Marie–Tooth disease: improving long-term care with a multidisciplinary approach\", J Multidiscip\n" +
                        "Healthc. 2016",
                cmt_disease_img

        )));

        //charge_syndrome

        List<String> charge_syndrome_keywords_tmp = Arrays.asList(
                "CHARGE", "Q87.8", "134", "ρινοφάρυγγα", "ατρησία", "νοητική υστέρηση", "βαρηκοΐα",
                "κρανιακάνεύρα", "Χειλεοσχιστία", "υπερωιοσχιστία", "Παράλυση ", "Καθυστέρηση ομιλίας ",
                "Μαθησιακές δυσκολίες ", "δυσφαγία", "Σιελόρροια", "απομύζηση", "προσωπικό κρανιακό νεύρο",
                "Υποτονία", "παλινδρόμηση"

        );
        ArrayList<String> charge_syndrome_keywords = new ArrayList<>(charge_syndrome_keywords_tmp);

        ArrayList<Integer> charge_syndrome_img = new ArrayList<>();
        charge_syndrome_img.add(R.drawable.charge_1);
        charge_syndrome_img.add(R.drawable.charge_2);
        mCardList.add(new MainRecyclerButton("CHARGE syndrome",charge_syndrome_keywords,new SyndromeData(
                "Σύνδρομο CHARGE (CHARGE syndrome)",
                R.raw.charge_syndrome,"ICD-10: Q87.8\n" +
                "ORPHA: 134\n",
                "Βιβλιογραφία (Σύνδρομο CHARGE)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/charge-syndrome/\n" +
                        "2. Christa M. de Geus et al., \" Guidelines in CHARGE syndrome and the missing link: Cranial imaging\", Am J Med Genet C Semin Med Genet. 2017\n" +
                        "3. Dieuwerke R Dijk et al., \" Growth in CHARGE syndrome: optimizing care with a multidisciplinary approach\", J Multidiscip Healthc. 2019\n" +
                        "4. John M. Graham, Glenis K. Scadding, Peter D. Bull, Eitors, Pediatric ENT, 2007.",
                charge_syndrome_img

        )));

        //congenital_disorder_of_glycosylation

        List<String> congenital_disorder_of_glycosylation_keywords_tmp = Arrays.asList(
                "CDG", "E77.8", "137", "Νοητική υστέρηση", "Επιληψία", "Αταξία", "Εγκεφαλική ατροφία", "Μικροκεφαλία",
                "υποπλασία", "μυϊκός τόνος", "αντανακλαστικά", "Δυσαρθρία", "Σιελόρροια", "παλινδρόμηση",
                "Congenital Disorder ","Congenital Disorder of Glycosylation"

        );
        ArrayList<String> congenital_disorder_of_glycosylation_keywords = new ArrayList<>(congenital_disorder_of_glycosylation_keywords_tmp);

        ArrayList<Integer> congenital_disorder_of_glycosylation_img = new ArrayList<>();
        congenital_disorder_of_glycosylation_img.add(R.drawable.cdg_1);
        mCardList.add(new MainRecyclerButton("Congenital Disorder of Glycosylation",congenital_disorder_of_glycosylation_keywords,new SyndromeData(
                "Σύνδρομο CDG (CDG syndrome - Congenital Disorder of Glycosylation)",
                R.raw.congenital_disorder_of_glycosylation,"ICD-10: E77.8\n" +
                "ORPHA: 137\n",
                "Βιβλιογραφία (Σύνδρομο CDG)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/cdg-syndrome/\n" +
                        "2. Laurien Vaes et al., \" PMM2‐CDG caused by uniparental disomy: Case report and literature review\", JIMD Rep. 2020",
                congenital_disorder_of_glycosylation_img

        )));

        //cornelia_delange_syndrome

        List<String> cornelia_delange_syndrome_keywords_tmp = Arrays.asList(
                "Cornelia de Lange", "Cornelia","Lange","de Lange", "Q87.1C", "199", "νοητική υστέρηση ", "αυτισμός",
                "Επιληψία", "Μικροκεφαλία", "Χειλεοσχιστία", "υπερωιοσχιστία", "βαρηκοΐα",
                "αναπνοή", "έκφραση", "Μυϊκή αδυναμία ",  "Γνωστικά ελλείμματα",  "φώνηση",
                "φωνή", "Μονότονη", "Βραχνή", "μορφολογία", "σύνταξη", "αλαλία", "δυσφαγία",
                "παλινδρόμηση", "Σιελόρροια"

        );
        ArrayList<String> cornelia_delange_syndrome_keywords = new ArrayList<>(cornelia_delange_syndrome_keywords_tmp);

        ArrayList<Integer> cornelia_delange_syndrome_img = new ArrayList<>();
        cornelia_delange_syndrome_img.add(R.drawable.cornelia_1);
        cornelia_delange_syndrome_img.add(R.drawable.cornelia_2);
        mCardList.add(new MainRecyclerButton("Cornelia de Lange syndrome",cornelia_delange_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Cornelia de Lange (Cornelia de Lange syndrome)",
                R.raw.cornelia_delange_syndrome,"ICD-10: Q87.1C\n" +
                "ORPHA: 199\n",
                "Βιβλιογραφία (Σύνδρομο Cornelia de Lange)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/cornelia-de-lange-syndrome/\n" +
                        "2. Antonie D. Kline et al., \" Diagnosis and management of Cornelia de Lange syndrome: first international consensus statement\", Nat Rev Genet. 2018\n" +
                        "3. Lisa Nelson et al., \" An experimental study of executive function and social impairment in Cornelia de Lange syndrome\", J Neurodev Disord. 2017\n" +
                        "4. George Psillas et al., \" Cornelia De Lange Syndrome and Cochlear Implantation\", Iran J Otorhinolaryngol. 2018",
                cornelia_delange_syndrome_img

        )));

        //cri_du_chat_syndrome

        List<String> cri_du_chat_syndrome_keywords_tmp = Arrays.asList(
                "Cri du Chat ","Cri ","Cri du","Chat ","du Chat ", "Q93.4", "281", "κλάμα ", "Νοητική υστέρηση ",
                "Υπερτονία", "υποτονία", "ΔΕΠΥ", "Μαθησιακές δυσκολίες",
                "καθυστέρηση ομιλίας", "ηχολαλία", "έκφραση", "λεξιλόγιο",
                "άρθρωση", "Υπερρινικότητα ", "διαδοχοκίνηση", "δυσφαγία",
                "Σιελόρροια"

        );
        ArrayList<String> cri_du_chat_syndrome_keywords = new ArrayList<>(cri_du_chat_syndrome_keywords_tmp);

        ArrayList<Integer> cri_du_chat_syndrome_img = new ArrayList<>();
        cri_du_chat_syndrome_img.add(R.drawable.cri_1);
        cri_du_chat_syndrome_img.add(R.drawable.cri_2);
        mCardList.add(new MainRecyclerButton("Cri du Chat syndrome",cri_du_chat_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Cri du Chat (Cri du Chat syndrome)",
                R.raw.cri_du_chat_syndrome,"ICD-10: Q93.4\n" +
                "ORPHA: 281\n",
                "Βιβλιογραφία (Σύνδρομο Cri du Chat)\n" +
                        "1. https://drustapbio.fandom.com/wiki/Cri_du_Chat\n" +
                        "2. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/cri-du-chat-syndrome/\n" +
                        "3. Kristoffersen, K. E. (2008), \"Speech and language development in cri du chat syndrome: A critical review\", Clinical Linguistics & Phonetics, 22(6), 443–457.\n" +
                        "4. Mi Kyung Kim et al, \"Effects of Oral Stimulation Intervention in Newborn Babies with Cri du Chat Syndrome: Single-Subject Research Design\", Occup Ther\n" +
                        "Int. 2018; 2018: 6573508.\n" +
                        "5. Maria Elena Liverani et al., \" Children and adults affected by Cri du Chat syndrome: Care's recommendations\", Pediatr Rep. 2019 Feb 26; 11(1): 7839.",
                cri_du_chat_syndrome_img

        )));


        //crouzon_syndrome

        List<String> crouzon_syndrome_keywords_tmp = Arrays.asList(
                "Crouzon", "Q75.1", "207", "Κρανιοσυνοστέωση", "βραχυκεφαλία",
                "Γνωστικά ελλείμματα", "Υποπλασία ", "μαλακή υπερώα", "Χειλεοσχιστία",
                "υπερωιοσχιστία ", "βαρηκοΐα", "Καθυστέρηση ομιλίας",  "αναπνοή", "άρθρωση",
                "Υπερρινικότητα"

        );
        ArrayList<String> crouzon_syndrome_keywords = new ArrayList<>(crouzon_syndrome_keywords_tmp);

        ArrayList<Integer> crouzon_syndrome_img = new ArrayList<>();
        crouzon_syndrome_img.add(R.drawable.crouzon_1);
        crouzon_syndrome_img.add(R.drawable.crouzon_2);
        mCardList.add(new MainRecyclerButton("Crouzon syndrome",crouzon_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Crouzon (Crouzon syndrome)",
                R.raw.crouzon_syndrome,"ICD-10: Q75.1\n" +
                "ORPHA: 207\n",
                "Βιβλιογραφία (Σύνδρομο Crouzon)\n" +
                        "1. Farnoosh Mohammadi et al., \"Patient with Crouzon Syndrome Treated with Modified Le Fort III Osteotomy without Previous Orthodontic Treatment: Case\n" +
                        "Report and a Review of the Literature\", Case Rep Dent. 2020; 2020: 6248971\n" +
                        "2. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/crouzon-syndrome/\n" +
                        "3. Cleft palate Foundation, \" Information about Crouzon Syndrome (Craniofacial Dysostosis)\"\n" +
                        "4. https://craniofacial.org/el/content/%CF%83%CF%8D%CE%BD%CE%B4%CF%81%CE%BF%CE%BC%CE%BF-crouzon",
                crouzon_syndrome_img
        )));

        mCardList.add(new MainRecyclerLettering("D"));

        //down_syndrome

        List<String> down_syndrome_keywords_tmp = Arrays.asList(
                "down", "Q90.0  ", "Q90.1  ", "Q90.2  ", "Q90.9", "870",
                "νοητική υστέρηση","υποτονία", "βραχυκεφαλία", "μακρογλωσσία", "υπερώα",
                "μικρωτία", "κώφωση ", "υπογλωττίδα", "υπερώα", "τραυλισμός", "δυσφαγία",
                "αδυναμία", "κόπωση", "υπολείμματα", "εισρόφηση", "διείσδυση "

        );
        ArrayList<String> down_syndrome_keywords = new ArrayList<>(down_syndrome_keywords_tmp);

        ArrayList<Integer> down_syndrome_img = new ArrayList<>();
        down_syndrome_img.add(R.drawable.down_1);
        mCardList.add(new MainRecyclerButton("Down syndrome",down_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Down (Down Syndrome)",
                R.raw.down_syndrome,"ICD-10: Q90.0 Q90.1 Q90.2 Q90.9\n" +
                "ORPHA:870\n",
                "Βιβλιογραφία (Σύνδρομο Down)\n" +
                        "1. Βιολογία Κατεύθυνσης, Υπουργείο Εθνικής Παιδείας και Θρησκευμάτων, 1999.\n" +
                        "2. John M. Graham, Glenis K. Scadding, Peter D. Bull, Eitors, Pediatric ENT, 2007.\n" +
                        "3. Arwen Jackson et al., \"Clinical Characteristics of Dysphagia in Children with Down Syndrome\", Springer Science+Business Media New York 2016\n" +
                        "4. John E. Riski, \"Cleft lip and palate and other craniofacial anomalies\", p. 21, (Lauridsen et al., 2001)\n" +
                        "5. Kathleen Scaler Scot, \"Stuttering and cluttering\".\n" +
                        "6. Linda Cooper-Brown et al., \" FEEDING AND SWALLOWING DYSFUNCTION IN GENETIC SYNDROMES\", Developmental Disabilities Research Reviews 14:147 –\n" +
                        "157 (2008).",
                down_syndrome_img

        )));

        mCardList.add(new MainRecyclerLettering("E"));

        //edwards_syndrome_trisomy_18

        List<String> edwards_syndrome_trisomy_18_keywords_tmp = Arrays.asList(
                "Τρισωμία 18 ", "Edwards ", "Q91.0  ", "Q91.1  ", "Q91.2 ",
                " Q91.3", "3380", "Γνωστικά ελλείμματα", "Νοητική υστέρηση", "Επιληψία",
                "Υποπλασία ", "παρεγκεφαλίδα", "Υποτονία ", "υπερτονία ", "Μικροκεφαλία",
                "Χειλεοσχιστία", "υπερωιοσχιστία", "Υδροκεφαλία", "ατρησία ", "μικρωτία", "βαρηκοΐα", "Αναπνοή",
                "έκφραση", "λεξιλόγιο", "σίτιση", "γαστροστομία", "απομύζηση", "παλινδρόμηση ", "δυσφαγία"

        );
        ArrayList<String> edwards_syndrome_trisomy_18_keywords = new ArrayList<>(edwards_syndrome_trisomy_18_keywords_tmp);

        ArrayList<Integer> edwards_syndrome_trisomy_18_img = new ArrayList<>();
        edwards_syndrome_trisomy_18_img.add(R.drawable.edwards_1);
        edwards_syndrome_trisomy_18_img.add(R.drawable.edwards_2);
        mCardList.add(new MainRecyclerButton("Edwards syndrome",edwards_syndrome_trisomy_18_keywords,new SyndromeData(
                "Σύνδρομο Edwards-Τρισωμία 18 (Edwards syndrome-Trisomy 18)",
                R.raw.edwards_syndrome_trisomy_18,"ICD-10: Q91.0 Q91.1 Q91.2 Q91.3\n" +
                "ORPHA:3380\n",
                "Βιβλιογραφία (Σύνδρομο Edwards)\n" +
                        "1. Anna Cereda and John C Carey, \" The trisomy 18 syndrome\", Orphanet J Rare Dis. 2012; 7: 81\n" +
                        "2. https://www.soft.org.uk/trisomy-18\n" +
                        "3. Meyer et al, \"Survival of children with trisomy 13 and trisomy 18: A multi-state population-based study\", Am J Med Genet A 2016 Apr;170A(4):825-37",
                edwards_syndrome_trisomy_18_img

        )));




        mCardList.add(new MainRecyclerLettering("F"));

        //fragile_x_syndrome

        List<String> fragile_x_syndrome_keywords_tmp = Arrays.asList(
                "εύθραυστο Χ χρωμόσωμα", "χρωμόσωμα χ", "χρωμόσωμα",  "Fragile X ","Fragile" ,"Q99.2", "908",
                "Νοητική υστέρηση ", "ΔΕΠΥ", "Αυτισμός ", "Επιληψία",
                "Υποτονία ", "σπαστικότητα", "Χειλεοσχιστία", "Κώφωση",
                "λοιμώξεις αυτιού", "ρυθμός", "φωνολογία", "απλοποίηση",
                "επιτονισμός", "φωνή", "τραυλισμός", "ταχυλαλία", "μορφολογία",
                "σύνταξη", "πραγματολογία", "αφήγηση"

        );
        ArrayList<String> fragile_x_syndrome_keywords = new ArrayList<>(fragile_x_syndrome_keywords_tmp);

        ArrayList<Integer> fragile_x_syndrome_img = new ArrayList<>();
        fragile_x_syndrome_img.add(R.drawable.fragile_x_1);

        mCardList.add(new MainRecyclerButton("Fragile X syndrome",fragile_x_syndrome_keywords,new SyndromeData(
                "Σύνδρομο εύθραυστου Χ χρωμοσώματος (Fragile X syndrome)",
                R.raw.fragile_x_syndrome,"ICD-10: Q99.2\n" +
                "ORPHA: 908\n",
                "Βιβλιογραφία (Σύνδρομο εύθραυστου Χ χρωμοσώματος)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/fragile-x-syndrome/\n" +
                        "2. Elizabeth Barnes et al. Phonological Accuracy and Intelligibility in Connected Speech of Boys with Fragile X Syndrome or Down Syndrome. J Speech Lang\n" +
                        "Hear Res. 2009 Aug; 52(4): 1048–1061.\n" +
                        "3. Bruno Estigarribia et al. Expressive morphosyntax in boys with Fragile X syndrome with and without autism spectrum disorder. Int J Lang Commun Disord.\n" +
                        "2011 Mar-Apr; 46(2): 216–230.\n" +
                        "4. Abigail L. Hogan-Brown et al. An Investigation of Narrative Ability in Boys with Autism and Fragile X Syndrome. Am J Intellect Dev Disabil. 2013 Mar; 118(2):\n" +
                        "77–94.\n" +
                        "5. Wilmar Saldarriaga et al. Fragile X syndrome. Colomb Med (Cali). 2014 Oct-Dec; 45(4): 190–198.",
                fragile_x_syndrome_img

        )));

        mCardList.add(new MainRecyclerLettering("G"));

        //gaucher_disease

        List<String> gaucher_disease_keywords_tmp = Arrays.asList(
                "E75.2A", "355", "Gaucher", "κόπωση", "επιληψία", "άνοια ", "μικροκεφαλία ", "Parkinson", "αταξία", "δυσφαγία ", "εισρόφηση", " πνευμονία", "απομύζηση"

        );
        ArrayList<String> gaucher_disease_keywords = new ArrayList<>(gaucher_disease_keywords_tmp);

        ArrayList<Integer> gaucher_disease_img = new ArrayList<>();
        gaucher_disease_img.add(R.drawable.gaucher_1);
        mCardList.add(new MainRecyclerButton("Gaucher disease",gaucher_disease_keywords,new SyndromeData(
                "Ασθένεια Gaucher (Gaucher Disease)",
                R.raw.gaucher_disease,"CD-10: E75.2A\nORPHA: 355\n",
                "Βιβλιογραφία (Ασθένεια Gaucher)\n" +
                        "1. Karin Weiss et al., \"The Clinical Management of Type 2 Gaucher Disease.\", 2014\n" +
                        "2. Jérôme Stirnemann et al., \"A Review of Gaucher Disease Pathophysiology, Clinical Presentation and Treatments\", Int J Mol Sci., 2017\n" +
                        "3. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/gaucher-disease/",
                gaucher_disease_img

        )));

        //glut1_deficiency_syndrome

        List<String> glut1_deficiency_syndrome_keywords_tmp = Arrays.asList(

                "G93.4", "71277", "GLUT 1", "Μικροκεφαλία", "Επιληψία", "αστάθεια", "χορεία", "μυϊκή ένταση ", "Δυσαρθρία",
                "Αταξία", "σπαστικότητα", "Νοητική έκπτωση", "προσωδία", "Παλινδρόμηση ",
                "Ανεπάρκεια πρωτεΐνης μεταφορέα γλυκόζης τύπου 1" , "πρωτεΐνη μεταφορέα γλυκόζης τύπου 1"
        );
        ArrayList<String> glut1_deficiency_syndrome_keywords = new ArrayList<>(glut1_deficiency_syndrome_keywords_tmp);

        ArrayList<Integer> glut1_deficiency_syndrome_img = new ArrayList<>();
        mCardList.add(new MainRecyclerButton("GLUT 1-deficiency syndrome",glut1_deficiency_syndrome_keywords,new SyndromeData(
                "Ανεπάρκεια πρωτεΐνης μεταφορέα γλυκόζης τύπου 1\n" +
                        "(Glucose transporter protein type 1-deficiency- GLUT 1-deficiency syndrome)",
                R.raw.glut1_deficiency_syndrome,"ICD-10: G93.4\n" +
                "ORPHA: 71277\n",
                "Βιβλιογραφία (Ανεπάρκεια GLUT1)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/huntingtons-disease/\n" +
                        "2. N. E. Carlozzi et al.,\"HDQLIFE: The development of two new computer adaptive tests for use in Huntington disease, Speech Difficulties and Swallowing\",\n" +
                        "Qual Life Res. 2016\n" +
                        "3. Lena Hartelius et al., \" Speech disorders in mild and moderate Huntington disease: results of dysarthria assessments of 19 individuals\", Delmar Learning,\n" +
                        "2003\n" +
                        "4. Heemskerk AW, Roos RA., \"Dysphagia in Huntington's disease: a review.\", Epub 2010\n" +
                        "5. Jan Rusz et al.,\" Phonatory Dysfunction as a Preclinical Symptom of Huntington Disease\", PLoS One. 2014",
                glut1_deficiency_syndrome_img

        )));

        //goldenhar_syndrome_hemifacial_microsomia

        List<String> goldenhar_syndrome_hemifacial_microsomia_keywords_tmp = Arrays.asList(
                "Goldenhar", "Ημιπροσωπική μικροσωμία ", "Q87.0D", "374", "Υποπλασία",
                "Ασυμμετρία προσώπου", "Χειλεοσχιστία", "υπερωιοσχιστία", "βαρηκοΐα", "Μικρωτία",
                "Ανωτία", "αναπνοή", "μαλακή υπερώα", "σίτιση", "απομύζηση", "δυσφαγία"

        );
        ArrayList<String> goldenhar_syndrome_hemifacial_microsomia_keywords = new ArrayList<>(goldenhar_syndrome_hemifacial_microsomia_keywords_tmp);

        ArrayList<Integer> goldenhar_syndrome_hemifacial_microsomia_img = new ArrayList<>();
        goldenhar_syndrome_hemifacial_microsomia_img.add(R.drawable.goldenhar_1);
        goldenhar_syndrome_hemifacial_microsomia_img.add(R.drawable.goldenhar_2);
        mCardList.add(new MainRecyclerButton("Goldenhar syndrome",goldenhar_syndrome_hemifacial_microsomia_keywords,new SyndromeData(
                "Σύνδρομο Goldenhar-Ημιπροσωπική μικροσωμία (Goldenhar syndrome- hemifacial microsomia)",
                R.raw.goldenhar_syndrome_hemifacial_microsomia,"ICD-10: Q87.0D\n" +
                "ORPHA: 374\n",
                "Βιβλιογραφία (Σύνδρομο Goldenhar)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/goldenhar-syndrome/\n" +
                        "2. https://craniofacial.org/el/content/%CF%83%CF%8D%CE%BD%CE%B4%CF%81%CE%BF%CE%BC%CE%BF-goldenhar\n" +
                        "3. Hercílio MARTELLI-JÚNIOR et al \" Goldenhar syndrome: clinical features with orofacial emphasis\" , J Appl Oral Sci. 2010 Nov-Dec; 18(6): 646–649.\n" +
                        "4. Vinay C, Reddy R S, Uloopi K S, Madhuri V, Sekhar R C. Craniofacial features in Goldenhar syndrome. J Indian Soc Pedod Prev Dent 2009;27:121-4\n" +
                        "5. Pricila Sleifer et al. Audiological Findings in Patients with Oculo-Auriculo-Vertebral Spectrum. Int Arch Otorhinolaryngol. 2015 Jan; 19(1): 5–9.",
                goldenhar_syndrome_hemifacial_microsomia_img

        )));

        mCardList.add(new MainRecyclerLettering("H"));

        //holoprosencephaly

        List<String> holoprosencephaly_keywords_tmp = Arrays.asList(
                "Q04.2", "2162", "ολοπροσεγκεφαλία", "Holoprosencephaly", "νοητική υστέρηση", "μικροκεφαλία", "χειλεοσχιστία", "ημιπροσωπική υποπλασία", "υπερωιοσχιστία"


        );
        ArrayList<String> holoprosencephaly_keywords = new ArrayList<>(holoprosencephaly_keywords_tmp);

        ArrayList<Integer> holoprosencephaly_img = new ArrayList<>();
        holoprosencephaly_img.add(R.drawable.holoproscencephaly_1);
        holoprosencephaly_img.add(R.drawable.holoproscencephaly_2);
        mCardList.add(new MainRecyclerButton("Holoprosencephaly",holoprosencephaly_keywords,new SyndromeData(
                "Ολοπροσεγκεφαλία (Holoprosencephaly)",
                R.raw.holoprosencephaly,"ICD-10: Q04.2\nORPHA: 2162\n",
                "Βιβλιογραφία (Ολοπροσεγκεφαλία)\n" +
                        "1. John M. Graham, Glenis K. Scadding, Peter D. Bull, Eitors, Pediatric ENT, 2007.\n" +
                        "2. https://www.genlab.gr/oloprosegkefalia/\n" +
                        "3. https://emvriomitriki.gr/egkymosynh/evryikes-anwmalies/kentriko-neyriko-systhma/oloprosegkefalia\n" +
                        "4. Manu S Raam et al., \"Holoprosencephaly: A Guide to Diagnosis and Clinical Management\", Indian Pediatr. 2011\n" +
                        "5. Paul Kruszka and Maximilian Muenke ,\"Syndromes Associated with Holoprosencephaly\", Am J Med Genet C Semin Med Genet. 2018",
                holoprosencephaly_img

        )));

        //hunter_syndrome

        List<String> hunter_syndrome_keywords_tmp = Arrays.asList(
                "Hunter ", "MPS II", "E76.1", "580", "Υδροκεφαλία", "Γνωστικά ελλείμματα",
                "Καθυστερημένη νοητική ανάπτυξη", "Μεγάλη γλώσσα", "Απώλεια ακοής", "βαρηκοΐα", "Άρθρωση ", "ρινοφάρυγγας" , "Mucopolysaccharidosis II" , "MPS II"

        );
        ArrayList<String> hunter_syndrome_keywords = new ArrayList<>(hunter_syndrome_keywords_tmp);

        ArrayList<Integer> hunter_syndrome_img = new ArrayList<>();
        hunter_syndrome_img.add(R.drawable.hunter_1);
        hunter_syndrome_img.add(R.drawable.hunter_2);
        mCardList.add(new MainRecyclerButton("Hunter syndrome",hunter_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Hunter (Hunter syndrome) - Mucopolysaccharidosis II (MPS II)",
                R.raw.hunter_syndrome,"ICD-10: E76.1\n" +
                "ORPHA: 580\n",
                "Βιβλιογραφία (Σύνδρομο Hunter)",
                hunter_syndrome_img

        )));

        //huntington_disease_huntington_chorea

        List<String> huntington_disease_huntington_chorea_keywords_tmp = Arrays.asList(
                "Huntington", "G10", "399", "ισορροπία", "αναπνοή", "Γνωστικά ελλείμματα", "έκφραση", "Υπερκινητική δυσαρθρία ", "διαδοχοκίνηση", "Προσωδία ",
                "Μονοτονία", "Φώνηση", "φωνή", "ρυθμός", "ασάφεια", "άρθρωση", "δυσφαγία", "χορεία" , "chorea"


        );
        ArrayList<String> huntington_disease_huntington_chorea_keywords = new ArrayList<>(huntington_disease_huntington_chorea_keywords_tmp);

        ArrayList<Integer> huntington_disease_huntington_chorea_img = new ArrayList<>();
        huntington_disease_huntington_chorea_img.add(R.drawable.huntington_1);
        huntington_disease_huntington_chorea_img.add(R.drawable.huntington_2);
        mCardList.add(new MainRecyclerButton("Huntington disease",huntington_disease_huntington_chorea_keywords,new SyndromeData(
                "Ασθένεια Huntington-Χορεία Huntington\n" +
                        "(Huntington disease-Huntington chorea)",
                R.raw.huntington_disease_huntington_chorea,"ICD-10: G10\n" +
                "ORPHA: 399\n",
                "Βιβλιογραφία (Ασθένεια Huntington)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/huntingtons-disease/\n" +
                        "2. N. E. Carlozzi et al., \"HDQLIFE: The development of two new computer adaptive tests for use in Huntington disease, Speech Difficulties and Swallowing\",\n" +
                        "Qual Life Res. 2016\n" +
                        "3. Lena Hartelius et al.,\" Speech disorders in mild and moderate Huntington disease: results of dysarthria assessments of 19 individuals\", Delmar Learning,\n" +
                        "2003\n" +
                        "4. Heemskerk AW, Roos RA., \"Dysphagia in Huntington's disease: a review.\", Epub 2010\n" +
                        "5. Jan Rusz et al.,\" Phonatory Dysfunction as a Preclinical Symptom of Huntington Disease\", PLoS One. 2014",
                huntington_disease_huntington_chorea_img

        )));

        //hurler_syndrome

        List<String> hurler_syndrome_keywords_tmp = Arrays.asList(
                "Hurler ", "MPS I", "E76.0", "93473", "Γνωστική έκπτωση", "Αναπνοή", "Μακρογλωσσία", "Υδροκεφαλία",
                "Εγκεφαλική ατροφία", "καθυστέρηση ομιλίας", "ωτίτιδα ", "βαρηκοΐα ", "δυσφαγία", "παλινδρόμηση", "Εισρόφηση", "Mucopolysaccharidosis I"
        );
        ArrayList<String> hurler_syndrome_keywords = new ArrayList<>(hurler_syndrome_keywords_tmp);

        ArrayList<Integer> hurler_syndrome_img = new ArrayList<>();
        hurler_syndrome_img.add(R.drawable.hurler_1);
        hurler_syndrome_img.add(R.drawable.hurler_2);
        mCardList.add(new MainRecyclerButton("Hurler syndrome",hurler_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Hurler (Hurler syndrome) - Mucopolysaccharidosis I (MPS I)",
                R.raw.hurler_syndrome,"ICD-10: E76.0\n" +
                "ORPHA: 93473\n",
                "Βιβλιογραφία (Σύνδρομο Hurler)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/hurler-syndrome/",
                hurler_syndrome_img

        )));

        mCardList.add(new MainRecyclerLettering("I"));

        mCardList.add(new MainRecyclerLettering("J"));

        //jacobsen_syndrome

        List<String> jacobsen_syndrome_keywords_tmp = Arrays.asList(
                "Jacobsen ", "Q93.5", "2308", "Νοητική υστέρηση ", "ΔΕΠΥ", "μικροκεφαλία",
                "Ασυμμετρία προσώπου", "μαλακή υπερώα", "Βαρηκοΐα", "Καθυστέρηση ομιλίας",
                "Γνωστικά ελλείμματα", "Μαθησιακές δυσκολίες"

        );
        ArrayList<String> jacobsen_syndrome_keywords = new ArrayList<>(jacobsen_syndrome_keywords_tmp);

        ArrayList<Integer> jacobsen_syndrome_img = new ArrayList<>();
        jacobsen_syndrome_img.add(R.drawable.jacobsen_1);
        mCardList.add(new MainRecyclerButton("Jacobsen syndrome",jacobsen_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Jacobsen (Jacobsen syndrome)",
                R.raw.jacobsen_syndrome,"ICD-10: Q93.5\n" +
                "ORPHA:2308\n",
                "Βιβλιογραφία (Σύνδρομο Jacobsen)\n" +
                        "1. Teresa Mattina et al., \" Jacobsen syndrome\" Orphanet J Rare Dis. 2009; 4: 9.\n" +
                        "2. https://rarediseases.info.nih.gov/diseases/307/jacobsen-syndrome",
                jacobsen_syndrome_img

        )));


        //joubert_syndrome

        List<String> joubert_syndrome_keywords_tmp = Arrays.asList(

                "Joubert ", "G71.2", "607", "Γνωστική έκπτωση", "Αταξία", "Νοητική υστέρηση ",
                "Υποτονία", "αναπνοή", "Επιληψία", "δυσπραξία", "απραξία", "ασάφεια", "απομύζηση",
                "Σιελόρροια", "γαστροστομία", "δυσφαγία"

        );
        ArrayList<String> joubert_syndrome_keywords = new ArrayList<>(joubert_syndrome_keywords_tmp);

        ArrayList<Integer> joubert_syndrome_img = new ArrayList<>();
        joubert_syndrome_img.add(R.drawable.jouber_1);
        mCardList.add(new MainRecyclerButton("Joubert syndrome",joubert_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Joubert (Joubert syndrome)",
                R.raw.joubert_syndrome,"ICD-10: G71.2\n" +
                "ORPHA: 607\n",
                "Βιβλιογραφία (Σύνδρομο Joubert)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/joubert-syndrome/\n" +
                        "2. Melissa A. Parisi \" Clinical and molecular features of Joubert syndrome and related disorders\" Am J Med Genet C Semin Med Genet. 2009 Nov 15; 151C(4):\n" +
                        "326–340.\n" +
                        "3. Ahmed Farag Elhassanien and Hesham Abdel-Aziz Alghaiaty \" Joubert syndrome: Clinical and radiological characteristics of nine patients\" Ann Indian Acad\n" +
                        "Neurol. 2013 Apr-Jun; 16(2): 239–244.",
                joubert_syndrome_img

        )));


        mCardList.add(new MainRecyclerLettering("K"));

        //kabuki_syndrome

        List<String> kabuki_syndrome_keywords_tmp = Arrays.asList(

                "Kabuki ", "Q87.8", "2322", "Νοητική υστέρηση ", "Αναπνοή",
                "αυτισμός", "Pierre Robin", "Επιληψία", "μαλακή υπερώα", "Χειλεοσχιστία",
                "υπερωιοσχιστία", "Δισχιδής γλώσσα", "Μικρογναθία", "Μικροκεφαλία", "βαρηκοΐα", "ωτίτιδα",
                "Καθυστέρηση ομιλίας", "κατανόηση", "παραγωγή", "Σύνταξη", "Ασάφεια", "παλινδρόμηση", "γαστροστομία ",
                "δυσφαγία"

        );
        ArrayList<String> kabuki_syndrome_keywords = new ArrayList<>(kabuki_syndrome_keywords_tmp);

        ArrayList<Integer> kabuki_syndrome_img = new ArrayList<>();
        kabuki_syndrome_img.add(R.drawable.kabuki);
        mCardList.add(new MainRecyclerButton("Kabuki syndrome",kabuki_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Kabuki (Kabuki syndrome)",
                R.raw.kabuki_syndrome,"ICD-10: Q87.8\n" +
                "ORPHA: 2322\n",
                "Βιβλιογραφία (Σύνδρομο Kabuki)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/kabuki-syndrome/\n" +
                        "2. Natália Silva-Andrade et al., \" Orofacial features and medical profile of eight individuals with Kabuki syndrome\" Med Oral Patol Oral Cir Bucal. 2019 Sep;\n" +
                        "24(5): e630–e635",
                kabuki_syndrome_img
        )));

        //kallmann_syndrome

        List<String> kallmann_syndrome_keywords_tmp = Arrays.asList(
                "Kallmann ", "E23.0", "478", "Νοητική υστέρηση", "υποτονία", "αυτισμός",
                "Επιληψία", "Βραχυκεφαλία", "μαλακή υπερώα", "Υποπλασία", "Μικρογναθία",
                "άρθρωση", "δυσφαγία", "απομύζηση "

        );
        ArrayList<String> kallmann_syndrome_keywords = new ArrayList<>(kallmann_syndrome_keywords_tmp);

        ArrayList<Integer> kallmann_syndrome_img = new ArrayList<>();
        mCardList.add(new MainRecyclerButton("Kallmann syndrome",kallmann_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Kallmann (Kallmann syndrome)",
                R.raw.kallmann_syndrome,"ICD-10: E23.0\n" +
                "ORPHA:478\n",
                "Βιβλιογραφία (Σύνδρομο Kallmann)\n" +
                        "1. Maria I. Stamou and Neoklis A. Georgopoulos \" Kallmann syndrome: phenotype and genotype of hypogonadotropic hypogonadism\" Metabolism. Author\n" +
                        "manuscript; available in PMC 2018 Sep 1.",
                kallmann_syndrome_img
        )));

        //krabbe_disease

        List<String> krabbe_disease_keywords_tmp = Arrays.asList(
                "E75.2", "487", "Krabbe", "σπαστικότητα", "υποτονία", "απώλεια ακοής", "αναπνοή", "Επιληψία", "Πρόωρος θάνατος", "Παλινδρόμηση ", "εισρόφηση", "γαστροστομία ", "δυσφαγία"

        );
        ArrayList<String> krabbe_disease_keywords = new ArrayList<>(krabbe_disease_keywords_tmp);

        ArrayList<Integer> krabbe_disease_img = new ArrayList<>();
        krabbe_disease_img.add(R.drawable.krabbe_1);
        krabbe_disease_img.add(R.drawable.krabbe_2);
        mCardList.add(new MainRecyclerButton("Krabbe disease",krabbe_disease_keywords,new SyndromeData(
                "Ασθένεια Krabbe (Krabbe disease)",
                R.raw.krabbe_disease,"ICD-10: E75.2\n" +
                "ORPHA: 487\n",
                "Βιβλιογραφία (Ασθένεια Krabbe)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/krabbe-disease/\n" +
                        "2. Evangelia Dimitriou et al., \"The Spectrum of Krabbe Disease in Greece: Biochemical and Molecular Findings\", JIMD Rep. 2016\n" +
                        "3. Maria L. Beltran-Quintero et al., \"Early progression of Krabbe disease in patients with symptom onset between 0 and 5 months.\", Orphanet J Rare Dis. 2019",
                krabbe_disease_img

        )));


        mCardList.add(new MainRecyclerLettering("L"));

        //lesch_nyhan_syndrome

        List<String> lesch_nyhan_syndrome_keywords_tmp = Arrays.asList(
                "E79.1", "510", "Lesch-Nyhan", "Lesch","Nyhan", "Lesch Nyhan", "δυστονία", "σπαστικότητα", "Αταξία", "Νοητική υστέρηση", "Επιληψία", "Αυτισμός", "αναπνοή", "Καθυστέρηση ομιλίας", "Δυσαρθρία",
                "ένταση", "φωνή", "τραυλισμός", "δυσφωνία", "Γαστροστομία", "Δυσφαγία", "παλινδρόμηση", "δυσφαγία"
        );
        ArrayList<String> lesch_nyhan_syndrome_keywords = new ArrayList<>(lesch_nyhan_syndrome_keywords_tmp);

        ArrayList<Integer> lesch_nyhan_syndrome_img = new ArrayList<>();
        lesch_nyhan_syndrome_img.add(R.drawable.lesch_1);
        mCardList.add(new MainRecyclerButton("Lesch-Nyhan syndrome",lesch_nyhan_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Lesch-Nyhan (Lesch-Nyhan syndrome)",
                R.raw.lesch_nyhan_syndrome,"ICD-10: E79.1\n" +
                "ORPHA:510\n",
                "Βιβλιογραφία (Σύνδρομο Lesch-Nyhan)\n" +
                        "1. Alison Christy et al. \" Severe Respiratory Acidosis in Status Epilepticus as a Possible Etiology of Sudden Death in Lesch–Nyhan Disease: A Case Report and\n" +
                        "Review of the Literature\" JIMD Rep. 2017; 35: 23–28.\n" +
                        "2. H. A. Jinnah et al. \" Delineation of the motor disorder of Lesch–Nyhan disease\" Brain. 2006 May; 129(Pt 5): 1201–1217.\n" +
                        "3. Rosa J Torres and Juan G Puig \" Hypoxanthine-guanine phosophoribosyltransferase (HPRT) deficiency: Lesch-Nyhan syndrome\" Orphanet J Rare Dis. 2007;\n" +
                        "2: 48.",
                lesch_nyhan_syndrome_img

        )));

        //lchad_deficiency

        List<String> lchad_deficiency_keywords_tmp = Arrays.asList(
                "E71.3", "5", "LCHAD", "Υποτονία", "Γαστροστομία", "καθυστέρηση ομιλίας", "αντανακλαστικό εξ εμέσεως"


        );
        ArrayList<String> lchad_deficiency_keywords = new ArrayList<>(lchad_deficiency_keywords_tmp);

        ArrayList<Integer> lchad_deficiency_img = new ArrayList<>();
        mCardList.add(new MainRecyclerButton("LCHAD deficiency",lchad_deficiency_keywords,new SyndromeData(
                "Ανεπάρκεια LCHAD (LCHAD deficiency)",
                R.raw.lchad_deficiency,"ICD-10: E71.3\n" +
                "ORPHA: 5\n",
                "Βιβλιογραφία (Ανεπάρκεια LCHAD)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/lchad-deficiency/",
                lchad_deficiency_img

        )));



        mCardList.add(new MainRecyclerLettering("M"));

        //marker_chromosome_15_syndrome

        List<String> marker_chromosome_15_syndrome_keywords_tmp = Arrays.asList(
                "Σύνδρομο χρωμοσωμικού δείκτη 15", "Marker chromosome 15", "Q99.8",
                "3306", "Νοητική υστέρηση", "υποτονία", "αυτισμός", "Επιληψία", "Βραχυκεφαλία",
                "μαλακή υπερώα", "Μικρογναθία", "άρθρωση", "απομύζηση", "δυσφαγία"

        );
        ArrayList<String> marker_chromosome_15_syndrome_keywords = new ArrayList<>(marker_chromosome_15_syndrome_keywords_tmp);

        ArrayList<Integer> marker_chromosome_15_syndrome_img = new ArrayList<>();
        marker_chromosome_15_syndrome_img.add(R.drawable.marker_1);
        mCardList.add(new MainRecyclerButton("Marker chromosome 15 syndrome",marker_chromosome_15_syndrome_keywords,new SyndromeData(
                "Σύνδρομο χρωμοσωμικού δείκτη 15 (Marker chromosome 15 syndrome)",
                R.raw.marker_chromosome_15_syndrome,"ICD-10: Q99.8\n" +
                "ORPHA: 3306\n",
                "Βιβλιογραφία (Σύνδρομο χρωμοσωμικού δείκτη 15)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/marker-chromosome-15-syndrome/\n" +
                        "2. Altuğ Koç et al. \" Supernumerary marker chromosome 15 in a male with azoospermia and open bite deformity\" Asian J Androl. 2009 Sep; 11(5): 617–622.",
                marker_chromosome_15_syndrome_img

        )));

        //maroteaux_lamy_syndrome

        List<String> maroteaux_lamy_syndrome_keywords_tmp = Arrays.asList(
                "Maroteaux Lamy ", "MPS VI", "E76.2", "583", "Αναπνοή", "Υδροκεφαλία", "βαρηκοΐα", "Κώφωση", "λοιμώξεις αυτιού" ,"Mucopolysaccharidosis VI" , "MPS VI"

        );
        ArrayList<String> maroteaux_lamy_syndrome_keywords = new ArrayList<>(maroteaux_lamy_syndrome_keywords_tmp);

        ArrayList<Integer> maroteaux_lamy_syndrome_img = new ArrayList<>();
        maroteaux_lamy_syndrome_img.add(R.drawable.maroteaux_1);
        maroteaux_lamy_syndrome_img.add(R.drawable.maroteaux_2);
        mCardList.add(new MainRecyclerButton("Maroteaux Lamy syndrome",maroteaux_lamy_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Maroteaux Lamy (Maroteaux Lamy syndrome)-\n" +
                        "Mucopolysaccharidosis VI (MPS VI)",
                R.raw.maroteaux_lamy_syndrome,"ICD-10: E76.2\n" +
                "ORPHA:583\n",
                "Βιβλιογραφία (Σύνδρομο Maroteaux Lamy)\n" +
                        "1. Hansjörg Dilger, Linn Leissner, Lenka Bosanska, Christina Lampe, and Ursula Plöckinger , \" Illness Perception and Clinical Treatment Experiences in Patients with\n" +
                        "M. Maroteaux-Lamy (Mucopolysaccharidosis Type VI) and a Turkish Migration Background in Germany\" , PLoS One. 2013\n" +
                        "2. Mehmet Umut Akyol, Tord D. Alden, Hernan Amartino, Jane Ashworth, Kumar Belani, Kenneth I. Berger, Andrea Borgo, Elizabeth Braunlin, Yoshikatsu Eto, Jeffrey I.\n" +
                        "Gold, Andrea Jester, Simon A. Jones, Cengiz Karsli, William Mackenzie, Diane Ruschel Marinho, Andrew McFadyen, Jim McGill, John J. Mitchell, Joseph\n" +
                        "Muenzer, Torayuki Okuyama, Paul J. Orchard, Bob Stevens, Sophie Thomas, Robert Walker, Robert Wynn, Roberto Giugliani, Paul Harmatz,Christian\n" +
                        "Hendriksz, Maurizio Scarpa, MPS Consensus Programme Steering Committee, and MPS Consensus Programme Co-Chairs, \" Recommendations for the\n" +
                        "management of MPS VI: systematic evidence- and consensus-based guidance\" , Orphanet J Rare Dis. 2019",
                maroteaux_lamy_syndrome_img

        )));

        //miller_dieker_syndrome

        List<String> miller_dieker_syndrome_keywords_tmp = Arrays.asList(

                "Miller Dieker", "Λυσεγκεφαλία ", "Lissencephaly", "Q04.3", "531",
                "Εγκεφαλική παράλυση", "υποτονία", "επιληψία", "αυτισμός",
                "Μικρογναθία", "Καθυστέρηση ομιλίας", "Αναπνοή", "ΔΕΠΥ",
                "Γνωστικά ελλείμματα", "Σιελόρροια", "Σίτιση ", "δυσφαγία"

        );
        ArrayList<String> miller_dieker_syndrome_keywords = new ArrayList<>(miller_dieker_syndrome_keywords_tmp);

        ArrayList<Integer> miller_dieker_syndrome_img = new ArrayList<>();
        miller_dieker_syndrome_img.add(R.drawable.miller_1);
        mCardList.add(new MainRecyclerButton("Miller Dieker syndrome",miller_dieker_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Miller Dieker - Λυσεγκεφαλία (Miller Dieker syndrome - Lissencephaly)",
                R.raw.miller_dieker_syndrome,"ICD-10: Q04.3\n" +
                "ORPHA:531\n",
                "Βιβλιογραφία (Σύνδρομο Miller Dieker)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/lissencefali/\n" +
                        "2. Young Jin Kim et al. \" Miller-Dieker Syndrome with der(17)t(12;17)(q24.33;p13.3)pat Presenting with a Potential Risk of Mis-identification as a de\n" +
                        "novo Submicroscopic Deletion of 17p13.3\" Korean J Lab Med. 2011 Jan; 31(1): 49–53.\n" +
                        "3. Linda Mahgoub et al. \" Miller–Dieker Syndrome Associated with Congenital Lobar Emphysema\" AJP Rep. 2014 May; 4(1): 13–16.\n" +
                        "4. Valeria Capra et al. \" Identification of a rare 17p13.3 duplication including the BHLHA9 and YWHAE genes in a family with developmental delay and\n" +
                        "behavioural problems\" BMC Med Genet. 2012; 13: 93.",
                miller_dieker_syndrome_img
        )));

        //mobius_syndrome

        List<String> mobius_syndrome_keywords_tmp = Arrays.asList(
                "Möbius ", "moebius", "Q87.0W", "570", "παράλυση ", "κρανιακά νεύρα",
                "προσωπικό", "απαγωγό", "Υπογλώσσιο ", "Γλωσσοφαρυγγικό", "πνευμονογαστρικό",
                "Τρίδυμο", "Νοητική υστέρηση ", "έκφραση", "Χειλεοσχιστία", "υπερωιοσχιστία", "Μακρογλωσσία",
                "βαρηκοΐα" ,"ωτίτιδα" ,"Καθυστέρηση ", "μιλίας", "φώνηση", "κατανόηση", "Αναπνοή", "δυσφαγία",
                "απομύζηση", "Γαστροστομία"

        );
        ArrayList<String> mobius_syndrome_keywords = new ArrayList<>(mobius_syndrome_keywords_tmp);

        ArrayList<Integer> mobius_syndrome_img = new ArrayList<>();
        mobius_syndrome_img.add(R.drawable.mobius_1);
        mobius_syndrome_img.add(R.drawable.mobius_2);
        mCardList.add(new MainRecyclerButton("Möbius syndrome",mobius_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Möbius (Möbius syndrome)",
                R.raw.mobius_syndrome,"ICD-10: Q87.0W\n" +
                "ORPHA: 570\n",
                "Βιβλιογραφία (Σύνδρομο Möbius)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/mobius-syndrome/\n" +
                        "2. Zelita Caldeira Ferreira Guedes \" Möbius Syndrome: Misoprostol Use and Speech and Language Characteristics\" Int Arch Otorhinolaryngol. 2014 Jul; 18(3):\n" +
                        "239–243.\n" +
                        "3. Ann W. Kummer \"Cleft Palate and Cranioofacial Anomalies: Effects on Speech and Resonanace\", Second Edition, Delmar Cengage Learning 2008,5:138-139",
                mobius_syndrome_img

        )));

        //morquio_syndrome

        List<String> morquio_syndrome_keywords_tmp = Arrays.asList(
                "Morquio", "MPS IV", "E76.2", "582", "βαρηκοΐα", "λοιμώξεις αυτιού", "κώφωση ",
                "καθυστέρηση ομιλίας", "φώνηση", "φωνητικές χορδές", "φωνή", "Αναπνοή", "λάρυγγας", "τραχεία", "δυσφαγία" , "Mucopolysaccharidosis IV"

        );
        ArrayList<String> morquio_syndrome_keywords = new ArrayList<>(morquio_syndrome_keywords_tmp);

        ArrayList<Integer> morquio_syndrome_img = new ArrayList<>();
        morquio_syndrome_img.add(R.drawable.morquio_1);
        morquio_syndrome_img.add(R.drawable.morquio_2);
        mCardList.add(new MainRecyclerButton("Morquio syndrome",morquio_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Morquio (Morquio syndrome)-\n" +
                        "Mucopolysaccharidosis IV (MPS IV)",
                R.raw.morquio_syndrome,"ICD-10: E76.2\n" +
                "ORPHA:582\n",
                "Βιβλιογραφία (Σύνδρομο Morquio)\n" +
                        "1. Kazuki Sawamoto, José Víctor Álvarez González, Matthew Piechnik, Francisco J. Otero, Maria L. Couce, Yasuyuki Suzuki, and Shunji Tomatsu, \"\n" +
                        "Mucopolysaccharidosis IVA: Diagnosis, Treatment, and Management\", Int J Mol Sci. 2020\n" +
                        "2. Kyoko Nagao, Thierry Morlet, Elizabeth Haley, Jennifer Padilla, Julianne Nemith, Robert W. Mason, and Shunji Tomatsu, \" Neurophysiology of Hearing in Patients\n" +
                        "with Mucopolysaccharidosis type IV\", Mol Genet Metab. 2018\n" +
                        "3. Krzysztof Szklanny, Ryszard Gubrynowicz, and Anna Tylki-Szymańska, \" Voice alterations in patients with Morquio A syndrome\", J Appl Genet. 2018",
                morquio_syndrome_img

        )));

        mCardList.add(new MainRecyclerLettering("N"));

        mCardList.add(new MainRecyclerLettering("O"));

        //osteogenesis_imperfecta_brittle_bone_disease

        List<String> osteogenesis_imperfecta_brittle_bone_disease_keywords_tmp = Arrays.asList(
                "Q78.0", "666", "ατελής οστεογένεση", "κρανιακά νεύρα", "γλωσσοφαρυγγικό", "πνευμονογαστρικό", "παραπληρωματικό",
                "υπογλώσσιο", "βαρηκοΐα ", "Αναπνευστικά ", "αταξία", "αντανακλαστικό απομύζησης", "Παλινδρόμηση" , "Osteogenesis Imperfecta" , "Brittle bone disease"



        );
        ArrayList<String> osteogenesis_imperfecta_brittle_bone_disease_keywords = new ArrayList<>(osteogenesis_imperfecta_brittle_bone_disease_keywords_tmp);

        ArrayList<Integer> osteogenesis_imperfecta_brittle_bone_disease_img = new ArrayList<>();
        osteogenesis_imperfecta_brittle_bone_disease_img.add(R.drawable.osteogenesis_1);
        osteogenesis_imperfecta_brittle_bone_disease_img.add(R.drawable.osteogenesis_2);
        mCardList.add(new MainRecyclerButton("Brittle bone disease",osteogenesis_imperfecta_brittle_bone_disease_keywords,new SyndromeData(
                "Ατελής Οστεογένεση (Osteogenesis Imperfecta-Brittle bone disease)",
                R.raw.osteogenesis_imperfecta_brittle_bone_disease,"ICD-10: Q78.0\n" +
                "ORPHA:666\n",
                "Βιβλιογραφία (Ατελής Οστεογέννεση)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/osteogenesis-imperfecta/\n" +
                        "2. John M. Graham, Glenis K. Scadding, Peter D. Bull, Eitors, Pediatric ENT, 2007.\n" +
                        "3. Caroline Marr, Alison Seasman,and Nick Bishop, \"Managing the patient with osteogenesis imperfecta: a multidisciplinary approach\", J Multidiscip Healthc.\n" +
                        "2017\n" +
                        "4. Joseph P. Pillion, David Vernick, and Jay Shapiro,\" Hearing Loss in Osteogenesis Imperfecta: Characteristics and Treatment Considerations\", Genet Res Int.\n" +
                        "2011",
                osteogenesis_imperfecta_brittle_bone_disease_img

        )));

        mCardList.add(new MainRecyclerLettering("P"));

        //patau_syndrome

        List<String> patau_syndrome_keywords_tmp = Arrays.asList(
                "Patau","Τρισωμία 13", "Q91.4", "Q91.5", "Q91.6", "Q91.7","3378", "αναπνοή","Επιληψία","Γνωστικά ελλείμματα", "Νοητική υστέρηση", "Ασυμμετρία προσώπου", "Χειλεοσχιστία",
                "υπερωιοσχιστία", "Δισχιδής γλώσσα", "Μικρογναθία", "Μικροκεφαλία", "Υψηλή υπερώα", "Βαρηκοΐα", "Καθυστέρηση ομιλίας", "άρθρωση", "φωνολογία", "απομύζηση", "δυσφαγία",
                "Trisomy 13"

        );
        ArrayList<String> patau_syndrome_keywords = new ArrayList<>(patau_syndrome_keywords_tmp);

        ArrayList<Integer> patau_syndrome_img = new ArrayList<>();
        patau_syndrome_img.add(R.drawable.patau_1);
        patau_syndrome_img.add(R.drawable.patau_2);
        patau_syndrome_img.add(R.drawable.patau_3);
        mCardList.add(new MainRecyclerButton("Patau syndrome",patau_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Patau - Τρισωμία 13 (Patau syndrome - Trisomy 13)",
                R.raw.patau_syndrome,"ICD-10: Q91.4 Q91.5 Q91.6 Q91.7\n" +
                "ORPHA:3378\n",
                "Βιβλιογραφία (Σύνδρομο Patau)\n" +
                        "1. Harry Pachajoa and Luis Enrique Meza Escobar \" Mosaic trisomy 13 and a sacral appendage\" BMJ Case Rep. 2013\n" +
                        "2. Fulesh Kunwar et al. \" Constitutional Mosaic Trisomy 13 in Two Germ Cell Layers is Different from Patau Syndrome? A Case Report\" J Clin Diagn Res. 2016\n" +
                        "Mar; 10(3): GD03–GD05.",
                patau_syndrome_img

        )));

        //pierre_robin_sequence

        List<String> pierre_robin_sequence_keywords_tmp = Arrays.asList(
                "Pierre Robin", "Q87.0", "718", "υπερωιοσχιστία", "μικρογναθία", "βαρηκοΐα", "αναπνοή", "δυσφαγία", "παλινδρόμηση", "ρινική αναρροή", "απώλεια βάρους"
                , "Ακολουθία Pierre Robin"
        );
        ArrayList<String> pierre_robin_sequence_keywords = new ArrayList<>(pierre_robin_sequence_keywords_tmp);

        ArrayList<Integer> pierre_robin_sequence_img = new ArrayList<>();
        pierre_robin_sequence_img.add(R.drawable.pierre_robin_1);
        pierre_robin_sequence_img.add(R.drawable.pierre_robin_2);
        mCardList.add(new MainRecyclerButton("Pierre Robin sequence",pierre_robin_sequence_keywords,new SyndromeData(
                "Ακολουθία Pierre Robin (Pierre Robin Sequence)",
                R.raw.pierre_robin_sequence,"ICD-10: Q87.0\nORPHA:718\n",
                "Βιβλιογραφία (Ακολουθία Pierre Robin)\n" +
                        "1. https://en.wikipedia.org/wiki/Pierre_Robin_sequence#cite_note-Jakobsen2006-6\n" +
                        "2. John M. Graham, Glenis K. Scadding, Peter D. Bull, Eitors, Pediatric ENT, 2007.\n" +
                        "3. Anne Morice et al., \" Severity of Retrognathia and Glossoptosis Does Not Predict Respiratory and Feeding Disorders in Pierre Robin Sequence\", Front\n" +
                        "Pediatr., 2018.\n" +
                        "4. Noopur Gangopadhyay et al., \"Pierre Robin Sequence\", Semin Plast Surg., 2012",
                pierre_robin_sequence_img

        )));

        //pitt_hopkins_syndrome

        List<String> pitt_hopkins_syndrome_keywords_tmp = Arrays.asList(
                "Pitt-Hopkins ", "Q87.0", "2896", "Νοητική υστέρηση", "Επιληψία",
                "Υποτονία", "Αυτισμός ", "Μικροκεφαλία", "Καθυστέρηση ομιλίας", "αναπνοή",
                "απομύζηση", "δυσφαγία", "Σιελόρροια"

        );
        ArrayList<String> pitt_hopkins_syndrome_keywords = new ArrayList<>(pitt_hopkins_syndrome_keywords_tmp);

        ArrayList<Integer> pitt_hopkins_syndrome_img = new ArrayList<>();
        pitt_hopkins_syndrome_img.add(R.drawable.pitt_1);
        pitt_hopkins_syndrome_img.add(R.drawable.pitt_2);
        mCardList.add(new MainRecyclerButton("Pitt-Hopkins syndrome",pitt_hopkins_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Pitt-Hopkins (Pitt-Hopkins syndrome)",
                R.raw.pitt_hopkins_syndrome,"ICD-10: Q87.0\n" +
                "ORPHA: 2896\n",
                "Βιβλιογραφία (Σύνδρομο Pitt-Hopkins)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/pit-hopkins-syndrome/\n" +
                        "2. Jorge Arturo Avina Fierroa, and Daniel Alejandro Hernandez Avina \" Pitt-Hopkins syndrome: Mental retardation, psychomotor and developmental delays\n" +
                        "with facial dysmorphism\" Journal of Pediatric Genetics 3 (2014) 141–145.\n" +
                        "3. Alice Watkins et al. \" Behavioural and psychological characteristics in Pitt-Hopkins syndrome: a comparison with Angelman and Cornelia de Lange\n" +
                        "syndromes\" J Neurodev Disord. 2019; 11: 24.",
                pitt_hopkins_syndrome_img

        )));

        //potocki_lupski_syndrome

        List<String> potocki_lupski_syndrome_keywords_tmp = Arrays.asList(
                "Potocki-Lupski ", "Q99.8", "1713", "Νοητική υστέρηση ", "Αυτισμός", "ΔΕΠΥ",
                "Υπερκινητικότητα", "Επιληψία", "υποτονία", "Μικρογναθία", "υπερωιοσχιστία",
                "μαλακή υπερώα", "Καθυστέρηση ομιλίας", "Μαθησιακές δυσκολίες", "απομύζηση", "Κόπωση",
                "παλινδρόμηση ", "κατάποση", "υπολείμματα", "Διείσδυση", "δυσφαγία"
        );
        ArrayList<String> potocki_lupski_syndrome_keywords = new ArrayList<>(potocki_lupski_syndrome_keywords_tmp);

        ArrayList<Integer> potocki_lupski_syndrome_img = new ArrayList<>();
        potocki_lupski_syndrome_img.add(R.drawable.potocki_1);
        mCardList.add(new MainRecyclerButton("Potocki-Lupski syndrome",potocki_lupski_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Potocki-Lupski (Potocki-Lupski syndrome)",
                R.raw.potocki_lupski_syndrome,"ICD-10: Q99.8\n" +
                "ORPHA: 1713\n",
                "Βιβλιογραφία (Σύνδρομο Potocki-Lupski)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/potocki-lupski-syndrome/\n" +
                        "2. Claudia Soler-Alfonso et al. \" Potocki-Lupski Syndrome: A Microduplication Syndrome Associated with Oropharyngeal Dysphagia and Failure to Thrive\" J\n" +
                        "Pediatr. 2011 Apr; 158(4): 655–659.e2.\n" +
                        "3. A. Gulhan Ercan-Sencicek et al. \" Searching for Potocki-Lupski syndrome Phenotype: A Patient with Language Impairment and no Autism\" Brain Dev. 2012\n" +
                        "Sep; 34(8): 700–703.",
                potocki_lupski_syndrome_img

        )));

        //prader_willi_syndrome

        List<String> prader_willi_syndrome_keywords_tmp = Arrays.asList(
                "Prader-Willi ", "Prader", "Willi ", "Prader Willi ", "Q87.1F", "739", "υποτονία ", "Νοητική υστέρηση",
                "Γνωστικά ελλείμματα", "αυτισμός", "ρινοφάρυγγας", "μαλακή υπερώα",
                "Καθυστέρηση ομιλίας", "άρθρωση", "Υπερρινικότητα", "Μαθησιακές δυσκολίες",
                "απομύζηση", "Κόπωση", "Σίτιση ", "υπολείμματα", "δυσφαγία", "εισρόφηση"

        );
        ArrayList<String> prader_willi_syndrome_keywords = new ArrayList<>(prader_willi_syndrome_keywords_tmp);

        ArrayList<Integer> prader_willi_syndrome_img = new ArrayList<>();
        prader_willi_syndrome_img.add(R.drawable.prader_1);
        prader_willi_syndrome_img.add(R.drawable.prader_2);
        mCardList.add(new MainRecyclerButton("Prader-Willi syndrome",prader_willi_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Prader-Willi (Prader-Willi syndrome)",
                R.raw.prader_willi_syndrome,"ICD-10: Q87.1F\n" +
                "ORPHA: 739\n",
                "Βιβλιογραφία (Σύνδρομο Prader-Willi)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/prader-willi-syndrome/\n" +
                        "2. Kuzma Strenilkov et al. \"A study of voice and non-voice processing in Prader-Willi syndrome\" Orphanet J Rare Dis. 2020; 15: 22.\n" +
                        "3. Merlin G. Butler et al. \"Prader-Willi Syndrome - Clinical Genetics, Diagnosis and Treatment Approaches: An Update\" Curr Pediatr Rev. 2019 Nov; 15(4):\n" +
                        "207–244.\n" +
                        "4. Linda Cooper-Brown et al. \" FEEDING AND SWALLOWING DYSFUNCTION IN GENETIC SYNDROMES\" DEVELOPMENTAL DISABILITIES RESEARCH REVIEWS 14:\n" +
                        "147 – 157 (2008)",
                prader_willi_syndrome_img

        )));

        mCardList.add(new MainRecyclerLettering("Q"));


        mCardList.add(new MainRecyclerLettering("R"));

        //rett_syndrome

        List<String> rett_syndrome_keywords_tmp = Arrays.asList(
                "Rett ", "F84.2", "778", "Υποτονία", "Απραξία", "ωτίτιδα",
                "απουσία ομιλίας", "αναπνοή", "ακουστική επεξεργασία", "παλινδρόμηση",
                "Στένωση οισοφάγου", "Κόπωση ", "Εισρόφηση", "δυσφαγία"

        );
        ArrayList<String> rett_syndrome_keywords = new ArrayList<>(rett_syndrome_keywords_tmp);

        ArrayList<Integer> rett_syndrome_img = new ArrayList<>();
        rett_syndrome_img.add(R.drawable.rett_1);
        rett_syndrome_img.add(R.drawable.rett_2);
        mCardList.add(new MainRecyclerButton("Rett syndrome",rett_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Rett (Rett syndrome)",
                R.raw.rett_syndrome,"ICD-10: F84.2\n" +
                "ORPHA: 778\n",
                "Βιβλιογραφία (Σύνδρομο Rett)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/rett-syndrome/\n" +
                        "2. Cary Fu et al., \"Consensus guidelines on managing Rett syndrome across the lifespan\", BMJ Paediatr Open. 2020; 4(1): e000717.\n" +
                        "3. Helen Leonard et al., \" Assessment and management of nutrition and growth in Rett syndrome\" J Pediatr Gastroenterol Nutr. Author manuscript; available\n" +
                        "in PMC 2014 Oct 1.",
                rett_syndrome_img

        )));


        mCardList.add(new MainRecyclerLettering("S"));

        //spielmeyer_vogt_disease

        List<String> spielmeyer_vogt_disease_keywords_tmp = Arrays.asList(
                "E75.4", "79264", "Spielmeyer-Vogt", "Spielmeyer","Vogt", "Spielmeyer Vogt", "Batten", "Επιληψία", "Γνωστική έκπτωση ", "Άνοια ", "εγκεφαλική ατροφία",
                "Υποτονία", "Αταξία ", "Σπαστικότητα ", "τετραπληγία", "Παρκισονισμός ", "Αναπνοή", "Καθυστέρηση ομιλίας", "Τραυλισμός",
                "ρυθμός", "άρθρωση", "Δυσαρθρία", "ΔΕΠΥ"

        );
        ArrayList<String> spielmeyer_vogt_disease_keywords = new ArrayList<>(spielmeyer_vogt_disease_keywords_tmp);

        ArrayList<Integer> spielmeyer_vogt_disease_img = new ArrayList<>();
        spielmeyer_vogt_disease_img.add(R.drawable.spiegelmeyer_1);
        mCardList.add(new MainRecyclerButton("Spielmeyer-Vogt disease",spielmeyer_vogt_disease_keywords,new SyndromeData(
                "Ασθένεια Spielmeyer-Vogt / Ασθένεια Batten -\n" +
                        "(Spielmeyer-Vogt disease / Batten disease)",
                R.raw.spielmeyer_vogt_disease,"ICD-10: E75.4\n" +
                "ORPHA: 79264\n",
                "Βιβλιογραφία (Ασθένεια Spielmeyer-Vogt)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/spielmeyer-vogt-disease/\n" +
                        "2. Sarah-Bianca Dolisca et al., \"Batten Disease: Clinical Aspects, Molecular Mechanisms, Translational Science, and Future Directions\", J Child Neurol. 2013\n" +
                        "3. Tyler B. Johnson et al., \" Therapeutic landscape for Batten disease: current treatments and future prospects\", Nat Rev Neurol. 2019",
                spielmeyer_vogt_disease_img

        )));

        //sanfilippo_syndrome

        List<String> sanfilippo_syndrome_keywords_tmp = Arrays.asList(
                "Sanfilippo", "MPS III", "E76.2E", "581", "ΔΕΠΥ", "αυτισμός", "Νοητική υστέρηση",
                "Γνωστική έκπτωση", "Μυϊκή αδυναμία ", "Λοιμώξεις αυτιού ", "Καθυστέρηση ομιλίας ", "άρθρωση", "απραξία", "δυσφαγία",
                "Mucopolysaccharidosis III"
        );
        ArrayList<String> sanfilippo_syndrome_keywords = new ArrayList<>(sanfilippo_syndrome_keywords_tmp);

        ArrayList<Integer> sanfilippo_syndrome_img = new ArrayList<>();
        sanfilippo_syndrome_img.add(R.drawable.sanfilippo_1);
        sanfilippo_syndrome_img.add(R.drawable.sanfillipo_2);
        mCardList.add(new MainRecyclerButton("Sanfilippo syndrome",sanfilippo_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Sanfilippo (Sanfilippo syndrome)-\n" +
                        "Mucopolysaccharidosis III (MPS III)",
                R.raw.sanfilippo_syndrome,"ICD-10: E76.2E\n" +
                "ORPHA: 581\n",
                "Βιβλιογραφία (Σύνδρομο Sanfilippo)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/sanfilippo-syndrome/\n" +
                        "2. Tamás Zelei, Kata Csetneki, Zoltán Vokó, and Csaba Siffel, \"Epidemiology of Sanfilippo syndrome: results of a systematic literature review\", Orphanet J Rare Dis.\n" +
                        "2018\n" +
                        "3. Frits A Wijburg, Grzegorz Węgrzyn, Barbara K Burton, and Anna Tylki-Szymańska, \" Mucopolysaccharidosis type III (Sanfilippo syndrome) and misdiagnosis of\n" +
                        "idiopathic developmental delay, attention deficit/hyperactivity disorder or autism spectrum disorder\", Acta Paediatr. 2013\n" +
                        "4. Marlies J Valstar, Jan Pieter Marchal, Martha Grootenhuis, Vivian Colland, and Frits A Wijburg,\" Cognitive development in patients with Mucopolysaccharidosis\n" +
                        "type III (Sanfilippo syndrome)\", Orphanet J Rare Dis. 2011\n" +
                        "5. Xiaohua Li, MM, Rui Xiao, PhD, Baiyu Chen, MM, Guanglu Yang, MD, Xiaomeng Zhang, MM, Zhuo Fu, MM, Junxian Fu, MM, Mengli Zhuang, MM, and Yinglong\n" +
                        "Huang, MD, PhD, \" A novel mutation of SGSH and clinical features analysis of mucopolysaccharidosis type IIIA\", Medicine (Baltimore). 2018",
                sanfilippo_syndrome_img

        )));

        //sly_syndrome

        List<String> sly_syndrome_keywords_tmp = Arrays.asList(
                "Sly ", "MPS VII", "E76.2", "584", "αναπνοή", "Επιληψία", "Υδροκεφαλία", "Μακρογλωσσία",
                "λοιμώξεις αυτιού", "Καθυστέρηση ομιλίας", "Μαθησιακές δυσκολίες", "ΔΕΠΥ", "Γνωστικά ελλείμματα",
                "Mucopolysaccharidosis VΙI"

        );
        ArrayList<String> sly_syndrome_keywords = new ArrayList<>(sly_syndrome_keywords_tmp);

        ArrayList<Integer> sly_syndrome_img = new ArrayList<>();
        sly_syndrome_img.add(R.drawable.sly_1);
        sly_syndrome_img.add(R.drawable.sly_2);
        mCardList.add(new MainRecyclerButton("Sly syndrome",sly_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Sly (Sly syndrome) - Mucopolysaccharidosis VΙI (MPS VII)",
                R.raw.sly_syndrome,"ICD-10: E76.2\n" +
                "ORPHA:584\n",
                "Βιβλιογραφία (Σύνδρομο Sly)\n" +
                        "1. Nathalie Guffon, Roseline Froissart, and Alain Fouilhoux , \" A rare late progression form of Sly syndrome mucopolysaccharidosis\" , JIMD Rep. 2019\n" +
                        "2. Alexandra Morrison, Esmee Oussoren, Tabea Friedel, Jordi Cruz, and Nalan Yilmaz, \" Pathway to diagnosis and burden of illness in mucopolysaccharidosis type VII –\n" +
                        "a European caregiver survey\", Orphanet J Rare Dis. 2019",
                sly_syndrome_img

        )));

        //spinal_muscular_atrophy

        List<String> spinal_muscular_atrophy_keywords_tmp = Arrays.asList(
                "G12.0", "70", "ατροφία νωτιαίου μυελού", "τρόμος", "μυϊκή αδυναμία ", "αναπνοή", "απομύζηση",
                "Spinal muscular atrophy" , "SMA"
        );
        ArrayList<String> spinal_muscular_atrophy_keywords = new ArrayList<>(spinal_muscular_atrophy_keywords_tmp);

        ArrayList<Integer> spinal_muscular_atrophy_img = new ArrayList<>();
        spinal_muscular_atrophy_img.add(R.drawable.sma_1);
        mCardList.add(new MainRecyclerButton("Spinal muscular atrophy",spinal_muscular_atrophy_keywords,new SyndromeData(
                "Ατροφία Νωτιαίου μυελού (Spinal muscular atrophy)-(SMA)",
                R.raw.spinal_muscular_atrophy,"ICD-10: G12.0\nORPHA: 70\n",
                "Βιβλιογραφία (SMA)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/spinal-muscular-atrophy/\n" +
                        "2. Andrew P. Tosolini and James N. Sleigh,\" Motor Neuron Gene Therapy: Lessons from Spinal Muscular Atrophy for Amyotrophic Lateral Sclerosis\", Front Mol\n" +
                        "Neurosci. 2017",
                spinal_muscular_atrophy_img

        )));


        mCardList.add(new MainRecyclerLettering("T"));

        //treacher_collins_syndrome

        List<String> treacher_collins_syndrome_keywords_tmp = Arrays.asList(
                "Treacher Collins ", "Q75.4", "86", "υποπλασία ",
                "Υπερωιοσχιστία", "μαλακή υπερώα", "Μικρωτία", "βαρηκοΐα",
                "άρθρωση", "αντήχηση", "υπερρινικότητα", "υπορινικότητα", "φώνηση",
                "φωνή", "Δυσπραξία "

        );
        ArrayList<String> treacher_collins_syndrome_keywords = new ArrayList<>(treacher_collins_syndrome_keywords_tmp);

        ArrayList<Integer> treacher_collins_syndrome_img = new ArrayList<>();
        treacher_collins_syndrome_img.add(R.drawable.treacher_1);
        mCardList.add(new MainRecyclerButton("Treacher Collins syndrome",treacher_collins_syndrome_keywords,new SyndromeData(
                "Σύνδρομο Treacher Collins (Treacher Collins syndrome)",
                R.raw.treacher_collins_syndrome,"ICD-10: Q75.4\n" +
                "ORPHA: 86\n",
                "Βιβλιογραφία (Σύνδρομο Treacher Collins)\n" +
                        "1. Pamela Åsten et al., \" Associations between speech features and phenotypic severity in Treacher Collins syndrome\" BMC Med Genet. 2014; 15: 47.\n" +
                        "2. Karla Terrazas et al., \" Rare syndromes of the head and face: mandibulofacial and acrofacial dysostoses\" Wiley Interdiscip Rev Dev Biol. Author manuscript;\n" +
                        "available in PMC 2018 May 1.\n" +
                        "3. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/treacher-collins-syndrome/",
                treacher_collins_syndrome_img

        )));

        mCardList.add(new MainRecyclerLettering("U"));

        mCardList.add(new MainRecyclerLettering("V"));

        mCardList.add(new MainRecyclerLettering("W"));

        //wilson_disease

        List<String> wilson_disease_keywords_tmp = Arrays.asList(
                "E83.0B", "905", "Wilson", "έκφραση", "αντανακλαστικά", "δυσαρθρία", "αρθρωτικά λάθη", "επιτονισμός", "αντήχηση",
                "διαδοχοκίνηση", "σιελόρροια", "επιληψία", "παρεγκεφαλίδα", "ΔΕΠΥ", "μνήμη", "Parkinson ", "Χορεία", "Σπαστικότητα", "Γνωστικά ελλείμματα"

        );
        ArrayList<String> wilson_disease_keywords = new ArrayList<>(wilson_disease_keywords_tmp);

        ArrayList<Integer> wilson_disease_img = new ArrayList<>();
        wilson_disease_img.add(R.drawable.willson_1);
        mCardList.add(new MainRecyclerButton("Wilson disease",wilson_disease_keywords,new SyndromeData(
                "Ασθένεια Wilson (Wilson Disease)",
                R.raw.wilson_disease,"ICD-10: E83.0B\n" +
                "ORPHA: 905\n",
                "Βιβλιογραφία (Ασθένεια Wilson)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/wilsons-sjukdom/\n" +
                        "2. Samuel Shribman et al., \"Clinical presentations of Wilson disease\", Ann Transl Med. 2019",
                wilson_disease_img

        )));

        mCardList.add(new MainRecyclerLettering("X"));
        mCardList.add(new MainRecyclerLettering("Y"));
        mCardList.add(new MainRecyclerLettering("Z"));




    }

}