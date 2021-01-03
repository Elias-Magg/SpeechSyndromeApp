package com.example.speechsyndromeapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Objects;

/**
 * This activity contains info on the project itself and the
 * people that participated in it
 */

public class AboutActivity extends AppCompatActivity {

    private TextView aboutText;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); //Disable night mode
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        aboutText = findViewById(R.id.about_text_view);

        String text = "Λίγα λόγια για την εφαρμογή μας...\n\n" +
                "Η εφαρμογή αυτή δημιουργήθηκε στα πλαίσια της πτυχιακής εργασίας της φοιτήτριας Οκουμούση Χριστίνας του τμήματος Λογοθεραπείας του Πανεπιστημίου Πατρών, με υπεύθυνη καθηγήτρια την Δρ Αιμιλία Μίχου. Σκοπός της εφαρμογής είναι η συλλογή και η παρουσίαση επιστημονικά ορθών δεδομένων σχετικών με γενετικά σύνδρομα που αφορούν άμεσα με την επιστήμη της Λογοθεραπείας. Όλα τα δεδομένα προέρχονται από πρόσφατα δημοσιευμένες έρευνες σε θέματα όπως στοματοπροσωπικές / ανατομικές ανωμαλίες, επιπτώσεις στην ακοή, στο λόγο, την ομιλία και την κατάποση. Σε κάθε σύνδρομο που αναλύεται υπάρχει βιβλιογραφική αναφορά και οι κωδικοί ICD-10 και ORPHA. \n\n" +
                "Η επιστήμη της Λογοθεραπείας\n\n" +
                "Λογοθεραπεία είναι η επιστήμη που έχει ως γνωστικό αντικείμενο τη μελέτη, έρευνα και εφαρμογή επιστημονικών γνώσεων γύρω από την ανθρώπινη επικοινωνία- φωνή, ομιλία, λόγο (προφορικό, γραπτό), μη λεκτική επικοινωνία – και τις διαταραχές αυτής, καθώς και τις διαταραχές των καταποτικών κινήσεων του στοματοφάρυγγα. Οι λογοθεραπευτές παρέχουν εξειδικευμένες υπηρεσίες σε άτομα με τις ακόλουθες διαταραχές: \n" +
                "•\tΑναπτυξιακές διαταραχές ομιλίας\n" +
                "    o\tΑρθρωτικές διαταραχές\n" +
                "    o\tΦωνολογικές διαταραχές\n" +
                "    o\tΔυσαρθρία και Δυσπραξία\n" +
                "    o\tΔιαταραχές στη ροή της ομιλίας \n      (δυσρυθμία, τραυλισμός, ταχυλαλία)\n" +
                "•\tΑναπτυξιακές γλωσσικές διαταραχές\n" +
                "    o\tΔιαταραχή κατανόηση και παραγωγή γραπτού \n      και / ή προφορικού λόγου\n" +
                "    o\tΒαρηκοΐα - κώφωση\n" +
                "    o\tΔιαταραχές χρήσης της γλώσσας- Πραγματολογικές \n      Διαταραχές (Αυτισμός, Σύνδρομο Asperger,\n      Σημασιολογική – Πραγματολογική διαταραχή)\n" +
                "•\tΜαθησιακές δυσκολίες\n" +
                "•\tΔιαταραχές σίτισης και κατάποσης (δυσφαγία)\n" +
                "•\tΔιαταραχές φωνής\n" +
                "•\tΚρανιοπροσωπικές ανωμαλίες\n" +
                "•\tΧειρουργικές επεμβάσεις κεφαλής και τραχήλου (λαρυγγεκτομή)\n" +
                "•\tΝευρογενείς διαταραχές και σύνδρομα\n" +
                "•\tΕγκεφαλικά επεισόδια – αφασίες\n" +
                "•\tΚρανιοεγκεφαλικές κακώσεις\n" +
                "\n" +
                "\n" +
                "Πηγή: http://logoth.upatras.gr/  \n";


        SpannableString ss = new SpannableString(text);
        StyleSpan bold = new StyleSpan(Typeface.BOLD);
        StyleSpan bold1 = new StyleSpan(Typeface.BOLD);

        ss.setSpan(bold,text.indexOf("Λίγα λόγια για την εφαρμογή μας..."),text.indexOf("Λίγα λόγια για την εφαρμογή μας...")+ "Λίγα λόγια για την εφαρμογή μας...".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new RelativeSizeSpan(1.5f),text.indexOf("Λίγα λόγια για την εφαρμογή μας..."),text.indexOf("Λίγα λόγια για την εφαρμογή μας...")+ "Λίγα λόγια για την εφαρμογή μας...".length(),0);
        ss.setSpan(bold1,text.indexOf("Η επιστήμη της Λογοθεραπείας"),text.indexOf("Η επιστήμη της Λογοθεραπείας")+ "Η επιστήμη της Λογοθεραπείας".length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new RelativeSizeSpan(1.5f),text.indexOf("Η επιστήμη της Λογοθεραπείας"),text.indexOf("Η επιστήμη της Λογοθεραπείας")+ "Η επιστήμη της Λογοθεραπείας".length(),0);

        aboutText.setText(ss);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) finish();



        return true;
    }
}
