package com.ashandilya.android.get_your_cv;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.shiraz.get_your_cv.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class PdfActivity extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    ImageView imageView_profile;

    Button buttonSignOut;

    EditText fileName, fname, fadd, email, cnum, mstat, nat, dob, klang;
    Button btnCreate;
    File pdf;
    final private int REQUEST_CODE_PERMISSION = 111;
    TextView pinfo, NAME, ADDRESS, EMAIL, CONTACT, DOB, LANG, NATION, MARITAL;
    //second criteria i.e. about education
    TextView EDU, CTEN, NOITEN, YOPTEN, PERTEN, CTWE, NOITWE, YOPTWE, PERTWE, GINFO, NOICOL, YOPCOL, PERCOL;
    EditText noiten, yopten, perten, noitwe, yoptwe, pertwe, noicol, yopcol, percol;
    //third criteria i.e. about work experience
    TextView WEXP, LIST, EXPDET, ORG, DES, CUREMP, WDUR, FRM, TO, ROLE,userName,userID,userEmail;
    EditText org, des, curemp, frm, too, role;
    //fourth fifth sixth and seven criterions
    TextView OBJ, PRO, PRODET, PROTIT, DESCRI, DURATIME, ROLETWO, TSIZE, FOI, SKILLS, STRENGTH, HOB, INDEXP, INDVIS, CDETNDURONE, ITRATTND, CDETNDURTWO, ACHIEVE, CACT, COEXTRA, REFDET, NAMETWO, DESIGN, INAME, EMAILID, MOBNO, COBJECT, DATENPLACE, DEC;
    EditText protit, descri, duratime, roletwo, tsize, foi, skills, strength, hob, cdetndurone, cdetndurtwo, achieve, coextra, nametwo, design, iname, emailid, mobno, cobject, datetwo, place, dec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        userName = findViewById(R.id.name);
        //userID = findViewById(R.id.id);
        userEmail = findViewById(R.id.user_email);
        imageView_profile = findViewById(R.id.user_profileImage);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            //String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            userName.setText(personName);
            userEmail.setText(personEmail);
            //userID.setText(personId);
            Glide.with(this).load(String.valueOf(personPhoto)).into(imageView_profile);
        }

        setupToolbar();//this function given by me.
        navigationView=findViewById(R.id.navigation_view);
        menuItemClick();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        fname = findViewById(R.id.fname);
        fadd = findViewById(R.id.fadd);
        email = findViewById(R.id.email);
        cnum = findViewById(R.id.cnum);
        dob = findViewById(R.id.dob);
        klang = findViewById(R.id.klang);
        nat = findViewById(R.id.nat);
        mstat = findViewById(R.id.mstat);
        fileName = findViewById(R.id.fileName);
        btnCreate = findViewById(R.id.btnCreate);
        NAME = this.findViewById(R.id.NAME);
        ADDRESS = this.findViewById(R.id.ADDRESS);
        ADDRESS.setText("Full Address");
        pinfo = this.findViewById(R.id.pinfo);
        pinfo.setText("Personal Information");
        EMAIL = this.findViewById(R.id.EMAIL);
        CONTACT = this.findViewById(R.id.CONTACT);
        CONTACT.setText("Contact no");
        DOB = this.findViewById(R.id.DOB);
        DOB.setText("Date of Birth");
        LANG = this.findViewById(R.id.LANG);
        LANG.setText("Known languages");
        NATION = this.findViewById(R.id.NATION);
        NATION.setText("Nationality");
        MARITAL = this.findViewById(R.id.MARITAL);
        MARITAL.setText("Marital Status");
        //second criteria i.e. about education
        noiten = findViewById(R.id.noiten);
        yopten = findViewById(R.id.yopten);
        perten = findViewById(R.id.perten);
        noitwe = findViewById(R.id.noitwe);
        yoptwe = findViewById(R.id.yoptwe);
        pertwe = findViewById(R.id.pertwe);
        noicol = findViewById(R.id.noicol);
        yopcol = findViewById(R.id.yopcol);
        percol = findViewById(R.id.percol);

        EDU = this.findViewById(R.id.EDU);
        EDU.setText("Education");
        CTEN = this.findViewById(R.id.CTEN);
        CTEN.setText("Class X / Equivalent info");
        NOITEN = this.findViewById(R.id.NOITEN);
        NOITEN.setText("Name of the institue");
        YOPTEN = this.findViewById(R.id.YOPTEN);
        YOPTEN.setText("Year of passing");
        PERTEN = this.findViewById(R.id.PERTEN);
        PERTEN.setText("percentage%/CGPA");
        CTWE = this.findViewById(R.id.CTWE);
        CTWE.setText("Class XII / Equivalent info");
        NOITWE = this.findViewById(R.id.NOITWE);
        NOITWE.setText("Name of the institute");
        YOPTWE = this.findViewById(R.id.YOPTWE);
        YOPTWE.setText("Year of passing");
        PERTWE = this.findViewById(R.id.PERTWE);
        PERTWE.setText("percentage%/CGPA");
        GINFO = this.findViewById(R.id.GINFO);
        GINFO.setText("Graduation info");
        NOICOL = this.findViewById(R.id.NOICOL);
        NOICOL.setText("Name of the College");
        YOPCOL = this.findViewById(R.id.YOPCOL);
        YOPCOL.setText("Year of passing");
        PERCOL = this.findViewById(R.id.PERCOL);
        PERCOL.setText("DGPA");

        //third criteria i.e. about work experience
        org = findViewById(R.id.org);
        des = findViewById(R.id.des);
        curemp = findViewById(R.id.curemp);
        frm = findViewById(R.id.frm);
        too = findViewById(R.id.too);
        role = findViewById(R.id.role);

        WEXP = this.findViewById(R.id.WEXP);
        WEXP.setText("Work experience");
        LIST = this.findViewById(R.id.LIST);
        LIST.setText("List your latest experience first");
        EXPDET = this.findViewById(R.id.EXPDET);
        EXPDET.setText("Experience Details");
        ORG = this.findViewById(R.id.ORG);
        ORG.setText("organization");
        DES = this.findViewById(R.id.DES);
        DES.setText("Designation");
        CUREMP = this.findViewById(R.id.CUREMP);
        CUREMP.setText("current employed or previously employed?");
        WDUR = this.findViewById(R.id.WDUR);
        WDUR.setText("Work duration");
        FRM = this.findViewById(R.id.FRM);
        FRM.setText(" From");
        TO = this.findViewById(R.id.TO);
        TO.setText("  To ");
        ROLE = this.findViewById(R.id.ROLE);
        ROLE.setText("Role");
        //fourth fifth sixth and seven criterions
        // protit,descri,duratime,roletwo,tsize,foi,skills,strength,hob,cdetndurone,cdetndurtwo,achieve,coextra,nametwo,design,iname,emailid,mobno,cobject,datetwo,place,dec;

        protit = findViewById(R.id.protit);
        descri = findViewById(R.id.descri);
        duratime = findViewById(R.id.duratime);
        roletwo = findViewById(R.id.roletwo);
        tsize = findViewById(R.id.tsize);
        foi = findViewById(R.id.foi);
        skills = findViewById(R.id.skills);
        strength = findViewById(R.id.strength);
        hob = findViewById(R.id.hob);
        cdetndurone = findViewById(R.id.cdetndurone);
        cdetndurtwo = findViewById(R.id.cdetndurtwo);
        achieve = findViewById(R.id.achieve);
        coextra = findViewById(R.id.coextra);
        nametwo = findViewById(R.id.nametwo);
        design = findViewById(R.id.design);
        iname = findViewById(R.id.iname);
        emailid = findViewById(R.id.emailid);
        mobno = findViewById(R.id.mobno);
        cobject = findViewById(R.id.cobject);
        datetwo = findViewById(R.id.datetwo);
        place = findViewById(R.id.place);
        dec = findViewById(R.id.dec);

        //(PRO,PRODET,PROTIT,DESCRI,DURATIME,ROLETWO,TSIZE,FOI,SKILLS,STRENGTH,HOB,INDEXP,INDVIS,CDETNDURONE,ITRATTND,CDETNDURTWO,ACHIEVE,CACT,COEXTRA,REFDET,NAMETWO,DESIGN,INAME,EMAILID,MOBNO,COBJECT,DATENPLACE,DEC)
        PRO = this.findViewById(R.id.PRO);
        PRO.setText("Project");
        PRODET = this.findViewById(R.id.PRODET);
        PRODET.setText("Project Details");
        PROTIT = this.findViewById(R.id.PROTIT);
        PROTIT.setText("project Title");
        DESCRI = this.findViewById(R.id.DESCRI);
        DESCRI.setText("Description");
        DURATIME = this.findViewById(R.id.DURATIME);
        DURATIME.setText("Duration/Time");
        ROLETWO = this.findViewById(R.id.ROLETWO);
        ROLETWO.setText("Role");
        TSIZE = this.findViewById(R.id.TSIZE);
        TSIZE.setText("Team size");
        FOI = this.findViewById(R.id.FOI);
        FOI.setText("Field of interest");
        SKILLS = this.findViewById(R.id.SKILLS);
        SKILLS.setText("Skills");
        STRENGTH = this.findViewById(R.id.STRENGTH);
        STRENGTH.setText("Strengths");
        HOB = this.findViewById(R.id.HOB);
        HOB.setText("Hobbies");
        INDEXP = this.findViewById(R.id.INDEXP);
        INDEXP.setText("Industrial Exposure");
        INDVIS = this.findViewById(R.id.INDVIS);
        INDVIS.setText("Industries Visited");

        CDETNDURONE = this.findViewById(R.id.CDETNDURONE);
        CDETNDURONE.setText("Company Details and Durations");
        ITRATTND = this.findViewById(R.id.ITRATTND);
        ITRATTND.setText("Inplant Training attended");
        CDETNDURTWO = this.findViewById(R.id.CDETNDURTWO);
        CDETNDURTWO.setText("Company Details and Durations");
        ACHIEVE = this.findViewById(R.id.ACHIEVE);
        ACHIEVE.setText("Achievements");
        CACT = this.findViewById(R.id.CACT);
        CACT.setText("Curricular Activities");
        COEXTRA = this.findViewById(R.id.COEXTRA);
        COEXTRA.setText("(Co/Extra)Curricular Activities");
        REFDET = this.findViewById(R.id.REFDET);
        REFDET.setText("Reference Details");
        NAMETWO = this.findViewById(R.id.NAMETWO);
        NAMETWO.setText("Name");
        DESIGN = this.findViewById(R.id.DESIGN);
        DESIGN.setText("Designation");
        INAME = this.findViewById(R.id.INAME);
        INAME.setText("Institution name/Organization");
        EMAILID = this.findViewById(R.id.EMAILID);
        EMAILID.setText("Email id");
        MOBNO = this.findViewById(R.id.MOBNO);
        MOBNO.setText("Mobile no");
        COBJECT = this.findViewById(R.id.COBJECT);
        COBJECT.setText("Career Objectives");
        OBJ = this.findViewById(R.id.OBJ);
        OBJ.setText("Objectives");
        DATENPLACE = this.findViewById(R.id.DATENPLACE);
        DATENPLACE.setText("Date and place");
        DEC = this.findViewById(R.id.DEC);
        DEC.setText("Declaration");

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fname.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the criteria to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (fileName.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter Name of File to get pdf", Toast.LENGTH_SHORT).show();
                } else if (fadd.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (email.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (cnum.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (dob.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (klang.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (nat.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (mstat.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                }
//second criteria starts here
                else if (noiten.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (yopten.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (perten.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (noitwe.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (yoptwe.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (pertwe.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (noicol.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (yopcol.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (percol.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                }
//second criteria starts here
                else if (org.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (des.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (curemp.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (frm.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (too.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (role.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                }
                //fourth fifth sixth and seven criterions
                else if (protit.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (descri.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (duratime.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (roletwo.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (tsize.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (foi.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (skills.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (strength.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (hob.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (cdetndurone.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (cdetndurtwo.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (achieve.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (coextra.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (nametwo.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (design.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (iname.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (emailid.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (mobno.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (cobject.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (datetwo.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (place.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else if (dec.getText().toString().isEmpty()) {
                    Toast.makeText(PdfActivity.this, "Enter all the critertia to Create a PDF", Toast.LENGTH_SHORT).show();
                } else {
                    createPDF();
                }
            }
        });
    }

    //Menu Button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }



    private void menuItemClick() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                switch (id){
                    case R.id.user_Profile:
                        Toast.makeText(PdfActivity.this, "User Profile", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.samplecv:
                        Toast.makeText(PdfActivity.this, "Sample CV", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_setting:
                        Toast.makeText(PdfActivity.this, "Setting", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.logout:
                        Toast.makeText(PdfActivity.this, "Logout", Toast.LENGTH_LONG).show();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    private void setupToolbar() {
        toolbar= findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        drawerLayout= findViewById(R.id.drawerLayout);
        //toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));
        toggle=new ActionBarDrawerToggle(PdfActivity.this,drawerLayout,toolbar,R.string.open,R.string.close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
  /*  public void toast(String item){
        Toast.makeText(PdfActivity.this,item,Toast.LENGTH_LONG).show();
    }*/

    private void createPDF() {
        int hasWritePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWritePermission != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    new AlertDialog.Builder(this)
                            .setMessage("Access Storage Permission")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.M)
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .create()
                            .show();
                    return;
                }
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
            }
            return;
        } else {
            File docPath = new File(Environment.getExternalStorageDirectory() + "/Documents");
            if (!docPath.exists()) {
                docPath.mkdir();
            }
            pdf = new File(docPath.getAbsolutePath(), fileName.getText().toString() + ".pdf");
            try {
                OutputStream stream = new FileOutputStream(pdf);
                Document document = new Document();
                PdfWriter.getInstance(document, stream);
                document.open();
                document.add(new Paragraph(pinfo.getText().toString()));
                document.add(new Paragraph(NAME.getText().toString()));
                document.add(new Paragraph(fname.getText().toString()));
                document.add(new Paragraph(ADDRESS.getText().toString()));
                document.add(new Paragraph(fadd.getText().toString()));
                document.add(new Paragraph(EMAIL.getText().toString()));
                document.add(new Paragraph(email.getText().toString()));
                document.add(new Paragraph(CONTACT.getText().toString()));
                document.add(new Paragraph(cnum.getText().toString()));
                document.add(new Paragraph(DOB.getText().toString()));
                document.add(new Paragraph(dob.getText().toString()));
                document.add(new Paragraph(LANG.getText().toString()));
                document.add(new Paragraph(klang.getText().toString()));
                document.add(new Paragraph(NATION.getText().toString()));
                document.add(new Paragraph(nat.getText().toString()));
                document.add(new Paragraph(MARITAL.getText().toString()));
                document.add(new Paragraph(mstat.getText().toString()));
                //second criteria starts here
                document.add(new Paragraph(EDU.getText().toString()));
                document.add(new Paragraph(CTEN.getText().toString()));
                document.add(new Paragraph(NOITEN.getText().toString()));
                document.add(new Paragraph(noiten.getText().toString()));
                document.add(new Paragraph(YOPTEN.getText().toString()));
                document.add(new Paragraph(yopten.getText().toString()));
                document.add(new Paragraph(PERTEN.getText().toString()));
                document.add(new Paragraph(perten.getText().toString()));
                document.add(new Paragraph(CTWE.getText().toString()));
                document.add(new Paragraph(NOITWE.getText().toString()));
                document.add(new Paragraph(noitwe.getText().toString()));
                document.add(new Paragraph(YOPTWE.getText().toString()));
                document.add(new Paragraph(yoptwe.getText().toString()));
                document.add(new Paragraph(PERTWE.getText().toString()));
                document.add(new Paragraph(pertwe.getText().toString()));
                document.add(new Paragraph(GINFO.getText().toString()));
                document.add(new Paragraph(NOICOL.getText().toString()));
                document.add(new Paragraph(noicol.getText().toString()));
                document.add(new Paragraph(YOPCOL.getText().toString()));
                document.add(new Paragraph(yopcol.getText().toString()));
                document.add(new Paragraph(PERCOL.getText().toString()));
                document.add(new Paragraph(percol.getText().toString()));
                //third criteria starts here
                document.add(new Paragraph(WEXP.getText().toString()));
                document.add(new Paragraph(LIST.getText().toString()));
                document.add(new Paragraph(EXPDET.getText().toString()));
                document.add(new Paragraph(ORG.getText().toString()));
                document.add(new Paragraph(org.getText().toString()));
                document.add(new Paragraph(DES.getText().toString()));
                document.add(new Paragraph(des.getText().toString()));
                document.add(new Paragraph(CUREMP.getText().toString()));
                document.add(new Paragraph(curemp.getText().toString()));
                document.add(new Paragraph(WDUR.getText().toString()));
                document.add(new Paragraph(FRM.getText().toString()));
                document.add(new Paragraph(frm.getText().toString()));
                document.add(new Paragraph(TO.getText().toString()));
                document.add(new Paragraph(too.getText().toString()));
                document.add(new Paragraph(ROLE.getText().toString()));
                document.add(new Paragraph(role.getText().toString()));
                //fourth fifth sixth and seven criterions
                document.add(new Paragraph(PRO.getText().toString()));
                document.add(new Paragraph(PRODET.getText().toString()));
                document.add(new Paragraph(PROTIT.getText().toString()));
                document.add(new Paragraph(protit.getText().toString()));
                document.add(new Paragraph(DESCRI.getText().toString()));
                document.add(new Paragraph(descri.getText().toString()));
                document.add(new Paragraph(DURATIME.getText().toString()));
                document.add(new Paragraph(duratime.getText().toString()));
                document.add(new Paragraph(ROLETWO.getText().toString()));
                document.add(new Paragraph(roletwo.getText().toString()));
                document.add(new Paragraph(TSIZE.getText().toString()));
                document.add(new Paragraph(tsize.getText().toString()));
                document.add(new Paragraph(FOI.getText().toString()));
                document.add(new Paragraph(foi.getText().toString()));
                document.add(new Paragraph(SKILLS.getText().toString()));
                document.add(new Paragraph(skills.getText().toString()));


                document.add(new Paragraph(STRENGTH.getText().toString()));
                document.add(new Paragraph(strength.getText().toString()));
                document.add(new Paragraph(HOB.getText().toString()));
                document.add(new Paragraph(hob.getText().toString()));
                document.add(new Paragraph(INDEXP.getText().toString()));
                document.add(new Paragraph(INDVIS.getText().toString()));
                document.add(new Paragraph(CDETNDURONE.getText().toString()));
                document.add(new Paragraph(cdetndurone.getText().toString()));
                document.add(new Paragraph(ITRATTND.getText().toString()));
                document.add(new Paragraph(CDETNDURTWO.getText().toString()));
                document.add(new Paragraph(cdetndurtwo.getText().toString()));
                document.add(new Paragraph(ACHIEVE.getText().toString()));
                document.add(new Paragraph(achieve.getText().toString()));
                document.add(new Paragraph(CACT.getText().toString()));
                document.add(new Paragraph(COEXTRA.getText().toString()));
                document.add(new Paragraph(coextra.getText().toString()));

                document.add(new Paragraph(REFDET.getText().toString()));
                document.add(new Paragraph(NAMETWO.getText().toString()));
                document.add(new Paragraph(nametwo.getText().toString()));
                document.add(new Paragraph(DESIGN.getText().toString()));
                document.add(new Paragraph(design.getText().toString()));
                document.add(new Paragraph(INAME.getText().toString()));
                document.add(new Paragraph(iname.getText().toString()));
                document.add(new Paragraph(EMAILID.getText().toString()));
                document.add(new Paragraph(emailid.getText().toString()));
                document.add(new Paragraph(MOBNO.getText().toString()));
                document.add(new Paragraph(mobno.getText().toString()));
                document.add(new Paragraph(COBJECT.getText().toString()));
                document.add(new Paragraph(OBJ.getText().toString()));
                document.add(new Paragraph(cobject.getText().toString()));
                document.add(new Paragraph(DATENPLACE.getText().toString()));
                document.add(new Paragraph(datetwo.getText().toString()));
                document.add(new Paragraph(place.getText().toString()));
                document.add(new Paragraph(DEC.getText().toString()));
                document.add(new Paragraph(dec.getText().toString()));


                document.close();
                Snackbar snacbar = Snackbar.make(findViewById(android.R.id.content), fileName.getText().toString() + " Saved: " + pdf.toString(), Snackbar.LENGTH_SHORT);
                snacbar.show();
                snacbar.setAction("Open",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showPDF();
                            }
                        });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
                        createPDF();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(this, "WRITE_External Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void showPDF() {
        PackageManager manager = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setType("application/pdf");
        List list = manager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() > 0) {
            Intent intent1 = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(pdf);
            intent1.setDataAndType(uri, "application/pdf");
            startActivity(intent1);
        } else {
            Toast.makeText(this, "Download any PDF Viewer to Open the Document", Toast.LENGTH_LONG).show();
        }
    }

    public void openMenu(View view) {
        Intent intent = new Intent(PdfActivity.this,menu.class);
        startActivity(intent);
    }
}

