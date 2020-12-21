package com.example.speechsyndromeapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class AboutActivity extends AppCompatActivity {

    private TextView aboutText;
    private ImageView aboutLogo;
    private ImageView aboutBranchLogo;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);

        aboutText = findViewById(R.id.about_text_view);
        aboutLogo = findViewById(R.id.about_image_view);
        aboutBranchLogo = findViewById(R.id.about_branch_logo);

        aboutText.setText("Λίγα λόγια για την εφαρμογή μας...\n" +
                "Η εφαρμογή αυτή δημιουργήθηκε στα πλαίσια της πτυχιακής εργασίας της φοιτήτριας Οκουμούση Χριστίνας του τμήματος Λογοθεραπείας του Πανεπιστημίου Πατρών, με υπεύθυνη καθηγήτρια την Δρ Αιμιλία Μίχου. Σκοπός της εφαρμογής είναι η συλλογή και η παρουσίαση επιστημονικά ορθών δεδομένων σχετικών με γενετικά σύνδρομα που αφορούν άμεσα με την επιστήμη της Λογοθεραπείας. Όλα τα δεδομένα προέρχονται από πρόσφατα δημοσιευμένες έρευνεςσε θέματα όπως στοματοπροσωπικές / ανατομικές ανωμαλίες, επιπτώσεις στην ακοή, στο λόγο, την ομιλία και την κατάποση. Σε κάθε σύνδρομο που αναλύεται υπάρχει βιβλιογραφική αναφορά, εικόνες και οι κωδικοί ICD-10 και ORPHA. \n" +
                "\n" +
                "[Σχεδιασμός/ Δημιουργία εφαρμογής: .... ]\n" +
                "Η επιστήμη της Λογοθεραπείας\n" +
                "Λογοθεραπεία είναι η επιστήμη που έχει ως γνωστικό αντικείμενο τη μελέτη, έρευνα και εφαρμογή επιστημονικών γνώσεων γύρω από την ανθρώπινη επικοινωνία- φωνή, ομιλία, λόγο (προφορικό, γραπτό), μη λεκτική επικοινωνία – και τις διαταραχές αυτής, καθώς και τις διαταραχές των καταποτικών κινήσεων του στοματοφάρυγγα. Οι λογοθεραπευτές παρέχουν εξειδικευμένες υπηρεσίες σε άτομα με τις ακόλουθες διαταραχές: \n" +
                "•\tΑναπτυξιακές διαταραχές ομιλίας\n" +
                "o\t    Αρθρωτικές διαταραχές\n" +
                "o\t    Φωνολογικές διαταραχές\n" +
                "o\t    Δυσαρθρία και Δυσπραξία\n" +
                "o\t    Διαταραχές στη ροή της ομιλίας (δυσρυθμία, τραυλισμός, ταχυλαλία)\n" +
                "•\tΑναπτυξιακές γλωσσικές διαταραχές\n" +
                "o\t    Διαταραχή κατανόηση και παραγωγή γραπτού και / ή προφορικού λόγου\n" +
                "o\t    Βαρηκοΐα - κώφωση\n" +
                "o\t    Διαταραχές χρήσης της γλώσσας- Πραγματολογικές Διαταραχές (Αυτισμός, Σύνδρομο Asperger, Σημασιολογική – Πραγματολογική διαταραχή)\n" +
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
                "Πηγή: http://logoth.upatras.gr/  \n");
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
