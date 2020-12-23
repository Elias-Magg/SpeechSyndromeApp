package com.example.speechsyndromeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.RecursiveAction;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String EXTRA_SYNDROME_DATA = "com.example.speechsyndromeapp.EXTRA_SYNDROME_DATA";


    private RecyclerView mRecyclerView;
    private MainRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayouteManager;
    private ArrayList<MainRecyclerItem> mCardList;
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
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

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.search_bar){
            if(mCardList.get(0) instanceof MainRecyclerSearch) removeItem(0);
            else insertItem(0, new MainRecyclerSearch("Write Something Here !"));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Sources:
                Toast.makeText(this,"Sources",Toast.LENGTH_LONG).show();
                break;
            case R.id.AboutUs:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
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

    public void insertItem(int position, MainRecyclerItem item) {
        mCardList.add(position,item);
        //notifyDataChanged for no animation
        mAdapter.notifyItemInserted(position);
        mLayouteManager.scrollToPosition(0);
    }

    public void removeItem(int position) {
        mCardList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void setData(){
        ArrayList<String> tmp = new ArrayList<>();

        mCardList.add(new MainRecyclerLettering("A"));

        //spinal_muscular_atrophy

        ArrayList<Integer> spinal_muscular_atrophy_img = new ArrayList<>();
        spinal_muscular_atrophy_img.add(R.drawable.spinal_muscular_atrophy_1);
        spinal_muscular_atrophy_img.add(R.drawable.spinal_muscular_atrophy_2);
        mCardList.add(new MainRecyclerButton("Ατροφία Νωτιαίου μυελού (Spinal muscular atrophy)-(SMA)",tmp,new SyndromeData(
                "Ατροφία Νωτιαίου μυελού (Spinal muscular atrophy)-(SMA)",
                R.raw.spinal_muscular_atrophy,"ICD-10: G12.0\nORPHA: 70\n",
                "Βιβλιογραφία (SMA)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/spinal-muscular-atrophy/\n" +
                        "2. Andrew P. Tosolini and James N. Sleigh,\" Motor Neuron Gene Therapy: Lessons from Spinal Muscular Atrophy for Amyotrophic Lateral Sclerosis\", Front Mol\n" +
                        "Neurosci. 2017",
                spinal_muscular_atrophy_img

        )));

        //incontinentia_pigmenti_bloch_sulzberger_syndrome

        ArrayList<Integer> incontinentia_pigmenti_bloch_sulzberger_syndrome_img = new ArrayList<>();
        incontinentia_pigmenti_bloch_sulzberger_syndrome_img.add(R.drawable.incontinentia_pigmenti_bloch_sulzberger_syndrome_1);
        incontinentia_pigmenti_bloch_sulzberger_syndrome_img.add(R.drawable.incontinentia_pigmenti_bloch_sulzberger_syndrome_2);
        mCardList.add(new MainRecyclerButton("Ακράτεια χρωστικής-Σύνδρομο Bloch-Sulzberger\n" +
                "(Incontinentia pigmenti-Bloch-Sulzberger Syndrome)",tmp,new SyndromeData(
                "Ακράτεια χρωστικής-Σύνδρομο Bloch-Sulzberger\n" +
                        "(Incontinentia pigmenti-Bloch-Sulzberger Syndrome)",
                R.raw.incontinentia_pigmenti_bloch_sulzberger_syndrome,"ICD-10: Q82.3\n" +
                "ORPHA:464\n",
                "Βιβλιογραφία (Incontinentia pigmenti\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/incontinentia-pigmenti/\n" +
                        "2. Cláudia Schermann Poziomczyk et al., \"Incontinentia pigmenti\", An Bras Dermatol., 2014.",
                incontinentia_pigmenti_bloch_sulzberger_syndrome_img

        )));

        //aspartylglucosaminuria

        ArrayList<Integer> aspartylglucosaminuria_img = new ArrayList<>();
        aspartylglucosaminuria_img.add(R.drawable.aspartylglucosaminuria_1);
        aspartylglucosaminuria_img.add(R.drawable.aspartylglucosaminuria_2);
        mCardList.add(new MainRecyclerButton("Ακράτεια χρωστικής-Σύνδρομο Bloch-Sulzberger\n" +
                "(Incontinentia pigmenti-Bloch-Sulzberger Syndrome)",tmp,new SyndromeData(
                "Ακράτεια χρωστικής-Σύνδρομο Bloch-Sulzberger\n" +
                        "(Incontinentia pigmenti-Bloch-Sulzberger Syndrome)",
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

        //lchad_deficiency

        ArrayList<Integer> lchad_deficiency_img = new ArrayList<>();
        mCardList.add(new MainRecyclerButton("Ανεπάρκεια LCHAD (LCHAD deficiency)",tmp,new SyndromeData(
                "Ανεπάρκεια LCHAD (LCHAD deficiency)",
                R.raw.lchad_deficiency,"ICD-10: E71.3\n" +
                "ORPHA: 5\n",
                "Βιβλιογραφία (Ανεπάρκεια LCHAD)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/lchad-deficiency/",
                lchad_deficiency_img

        )));

        //glut1_deficiency_syndrome

        ArrayList<Integer> glut1_deficiency_syndrome_img = new ArrayList<>();
        mCardList.add(new MainRecyclerButton("Ανεπάρκεια πρωτεΐνης μεταφορέα γλυκόζης τύπου 1\n" +
                "(Glucose transporter protein type 1-deficiency- GLUT 1-deficiency syndrome)",tmp,new SyndromeData(
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


        //osteogenesis_imperfecta_brittle_bone_disease

        ArrayList<Integer> osteogenesis_imperfecta_brittle_bone_disease_img = new ArrayList<>();
        osteogenesis_imperfecta_brittle_bone_disease_img.add(R.drawable.osteogenesis_imperfecta_brittle_bone_disease_1);
        osteogenesis_imperfecta_brittle_bone_disease_img.add(R.drawable.osteogenesis_imperfecta_brittle_bone_disease_2);
        mCardList.add(new MainRecyclerButton("Ατελής Οστεογένεση (Osteogenesis Imperfecta-Brittle bone disease)",tmp,new SyndromeData(
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

        //achondroplasia

        ArrayList<Integer> achondroplasia_img = new ArrayList<>();
        achondroplasia_img.add(R.drawable.achondroplasia_1);
        achondroplasia_img.add(R.drawable.achondroplasia_2);
        mCardList.add(new MainRecyclerButton("Αχονδροπλασία (Achondroplasia)",tmp,new SyndromeData(
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

        ArrayList<Integer> aicardi_syndrome_img = new ArrayList<>();
        aicardi_syndrome_img.add(R.drawable.aicardi_syndrome_1);
        aicardi_syndrome_img.add(R.drawable.aicardi_syndrome_2);
        mCardList.add(new MainRecyclerButton("Σύνδρομο Aicardi (Aicardi syndrome)",tmp,new SyndromeData(
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

        ArrayList<Integer> alport_syndrome_img = new ArrayList<>();
        alport_syndrome_img.add(R.drawable.alport_syndrome_1);
        alport_syndrome_img.add(R.drawable.alport_syndrome_2);
        mCardList.add(new MainRecyclerButton("Σύνδρομο Alport (Alport syndrome)",tmp,new SyndromeData(
                "Σύνδρομο Alport (Alport syndrome)",
                R.raw.alport_syndrome,"ICD-10: Q87.8\n" +
                "ORPHA:63\n",
                "Βιβλιογραφία (Σύνδρομο Alport)\n" +
                        "1. John M. Graham, Glenis K. Scadding, Peter D. Bull, Eitors, Pediatric ENT, 2007.\n" +
                        "2. Ramesh Kaipa and Hannah Tether, \" Speech, language, and hearing function in twins with Alport syndrome: A seven-year retrospective case report\", J Otol. 2017",
                alport_syndrome_img

        )));

        //alstrom_syndrome

        ArrayList<Integer> alstrom_syndrome_img = new ArrayList<>();
        alstrom_syndrome_img.add(R.drawable.alstrom_syndrome_1);
        alstrom_syndrome_img.add(R.drawable.alstrom_syndrome_2);
        mCardList.add(new MainRecyclerButton("Σύνδρομο Alstrom (Alstrom syndrome)",tmp,new SyndromeData(
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

        ArrayList<Integer> angeman_syndrome_img = new ArrayList<>();
        angeman_syndrome_img.add(R.drawable.angeman_syndrome_1);
        angeman_syndrome_img.add(R.drawable.angeman_syndrome_2);
        mCardList.add(new MainRecyclerButton("Σύνδρομο Angelman (Angeman syndrome)",tmp,new SyndromeData(
                "Σύνδρομο Angelman (Angeman syndrome)",
                R.raw.angeman_syndrome,"ICD-10: Q93.5\n" +
                "ORPHA: 72\n",
                "Βιβλιογραφία (Σύνδρομο Angelman)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/angelman-syndrome/\n" +
                        "2. Robert P. Carson et al., \" Preserved expressive language as a phenotypic determinant of Mosaic Angelman Syndrome\", Mol Genet Genomic Med. 2019\n" +
                        "3. Karine Pelc, Guy Cheron, and Bernard Dan,\" Behavior and neuropsychiatric manifestations in Angelman syndrome\", Neuropsychiatr Dis Treat. 2008",
                angeman_syndrome_img

        )));




        mCardList.add(new MainRecyclerLettering("Β"));


        //branchiootorenal_syndrome

        ArrayList<Integer> branchiootorenal_syndrome_img = new ArrayList<>();
        branchiootorenal_syndrome_img.add(R.drawable.branchiootorenal_syndrome_1);
        branchiootorenal_syndrome_img.add(R.drawable.branchiootorenal_syndrome_2);
        mCardList.add(new MainRecyclerButton("Σύνδρομο BOR (Branchiootorenal syndrome)",tmp,new SyndromeData(
                "Σύνδρομο BOR (Branchiootorenal syndrome)",
                R.raw.branchiootorenal_syndrome,"ICD-10: Q87.8\nORPHA:107\n",
                "Βιβλιογραφία (Σύνδρομο BOR)\n" +
                        "1. John M. Graham, Glenis K. Scadding, Peter D. Bull, Eitors, Pediatric ENT, 2007.\n" +
                        "2. Tâmara Andrade Lindau et al., \"Anatomical Changes and Audiological Profile in Branchio-oto-renal Syndrome: A Literature Review\", Int Arch\n" +
                        "Otorhinolaryngol., 2014",
                branchiootorenal_syndrome_img

        )));

        mCardList.add(new MainRecyclerLettering("Γ"));

        //gaucher_disease

        ArrayList<Integer> gaucher_disease_img = new ArrayList<>();
        gaucher_disease_img.add(R.drawable.gaucher_disease_1);
        gaucher_disease_img.add(R.drawable.gaucher_disease_2);
        mCardList.add(new MainRecyclerButton("Ασθένεια Gaucher (Gaucher Disease)",tmp,new SyndromeData(
                "Ασθένεια Gaucher (Gaucher Disease)",
                R.raw.gaucher_disease,"CD-10: E75.2A\nORPHA: 355\n",
                "Βιβλιογραφία (Ασθένεια Gaucher)\n" +
                        "1. Karin Weiss et al., \"The Clinical Management of Type 2 Gaucher Disease.\", 2014\n" +
                        "2. Jérôme Stirnemann et al., \"A Review of Gaucher Disease Pathophysiology, Clinical Presentation and Treatments\", Int J Mol Sci., 2017\n" +
                        "3. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/gaucher-disease/",
                gaucher_disease_img

        )));

        //wilson_disease

        ArrayList<Integer> wilson_disease_img = new ArrayList<>();
        wilson_disease_img.add(R.drawable.wilson_disease_1);
        wilson_disease_img.add(R.drawable.wilson_disease_2);
        mCardList.add(new MainRecyclerButton("Ασθένεια Wilson (Wilson Disease)",tmp,new SyndromeData(
                "Ασθένεια Wilson (Wilson Disease)",
                R.raw.wilson_disease,"ICD-10: E83.0B\n" +
                "ORPHA: 905\n",
                "Βιβλιογραφία (Ασθένεια Wilson)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/wilsons-sjukdom/\n" +
                        "2. Samuel Shribman et al., \"Clinical presentations of Wilson disease\", Ann Transl Med. 2019",
                wilson_disease_img

        )));

        //beckwith_wiedemann_syndrome

        ArrayList<Integer> beckwith_wiedemann_syndrome_img = new ArrayList<>();
        beckwith_wiedemann_syndrome_img.add(R.drawable.beckwith_wiedemann_syndrome_1);
        beckwith_wiedemann_syndrome_img.add(R.drawable.beckwith_wiedemann_syndrome_2);
        mCardList.add(new MainRecyclerButton("Σύνδρομο Beckwith-Wiedemann (Beckwith-Wiedemann syndrome)",tmp,new SyndromeData(
                "Σύνδρομο Beckwith-Wiedemann (Beckwith-Wiedemann syndrome)",
                R.raw.beckwith_wiedemann_syndrome,"ICD-10: Q87.3\n" +
                "ORPHA: 116\n",
                "Βιβλιογραφία (Σύνδρομο Beckwith-Wiedemann)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/beckwith-wiedemann-syndrome/\n" +
                        "2. Kathleen H. Wang et al.,\" Diagnosis and Management of Beckwith-Wiedemann Syndrome\", Front Pediatr. 2019\n" +
                        "3. John M. Graham, Glenis K. Scadding, Peter D. Bull, Eitors, Pediatric ENT, 2007",
                beckwith_wiedemann_syndrome_img

        )));

        mCardList.add(new MainRecyclerLettering("Δ"));

        mCardList.add(new MainRecyclerLettering("Ε"));

        //edwards_syndrome_trisomy_18

        ArrayList<Integer> edwards_syndrome_trisomy_18_img = new ArrayList<>();
        edwards_syndrome_trisomy_18_img.add(R.drawable.edwards_syndrome_trisomy_18_1);
        edwards_syndrome_trisomy_18_img.add(R.drawable.edwards_syndrome_trisomy_18_2);
        mCardList.add(new MainRecyclerButton("Σύνδρομο Edwards-Τρισωμία 18 (Edwards syndrome-Trisomy 18)",tmp,new SyndromeData(
                "Σύνδρομο Edwards-Τρισωμία 18 (Edwards syndrome-Trisomy 18)",
                R.raw.edwards_syndrome_trisomy_18,"ICD-10: Q91.0 Q91.1 Q91.2 Q91.3\n" +
                "ORPHA:3380\n",
                "Βιβλιογραφία (Σύνδρομο Edwards)\n" +
                        "1. Anna Cereda and John C Carey, \" The trisomy 18 syndrome\", Orphanet J Rare Dis. 2012; 7: 81\n" +
                        "2. https://www.soft.org.uk/trisomy-18\n" +
                        "3. Meyer et al, \"Survival of children with trisomy 13 and trisomy 18: A multi-state population-based study\", Am J Med Genet A 2016 Apr;170A(4):825-37",
                edwards_syndrome_trisomy_18_img

        )));


        //fragile_x_syndrome

        ArrayList<Integer> fragile_x_syndrome_img = new ArrayList<>();
        fragile_x_syndrome_img.add(R.drawable.fragile_x_syndrome_1);
        fragile_x_syndrome_img.add(R.drawable.fragile_x_syndrome_2);
        mCardList.add(new MainRecyclerButton("Σύνδρομο εύθραυστου Χ χρωμοσώματος (Fragile X syndrome)",tmp,new SyndromeData(
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

        mCardList.add(new MainRecyclerLettering("Ζ"));

        mCardList.add(new MainRecyclerLettering("Η"));

        //goldenhar_syndrome_hemifacial_microsomia

        ArrayList<Integer> goldenhar_syndrome_hemifacial_microsomia_img = new ArrayList<>();
        goldenhar_syndrome_hemifacial_microsomia_img.add(R.drawable.goldenhar_syndrome_hemifacial_microsomia_1);
        goldenhar_syndrome_hemifacial_microsomia_img.add(R.drawable.goldenhar_syndrome_hemifacial_microsomia_2);
        mCardList.add(new MainRecyclerButton("Σύνδρομο Goldenhar-Ημιπροσωπική μικροσωμία (Goldenhar syndrome- hemifacial microsomia)",tmp,new SyndromeData(
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

        mCardList.add(new MainRecyclerLettering("Θ"));

        mCardList.add(new MainRecyclerLettering("Ι"));

        mCardList.add(new MainRecyclerLettering("Κ"));

        //krabbe_disease

        ArrayList<Integer> krabbe_disease_img = new ArrayList<>();
        krabbe_disease_img.add(R.drawable.krabbe_disease_1);
        krabbe_disease_img.add(R.drawable.krabbe_disease_2);
        mCardList.add(new MainRecyclerButton("Ασθένεια Krabbe (Krabbe disease)",tmp,new SyndromeData(
                "Ασθένεια Krabbe (Krabbe disease)",
                R.raw.krabbe_disease,"ICD-10: E75.2\n" +
                "ORPHA: 487\n",
                "Βιβλιογραφία (Ασθένεια Krabbe)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/krabbe-disease/\n" +
                        "2. Evangelia Dimitriou et al., \"The Spectrum of Krabbe Disease in Greece: Biochemical and Molecular Findings\", JIMD Rep. 2016\n" +
                        "3. Maria L. Beltran-Quintero et al., \"Early progression of Krabbe disease in patients with symptom onset between 0 and 5 months.\", Orphanet J Rare Dis. 2019",
                krabbe_disease_img

        )));

        //carpenter_syndrome

        ArrayList<Integer> carpenter_syndrome_img = new ArrayList<>();
        carpenter_syndrome_img.add(R.drawable.carpenter_syndrome_1);
        carpenter_syndrome_img.add(R.drawable.carpenter_syndrome_2);
        mCardList.add(new MainRecyclerButton("Σύνδρομο Carpenter (Carpenter syndrome)",tmp,new SyndromeData(
                "Σύνδρομο Carpenter (Carpenter syndrome)",
                R.raw.carpenter_syndrome,"ICD-10: Q87.0\n" +
                "ORPHA: 65759\n",
                "Βιβλιογραφία (Σύνδρομο Carpenter)\n" +
                        "1. https://rarediseases.org/rare-diseases/carpenter-syndrome/\n" +
                        "2. https://childrensnational.org/visit/conditions-and-treatments/genetic-disorders-and-birth-defects/carpenter-syndrome",
                carpenter_syndrome_img

        )));

        //congenital_disorder_of_glycosylation

        ArrayList<Integer> congenital_disorder_of_glycosylation_img = new ArrayList<>();
        congenital_disorder_of_glycosylation_img.add(R.drawable.congenital_disorder_of_glycosylation_1);
        congenital_disorder_of_glycosylation_img.add(R.drawable.congenital_disorder_of_glycosylation_2);
        mCardList.add(new MainRecyclerButton("Σύνδρομο CDG (CDG syndrome - Congenital Disorder of Glycosylation)",tmp,new SyndromeData(
                "Σύνδρομο CDG (CDG syndrome - Congenital Disorder of Glycosylation)",
                R.raw.congenital_disorder_of_glycosylation,"ICD-10: E77.8\n" +
                "ORPHA: 137\n",
                "Βιβλιογραφία (Σύνδρομο CDG)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/cdg-syndrome/\n" +
                        "2. Laurien Vaes et al., \" PMM2‐CDG caused by uniparental disomy: Case report and literature review\", JIMD Rep. 2020",
                congenital_disorder_of_glycosylation_img

        )));

        //cornelia_delange_syndrome

        ArrayList<Integer> cornelia_delange_syndrome_img = new ArrayList<>();
        cornelia_delange_syndrome_img.add(R.drawable.cornelia_delange_syndrome_1);
        cornelia_delange_syndrome_img.add(R.drawable.cornelia_delange_syndrome_2);
        cornelia_delange_syndrome_img.add(R.drawable.cornelia_delange_syndrome_3);
        mCardList.add(new MainRecyclerButton("Σύνδρομο Cornelia de Lange (Cornelia de Lange syndrome)",tmp,new SyndromeData(
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

        ArrayList<Integer> cri_du_chat_syndrome_img = new ArrayList<>();
        cri_du_chat_syndrome_img.add(R.drawable.cri_du_chat_syndrome_1);
        cri_du_chat_syndrome_img.add(R.drawable.cri_du_chat_syndrome_2);
        cri_du_chat_syndrome_img.add(R.drawable.cri_du_chat_syndrome_3);
        mCardList.add(new MainRecyclerButton("Σύνδρομο Cri du Chat (Cri du Chat syndrome)",tmp,new SyndromeData(
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


        //cri_du_chat_syndrome

        ArrayList<Integer> crouzon_syndrome_img = new ArrayList<>();
        crouzon_syndrome_img.add(R.drawable.crouzon_syndrome_1);
        crouzon_syndrome_img.add(R.drawable.crouzon_syndrome_2);
        crouzon_syndrome_img.add(R.drawable.crouzon_syndrome_3);
        mCardList.add(new MainRecyclerButton("Σύνδρομο Crouzon (Crouzon syndrome)",tmp,new SyndromeData(
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

        //kallmann_syndrome

        ArrayList<Integer> kallmann_syndrome_img = new ArrayList<>();
        mCardList.add(new MainRecyclerButton("Σύνδρομο Kallmann (Kallmann syndrome)",tmp,new SyndromeData(
                "Σύνδρομο Kallmann (Kallmann syndrome)",
                R.raw.kallmann_syndrome,"ICD-10: E23.0\n" +
                "ORPHA:478\n",
                "Βιβλιογραφία (Σύνδρομο Kallmann)\n" +
                        "1. Maria I. Stamou and Neoklis A. Georgopoulos \" Kallmann syndrome: phenotype and genotype of hypogonadotropic hypogonadism\" Metabolism. Author\n" +
                        "manuscript; available in PMC 2018 Sep 1.",
                kallmann_syndrome_img
        )));

        mCardList.add(new MainRecyclerLettering("Λ"));

        mCardList.add(new MainRecyclerLettering("Μ"));

        //morquio_syndrome

        ArrayList<Integer> morquio_syndrome_img = new ArrayList<>();
        morquio_syndrome_img.add(R.drawable.morquio_syndrome_1);
        morquio_syndrome_img.add(R.drawable.morquio_syndrome_2);
        mCardList.add(new MainRecyclerButton("Σύνδρομο Morquio (Morquio syndrome)-\n" +
                "Mucopolysaccharidosis IV (MPS IV)",tmp,new SyndromeData(
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

        //maroteaux_lamy_syndrome

        ArrayList<Integer> maroteaux_lamy_syndrome_img = new ArrayList<>();
        maroteaux_lamy_syndrome_img.add(R.drawable.maroteaux_lamy_syndrome_1);
        maroteaux_lamy_syndrome_img.add(R.drawable.maroteaux_lamy_syndrome_2);
        mCardList.add(new MainRecyclerButton("Σύνδρομο Maroteaux Lamy (Maroteaux Lamy syndrome)-\n" +
                "Mucopolysaccharidosis VI (MPS VI)",tmp,new SyndromeData(
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



        mCardList.add(new MainRecyclerLettering("Ν"));

        //down_syndrome

        ArrayList<Integer> down_syndrome_img = new ArrayList<>();
        down_syndrome_img.add(R.drawable.down_syndrome_1);
        down_syndrome_img.add(R.drawable.down_syndrome_2);
        mCardList.add(new MainRecyclerButton("Σύνδρομο Down (Down Syndrome)",tmp,new SyndromeData(
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

        mCardList.add(new MainRecyclerLettering("Ξ"));

        mCardList.add(new MainRecyclerLettering("Ο"));

        //holoprosencephaly

        ArrayList<Integer> holoprosencephaly_img = new ArrayList<>();
        holoprosencephaly_img.add(R.drawable.holoprosencephaly_1);
        holoprosencephaly_img.add(R.drawable.holoprosencephaly_2);
        mCardList.add(new MainRecyclerButton("Ολοπροσεγκεφαλία (Holoprosencephaly)",tmp,new SyndromeData(
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

        mCardList.add(new MainRecyclerLettering("Π"));

        //pierre_robin_sequence

        ArrayList<Integer> pierre_robin_sequence_img = new ArrayList<>();
        pierre_robin_sequence_img.add(R.drawable.pierre_robin_sequence_1);
        pierre_robin_sequence_img.add(R.drawable.pierre_robin_sequence_2);
        mCardList.add(new MainRecyclerButton("Ακολουθία Pierre Robin (Pierre Robin Sequence)",tmp,new SyndromeData(
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

        //potocki_lupski_syndrome

        ArrayList<Integer> potocki_lupski_syndrome_img = new ArrayList<>();
        potocki_lupski_syndrome_img.add(R.drawable.potocki_lupski_syndrome_1);
        potocki_lupski_syndrome_img.add(R.drawable.potocki_lupski_syndrome_2);
        mCardList.add(new MainRecyclerButton("Σύνδρομο Potocki-Lupski (Potocki-Lupski syndrome)",tmp,new SyndromeData(
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

        //pitt_hopkins_syndrome

        ArrayList<Integer> pitt_hopkins_syndrome_img = new ArrayList<>();
        pitt_hopkins_syndrome_img.add(R.drawable.pitt_hopkins_syndrome_1);
        pitt_hopkins_syndrome_img.add(R.drawable.pitt_hopkins_syndrome_2);
        mCardList.add(new MainRecyclerButton("Σύνδρομο Pitt-Hopkins (Pitt-Hopkins syndrome)",tmp,new SyndromeData(
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

        //prader_willi_syndrome

        ArrayList<Integer> prader_willi_syndrome_img = new ArrayList<>();
        prader_willi_syndrome_img.add(R.drawable.prader_willi_syndrome_1);
        prader_willi_syndrome_img.add(R.drawable.prader_willi_syndrome_2);
        prader_willi_syndrome_img.add(R.drawable.prader_willi_syndrome_3);
        mCardList.add(new MainRecyclerButton("Σύνδρομο Prader-Willi (Prader-Willi syndrome)",tmp,new SyndromeData(
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

        mCardList.add(new MainRecyclerLettering("Ρ"));

        mCardList.add(new MainRecyclerLettering("Σ"));

        //cmt_disease

        ArrayList<Integer> cmt_disease_img = new ArrayList<>();
        cmt_disease_img.add(R.drawable.cmt_disease_1);
        cmt_disease_img.add(R.drawable.cmt_disease_2);
        mCardList.add(new MainRecyclerButton("Ασθένεια Charcot-Marie-Tooth (CMT Disease)",tmp,new SyndromeData(
                "Ασθένεια Charcot-Marie-Tooth (CMT Disease)",
                R.raw.cmt_disease,"ICD-10: G60.0\n",
                "Βιβλιογραφία (Ασθένεια CMT)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/charcot-marie-tooth-disease/,\n" +
                        "2. Donald McCorquodale et al.,\"Management of Charcot–Marie–Tooth disease: improving long-term care with a multidisciplinary approach\", J Multidiscip\n" +
                        "Healthc. 2016",
                cmt_disease_img

        )));

        //spielmeyer_vogt_disease

        ArrayList<Integer> spielmeyer_vogt_disease_img = new ArrayList<>();
        spielmeyer_vogt_disease_img.add(R.drawable.spielmeyer_vogt_disease_1);
        spielmeyer_vogt_disease_img.add(R.drawable.spielmeyer_vogt_disease_2);
        mCardList.add(new MainRecyclerButton("Ασθένεια Spielmeyer-Vogt / Ασθένεια Batten -\n" +
                "(Spielmeyer-Vogt disease / Batten disease)",tmp,new SyndromeData(
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

        ArrayList<Integer> sanfilippo_syndrome_img = new ArrayList<>();
        sanfilippo_syndrome_img.add(R.drawable.sanfilippo_syndrome_1);
        sanfilippo_syndrome_img.add(R.drawable.sanfilippo_syndrome_2);
        mCardList.add(new MainRecyclerButton("Σύνδρομο Sanfilippo (Sanfilippo syndrome)-\n" +
                "Mucopolysaccharidosis III (MPS III)",tmp,new SyndromeData(
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

        //sanfilippo_syndrome

        ArrayList<Integer> sly_syndrome_img = new ArrayList<>();
        sly_syndrome_img.add(R.drawable.sly_syndrome_1);
        sly_syndrome_img.add(R.drawable.sly_syndrome_2);
        mCardList.add(new MainRecyclerButton("Σύνδρομο Sly (Sly syndrome) - Mucopolysaccharidosis VΙI (MPS VII)",tmp,new SyndromeData(
                "Σύνδρομο Sly (Sly syndrome) - Mucopolysaccharidosis VΙI (MPS VII)",
                R.raw.sly_syndrome,"ICD-10: E76.2\n" +
                "ORPHA:584\n",
                "Βιβλιογραφία (Σύνδρομο Sly)\n" +
                        "1. Nathalie Guffon, Roseline Froissart, and Alain Fouilhoux , \" A rare late progression form of Sly syndrome mucopolysaccharidosis\" , JIMD Rep. 2019\n" +
                        "2. Alexandra Morrison, Esmee Oussoren, Tabea Friedel, Jordi Cruz, and Nalan Yilmaz, \" Pathway to diagnosis and burden of illness in mucopolysaccharidosis type VII –\n" +
                        "a European caregiver survey\", Orphanet J Rare Dis. 2019",
                sly_syndrome_img

        )));




        mCardList.add(new MainRecyclerLettering("Τ"));

        //charge_syndrome

        ArrayList<Integer> charge_syndrome_img = new ArrayList<>();
        charge_syndrome_img.add(R.drawable.charge_syndrome_1);
        charge_syndrome_img.add(R.drawable.charge_syndrome_2);
        mCardList.add(new MainRecyclerButton("Σύνδρομο CHARGE (CHARGE syndrome)",tmp,new SyndromeData(
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


        mCardList.add(new MainRecyclerLettering("Υ"));

        mCardList.add(new MainRecyclerLettering("Φ"));

        mCardList.add(new MainRecyclerLettering("Χ"));

        //huntington_disease_huntington_chorea

        ArrayList<Integer> huntington_disease_huntington_chorea_img = new ArrayList<>();
        huntington_disease_huntington_chorea_img.add(R.drawable.huntington_disease_huntington_chorea_1);
        huntington_disease_huntington_chorea_img.add(R.drawable.huntington_disease_huntington_chorea_2);
        mCardList.add(new MainRecyclerButton("Ασθένεια Huntington-Χορεία Huntington\n" +
                "(Huntington disease-Huntington chorea)",tmp,new SyndromeData(
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

        ArrayList<Integer> hurler_syndrome_img = new ArrayList<>();
        hurler_syndrome_img.add(R.drawable.hurler_syndrome_1);
        hurler_syndrome_img.add(R.drawable.hurler_syndrome_2);
        mCardList.add(new MainRecyclerButton("Σύνδρομο Hurler (Hurler syndrome) - Mucopolysaccharidosis I (MPS I)",tmp,new SyndromeData(
                "Σύνδρομο Hurler (Hurler syndrome) - Mucopolysaccharidosis I (MPS I)",
                R.raw.hurler_syndrome,"ICD-10: E76.0\n" +
                "ORPHA: 93473\n",
                "Βιβλιογραφία (Σύνδρομο Hurler)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/hurler-syndrome/",
                hurler_syndrome_img

        )));

        //hunter_syndrome

        ArrayList<Integer> hunter_syndrome_img = new ArrayList<>();
        hunter_syndrome_img.add(R.drawable.hunter_syndrome_1);
        hunter_syndrome_img.add(R.drawable.hunter_syndrome_2);
        mCardList.add(new MainRecyclerButton("Σύνδρομο Hunter (Hunter syndrome) - Mucopolysaccharidosis II (MPS II)",tmp,new SyndromeData(
                "Σύνδρομο Hunter (Hunter syndrome) - Mucopolysaccharidosis II (MPS II)",
                R.raw.hunter_syndrome,"ICD-10: E76.1\n" +
                "ORPHA: 580\n",
                "Βιβλιογραφία (Σύνδρομο Hunter)",
                hunter_syndrome_img

        )));

        //hunter_syndrome

        ArrayList<Integer> marker_chromosome_15_syndrome_img = new ArrayList<>();
        marker_chromosome_15_syndrome_img.add(R.drawable.marker_chromosome_15_syndrome_1);
        mCardList.add(new MainRecyclerButton("Σύνδρομο χρωμοσωμικού δείκτη 15 (Marker chromosome 15 syndrome)",tmp,new SyndromeData(
                "Σύνδρομο χρωμοσωμικού δείκτη 15 (Marker chromosome 15 syndrome)",
                R.raw.marker_chromosome_15_syndrome,"ICD-10: Q99.8\n" +
                "ORPHA: 3306\n",
                "Βιβλιογραφία (Σύνδρομο χρωμοσωμικού δείκτη 15)\n" +
                        "1. https://www.mun-h-center.se/en/research-and-facts/rare-diseases/marker-chromosome-15-syndrome/\n" +
                        "2. Altuğ Koç et al. \" Supernumerary marker chromosome 15 in a male with azoospermia and open bite deformity\" Asian J Androl. 2009 Sep; 11(5): 617–622.",
                marker_chromosome_15_syndrome_img

        )));

        mCardList.add(new MainRecyclerLettering("Ψ"));

        mCardList.add(new MainRecyclerLettering("Ω"));




    }

}